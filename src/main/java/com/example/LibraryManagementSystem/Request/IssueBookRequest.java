package com.example.LibraryManagementSystem.Request;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssueBookRequest {
    private int studentId;
    private int bookId;
}
