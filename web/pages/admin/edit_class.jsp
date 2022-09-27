<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/25
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>

<form action="adminServlet" method="get">

    <input type="hidden" name="action" value="updateClass">
    <input type="hidden" name="classId" value="${param.classId}">

    <table class="table table-condensed" style="width: 50%; text-align: center; margin: 50px auto;">
        <tr class="text-center">
            <td>班级名称</td>
            <td><input type="text" class="form-control" name="className" value="${param.className}"></td>
        </tr>

        <tr class="text-center">
            <td>总班费</td>
            <td><input type="text" class="form-control" name="classMoney" value="${param.classMoney}"></td>
        </tr>

        <tr class="text-center">
            <td>入学年份</td>
            <td><input type="text" class="form-control" name="classYear" value="${param.classYear}"></td>
        </tr>

        <tr class="text-center">
            <td colspan="2" style="font-size: 0.8em; color: #9d9d9d">均不能为空</td>
        </tr>

        <tr class="text-center">
            <td colspan="2">
                <input type="submit" class="btn btn-success btn-block" style="margin:auto; width: 50%;" value="完成">
            </td>
        </tr>
    </table>

</form>

</body>
</html>
