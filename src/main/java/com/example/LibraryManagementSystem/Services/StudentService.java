package com.example.LibraryManagementSystem.Services;

import com.example.LibraryManagementSystem.Models.Student;
import com.example.LibraryManagementSystem.Repository.StudentRepository;
import com.example.LibraryManagementSystem.Request.StudentCreateRequest;
import com.example.LibraryManagementSystem.Request.StudentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student getStudent(int student_id)
    {
        //see
      return studentRepository.findById(student_id).orElse(Student.builder().build());

    }
    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }
    public void createStudent(StudentCreateRequest studentCreateRequest)
    {
        Student student= Student.builder().name(studentCreateRequest.getName()).age(studentCreateRequest.getAge()).email(studentCreateRequest.getEmail()).build();
        studentRepository.save(student);
    }
    public void updateStudent(int id, StudentUpdateRequest studentUpdateRequest)
    {
        studentRepository.update(studentUpdateRequest.getEmail(),studentUpdateRequest.getName(),studentUpdateRequest.getAge(),id);

    }
    public List<Student> getStudentByAge(int age)
    {
        return studentRepository.getStudentsByAge(age);
    }
    public void deleteStudent(int student_id)
    {
        studentRepository.deleteById(student_id);
    }
}
