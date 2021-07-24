package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Repository.BookRepository;
import com.example.LibraryManagementSystem.Request.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public Book getBook(int id)
    {
        return  bookRepository.findById(id).orElse(Book.builder().build());

    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }
    public void createBookWithAuthorDetails(BookCreateRequest bookCreateRequest)
    {
        String email=bookCreateRequest.getAuthor().getEmail();
        Author author= authorService.getAuthorByEmail(email);

        Book book = Book.builder().cost(bookCreateRequest.getCost()).name((bookCreateRequest.getName())).bookCategory(bookCreateRequest.getBookCategory()).build();
        if(author== null){
            author= Author.builder().name(bookCreateRequest.getAuthor().getName()).email(email).age(bookCreateRequest.getAuthor().getAge()).books(Arrays.asList(book)).build();
            author=authorService.createAuthor(author);

        }
        book.setAuthor(author);
        bookRepository.save(book);
    }
    public void createOrUpdateBook(Book book)
    {
        bookRepository.save(book);
    }

}
