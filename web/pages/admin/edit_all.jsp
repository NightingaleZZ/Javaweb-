<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/25
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>
    <style>
        input[type=text] {
            width: 110px;
        }
    </style>
    <script>
        window.onload = function () {
            // 判断是否是管理员
            if (${requestScope.student.isAdmin == 1}) {
                document.getElementById("r1").checked = true;
            } else if (${requestScope.student.isAdmin == 0}) {
                document.getElementById("r2").checked = true;
            }

            // 判断是否提交
            if (${requestScope.money.moneyState == 1}) {
                document.getElementById("r3").checked = true;
            } else if (${requestScope.money.moneyState == 0}) {
                document.getElementById("r4").checked = true;
            }
        }
    </script>
</head>
<body>
<form action="adminServlet" method="post">

    <input type="hidden" name="action" value="updateAll">
    <input type="hidden" name="money_id" value="${param.moneyId}">

    <table>
        <tr>
            <td>班级名称</td>
            <td>学生姓名</td>
            <td>学号</td>
            <td>性别</td>
            <td>是否是班级管理员</td>
            <td>缴费金额</td>
            <td>缴费时间</td>
            <td>缴费用途</td>
            <td>是否提交缴费</td>
            <td>操作</td>
        </tr>
        <tr>
            <td><input type="text" name="class_name" value="${param.className}"></td>
            <td><input type="text" name="student_name" value="${param.studentName}"></td>
            <td><input type="text" name="student_no" value="${param.studentNo}"></td>
            <td><input type="text" name="student_gender" value="${param.studentGender}"></td>
            <td>
                是 <input type="radio" name="is_admin" id="r1" value="1">
                |
                否 <input type="radio" name="is_admin" id="r2" value="0">
            </td>
            <td><input type="text" name="money_count" value="${param.moneyCount}"></td>
            <td><input type="text" name="money_time" value="${param.moneyTime}"></td>
            <td><input type="text" name="money_use" value="${param.moneyUse}"></td>
            <td>
                已提交 <input type="radio" name="money_state" id="r3" value="1">
                |
                未提交 <input type="radio" name="money_state" id="r4" value="0">
            </td>
            <td><input type="submit" value="提交"></td>
        </tr>
    </table>
</form>
</body>
</html>
