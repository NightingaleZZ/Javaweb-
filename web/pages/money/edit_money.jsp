<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/19
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>
    <script>
        window.onload = function () {
            // 判断是否提交
            if (${requestScope.moneyInfo.moneyState == 1}) {
                document.getElementById("r1").checked = true;
            } else if (${requestScope.moneyInfo.moneyState == 0}) {
                document.getElementById("r2").checked = true;
            }

            $("#get_now_time").click(function () {
                let date = new Date();
                let year = date.getFullYear();
                let month = date.getMonth() + 1;
                let day = date.getDate();

                $("#m_time").val(year + "-" + month + "-" + day);

                return false;
            })
        }

    </script>
</head>
<body>

<form action="moneyServlet">

    <%--    <input type="hidden" name="student_id" value="${param.student_id}">--%>
    <input type="hidden" name="pageNo" value="${param.pageNo}">
    <input type="hidden" name="money_id" value="${param.money_id}">
    <input type="hidden" name="money_count_before" value="${param.money_count}">
    <input type="hidden" name="action" value="${empty param.money_id ? "add" : "update"}">
    <table class="table table-condensed" style="width: 50%; text-align: center; margin: 50px auto;">
        <tr class="text-center">
            <td>学生姓名</td>
            <td>
                <select name="student_name" class="form-control">
                    <c:if test="${not empty requestScope.studentName}">
                        <option value="${requestScope.studentName}">${requestScope.studentName}</option>
                    </c:if>
                    <c:forEach items="${requestScope.students}" var="student">
                        <c:if test="${student.studentName != requestScope.studentName}">
                            <option value="${student.studentName}">${student.studentName}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <%--<input type="text" name="student_name" value="${requestScope.studentName}">--%>
            </td>

        </tr>

        <tr class="text-center">
            <td>缴费金额</td>
            <td><input type="text" class="form-control" name="money_count" value="${requestScope.moneyInfo.moneyCount}">
            </td>
        </tr>

        <tr class="text-center">
            <td>
                缴费时间
                <span style="font-size: 0.8em; color: #9d9d9d">(yyyy-MM-dd)</span>
                <button id="get_now_time" class="btn btn-default btn-sm">现在</button>
            </td>
            <td><input type="text" class="form-control" name="money_time" id="m_time"
                       value="${requestScope.moneyInfo.moneyTime}">
            </td>
        </tr>

        <tr class="text-center">
            <td>缴费用途</td>
            <td>
                <input type="text" class="form-control" name="money_use"
                       placeholder="${requestScope.moneyInfo.moneyUse == "" ? "请输入" : requestScope.moneyInfo.moneyUse}"
                       value="${requestScope.moneyInfo.moneyUse}">
            </td>
        </tr>

        <tr class="text-center">
            <td>是否提交缴费</td>
            <td>
                已提交 <input type="radio" name="money_state" value="1" id="r1">
                |
                未提交 <input type="radio" name="money_state" value="0" id="r2">
            </td>
        </tr>

        <tr class="text-center">
            <td colspan="2" style="font-size: 0.8em; color: #9d9d9d">缴费数, 缴费时间, 提交状态不能为空哦</td>
        </tr>

        <tr class="text-center">
            <td colspan="2">
                <input type="submit" class="btn btn-success btn-block" style="margin:auto; width: 50%;" value="完成">
            </td>
        </tr>

    </table>
</form>

<br><br><br><br><br><br><br>
<div class="container">
    <div class="row">
        <div class="col-sm-1 col-sm-offset-11">
            <form action="moneyServlet">
                <input type="hidden" name="pageNo" value="${param.pageNo}">
                <input type="hidden" name="action" value="goBack">
                <input type="hidden" name="classId" value="${requestScope.classId}">
                <input type="submit" class="btn btn-info" value="返回">
            </form>
        </div>
    </div>
</div>

</body>
</html>
