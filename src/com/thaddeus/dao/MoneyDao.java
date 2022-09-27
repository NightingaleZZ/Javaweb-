package com.thaddeus.dao;

import com.thaddeus.bean.Money;

import java.util.List;

public interface MoneyDao {

    List<Money> queryMoneyByStudentId(Integer  studentId);

    /**
     * 查询本班学生所有班费记录
     *
     * @return
     */
    List<Money> queryMoneyByStudentIdList(List<Integer>  studentId);

    Money queryMoneyByMoneyId(Integer moneyId);

    void updateMoney(Money money);

    void deleteMoneyById(Integer moneyId);

    void addOne(Money money);

    void updateMoneyStateSub(Integer moneyId);

}
