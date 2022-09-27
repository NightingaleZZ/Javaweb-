package com.thaddeus.service;

import com.thaddeus.bean.Student;

import java.util.List;

public interface StudentService {

    Student login(Student student);

    Student getStudentByStudentId(Integer studentId);

    Student getStudentByName(String studentName);

    List<Student> getStudentByClassId(Integer classId);

    void updateStudentInfo(Student student);
}
