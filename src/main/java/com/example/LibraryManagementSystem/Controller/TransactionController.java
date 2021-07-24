package com.example.LibraryManagementSystem.Controller;


import com.example.LibraryManagementSystem.Request.IssueBookRequest;
import com.example.LibraryManagementSystem.Request.ReturnBookRequest;
import com.example.LibraryManagementSystem.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/issue")
    public String issueBook(@RequestBody IssueBookRequest issueBookRequest) throws  Exception{
        return transactionService.issueBook(issueBookRequest);
    }

    @PostMapping("/return")
    public String returnBook(@RequestBody ReturnBookRequest returnBookRequest) throws Exception{
        return  transactionService.returnBook(returnBookRequest);
    }


}
