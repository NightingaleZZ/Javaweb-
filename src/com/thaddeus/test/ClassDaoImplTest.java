package com.thaddeus.test;

import com.thaddeus.dao.ClassDao;
import com.thaddeus.dao.impl.ClassDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassDaoImplTest {

    ClassDao classDao = new ClassDaoImpl();

    @Test
    public void getAllIn() {
        System.out.println(classDao.getAllIn(1));
    }

    @Test
    public void getAllOut() {
        System.out.println(classDao.getAllOut(2));
    }
}