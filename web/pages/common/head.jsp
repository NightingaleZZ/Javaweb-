<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/23
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
%>

<base href="<%=basePath%>">

<link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="static/bootstrap/css/style.css">
<script src="static/bootstrap/js/jquery-1.7.2.js"></script>
<script src="static/bootstrap/js/bootstrap.min.js"></script>
