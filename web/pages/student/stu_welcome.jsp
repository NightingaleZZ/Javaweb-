<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/16
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>
    <script>
        window.onload = function () {
            document.getElementById("is_admin").onclick = function () {
                if (${sessionScope.student.isAdmin == 0}) {
                    alert("您不是本班管理员, 不能对班费进行管理");
                    return false;
                }
            }
        }

        if (${not empty requestScope.addSuccess}) {
            alert("提交成功!提交状态为(${requestScope.moneyState == 1 ? "已提交" : "未提交"})");
        }
    </script>
</head>
<body>

<div class="container" style="margin-top: 50px">
    <div class="row">
        <div class="col-sm-4 col-sm-offset-4 text-center" style="height: 100px; line-height: 100px;">
            <h1>欢迎${sessionScope.student.studentName}!</h1>
        </div>
        <div class="col-sm-1 col-sm-offset-3" style="height: 100px;">
            <a href="studentServlet?action=logout" class="btn btn-danger">注销</a>
        </div>
    </div>

    <br><br>

    <div class="row">
        <div class="col-sm-2 col-sm-offset-5 text-center">
            <button class="btn btn-success btn-block btn-lg" onclick="window.location.href='pages/student/pay.jsp?classId=${sessionScope.student.classId}&studentId=${sessionScope.student.studentId}'">
                点我缴费!
            </button>
        </div>
    </div>

    <br><br><hr><br><br>

    <div class="row">
        <div class="col-sm-2 col-sm-offset-2 text-center">
            <a href="moneyServlet?action=view&classId=${sessionScope.student.classId}" class="btn btn-warning btn-block">查看班费</a>
        </div>

        <div class="col-sm-2 col-sm-offset-4 text-center">
            <a href="adminServlet?action=page&classId=${sessionScope.student.classId}" id="is_admin" class="btn btn-info btn-block">管理班费</a>
        </div>
    </div>
</div>



</body>
</html>
