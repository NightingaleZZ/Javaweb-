package com.thaddeus.web;

import com.thaddeus.bean.*;
import com.thaddeus.bean.Class;
import com.thaddeus.service.AdminService;
import com.thaddeus.service.ClassService;
import com.thaddeus.service.MoneyService;
import com.thaddeus.service.StudentService;
import com.thaddeus.service.impl.AdminServiceImpl;
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
public class AdminServlet extends BaseServlet {

    private final AdminService adminService = new AdminServiceImpl();
    private final ClassService classService = new ClassServiceImpl();
    private final StudentService studentService = new StudentServiceImpl();
    private final MoneyService moneyService = new MoneyServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        boolean existAdmin = adminService.existAdmin(username);

        if (existAdmin) {
            // 存在该管理员
            String password = req.getParameter("password");

            Admin admin = adminService.login(username, password);

            if (admin != null) {
                // 登陆成功
                req.getSession().setAttribute("admin", admin);
                req.getRequestDispatcher("/pages/admin/admin_welcome.jsp").forward(req, resp);
            } else {
                // 登陆失败
                req.setAttribute("errorMsgAdmin", "用户名或密码错误");
                req.setAttribute("username", username);
                req.getRequestDispatcher("/pages/admin/admin_login.jsp").forward(req, resp);
            }
        } else {
            // 不存在该管理员
            resp.getWriter().println("<script>alert(\"您不是超级管理员\");</script>");
            // 跳转回主页面
            resp.getWriter().println("<script>window.location.href=\"" + req.getContextPath() + "/index.jsp\"</script>");
//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();

        resp.sendRedirect(req.getContextPath());
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int classId = WebUtil.parseInt(req.getParameter("classId"), 0);
        Class classInfo = classService.getClassInfo(classId);

        List<Student> students = studentService.getStudentByClassId(classId);

        List<Integer> studentId = new ArrayList<>();

        for (Student student : students) {
            studentId.add(student.getStudentId());
        }

        Page<Money> moneyPage = new Page<>();
        moneyPage.setUrl("adminServlet?action=page&classId=" + classId);

        int pageNo = WebUtil.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtil.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        // 当前页码
        moneyPage.setPageNo(pageNo);

        // 每页显示数量
        moneyPage.setPageSize(pageSize);

        // 总记录数
        Integer pageTotalCount = adminService.getMoneyPageCount(studentId);
        moneyPage.setPageTotalCount(pageTotalCount);

        // 总页码
        int pageTotalNo = pageTotalCount / pageSize + (pageTotalCount % pageSize == 0 ? 0 : 1);
        moneyPage.setPageTotalNo(pageTotalNo);

        int begin = (pageNo - 1) * pageSize;


        for (Student student : students) {
            Integer stuId = student.getStudentId();
            studentId.add(stuId);
//            List<Money> moneyList = adminService.getMoneyPage(begin, pageSize, studentId);
//            monies.put(studentId, moneyList);
        }

        List<Money> monies = adminService.getMoneyPage(begin, pageSize, studentId);

//        System.out.println(studentId.toString());

        moneyPage.setItems(monies);

        //收入
        Integer totalRevenue = classService.getTotalRevenue(classId);
        // 支出
        Integer totalExpenditure = classService.getTotalExpenditure(classId);

        req.setAttribute("successSub", req.getParameter("success_sub"));

        req.setAttribute("classInfo", classInfo);
        req.setAttribute("students", students);
        req.setAttribute("page", moneyPage);
        req.setAttribute("revenue", totalRevenue);
        req.setAttribute("expenditure", totalExpenditure);

        req.getRequestDispatcher("pages/money/manage_money.jsp").forward(req, resp);

    }

    protected void getEveryClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Class> allClass = classService.getAllClass();

        req.setAttribute("allClass", allClass);

        req.getRequestDispatcher("/pages/admin/manage_class.jsp").forward(req, resp);

    }

    protected void getClassInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/admin/edit_class.jsp").forward(req, resp);
    }

    protected void updateClass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int classId = WebUtil.parseInt(req.getParameter("classId"), 0);
        String className = req.getParameter("className");
        int classMoney = WebUtil.parseInt(req.getParameter("classMoney"), 0);
        int classYear = WebUtil.parseInt(req.getParameter("classYear"), -1);

        Class aClass = new Class(classId, className, classMoney, classYear);

        classService.updateClassInfo(aClass);

        resp.sendRedirect(req.getContextPath() + "/adminServlet?action=getEveryClass&classId=" + classId);
    }

    protected void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 获取所有班级
        List<Class> allClass = classService.getAllClass();

        // 获取所有学生
        Map<Integer, List<Student>> students = new HashMap<>(); // (K -> 班级 id, V -> 班级所有学生)

        for (Class clazz : allClass) {
            Integer classId = clazz.getClassId();

            List<Student> studentByClassId = studentService.getStudentByClassId(classId);

            students.put(classId, studentByClassId);
        }

        // 获取所有缴费记录
        Map<Integer, List<Money>> monies = new HashMap<>(); // (K -> 学生 id, V -> 该学生的缴费记录)

        for (Map.Entry<Integer, List<Student>> student : students.entrySet()) {
            List<Student> studentList = student.getValue();

            for (Student stu : studentList) {
                Integer studentId = stu.getStudentId();

                List<Money> moneyInfoByStudentId = moneyService.getMoneyInfoByStudentId(studentId);

                monies.put(studentId, moneyInfoByStudentId);
            }
        }

        req.setAttribute("allClass", allClass);
        req.setAttribute("students", students);
        req.setAttribute("monies", monies);

        req.getRequestDispatcher("/pages/admin/all_info.jsp").forward(req, resp);
    }

    protected void getAllInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int studentId = WebUtil.parseInt(req.getParameter("studentId"), 0);
        int moneyId = WebUtil.parseInt(req.getParameter("moneyId"), 0);

        Student student = studentService.getStudentByStudentId(studentId);
        Money money = moneyService.getMoneyInfoByMoneyId(moneyId);

        req.setAttribute("student", student);
        req.setAttribute("money", money);

        req.getRequestDispatcher("/pages/admin/edit_all.jsp").forward(req, resp);
    }

    protected void updateAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {

        // 此处 bug 同 MoneyServlet 的 update() 方法
        String className = req.getParameter("class_name");
        Integer classId = classService.getClassByClassName(className).getClassId();

        Class aClass = new Class(classId, className, null, null);

        classService.updateClassInfo(aClass);

        String studentName = req.getParameter("student_name");
        Integer studentId = studentService.getStudentByName(studentName).getStudentId();

        String studentNo = req.getParameter("student_no");
        String studentGender = req.getParameter("student_gender");
        int isAdmin = WebUtil.parseInt(req.getParameter("is_admin"), 0);
        Student student = new Student(studentId, classId, studentNo, studentName, studentGender, isAdmin);
        studentService.updateStudentInfo(student);

        int moneyId = WebUtil.parseInt(req.getParameter("moneyId"), 0);
        int moneyCount = WebUtil.parseInt(req.getParameter("money_count"), 0);
        Date moneyTime = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("money_time"));
        String moneyUse = req.getParameter("money_use");
        int moneyState = WebUtil.parseInt(req.getParameter("money_state"), 0);
        Money money = new Money(moneyId, studentId, moneyCount, moneyTime, moneyUse, moneyState);
        moneyService.updateMoneyInfo(money);

        resp.sendRedirect(req.getContextPath() + "/adminServlet?action=getAll");


    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int classId = WebUtil.parseInt(req.getParameter("class_id"), 0);
        int moneyId = WebUtil.parseInt(req.getParameter("money_id"), 0);

        Integer moneyState = moneyService.getMoneyInfoByMoneyId(moneyId).getMoneyState();

        // 只有删除已提交的班费信息, 总班费才会减少
        if (moneyState == 1) {

            // 更改总班费
            Integer moneyCount = moneyService.getMoneyInfoByMoneyId(moneyId).getMoneyCount();

            classService.delUpdateClassMoney(moneyState, classId, moneyCount);
        }

        // 删除该条班费记录
        moneyService.deleteMoneyInfo(moneyId);

        resp.sendRedirect(req.getContextPath() + "/adminServlet?action=getAll");

    }

    protected void clean(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
