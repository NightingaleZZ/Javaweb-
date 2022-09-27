package com.thaddeus.dao;

import com.thaddeus.bean.Student;

import java.util.List;

public interface StudentDao {

    List<Student> queryByClassId(Integer classId);

    Student queryByStudentId(Integer studentId);

    /**
     * 通过 id 查询学生信息
     * @param studentName
     * @return
     */
    Student queryByName(String studentName);

    /**
     * 通过学号和密码查询学生(用于登录)
     * @param studentNo 学号
     * @param studentPassword 密码
     * @return
     */
    Student queryByNoAndPassword(String studentNo, String studentPassword);

    void updateStudent(Student student);
}
