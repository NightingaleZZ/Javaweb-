<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/16
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>$Title$</title>
    <%@include file="/pages/common/head.jsp" %>
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">

    <script>
        $(function () {
            let v = $(".txtb input");
            v.on("focus", function () {
                $(this).addClass("focus");
            });

            v.on("blur", function () {
                if ($(this).val() === "")
                    $(this).removeClass("focus");
            });
        })
    </script>

    <style>
        body {
            min-height: 100vh;
            background-image: linear-gradient(120deg, #7ab6b6, #e5cfaa);
        }
    </style>
</head>
<body>

<form action="studentServlet" method="post" class="login-form">

    <input type="hidden" name="action" value="login">

    <h1>Welcome</h1>

    <div class="txtb">
        <input type="text" placeholder="学号" name="student_no" value="${requestScope.studentNo}">
        <span></span>
    </div>

    <div class="txtb">
        <input type="password" placeholder="密码" name="student_password" id="student_password">
        <span></span>
    </div>

    <div class="text-center" style="font-size: 0.8em; color: #9d9d9d">
        ${empty requestScope.errorMsgStu ? "" : requestScope.errorMsgStu}
    </div>

    <br>

    <button type="submit" class="btn btn-default logbtn"><i class="fa fa-arrow-right"></i></button>

    <div class="bottom-text">
        <a href="pages/admin/admin_login.jsp" style="font-size: 1.12em">超级管理员?</a>
    </div>

</form>



<%--<div class="container">--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-offset-4 col-md-4 col-sm-offset-3 col-sm-6">--%>
<%--            <h1>欢迎! 请登录: </h1>--%>
<%--            <span>${empty requestScope.errorMsgStu ? "请输入学号和密码进入缴费页面" : requestScope.errorMsgStu}</span>--%>
<%--            <form action="studentServlet" method="post" class="form-horizontal">--%>

<%--                <input type="hidden" name="action" value="login">--%>

<%--                <div class="form-group">--%>
<%--                    <i class="fa fa-user"></i><input type="text" class="form-control" placeholder="StudentNo"--%>
<%--                                                     name="student_no" value="${requestScope.studentNo}" required>--%>
<%--                </div>--%>

<%--                <div class="form-group">--%>
<%--                    <i class="fa fa-lock"></i><input type="password" class="form-control" placeholder="Password"--%>
<%--                                                     name="student_password" id="student_password" required>--%>
<%--                </div>--%>

<%--                <div class="form-group">--%>
<%--                    <button type="submit" class="btn btn-default"><i class="fa fa-arrow-right"></i></button>--%>
<%--                </div>--%>

<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--    <span>${empty requestScope.errorMsgStu ? "请输入学号和密码进入缴费页面" : requestScope.errorMsgStu}</span> <br>--%>
<%--<a href="pages/admin/admin_login.jsp">超级管理员?</a>--%>

</body>
</html>
