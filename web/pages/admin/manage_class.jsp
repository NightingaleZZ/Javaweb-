<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/25
  Time: 19:19
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
        <th class="text-center">班级名称</th>
        <th class="text-center">总班费</th>
        <th class="text-center">入学年份</th>
        <th class="text-center">操作</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${requestScope.allClass}" var="clazz">
        <tr class="text-center">
            <td>${clazz.className}</td>
            <td>${clazz.classMoney}</td>
            <td>${clazz.classYear}</td>
            <td>
                <a href="adminServlet?action=page&classId=${clazz.classId}"><button class="btn btn-default btn-sm">查看明细</button></a>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="adminServlet?action=getClassInfo&classId=${clazz.classId}&className=${clazz.className}&classMoney=${clazz.classMoney}&classYear=${clazz.classYear}"><button class="btn btn-info btn-sm">修改</button></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>


<div class="container">
    <div class="row">
        <div class="col-sm-1">
            <a href="pages/admin/admin_welcome.jsp"><button class="btn btn-primary">返回</button></a>
        </div>
    </div>
</div>
<br>

</body>
</html>
