package com.example.LibraryManagementSystem.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateRequest {
    private String name;
    private String email;
    private int age;
}
