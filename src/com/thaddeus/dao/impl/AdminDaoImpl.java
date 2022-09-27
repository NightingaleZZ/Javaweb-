package com.thaddeus.dao.impl;

import com.thaddeus.bean.Admin;
import com.thaddeus.bean.Money;
import com.thaddeus.dao.AdminDao;

import java.util.List;

public class AdminDaoImpl extends BaseDao implements AdminDao {

    @Override
    public Admin queryAdminByUsername(String username) {
        String sql = "select" +
                " `admin_id` adminId," +
                " `admin_username` adminUsername," +
                " `admin_password` adminPassword," +
                " `admin_email` adminEmail" +
                " from `t_admin` where `admin_username` = ?";
        return queryForOne(Admin.class, sql, username);
    }

    @Override
    public Admin queryAdminByUsernameAndPassword(String username, String password) {
        String sql = "select" +
                " `admin_id` adminId," +
                " `admin_username` adminUsername," +
                " `admin_password` adminPassword," +
                " `admin_email` adminEmail" +
                " from `t_admin` where `admin_username` = ? and `admin_password` = MD5(?)";

        return queryForOne(Admin.class, sql, username, password);
    }


    @Override
    public List<Money> getMoneyByPage(Integer begin, Integer pageSize, List<Integer> studentId) {
        String sql = "select" +
                " `money_id` moneyId," +
                " `student_id` studentId," +
                " `money_count` moneyCount," +
                " `money_time` moneyTime," +
                " `money_use` moneyUse," +
                " `money_state` moneyState" +
                " from `t_money` where `student_id` in (";

        StringBuilder newSql = new StringBuilder(sql);
        for (int i = 0; i < studentId.size(); i++) {
            newSql.append(studentId.get(i));
            if (i != studentId.size() - 1) {
                newSql.append(", ");
            }
        }

        newSql.append(") order by `student_id`, `money_count`, `money_id` desc limit ?, ?");

//        System.out.println(newSql);

        return queryForList(Money.class, newSql.toString(), begin, pageSize);
    }

    @Override
    public Integer queryTotalCount(List<Integer> studentId) {
        String sql = "select count(*) from `t_money` where `student_id` in (";

        StringBuilder newSql = new StringBuilder(sql);
        for (int i = 0; i < studentId.size(); i++) {
            newSql.append(studentId.get(i));
            if (i != studentId.size() - 1) {
                newSql.append(", ");
            }
        }

        newSql.append(")");

        Number o = (Number) queryForSingleValue(newSql.toString());
        return o.intValue();
    }
}
