package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Models.*;
import com.example.LibraryManagementSystem.Repository.TransactionRepository;
import com.example.LibraryManagementSystem.Request.IssueBookRequest;
import com.example.LibraryManagementSystem.Request.ReturnBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookService bookService;

    @Autowired
    StudentService studentService;

    //value from application properties
    @Value("${students.max_allowed_books}")
    int maxAllowedBook;
    @Value("${books.allotted_days}")
    int daysAllotted;
    @Value("${books.fine_per_day}")
    int finePerDay;

    public String issueBook(IssueBookRequest issueBookRequest) throws Exception{
        int bookId=issueBookRequest.getBookId();
        int studentId= issueBookRequest.getStudentId();

        Book book =bookService.getBook(bookId);
        Student student=studentService.getStudent(studentId);

        if(book==null)
        {
            throw new Exception("Book with id "+ bookId+" is not present in the library");

        }
        if(book.getStudent()!=null)
        {
            throw new Exception("Book with id "+bookId+"is already assigned.");

        }
        if (student==null)
        {
            throw  new Exception("Student with id "+ studentId+ " is not present");



        }
        if(student.getBooks().size() >= maxAllowedBook)
        {
            throw new Exception("Maximum number of books are already assigned to the student "+studentId);

        }

        Transaction transaction = Transaction.builder().transactionStatus(TransactionStatus.PENDING).book(book).transactionId(UUID.randomUUID().toString()).transactionType(TransactionType.ISSUE).build();
        try{
            transaction=transactionRepository.save(transaction);
            book.setStudent(student);
            bookService.createOrUpdateBook(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            transactionRepository.save(transaction);
            return transaction.getTransactionId();
        }catch (Exception e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setRemark(e.getMessage());
            transactionRepository.save(transaction);
            throw  new Exception("Transaction couldn't be completed-" +e.getMessage());
        }



    }
    public String returnBook(ReturnBookRequest returnBookRequest) throws  Exception{
        int bookId=returnBookRequest.getBookId();
        int studentId=returnBookRequest.getStudentId();

        Transaction issueTransaction = transactionRepository.getTransaction(bookId,studentId);
        if(!TransactionType.ISSUE.equals(issueTransaction.getTransactionType()))
        {
            throw new Exception("Issue transaction could not be found");

        }
        Book book =issueTransaction.getBook();
        Student student=issueTransaction.getStudent();
        if(book==null)
        {
            throw new Exception("Book with id "+bookId+"could not be found in the library");
        }
        if(book.getStudent()==null)
        {
            throw  new Exception("Student with id "+studentId+"is not present");
        }
long issueTransactionMillis=issueTransaction.getTransactionDate().getTime();
        long currentTimeMillis=System.currentTimeMillis();
        long timePassed=currentTimeMillis- issueTransactionMillis;
        long daysPassed= TimeUnit.DAYS.convert(timePassed,TimeUnit.MILLISECONDS);
        int fine=0;
        if(daysPassed>daysAllotted)
        {
            fine +=(daysPassed-daysAllotted)*finePerDay;
        }
        Transaction transaction=Transaction.builder().transactionType(TransactionType.RETURN).book(book).student(student).transactionStatus(TransactionStatus.PENDING).remark(returnBookRequest.getRemarks()).transactionId(UUID.randomUUID().toString()).fine(fine).build();
        transaction = transactionRepository.save(transaction);
        book.setStudent(null);
        bookService.createOrUpdateBook(book);

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);
        return transaction.getTransactionId();
    }

}
