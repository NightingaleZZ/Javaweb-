package com.thaddeus.bean;

public class Class {
    private Integer classId;
    private String className;
    private Integer classMoney;
    private Integer classYear;

    public Class() {
    }

    public Class(Integer classId, String className, Integer classMoney, Integer classYear) {
        this.classId = classId;
        this.className = className;
        this.classMoney = classMoney;
        this.classYear = classYear;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getClassMoney() {
        return classMoney;
    }

    public void setClassMoney(Integer classMoney) {
        this.classMoney = classMoney;
    }

    public Integer getClassYear() {
        return classYear;
    }

    public void setClassYear(Integer classYear) {
        this.classYear = classYear;
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classMoney=" + classMoney +
                ", classYear=" + classYear +
                '}';
    }
}
