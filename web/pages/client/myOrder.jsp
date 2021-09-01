<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp"%>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif" >
    <span class="wel_word">图书管理系统</span>

    <%-- 静态包含 manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp"%>


</div>

<div id="main">
    <table>
        <tr>
            <td>订单号</td>
            <td>时间</td>
            <td>总价</td>
            <td>状态</td>

            <td colspan="2">操作</td>
        </tr>

        <c:forEach items="${requestScope.orders}" var="order">
            <tr>
                <td>${order.order_id}</td>
                <td>${order.create_time}</td>
                <td>${order.price}</td>
                <c:if test="${order.status==0}">
                    <td>
                        未发货
                    </td>
                </c:if>
                <c:if test="${order.status==1}">
                    <td>
                        <a href="orderServlet?action=receiveOrder&orderId=${order.order_id}">确认收货</a>
                    </td>
                </c:if>
                <c:if test="${order.status==2}">
                    <td>
                        已收货
                    </td>
                </c:if>
                <td><a href="orderServlet?action=showOrderDetail&orderId=${order.order_id}">查看详情</a></td>

            </tr>
        </c:forEach>
    </table>


    <%--静态包含分页条--%>

</div>

<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>

</body>
</html>