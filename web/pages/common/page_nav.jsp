<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thaddeus
  Date: 2022/5/27
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="pagination">

    <%-- 当页码不是第一页时显示 "首页" 和 "上一页" --%>
    <c:if test="${requestScope.page.pageNo != 1}">
        <li><a href="${requestScope.page.url}&pageNo=1">首页</a></li>
        <li>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}">&larr; 上一页</a>
        </li>
    </c:if>
    <c:if test="${requestScope.page.pageNo == 1}">
        <li class="disabled"><a href="${requestScope.page.url}&pageNo=1" onclick="return false">首页</a></li>
        <li class="disabled">
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo - 1}" onclick="return false">&larr; 上一页</a>
        </li>
    </c:if>

    <%-- 页码最多 5 个 --%>
    <c:choose>
        <%-- 总页码 < 5, 输出所有 --%>
        <c:when test="${requestScope.page.pageTotalNo < 5}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotalNo}"/>
        </c:when>

        <%-- 总页码 >= 5 --%>
        <c:when test="${requestScope.page.pageTotalNo >= 5}">
            <c:choose>
                <%-- 当前页码为前三个, 页码范围是 1 --> 5 --%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>

                <%-- 当前页码为最后三个, 页码范围是 总页码 - 4 --> 总页码 --%>
                <c:when test="${requestScope.page.pageNo >= requestScope.page.pageTotalNo - 3}">
                    <c:set var="begin" value="${requestScope.page.pageTotalNo - 4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotalNo}"/>
                </c:when>

                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageNo - 2}"/>
                    <c:set var="end" value="${requestScope.page.pageNo + 2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>

    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="index">
        <c:if test="${requestScope.page.pageNo == index}">
            <li class="active"><a href="#" onclick="return false">${index}</a></li>
        </c:if>
        <c:if test="${requestScope.page.pageNo != index}">
            <li><a href="${requestScope.page.url}&pageNo=${index}">${index}</a></li>
        </c:if>
    </c:forEach>

    <%-- 当页码不是最后一页时显示 "下一页" 和 "末页" --%>
    <c:if test="${requestScope.page.pageNo != requestScope.page.pageTotalNo}">
        <li><a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页 &rarr;</a></li>
        <li><a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotalNo}">末页</a></li>
    </c:if>
    <c:if test="${requestScope.page.pageNo == requestScope.page.pageTotalNo}">
        <li class="disabled">
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}" onclick="return false">下一页 &rarr;</a>
        </li>
        <li class="disabled">
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotalNo}" onclick="return false">末页</a>
        </li>
    </c:if>

</ul>