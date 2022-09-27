<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/26
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">

        // 全选按钮
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

        // 删除确认
        function deleteElem(node) {
            let parentNode = node.parentNode.parentNode;

            return confirm("你确定删除" + parentNode.children[3].innerHTML + "的这条缴费记录吗?该记录提交状态为(" +
                parentNode.children[7].innerHTML + ")");
        }

        // 提交已选项
        function submitAll() {
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

            if (index !== 0) {
                window.location.href = "moneyServlet?action=submitAllPersonal&moneyIdList=" + moneyIdList + "&studentId=" + elementsByName[0].parentNode.parentNode.parentNode.children[2].innerHTML;
            }
        }

        if (${not empty requestScope.errorMsg}) {
            alert("提交失败");
        }

        if (${requestScope.successSub == 1}) {
            alert("提交成功!");
        }
    </script>
</head>
<body>

<div class="headerLine">班费管理系统</div>

<div class="container">

    <div class="row">
        <div class="col-sm-1">
            <button class="btn btn-default" onclick="submitAll()">提交所选项</button>
<%--            <a href="pages/money/personal_edit_money.jsp?student_id=${requestScope.student.studentId}" class="btn btn-success">添加</a>--%>
        </div>

        <div class="col-sm-6 col-sm-offset-1">
            <a href="pages/money/personal_edit_money.jsp?student_name=${requestScope.student.studentName}&student_id=${requestScope.student.studentId}" class="btn btn-success">添加</a>
<%--            <button class="btn btn-default" onclick="submitAll()">提交所选项</button>--%>
        </div>

    </div>
</div>
<br>

<table class="table table-hover table-condensed">
    <caption class="text-center">学生${requestScope.student.studentName}的缴费记录</caption>
    <thead>
    <tr>
        <th class="text-center">
            <label for="select_all">
                <input type="checkbox" id="select_all">
            </label>
        </th>
        <th style="display: none">moneyId</th>
        <th style="display: none">studentId</th>
        <th style="display: none">studentName</th>
        <%-- <th>学生学号</th> --%>
        <th class="text-center">缴费金额</th>
        <th class="text-center">缴费时间</th>
        <th class="text-center">缴费用途</th>
        <th class="text-center">是否提交缴费</th>
        <th class="text-center">操作</th>
    </tr>
    </thead>

    <tbody>

    <c:forEach items="${requestScope.monies}" var="money">

        <%-- 已提交显示为绿色 --%>
        <c:if test="${money.moneyState == 1}">
            <tr class="success text-center">
                <td>
                    <label>
                        <input type="checkbox" name="one_box">
                    </label>
                </td>
                <td style="display: none">${money.moneyId}</td>
                <td style="display: none">${requestScope.student.studentId}</td>
                <td style="display: none">${requestScope.student.studentName}</td>
                    <%-- <td>${student.studentNo}</td> --%>
                <td>${money.moneyCount}</td>
                <td>${money.moneyTime}</td>
                <td>${empty money.moneyUse ? "收入" : money.moneyUse}</td>
                <td>${money.moneyState == 1 ? "已提交" : "未提交"}</td>
                <td>
                    <a href="moneyServlet?action=getPersonalMoneyInfo&student_id=${requestScope.student.studentId}&student_name=${requestScope.student.studentName}&money_id=${money.moneyId}&money_count_before=${money.moneyCount}"><button class="btn btn-info btn-sm">修改</button></a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="moneyServlet?action=deletePersonal&student_id=${requestScope.student.studentId}&money_id=${money.moneyId}" onclick="return deleteElem(this)"><button class="btn btn-danger btn-sm">删除</button></a>
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
                <td style="display: none">${requestScope.student.studentId}</td>
                <td style="display: none">${requestScope.student.studentName}</td>
                    <%-- <td>${student.studentNo}</td> --%>
                <td>${money.moneyCount}</td>
                <td>${money.moneyTime}</td>
                <td>${empty money.moneyUse ? "收入" : money.moneyUse}</td>
                <td>${money.moneyState == 1 ? "已提交" : "未提交"}</td>
                <td>
                    <a href="moneyServlet?action=getPersonalMoneyInfo&student_id=${requestScope.student.studentId}&student_name=${requestScope.student.studentName}&money_id=${money.moneyId}"><button class="btn btn-info btn-sm">修改</button></a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="moneyServlet?action=deletePersonal&student_id=${requestScope.student.studentId}&money_id=${money.moneyId}" onclick="return deleteElem(this)"><button class="btn btn-danger btn-sm">删除</button></a>
                </td>
            </tr>
        </c:if>

    </c:forEach>
    </tbody>

</table>

<br><br>
<div class="container">
    <div class="row">
        <div class="col-sm-1 col-sm-offset-11">
            <a href="moneyServlet?action=complete&class_id=${requestScope.student.classId}"><button class="btn btn-success ">完成</button></a>
        </div>
    </div>
</div>
<br>

</body>
</html>
