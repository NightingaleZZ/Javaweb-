package com.thaddeus.bean;

import java.util.Date;

public class Money {
    private Integer moneyId;
    private Integer studentId;
    private Integer moneyCount;
    private Date moneyTime;
    private String moneyUse;
    private Integer moneyState;

    public Money() {
    }

    public Money(Integer moneyId, Integer studentId, Integer moneyCount, Date moneyTime, String moneyUse, Integer moneyState) {
        this.moneyId = moneyId;
        this.studentId = studentId;
        this.moneyCount = moneyCount;
        this.moneyTime = moneyTime;
        this.moneyUse = moneyUse;
        this.moneyState = moneyState;
    }

    public Integer getMoneyId() {
        return moneyId;
    }

    public void setMoneyId(Integer moneyId) {
        this.moneyId = moneyId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
    }

    public Date getMoneyTime() {
        return moneyTime;
    }

    public void setMoneyTime(Date moneyTime) {
        this.moneyTime = moneyTime;
    }

    public String getMoneyUse() {
        return moneyUse;
    }

    public void setMoneyUse(String moneyUse) {
        this.moneyUse = moneyUse;
    }

    public Integer getMoneyState() {
        return moneyState;
    }

    public void setMoneyState(Integer moneyState) {
        this.moneyState = moneyState;
    }

    @Override
    public String toString() {
        return "Money{" +
                "moneyId=" + moneyId +
                ", studentId=" + studentId +
                ", moneyCount=" + moneyCount +
                ", moneyTime=" + moneyTime +
                ", moneyUse='" + moneyUse + '\'' +
                ", moneyState=" + moneyState +
                '}';
    }
}
