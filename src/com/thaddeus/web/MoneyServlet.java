package com.thaddeus.web;

import com.thaddeus.bean.Class;
import com.thaddeus.bean.Money;
import com.thaddeus.bean.Student;
import com.thaddeus.service.ClassService;
import com.thaddeus.service.MoneyService;
import com.thaddeus.service.StudentService;
import com.thaddeus.service.impl.ClassServiceImpl;
import com.thaddeus.service.impl.MoneyServiceImpl;
import com.thaddeus.service.impl.StudentServiceImpl;
import com.thaddeus.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("all")
public class MoneyServlet extends BaseServlet {

    private final MoneyService moneyService = new MoneyServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final ClassService classService = new ClassServiceImpl();

    protected void view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 从 session 域中获取登录的学生
        Student student = (Student) req.getSession().getAttribute("student");
//        System.out.println("登录的学生信息: " + student);

        // 获取班级 id
        int classId = student.getClassId();
        Class classInfo = classService.getClassInfo(classId);
//        System.out.println("classInfo = " + classInfo);

        // 获取该班级所有学生信息
        List<Student> students = studentService.getStudentByClassId(classId);
//        System.out.println("students = " + students);

        List<Integer> stuId = new ArrayList<>();

        // 获得该班级所有班费信息
        for (Student stu : students) {
            Integer studentId = stu.getStudentId();
            stuId.add(studentId);
        }

        List<Money> monies = new ArrayList<>();

        monies = moneyService.getMoneyInfoByStudentIdList(stuId);
//        System.out.println("monies = " + monies);

        //收入
        Integer totalRevenue = classService.getTotalRevenue(classId);
        // 支出
        Integer totalExpenditure = classService.getTotalExpenditure(classId);

        // 返回当前学生班级信息
        req.setAttribute("classInfo", classInfo);

        // 返回该班级所有学生信息
        req.setAttribute("students", students);

        // 返回该班级所有班费信息, 在页面进行对应输出
        req.setAttribute("monies", monies);

        req.setAttribute("revenue", totalRevenue);
        req.setAttribute("expenditure", totalExpenditure);

        req.getRequestDispatcher("/pages/money/view_money.jsp").forward(req, resp);
    }

//    protected void manage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        int classId = 0;
//
//        // 从 session 域中获取登录的学生
////        Student student = (Student) req.getSession().getAttribute("student");
////        System.out.println("登录的学生信息: " + student);
//
//        // 获取班级 id
//        classId = WebUtil.parseInt(req.getParameter("classId"), 0);
//
//        Class classInfo = classService.getClassInfo(classId);
////        System.out.println("classInfo = " + classInfo);
//
//        // 获取该班级所有学生信息
//        List<Student> students = studentService.getStudentByClassId(classId);
////        System.out.println("students = " + students);
//
//        // 获得该班级所有班费信息
//        Map<Integer, List<Money>> monies = new HashMap<>(); // (K -> 学生 id, V -> 该学生的缴费记录)
//        for (Student stu : students) {
//            Integer studentId = stu.getStudentId();
//            List<Money> moneyList = moneyService.getMoneyInfoByStudentId(studentId);
//            monies.put(studentId, moneyList);
//        }
////        System.out.println("monies = " + monies);
//
//        // 返回当前学生班级信息
//        req.setAttribute("classInfo", classInfo);
//
//        // 返回该班级所有学生信息
//        req.setAttribute("students", students);
//
//        // 返回该班级所有班费信息, 在页面进行对应输出
//        req.setAttribute("monies", monies);
//
//        req.getRequestDispatcher("pages/money/manage_money.jsp").forward(req, resp);
//    }

    protected void getMoneyInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String studentName = req.getParameter("student_name");
        Integer classId = WebUtil.parseInt(req.getParameter("class_id"), 0);
        List<Student> students = studentService.getStudentByClassId(classId);

        // 获取班费记录的 id
        int moneyId = WebUtil.parseInt(req.getParameter("money_id"), 0);

        Money moneyInfo = moneyService.getMoneyInfoByMoneyId(moneyId);

        req.setAttribute("classId", classId);
        req.setAttribute("studentName", studentName);
        req.setAttribute("students", students);
        req.setAttribute("moneyInfo", moneyInfo);

        req.getRequestDispatcher("/pages/money/edit_money.jsp").forward(req, resp);
    }

    protected void complete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {

        int classId = WebUtil.parseInt(req.getParameter("class_id"), 0);

        resp.sendRedirect(req.getContextPath() + "/adminServlet?action=page&classId=" + classId);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {

        String studentName = req.getParameter("student_name");
        Student student = studentService.getStudentByName(studentName);

        Integer classId = student.getClassId();
//        req.setAttribute("class_id", classId);

        if (req.getParameter("money_count") == null
                || req.getParameter("money_time") == null
                || req.getParameter("money_state") == null) {

//            List<Student> students = studentService.getStudentByClassId(classId);

//            req.setAttribute("students", students);
            req.setAttribute("errorMsg", "1");

            req.getRequestDispatcher("/adminServlet?action=page&classId=" + classId).forward(req, resp);

//            return;
        }

        Integer studentId = student.getStudentId();

        int moneyCount = WebUtil.parseInt(req.getParameter("money_count"), 0);
        Date moneyTime = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("money_time"));
        String moneyUse = req.getParameter("money_use");
        int moneyState = WebUtil.parseInt(req.getParameter("money_state"), 0);

        Money money = new Money(null, studentId, moneyCount, moneyTime, moneyUse, moneyState);

        moneyService.addMoneyInfo(money);
        if (moneyState == 1) {
            classService.updateClassMoney(moneyState, classId, moneyCount);
        }

        resp.sendRedirect(req.getContextPath() + "/adminServlet?action=page&success_sub=1&classId=" + classId + "&pageNo=" + req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException, ServletException {

        // 获取参数
//        int studentId = WebUtil.parseInt(req.getParameter("student_id"), 0);
        int moneyId = WebUtil.parseInt(req.getParameter("money_id"), 0);

        // 修改前的提交状态
        Integer moneyState1 = moneyService.getMoneyInfoByMoneyId(moneyId).getMoneyState();

        String studentName = req.getParameter("student_name");

        // 此处有 bug: 因为不能直接接收来自参数的学生 id, 所以只能通过学生姓名查找学生, 如有同名学生则会出现 id 错误的情况
        Student student = studentService.getStudentByName(studentName);
        Integer studentId = student.getStudentId();
        Integer classId = student.getClassId();

        if (req.getParameter("money_count") == null
                || Objects.equals(req.getParameter("money_time"), "")
                || req.getParameter("money_state") == null) {

//            List<Student> students = studentService.getStudentByClassId(classId);

//            req.setAttribute("students", students);
            req.setAttribute("errorMsg", "1");

            req.getRequestDispatcher("/adminServlet?action=page&classId=" + classId).forward(req, resp);

//            return;
        }

        int moneyCount = WebUtil.parseInt(req.getParameter("money_count"), 0);
        int moneyCountBefore = WebUtil.parseInt(req.getParameter("money_count_before"), 0);
        Date moneyTime = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("money_time"));
        String moneyUse = req.getParameter("money_use");

        // 修改后提交状态
        int moneyState = WebUtil.parseInt(req.getParameter("money_state"), 0);

        // 修改班费记录
        Money money = new Money(moneyId, studentId, moneyCount, moneyTime, moneyUse, moneyState);
        moneyService.updateMoneyInfo(money);

//        Student student = (Student) req.getSession().getAttribute("student");
        req.setAttribute("class_id", classId);

        // 只有前后提交状态不同或提交金额改变才需要改总班费
        if (moneyState != moneyState1) {
            classService.updateClassMoney(moneyState, classId, moneyCount);
        }

        if (moneyCount != moneyCountBefore && moneyState == 1) {
            classService.updateClassMoney(1, classId, moneyCount - moneyCountBefore);
        }

        resp.sendRedirect(req.getContextPath() + "/adminServlet?action=page&success_sub=1&classId=" + classId + "&pageNo=" + req.getParameter("pageNo"));
//        req.getRequestDispatcher("/pages/money/manage_money.jsp").forward(req, resp);

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int studentId = WebUtil.parseInt(req.getParameter("student_id"), 0);
        Integer classId = studentService.getStudentByStudentId(studentId).getClassId();

        int moneyId = WebUtil.parseInt(req.getParameter("money_id"), 0);

        Integer moneyState = moneyService.getMoneyInfoByMoneyId(moneyId).getMoneyState();

        // 只有删除已提交的班费信息, 总班费才会减少
        if (moneyState == 1) {

            Integer moneyCount = moneyService.getMoneyInfoByMoneyId(moneyId).getMoneyCount();

            classService.delUpdateClassMoney(moneyState, classId, moneyCount);
        }

        moneyService.deleteMoneyInfo(moneyId);

        resp.sendRedirect(req.getContextPath() + "/adminServlet?action=page&classId=" + classId + "&pageNo=" + req.getParameter("pageNo"));

    }

    protected void submitAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String moneyIds = req.getParameter("moneyIdList");
        int studentId = WebUtil.parseInt(req.getParameter("studentId"), 0);
        Integer classId = studentService.getStudentByStudentId(studentId).getClassId();

        List<Integer> moneyIdList = new ArrayList<>();

        String[] split = moneyIds.split(",");
        for (String s : split) {
            int i = Integer.parseInt(s);
            moneyIdList.add(i);
        }

//        System.out.println(moneyIdList);

        for (Integer moneyId : moneyIdList) {
            Money money = moneyService.getMoneyInfoByMoneyId(moneyId);

            // 未提交状态
            if (money.getMoneyState() != 1) {

                moneyService.setMoneyStateSub(moneyId);
                classService.updateClassMoney(1, classId, money.getMoneyCount());
            }
        }

        resp.sendRedirect(req.getContextPath() + "/adminServlet?action=page&pageNo=" + req.getParameter("pageNo") + "&classId=" + classId);

    }

    protected void queryStuMoneyInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = studentService.getStudentByName(req.getParameter("student_name"));

        List<Money> monies = moneyService.getMoneyInfoByStudentId(student.getStudentId());

        req.setAttribute("student", student);
        req.setAttribute("monies", monies);
        req.setAttribute("successSub", req.getParameter("success_sub"));

        req.getRequestDispatcher("/pages/money/personal_money.jsp").forward(req, resp);
    }

    protected void getPersonalMoneyInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String studentName = req.getParameter("student_name");
        Integer classId = studentService.getStudentByStudentId(WebUtil.parseInt(req.getParameter("student_id"), 0)).getClassId();
        List<Student> students = studentService.getStudentByClassId(classId);

        // 获取班费记录的 id
        int moneyId = WebUtil.parseInt(req.getParameter("money_id"), 0);

        Money moneyInfo = moneyService.getMoneyInfoByMoneyId(moneyId);

        req.setAttribute("studentName", studentName);
        req.setAttribute("students", students);
        req.setAttribute("moneyInfo", moneyInfo);

        req.getRequestDispatcher("/pages/money/personal_edit_money.jsp").forward(req, resp);
    }

    protected void submitAllPersonal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String moneyIds = req.getParameter("moneyIdList");

        int studentId = WebUtil.parseInt(req.getParameter("studentId"), 0);

        Student student = studentService.getStudentByStudentId(studentId);

        String studentName = student.getStudentName();

        Integer classId = student.getClassId();

        List<Integer> moneyIdList = new ArrayList<>();

        String[] split = moneyIds.split(",");
        for (String s : split) {
            int i = Integer.parseInt(s);
            moneyIdList.add(i);
        }

//        System.out.println(moneyIdList);

        for (Integer moneyId : moneyIdList) {
            Money money = moneyService.getMoneyInfoByMoneyId(moneyId);

            // 未提交状态
            if (money.getMoneyState() != 1) {

                moneyService.setMoneyStateSub(moneyId);
                classService.updateClassMoney(1, classId, money.getMoneyCount());
            }
        }

        resp.sendRedirect(req.getContextPath() + "/moneyServlet?action=queryStuMoneyInfo&student_name=" + studentName + "&classId=" + classId);

    }

    protected void addPersonal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {

        int studentId = WebUtil.parseInt(req.getParameter("student_id"), 0);
        Student student = studentService.getStudentByStudentId(studentId);

        Integer classId = student.getClassId();

        if (Objects.equals(req.getParameter("money_count"), "")
                || Objects.equals(req.getParameter("money_time"), "")) {

//            List<Student> students = studentService.getStudentByClassId(classId);

//            req.setAttribute("students", students);
            req.setAttribute("errorMsg", "1");

            req.getRequestDispatcher("/moneyServlet?action=queryStuMoneyInfo&student_name=" + student.getStudentName()).forward(req, resp);

//            return;
        }

        int moneyCount = WebUtil.parseInt(req.getParameter("money_count"), 0);
        Date moneyTime = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("money_time"));
        String moneyUse = req.getParameter("money_use");
        int moneyState = WebUtil.parseInt(req.getParameter("money_state"), 0);

        Money money = new Money(null, studentId, moneyCount, moneyTime, moneyUse, moneyState);

        moneyService.addMoneyInfo(money);
        if (moneyState == 1) {
            classService.updateClassMoney(moneyState, classId, moneyCount);
        }

        resp.sendRedirect(req.getContextPath() + "/moneyServlet?action=queryStuMoneyInfo&success_sub=1&student_name=" + student.getStudentName() + "&classId=" + classId);
    }

    protected void updatePersonal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {

        // 获取参数
        int studentId = WebUtil.parseInt(req.getParameter("student_id"), 0);
        String studentName = studentService.getStudentByStudentId(studentId).getStudentName();
        int moneyId = WebUtil.parseInt(req.getParameter("money_id"), 0);

        // 修改前提交状态
        Integer moneyState1 = moneyService.getMoneyInfoByMoneyId(moneyId).getMoneyState();

        if (Objects.equals(req.getParameter("money_count"), "")
                || Objects.equals(req.getParameter("money_time"), "")) {

//            List<Student> students = studentService.getStudentByClassId(classId);

//            req.setAttribute("students", students);
            req.setAttribute("errorMsg", "1");

            req.getRequestDispatcher("/moneyServlet?action=queryStuMoneyInfo&student_name=" + studentName).forward(req, resp);

//            return;
        }

        int moneyCount = WebUtil.parseInt(req.getParameter("money_count"), 0);
        int moneyCountBefore = WebUtil.parseInt(req.getParameter("money_count_before"), 0);
        Date moneyTime = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("money_time"));
        String moneyUse = req.getParameter("money_use");

        // 修改后提交状态
        int moneyState = WebUtil.parseInt(req.getParameter("money_state"), 0);

        // 修改班费记录
        Money money = new Money(moneyId, studentId, moneyCount, moneyTime, moneyUse, moneyState);
        moneyService.updateMoneyInfo(money);

//        Student student = (Student) req.getSession().getAttribute("student");
        Integer classId = studentService.getStudentByStudentId(studentId).getClassId();

        // 只有前后提交状态不同才需要改总班费
        if (moneyState != moneyState1) {
            classService.updateClassMoney(moneyState, classId, moneyCount);
        }

        if (moneyCount != moneyCountBefore && moneyState == 1) {
            classService.updateClassMoney(1, classId, moneyCount - moneyCountBefore);
        }

        resp.sendRedirect(req.getContextPath() + "/moneyServlet?action=queryStuMoneyInfo&success_sub=1&student_name=" + studentName + "&classId=" + classId);
//        req.getRequestDispatcher("/pages/money/manage_money.jsp").forward(req, resp);

    }

    protected void deletePersonal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int studentId = WebUtil.parseInt(req.getParameter("student_id"), 0);
        Integer classId = studentService.getStudentByStudentId(studentId).getClassId();

        int moneyId = WebUtil.parseInt(req.getParameter("money_id"), 0);

        Integer moneyState = moneyService.getMoneyInfoByMoneyId(moneyId).getMoneyState();

        // 只有删除已提交的班费信息, 总班费才会减少
        if (moneyState == 1) {

            Integer moneyCount = moneyService.getMoneyInfoByMoneyId(moneyId).getMoneyCount();

            classService.delUpdateClassMoney(moneyState, classId, moneyCount);
        }

        moneyService.deleteMoneyInfo(moneyId);

        resp.sendRedirect(req.getContextPath() + "/moneyServlet?action=queryStuMoneyInfo&student_name=" + studentService.getStudentByStudentId(studentId).getStudentName() + "&classId=" + classId);

    }

    protected void goBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int classId = WebUtil.parseInt(req.getParameter("classId"), 0);

        resp.sendRedirect(req.getContextPath() + "/adminServlet?action=page&classId=" + classId + "&pageNo=" + req.getParameter("pageNo"));
    }
}
