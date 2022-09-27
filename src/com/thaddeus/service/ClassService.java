package com.thaddeus.service;

import com.thaddeus.bean.Class;

import java.util.List;

public interface ClassService {

    /**
     * 获得 classId 对应的班级信息
     *
     * @param classId
     * @return
     */
    Class getClassInfo(Integer classId);

    /**
     * 更新总班费
     *
     * @param moneyState
     * @param classId
     * @param moneyCount
     */
    void updateClassMoney(Integer moneyState, Integer classId, Integer moneyCount);

    void delUpdateClassMoney(Integer moneyState, Integer classId, Integer moneyCount);

    List<Class> getAllClass();

    void updateClassInfo(Class aClass);

    Class getClassByClassName(String className);

    Integer getTotalRevenue(Integer classId);

    Integer getTotalExpenditure(Integer classId);
}
