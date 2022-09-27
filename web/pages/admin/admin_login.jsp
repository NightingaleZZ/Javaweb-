<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/23
  Time: 15:35
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

<form action="adminServlet" method="post" class="login-form">

    <input type="hidden" name="action" value="login">

    <h1>Administrator</h1>

    <div class="txtb">
        <input type="text" placeholder="用户名" name="username" value="${requestScope.username}">
        <span></span>
    </div>

    <div class="txtb">
        <input type="password" placeholder="密码" name="password">
        <span></span>
    </div>

    <div class="text-center"
         style="font-size: 0.8em; color: #9d9d9d">${empty requestScope.errorMsgAdmin ? "" : requestScope.errorMsgAdmin}
    </div>

    <br>

    <button type="submit" class="btn btn-default logbtn"><i class="fa fa-arrow-right"></i></button>

    <div class="bottom-text">
        <a href="index.jsp" style="font-size: 1.12em">返回</a>
    </div>

</form>


<%--<div class="container">--%>

<%--    <div class="row">--%>
<%--        <div class="col-md-offset-4 col-md-4 col-sm-offset-3 col-sm-6">--%>

<%--            <h1>欢迎!超级管理员</h1>--%>

<%--            <span>${empty requestScope.errorMsgAdmin ? "请输入用户名和密码进入管理页面" : requestScope.errorMsgAdmin}</span>--%>

<%--            <form action="adminServlet" method="post" class="form-horizontal">--%>

<%--                <input type="hidden" name="action" value="login">--%>

<%--                <div class="form-group">--%>
<%--                    <i class="fa fa-user"></i><input type="text" name="username" value="${requestScope.username}"--%>
<%--                                                     class="form-control" placeholder="Username">--%>
<%--                </div>--%>
<%--                <div class="form-group">--%>
<%--                    <i class="fa fa-lock"></i><input type="password" name="password"--%>
<%--                                                     class="form-control" placeholder="Password">--%>
<%--                </div>--%>
<%--                <div class="form-group">--%>
<%--                    <button type="submit" class="btn btn-default"><i class="fa fa-arrow-right"></i></button>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<%--<span>${empty requestScope.errorMsgAdmin ? "请输入用户名和密码进入管理页面" : requestScope.errorMsgAdmin}</span> <br>--%>
<%--<form action="adminServlet" method="post">--%>
<%--    <input type="hidden" name="action" value="login">--%>
<%--    用户名: <input type="text" name="username" value="${requestScope.username}"> <br>--%>
<%--    密码: <input type="password" name="password"> <br>--%>
<%--    <input type="submit" value="登录">--%>
<%--</form>--%>
</body>
</html>
