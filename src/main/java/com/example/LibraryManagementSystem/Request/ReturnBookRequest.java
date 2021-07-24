package com.example.LibraryManagementSystem.Request;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReturnBookRequest {
    private int studentId;
    private int bookId;
    private String remarks;
}
