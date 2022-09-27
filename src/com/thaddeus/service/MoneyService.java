package com.thaddeus.service;

import com.thaddeus.bean.Money;

import java.util.List;

public interface MoneyService {

    List<Money> getMoneyInfoByStudentId(Integer studentId);

    List<Money> getMoneyInfoByStudentIdList(List<Integer> studentId);

    Money getMoneyInfoByMoneyId(Integer moneyId);

    void updateMoneyInfo(Money money);

    void deleteMoneyInfo(Integer moneyId);

    void addMoneyInfo(Money money);

    void setMoneyStateSub(Integer moneyId);

}
