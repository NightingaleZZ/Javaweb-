package com.thaddeus.dao.impl;

import com.thaddeus.bean.Class;
import com.thaddeus.dao.ClassDao;

import java.util.List;

public class ClassDaoImpl extends BaseDao implements ClassDao {

    @Override
    public Class queryClassById(Integer classId) {
        String sql = "select" +
                " `class_id` classId," +
                " `class_name` className," +
                " `class_money` classMoney," +
                " `class_year` classYear" +
                " from `t_class` where `class_id` = ?";
        return queryForOne(Class.class, sql, classId);
    }

    @Override
    public void updateMoneyById(Integer moneyState, Integer classId, Integer moneyCount) {
        String sql;

        // 根据是否提交的状态改变 sql 语句
        if (moneyState == 1) {
            sql = "update `t_class` set `class_money` = `class_money` + ? where `class_id` = ?";
        } else {
            sql = "update `t_class` set `class_money` = `class_money` - ? where `class_id` = ?";
        }
        update(sql, moneyCount, classId);
    }

    @Override
    public void delUpdateMoneyById(Integer moneyState, Integer classId, Integer moneyCount) {
        String sql = "update `t_class` set `class_money` = `class_money` - ? where `class_id` = ?";

        update(sql, moneyCount, classId);
    }

    @Override
    public List<Class> queryAllClass() {
        String sql = "select" +
                " `class_id` classId," +
                " `class_name` className," +
                " `class_money` classMoney," +
                " `class_year` classYear" +
                " from `t_class` order by `class_id`";
        return queryForList(Class.class, sql);
    }

    @Override
    public Class queryClassByClassName(String className) {
        String sql = "select" +
                " `class_id` classId," +
                " `class_name` className," +
                " `class_money` classMoney," +
                " `class_year` classYear" +
                " from `t_class` where `class_name` = ?";
        return queryForOne(Class.class, sql, className);
    }

    @Override
    public void updateClass(Class aClass) {
        String sql = "update `t_class` set" +
                " `class_name` = ?," +
                " `class_money` = ?," +
                " `class_year` = ?" +
                " where `class_id` = ?";
        update(sql, aClass.getClassName(), aClass.getClassMoney(), aClass.getClassYear(), aClass.getClassId());
    }

    @Override
    public Integer getAllIn(Integer classId) {
        String sql = "call getIn(?)";

        return procedure(sql, classId);
    }

    @Override
    public Integer getAllOut(Integer classId) {
        String sql = "call getOut(?)";

        return procedure(sql, classId);
    }

}
