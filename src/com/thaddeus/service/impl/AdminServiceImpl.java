package com.thaddeus.service.impl;

import com.thaddeus.bean.Admin;
import com.thaddeus.bean.Money;
import com.thaddeus.bean.Page;
import com.thaddeus.dao.AdminDao;
import com.thaddeus.dao.MoneyDao;
import com.thaddeus.dao.impl.AdminDaoImpl;
import com.thaddeus.dao.impl.MoneyDaoImpl;
import com.thaddeus.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao = new AdminDaoImpl();
    private final MoneyDao moneyDao = new MoneyDaoImpl();

    @Override
    public boolean existAdmin(String username) {
        return adminDao.queryAdminByUsername(username) != null;
    }

    @Override
    public Admin login(String username, String password) {
        return adminDao.queryAdminByUsernameAndPassword(username, password);
    }

    @Override
    public List<Money> getMoneyPage(Integer begin, Integer pageSize, List<Integer> studentId) {
        return adminDao.getMoneyByPage(begin, pageSize, studentId);
    }

    @Override
    public Integer getMoneyPageCount(List<Integer> studentId) {
        return adminDao.queryTotalCount(studentId);
    }

}
