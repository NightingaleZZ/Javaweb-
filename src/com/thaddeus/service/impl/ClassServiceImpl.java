package com.thaddeus.service.impl;

import com.thaddeus.bean.Class;
import com.thaddeus.dao.ClassDao;
import com.thaddeus.dao.impl.ClassDaoImpl;
import com.thaddeus.service.ClassService;

import java.util.List;

public class ClassServiceImpl implements ClassService {

    private final ClassDao classDao = new ClassDaoImpl();

    @Override
    public Class getClassInfo(Integer classId) {
        return classDao.queryClassById(classId);
    }

    @Override
    public void updateClassMoney(Integer moneyState, Integer classId, Integer moneyCount) {
        classDao.updateMoneyById(moneyState, classId, moneyCount);
    }

    @Override
    public void delUpdateClassMoney(Integer moneyState, Integer classId, Integer moneyCount) {
        classDao.delUpdateMoneyById(moneyState, classId, moneyCount);
    }

    @Override
    public List<Class> getAllClass() {
        return classDao.queryAllClass();
    }

    @Override
    public void updateClassInfo(Class aClass) {
        classDao.updateClass(aClass);
    }

    @Override
    public Class getClassByClassName(String className) {
        return classDao.queryClassByClassName(className);
    }

    @Override
    public Integer getTotalRevenue(Integer classId) {
        return classDao.getAllIn(classId);
    }

    @Override
    public Integer getTotalExpenditure(Integer classId) {
        return classDao.getAllOut(classId);
    }

}
