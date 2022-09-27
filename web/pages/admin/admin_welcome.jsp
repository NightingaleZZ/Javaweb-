<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/23
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>

<div class="container" style="margin-top: 50px">
    <div class="row">
        <div class="col-sm-4 col-sm-offset-4 text-center" style="height: 100px; line-height: 100px;">
            <h1>欢迎超级管理员${sessionScope.admin.adminUsername}!</h1>
        </div>
        <div class="col-sm-1 col-sm-offset-3" style="height: 100px;">
            <a href="adminServlet?action=logout" class="btn btn-danger">注销</a>
        </div>
    </div>

    <br><br><br><br>
    <hr>
    <br>
    <div class="row">
        <div class="col-sm-2 col-sm-offset-2 text-center">
            <a href="adminServlet?action=getAll" class="btn btn-warning btn-block">查看所有</a>
        </div>

        <div class="col-sm-2 col-sm-offset-4 text-center">
            <a href="adminServlet?action=getEveryClass" class="btn btn-info btn-block">管理班级</a>
        </div>
    </div>
</div>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<div class="container">
    <div class="row">
        <div class="col-sm-1 col-sm-offset-11"><a class="btn btn-primary" href="adminServlet?action=clean">返回首页</a>
        </div>
    </div>
</div>
<br>

</body>
</html>
