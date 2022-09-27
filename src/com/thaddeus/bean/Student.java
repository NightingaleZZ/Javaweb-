package com.thaddeus.bean;

public class Student {
    private Integer studentId;
    private Integer classId;
    private String studentNo;
    private String studentPassword;
    private String studentName;
    private String studentGender;
    private Integer isAdmin;

    public Student() {
    }

    public Student(Integer studentId, Integer classId, String studentNo, String studentName, String studentGender, Integer isAdmin) {
        this.studentId = studentId;
        this.classId = classId;
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.isAdmin = isAdmin;
    }

    public Student(Integer studentId, Integer classId, String studentNo, String studentPassword, String studentName, String studentGender, Integer isAdmin) {
        this.studentId = studentId;
        this.classId = classId;
        this.studentNo = studentNo;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.isAdmin = isAdmin;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", classId=" + classId +
                ", studentNo='" + studentNo + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentGender='" + studentGender + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
