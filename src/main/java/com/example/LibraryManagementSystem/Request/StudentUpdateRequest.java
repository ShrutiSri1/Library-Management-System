package com.example.LibraryManagementSystem.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentUpdateRequest {
    private int id;
    private String name;
    private String email;
    private int age;
}
