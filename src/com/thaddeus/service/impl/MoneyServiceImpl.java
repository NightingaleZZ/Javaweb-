package com.thaddeus.service.impl;

import com.thaddeus.bean.Money;
import com.thaddeus.dao.MoneyDao;
import com.thaddeus.dao.impl.MoneyDaoImpl;
import com.thaddeus.service.MoneyService;

import java.util.List;

public class MoneyServiceImpl implements MoneyService {

    private final MoneyDao moneyDao = new MoneyDaoImpl();

    @Override
    public List<Money> getMoneyInfoByStudentId(Integer studentId) {
        return moneyDao.queryMoneyByStudentId(studentId);
    }

    @Override
    public List<Money> getMoneyInfoByStudentIdList(List<Integer> studentId) {
        return moneyDao.queryMoneyByStudentIdList(studentId);
    }

    @Override
    public Money getMoneyInfoByMoneyId(Integer moneyId) {
        return moneyDao.queryMoneyByMoneyId(moneyId);
    }

    @Override
    public void updateMoneyInfo(Money money) {
        moneyDao.updateMoney(money);
    }

    @Override
    public void deleteMoneyInfo(Integer moneyId) {
        moneyDao.deleteMoneyById(moneyId);
    }

    @Override
    public void addMoneyInfo(Money money) {
        moneyDao.addOne(money);
    }

    @Override
    public void setMoneyStateSub(Integer moneyId) {
        moneyDao.updateMoneyStateSub(moneyId);
    }
}
