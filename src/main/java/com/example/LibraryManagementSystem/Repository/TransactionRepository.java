package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query(value = "select * from Transaction t where t.book_book_id= ?1 and t.my_student = ?2 order by t.id desc limit 1", nativeQuery = true)
    Transaction getTransaction(int bookId, int studentId);
}
