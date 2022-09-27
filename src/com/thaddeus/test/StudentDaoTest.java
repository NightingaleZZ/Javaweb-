package com.thaddeus.test;

import com.thaddeus.bean.Student;
import com.thaddeus.dao.StudentDao;
import com.thaddeus.dao.impl.StudentDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentDaoTest {

    @Test
    public void queryByIdAndPassword() {
        StudentDao studentDao = new StudentDaoImpl();

        System.out.println(studentDao.queryByNoAndPassword("3200432106", "AwBzCh02w10z31h"));
    }
}