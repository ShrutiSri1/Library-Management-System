package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Models.Book;
import com.example.LibraryManagementSystem.Request.BookCreateRequest;
import com.example.LibraryManagementSystem.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") int id)
    {
        return bookService.getBook(id);
    }

    @GetMapping("/book/all")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }
    public void createBookWithAuthorDetails(@RequestBody BookCreateRequest bookCreateRequest)
    {
        bookService.createBookWithAuthorDetails(bookCreateRequest);
    }
}
