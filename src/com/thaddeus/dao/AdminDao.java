package com.thaddeus.dao;

import com.thaddeus.bean.Admin;
import com.thaddeus.bean.Money;

import java.util.List;

public interface AdminDao {

    Admin queryAdminByUsername(String username);

    Admin queryAdminByUsernameAndPassword(String username, String password);

    List<Money> getMoneyByPage(Integer begin, Integer pageSize, List<Integer> studentId);

    Integer queryTotalCount(List<Integer> studentId);

}
