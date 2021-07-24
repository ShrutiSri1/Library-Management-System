package com.example.LibraryManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String country;
    @OneToMany(mappedBy = "author")
    @JsonIgnoreProperties("author")
    private List<Book> books;
    private int age;




}
