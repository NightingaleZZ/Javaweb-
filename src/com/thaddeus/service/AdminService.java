package com.thaddeus.service;

import com.thaddeus.bean.Admin;
import com.thaddeus.bean.Money;
import com.thaddeus.bean.Page;

import java.util.List;

public interface AdminService {

    boolean existAdmin(String username);

    Admin login(String username, String password);

    List<Money> getMoneyPage(Integer begin, Integer pageSize, List<Integer> studentId);

    Integer getMoneyPageCount(List<Integer> studentId);

}
