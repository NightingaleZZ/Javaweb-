package com.thaddeus.dao;

import com.thaddeus.bean.Class;

import java.util.List;

public interface ClassDao {

    /**
     * 通过班级 id 查询班级信息
     *
     * @param classId
     * @return
     */
    Class queryClassById(Integer classId);

    /**
     * 更新班级总班费
     *
     * @param moneyState
     * @param classId
     * @param moneyCount
     */
    void updateMoneyById(Integer moneyState, Integer classId, Integer moneyCount);

    void delUpdateMoneyById(Integer moneyState, Integer classId, Integer moneyCount);

    List<Class> queryAllClass();

    Class queryClassByClassName(String className);

    void updateClass(Class aClass);

    Integer getAllIn(Integer classId);

    Integer getAllOut(Integer classId);

}
