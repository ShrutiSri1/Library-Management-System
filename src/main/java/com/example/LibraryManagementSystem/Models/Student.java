package com.example.LibraryManagementSystem.Models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    private int age;

    private String name;
    @OneToMany(mappedBy = "student")
    private List<Book> books;
    @OneToMany(mappedBy = "student")
    private List<Transaction> transactions;

    @CreationTimestamp
    private Date createdOn;

}
