package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public Author createAuthor(Author author)
    {
        return authorRepository.save(author);
    }
    public Author getAuthorByEmail(String email){
        return authorRepository.findByEmail(email);
    }
}
