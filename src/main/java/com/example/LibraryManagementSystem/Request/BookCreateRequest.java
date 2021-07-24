package com.example.LibraryManagementSystem.Request;

import com.example.LibraryManagementSystem.Models.Author;
import com.example.LibraryManagementSystem.Models.BookCategory;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateRequest {
    private String name;
    private int cost;
    private BookCategory bookCategory;
    private Author author;
    private int age;
}
