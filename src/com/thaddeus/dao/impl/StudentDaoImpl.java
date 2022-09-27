package com.thaddeus.dao.impl;

import com.thaddeus.bean.Student;
import com.thaddeus.dao.StudentDao;

import java.util.List;

public class StudentDaoImpl extends BaseDao implements StudentDao {

    @Override
    public List<Student> queryByClassId(Integer classId) {
        String sql = "select" +
                " `student_id` studentId," +
                " `class_id` classId," +
                " `student_no` studentNo," +
                " `student_password` studentPassword," +
                " `student_name` studentName," +
                " `student_gender` studentGender," +
                " `is_admin` isAdmin" +
                " from t_student where `class_id` = ? order by `student_id`";
        return queryForList(Student.class, sql, classId);
    }

    @Override
    public Student queryByStudentId(Integer studentId) {
        String sql = "select" +
                " `student_id` studentId," +
                " `class_id` classId," +
                " `student_no` studentNo," +
                " `student_password` studentPassword," +
                " `student_name` studentName," +
                " `student_gender` studentGender," +
                " `is_admin` isAdmin" +
                " from t_student where `student_id` = ?";
        return queryForOne(Student.class, sql, studentId);
    }

    @Override
    public Student queryByName(String studentName) {
        String sql = "select" +
                " `student_id` studentId," +
                " `class_id` classId," +
                " `student_no` studentNo," +
                " `student_password` studentPassword," +
                " `student_name` studentName," +
                " `student_gender` studentGender," +
                " `is_admin` isAdmin" +
                " from t_student where `student_name` = ?";
        return queryForOne(Student.class, sql, studentName);
    }

    @Override
    public Student queryByNoAndPassword(String studentNo, String studentPassword) {
        String sql = "select" +
                " `student_id` studentId," +
                " `class_id` classId," +
                " `student_no` studentNo," +
                " `student_password` studentPassword," +
                " `student_name` studentName," +
                " `student_gender` studentGender," +
                " `is_admin` isAdmin" +
                " from t_student where student_no = ? and student_password = MD5(?)";
        return queryForOne(Student.class, sql, studentNo, studentPassword);
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "update `t_student` set" +
                " `class_id` = ?," +
                " `student_no` = ?," +
                " `student_name` = ?," +
                " `student_gender` = ?," +
                " `is_admin` = ? where `student_id` = ?";
        update(sql, student.getClassId(), student.getStudentNo(), student.getStudentName(), student.getStudentGender(), student.getIsAdmin(), student.getStudentId());
    }
}
