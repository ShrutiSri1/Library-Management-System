package com.example.LibraryManagementSystem.Controller;

import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Request.StudentCreateRequest;
import com.example.LibraryManagementSystem.Request.StudentUpdateRequest;
import com.example.LibraryManagementSystem.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable("student_id") int student_id)
    {
        return studentService.getStudent(student_id);
    }

    @GetMapping("/student/all")
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }
@GetMapping("/student/age/{age}")
    public List<Student> getStudentByAge(@PathVariable("age") int age)
    {
        return studentService.getStudentByAge(age);
    }
    @PostMapping("/studentCreate")
    public void createStudent(@RequestBody StudentCreateRequest studentCreateRequest)
    {
        studentService.createStudent(studentCreateRequest);
    }
    @PostMapping("/studentUpdate/{id}")
    public void updateStudent(@PathVariable("id") int id, @RequestBody StudentUpdateRequest studentUpdateRequest)
    {
        studentService.updateStudent(id,studentUpdateRequest);
    }

    @DeleteMapping("/student/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int id)
    {
        studentService.deleteStudent(id);
    }

}
