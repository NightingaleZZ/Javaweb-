package com.thaddeus.dao.impl;

import com.thaddeus.bean.Money;
import com.thaddeus.dao.MoneyDao;

import java.util.List;

public class MoneyDaoImpl extends BaseDao implements MoneyDao {

    @Override
    public List<Money> queryMoneyByStudentId(Integer  studentId) {
        String sql = "select" +
                " `money_id` moneyId," +
                " `student_id` studentId," +
                " `money_count` moneyCount," +
                " `money_time` moneyTime," +
                " `money_use` moneyUse," +
                " `money_state` moneyState" +
                " from `t_money` where `student_id` = ? order by `student_id`, `money_count`, `money_id` desc";

        return queryForList(Money.class, sql, studentId);
    }

    @Override
    public List<Money> queryMoneyByStudentIdList(List<Integer> studentId) {
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

        newSql.append(") order by `student_id`, `money_count`, `money_id` desc");

        return queryForList(Money.class, newSql.toString());
    }

    @Override
    public Money queryMoneyByMoneyId(Integer moneyId) {
        String sql = "select" +
                " `money_id` moneyId," +
                " `student_id` studentId," +
                " `money_count` moneyCount," +
                " `money_time` moneyTime," +
                " `money_use` moneyUse," +
                " `money_state` moneyState" +
                " from `t_money` where `money_id` = ?";
        return queryForOne(Money.class, sql, moneyId);
    }

    @Override
    public void updateMoney(Money money) {
        String sql = "update t_money set" +
                " `student_id` = ?," +
                " `money_count` = ?," +
                " `money_time` = ?," +
                " `money_use` = ?," +
                " `money_state` = ?" +
                " where `money_id` = ?";
        update(sql, money.getStudentId(), money.getMoneyCount(), money.getMoneyTime(), money.getMoneyUse(), money.getMoneyState(), money.getMoneyId());
    }

    @Override
    public void deleteMoneyById(Integer moneyId) {
        String sql = "delete from t_money where `money_id` = ?";

        update(sql, moneyId);
    }

    @Override
    public void addOne(Money money) {
        String sql = "insert into `t_money` values(?, ?, ?, ?, ?, ?)";
        update(sql, null, money.getStudentId(), money.getMoneyCount(), money.getMoneyTime(), money.getMoneyUse(), money.getMoneyState());
    }

    @Override
    public void updateMoneyStateSub(Integer moneyId) {
        String sql = "update `t_money` set `money_state` = 1 where `money_id` = ?";
        update(sql, moneyId);
    }
}
