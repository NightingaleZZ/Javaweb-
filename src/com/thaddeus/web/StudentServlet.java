package com.thaddeus.web;

import com.thaddeus.bean.Money;
import com.thaddeus.bean.Student;
import com.thaddeus.service.MoneyService;
import com.thaddeus.service.StudentService;
import com.thaddeus.service.impl.MoneyServiceImpl;
import com.thaddeus.service.impl.StudentServiceImpl;
import com.thaddeus.util.QRCodeUtil;
import com.thaddeus.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("all")
public class StudentServlet extends BaseServlet {

    private final StudentService studentService = new StudentServiceImpl();
    private final MoneyService moneyService = new MoneyServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentNo = req.getParameter("student_no");
        String studentPassword = req.getParameter("student_password");

        Student student = studentService.login(new Student(null, null, studentNo, studentPassword, null, null, null));

//        System.out.println(student);

        if (student != null) {
            // 登陆成功
            req.getSession().setAttribute("student", student);
            req.getRequestDispatcher("/pages/student/stu_welcome.jsp").forward(req, resp);
        } else {
            // 登陆失败
            req.setAttribute("errorMsgStu", "学号或密码错误!");
            req.setAttribute("studentNo", studentNo);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();

        resp.sendRedirect(req.getContextPath());
    }

    protected void getStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int classId = WebUtil.parseInt(req.getParameter("class_id"), 0);
        List<Student> students = studentService.getStudentByClassId(classId);

        req.setAttribute("classId", classId);
        req.setAttribute("students", students);
        req.getRequestDispatcher("/pages/money/edit_money.jsp?pageNo=" + req.getParameter("pageNo")).forward(req, resp);
    }

    protected void pay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
//        int classId = WebUtil.parseInt(req.getParameter("classId"), 0);

        int studentId = WebUtil.parseInt(req.getParameter("studentId"), 0);

        // 前端判空一次, 后台再确认一次
        if (!Objects.equals(req.getParameter("moneyCount"), "") && !Objects.equals(req.getParameter("moneyTime"), "")) {

            int moneyCount = WebUtil.parseInt(req.getParameter("moneyCount"), 0);
            Date moneyTime = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("moneyTime"));
            String moneyUse = req.getParameter("moneyUse");
            int moneyState = WebUtil.parseInt(req.getParameter("moneyState"), 0);

            Money money = new Money(null, studentId, moneyCount, moneyTime, moneyUse, moneyState);

            moneyService.addMoneyInfo(money);

            req.setAttribute("addSuccess", 1);
            req.setAttribute("moneyState", moneyState);

            req.getRequestDispatcher("/pages/student/stu_welcome.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    protected void qrCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        String contents = req.getParameter("contents");
        String[] split = contents.split(",");
        contents = "缴费: " + split[0] + "元, 缴费时间: " + split[1];

        QRCodeUtil.createQRCode(contents, 180, 180, resp);
    }

//    protected int confirm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String uuid = req.getParameter("uuid");
//        boolean result = true;
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("uuid = " + uuid);
//
//        if (uuid == null) {
//            result = false;
//        }
//        return result ? 1 : 0;
//    }

}
