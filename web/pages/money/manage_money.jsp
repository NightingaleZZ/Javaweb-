<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/17
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">

        window.onload = function () {
            let btn = document.getElementById("select_all");
            btn.onclick = function () {
                let flag = this.checked;
                let items = document.getElementsByName("one_box");
                for (let i = 0; i < items.length; i++) {
                    items[i].checked = flag;//将所有item的状态设为全选按钮的状态
                }
            }


            let items = document.getElementsByName("one_box");
            for (let i = 0; i < items.length; i++) {
                items[i].onclick = function () {//对每个item设置点击
                    let number = 0;//记录选中的个数
                    for (let j = 0; j < items.length; j++) {
                        if (items[j].checked) {
                            number++;
                        }
                    }
                    document.getElementById("all").checked = (items.length === number);
                }
            }
        }

        function deleteElem(node) {
            let parentNode = node.parentNode.parentNode;

            return confirm("你确定删除" + parentNode.children[3].innerHTML + "的缴费记录吗?该记录提交状态为(" +
                parentNode.children[7].innerHTML + ")");
        }

        function submitAll() {
            // alert(111);
            let elementsByName = document.getElementsByName("one_box");

            // alert(elementsByName);
            let index = 0;
            let moneyIdList = [];

            for (let i = 0; i < elementsByName.length; i++) {
                if (elementsByName[i].checked === true) {
                    moneyIdList[index++] = elementsByName[i].parentNode.parentNode.parentNode.children[1].innerHTML;
                    // alert(elementsByName[i].parentNode.parentNode.children[1].innerHTML);
                    // window.location.href = "moneyServlet?action=submitAll&moneyId=" + elementsByName[i].parentNode.parentNode.children[1].innerHTML + "&studentId=" + elementsByName[i].parentNode.parentNode.children[2].innerHTML;
                }
            }
            // alert(index);

            if (index !== 0) {
                window.location.href = "moneyServlet?action=submitAll&pageNo=${requestScope.page.pageNo}&moneyIdList=" + moneyIdList + "&studentId=" + elementsByName[0].parentNode.parentNode.parentNode.children[2].innerHTML;
            }
            // let xmlHttpRequest = new XMLHttpRequest();
            // xmlHttpRequest.open("POST", "http://localhost:8080/dbms/ajaxServlet?action=submitAll", true);
            // xmlHttpRequest.send();
        }

        if (${not empty requestScope.errorMsg}) {
            alert("提交失败");
        }

        if (${requestScope.successSub == 1}) {
            alert("提交成功!")
        }
    </script>
</head>
<body>

<div class="headerLine">班费管理系统</div>

<div class="container">

    <div class="row">
        <div class="col-sm-1">
            <button onclick="submitAll()" class="btn btn-default">提交所选项</button>
<%--            <a href="studentServlet?action=getStudents&pageNo=${requestScope.page.pageNo}&class_id=${requestScope.classInfo.classId}"><button class="btn btn-success">添加</button></a>--%>
        </div>

        <div class="col-sm-6 col-sm-offset-1">
<%--            <button onclick="submitAll()" class="btn btn-default">提交所选项</button>--%>
            <a href="studentServlet?action=getStudents&pageNo=${requestScope.page.pageNo}&class_id=${requestScope.classInfo.classId}"><button class="btn btn-success">添加</button></a>
        </div>

        <div class="col-sm-4">
            <form action="moneyServlet" method="post">
                <input type="hidden" name="action" value="queryStuMoneyInfo">
                <div class="row">
                    <div class="col-sm-9">
                        <select name="student_name" class="form-control">
                            <c:forEach items="${requestScope.students}" var="student">
                                <option value="${student.studentName}">${student.studentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-3">
                        <input type="submit" class="btn btn-primary" value="查询">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<br>

<table class="table table-hover">
    <thead>
    <tr>
        <th class="text-center">
            <label for="select_all">
                <input type="checkbox" id="select_all">
            </label>
        </th>
        <th style="display: none">moneyId</th>
        <th style="display: none">studentId</th>
        <th class="text-center">学生姓名</th>
        <%-- <th>学生学号</th> --%>
        <th class="text-center">缴费金额</th>
        <th class="text-center">缴费时间</th>
        <th class="text-center">缴费用途</th>
        <th class="text-center">是否提交缴费</th>
        <th class="text-center">操作</th>
    </tr>
    </thead>

    <tbody>

    <c:forEach items="${requestScope.students}" var="student">
        <c:forEach items="${requestScope.page.items}" var="money">
            <c:if test="${student.studentId == money.studentId}">
                <%-- 已提交显示为绿色 --%>
                <c:if test="${money.moneyState == 1}">
                    <tr class="success text-center">
                        <td>
                            <label>
                                <input type="checkbox" name="one_box">
                            </label>
                        </td>
                        <td style="display: none">${money.moneyId}</td>
                        <td style="display: none">${student.studentId}</td>
                        <td>${student.studentName}</td>
                            <%-- <td>${student.studentNo}</td> --%>
                        <td>${money.moneyCount}</td>
                        <td>${money.moneyTime}</td>
                        <td>${empty money.moneyUse ? "收入" : money.moneyUse}</td>
                        <td>${money.moneyState == 1 ? "已提交" : "未提交"}</td>
                        <td>
                            <a href="moneyServlet?action=getMoneyInfo&pageNo=${requestScope.page.pageNo}&class_id=${student.classId}&student_id=${student.studentId}&student_name=${student.studentName}&money_id=${money.moneyId}&money_count=${money.moneyCount}"><button class="btn btn-info btn-sm">修改</button></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="moneyServlet?action=delete&pageNo=${requestScope.page.pageNo}&student_id=${student.studentId}&money_id=${money.moneyId}" onclick="return deleteElem(this)"><button class="btn btn-danger btn-sm">删除</button></a>
                        </td>
                    </tr>
                </c:if>

                <%-- 未提交显示为红色 --%>
                <c:if test="${money.moneyState == 0}">
                    <tr class="danger text-center">
                        <td>
                            <label>
                                <input type="checkbox" name="one_box">
                            </label>
                        </td>
                        <td style="display: none">${money.moneyId}</td>
                        <td style="display: none">${student.studentId}</td>
                        <td>${student.studentName}</td>
                            <%-- <td>${student.studentNo}</td> --%>
                        <td>${money.moneyCount}</td>
                        <td>${money.moneyTime}</td>
                        <td>${empty money.moneyUse ? "收入" : money.moneyUse}</td>
                        <td>${money.moneyState == 1 ? "已提交" : "未提交"}</td>
                        <td>
                            <a href="moneyServlet?action=getMoneyInfo&pageNo=${requestScope.page.pageNo}&class_id=${student.classId}&student_id=${student.studentId}&student_name=${student.studentName}&money_id=${money.moneyId}&money_count=${money.moneyCount}"><button class="btn btn-info btn-sm">修改</button></a>
                            &nbsp;&nbsp;&nbsp;
                            <a href="moneyServlet?action=delete&pageNo=${requestScope.page.pageNo}&student_id=${student.studentId}&money_id=${money.moneyId}" onclick="return deleteElem(this)"><button class="btn btn-danger btn-sm">删除</button></a>
                        </td>
                    </tr>
                </c:if>
            </c:if>
        </c:forEach>
    </c:forEach>

    <tr>
        <td class="info text-center" colspan="7">${requestScope.classInfo.className}班级总班费: ${requestScope.classInfo.classMoney > 0 ? requestScope.classInfo.classMoney : 0}, 总收入: ${requestScope.revenue}, 总支出: ${requestScope.expenditure}</td>
    </tr>
    </tbody>

</table>

<div class="text-center">
    <%@include file="/pages/common/page_nav.jsp" %>
</div>

<br><br>
<div class="container">

    <div class="row">

        <div class="col-sm-1 col-sm-offset-10">
            <c:if test="${not empty sessionScope.student}">
                <a href="pages/student/stu_welcome.jsp"><button class="btn btn-default">返回</button></a>
            </c:if>

            <c:if test="${empty sessionScope.student}">
                <a href="adminServlet?action=getEveryClass"><button class="btn btn-default">返回</button></a>
            </c:if>
        </div>
        <div class="col-sm-1"><a class="btn btn-primary" href="adminServlet?action=clean">返回首页</a></div>
    </div>
</div>

</body>
</html>
