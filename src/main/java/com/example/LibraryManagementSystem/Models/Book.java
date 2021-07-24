package com.example.LibraryManagementSystem.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int book_id;
    private String name;
    private int cost;

    @Enumerated(value = EnumType.STRING)
    private BookCategory bookCategory;

    @ManyToOne
    @JoinColumn
    private Student student;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("books")
    private Author author;
    @CreationTimestamp
    private Date createdOn;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactions;

}
