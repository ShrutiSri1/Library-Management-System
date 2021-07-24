package com.example.LibraryManagementSystem.Repository;

import com.example.LibraryManagementSystem.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query(value = "select * from my_student where age> :age",nativeQuery = true)
    List<Student> getStudentsByAge(int age);


    @Transactional
    @Modifying
    @Query("update Student s set s.name = ?1, s.email = ?2, s.age = ?3 where s.id = ?4")
    void update(String name,String email, int age,int id);
}
