<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/25
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        function deleteElem(node) {
            let parentNode = node.parentNode.parentNode;

            return confirm("你确定删除" + parentNode.children[0].innerHTML + "班" + parentNode.children[1].innerHTML + "学生的缴费记录吗?该记录提交状态为(" +
                parentNode.children[7].innerHTML + ")");
        }

        // window.onload = function () {
        //     document.getElementById("delete").onclick = function () {
        //         let parentNode = document.getElementById("delete").parentNode.parentNode;
        //
        //         return confirm("你确定删除" + parentNode.children[1].innerHTML + "吗?");
        //     }
        // }

    </script>
</head>
<body>

<div class="headerLine">班费管理系统</div>
<br>

<table class="table table-hover table-condensed">
    <thead>
    <tr>
        <th class="text-center">班级名称</th>
        <th class="text-center">学生姓名</th>
        <th class="text-center">学号</th>
        <th class="text-center">性别</th>
        <th class="text-center">是否是班级管理员</th>
        <th class="text-center">缴费金额</th>
        <th class="text-center">缴费时间</th>
        <th class="text-center">缴费用途</th>
        <th class="text-center">是否提交缴费</th>
        <%--<th>操作</th>--%>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${requestScope.allClass}" var="clazz">
        <c:forEach items="${requestScope.students}" var="student">
            <c:if test="${clazz.classId == student.key}">
                <c:forEach items="${student.value}" var="student_list">
                    <c:forEach items="${requestScope.monies}" var="money">
                        <c:if test="${student_list.studentId == money.key}">
                            <c:forEach items="${money.value}" var="money_list">
                                <c:if test="${money_list.moneyState == 1}">
                                    <tr class="success text-center">
                                        <td>${clazz.className}</td>
                                        <td>${student_list.studentName}</td>
                                        <td>${student_list.studentNo}</td>
                                        <td>${student_list.studentGender}</td>
                                        <td>${student_list.isAdmin == 1 ? "是" : "否"}</td>
                                        <td>${money_list.moneyCount}</td>
                                        <td>${money_list.moneyTime}</td>
                                        <td>${empty money_list.moneyUse ? "无" : money_list.moneyUse}</td>
                                        <td>${money_list.moneyState == 1 ? "已提交" : "未提交"}</td>
                                        <%--<td>--%>
                                        <%--<a href="adminServlet?action=getAllInfo&studentId=${student_list.studentId}&moneyId=${money_list.moneyId}&className=${clazz.className}&studentName=${student_list.studentName}&studentNo=${student_list.studentNo}&studentGender=${student_list.studentGender}&moneyCount=${money_list.moneyCount}&moneyTime=${money_list.moneyTime}&moneyUse=${money_list.moneyUse}">修改</a>--%>
                                        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
                                        <%--<a href="adminServlet?action=delete&class_id=${clazz.classId}&money_id=${money_list.moneyId}"--%>
                                        <%--onclick="return deleteElem(this)">删除</a>--%>
                                        <%--</td>--%>
                                    </tr>
                                </c:if>

                                <c:if test="${money_list.moneyState == 0}">
                                    <tr class="danger text-center">
                                        <td>${clazz.className}</td>
                                        <td>${student_list.studentName}</td>
                                        <td>${student_list.studentNo}</td>
                                        <td>${student_list.studentGender}</td>
                                        <td>${student_list.isAdmin == 1 ? "是" : "否"}</td>
                                        <td>${money_list.moneyCount}</td>
                                        <td>${money_list.moneyTime}</td>
                                        <td>${empty money_list.moneyUse ? "无" : money_list.moneyUse}</td>
                                        <td>${money_list.moneyState == 1 ? "已提交" : "未提交"}</td>
                                        <%--<td>--%>
                                        <%--<a href="adminServlet?action=getAllInfo&studentId=${student_list.studentId}&moneyId=${money_list.moneyId}&className=${clazz.className}&studentName=${student_list.studentName}&studentNo=${student_list.studentNo}&studentGender=${student_list.studentGender}&moneyCount=${money_list.moneyCount}&moneyTime=${money_list.moneyTime}&moneyUse=${money_list.moneyUse}">修改</a>--%>
                                        <%--&nbsp;&nbsp;&nbsp;&nbsp;--%>
                                        <%--<a href="adminServlet?action=delete&class_id=${clazz.classId}&money_id=${money_list.moneyId}"--%>
                                        <%--onclick="return deleteElem(this)">删除</a>--%>
                                        <%--</td>--%>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </c:if>
        </c:forEach>
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
