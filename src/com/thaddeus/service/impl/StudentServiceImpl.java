package com.thaddeus.service.impl;

import com.thaddeus.bean.Student;
import com.thaddeus.dao.StudentDao;
import com.thaddeus.dao.impl.StudentDaoImpl;
import com.thaddeus.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public Student login(Student student) {
        return studentDao.queryByNoAndPassword(student.getStudentNo(), student.getStudentPassword());
    }

    @Override
    public Student getStudentByStudentId(Integer studentId) {
        return studentDao.queryByStudentId(studentId);
    }

    @Override
    public Student getStudentByName(String studentName) {
        return studentDao.queryByName(studentName);
    }

    @Override
    public List<Student> getStudentByClassId(Integer classId) {
        return studentDao.queryByClassId(classId);
    }

    @Override
    public void updateStudentInfo(Student student) {
        studentDao.updateStudent(student);
    }
}
