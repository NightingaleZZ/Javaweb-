<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/17
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>

<div class="headerLine">班费管理系统</div>
<br>

<table class="table table-hover table-condensed">
    <thead>
    <tr>
        <th class="text-center">学生姓名</th>
        <%-- <th class="text-center">学生学号</th> --%>
        <th class="text-center">缴费金额</th>
        <th class="text-center">缴费时间</th>
        <th class="text-center">缴费用途</th>
        <c:if test="${sessionScope.student.isAdmin == 1}">
            <th class="text-center">是否提交缴费</th>
        </c:if>
    </tr>
    </thead>

    <c:forEach items="${requestScope.students}" var="student">
        <c:forEach items="${requestScope.monies}" var="money">
            <c:if test="${student.studentId == money.studentId}">
                <tr class="text-center">
                    <td>${student.studentName}</td>
                        <%-- <td>${student.studentNo}</td> --%>
                    <td>${money.moneyCount}</td>
                    <td>${money.moneyTime}</td>
                    <td>${empty money.moneyUse ? "无" : money.moneyUse}</td>
                        <%-- 是管理员则显示班费是否提交 --%>
                    <c:if test="${sessionScope.student.isAdmin == 1}">
                        <td>${money.moneyState == 1 ? "已提交" : "未提交"}</td>
                    </c:if>
                </tr>
            </c:if>
        </c:forEach>
    </c:forEach>
    <tr class="text-center">
        <td colspan="6" class="info">${requestScope.classInfo.className}班级总班费: ${requestScope.classInfo.classMoney}, 总收入: ${requestScope.revenue}, 总支出: ${requestScope.expenditure}</td>
    </tr>
</table>

<div class="container">
    <div class="row">
        <div class="col-sm-1">
            <a href="pages/student/stu_welcome.jsp" class="btn btn-primary">返回</a>
        </div>
    </div>
</div>
<br>

</body>
</html>
