<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>订单详情</title>

	<%-- 静态包含 base标签、css样式、jQuery文件 --%>
	<%@ include file="/pages/common/head.jsp"%>


</head>
<body>
<div id="header">
	<img class="logo_img" alt="" src="static/img/logo.gif" >
	<span class="wel_word">购物车</span>

	<%--静态包含，登录 成功之后的菜单 --%>
	<%@ include file="/pages/common/login_success_menu.jsp"%>
</div>

<div id="main">

	<table>
		<tr>
			<td>商品名称</td>
			<td>数量</td>
			<td>单价</td>
			<td>金额</td>
		</tr>



			<c:forEach items="${requestScope.orderItems}" var="orderItem">
				<tr>
					<td>${orderItem.name}</td>
					<td>${orderItem.count}</td>
					<td>${orderItem.price}</td>
					<td>${orderItem.totalPrice}</td>
				</tr>
			</c:forEach>
	</table>
	<%--如果购物车非空才输出页面的内容--%>

		<div class="cart_info">
			<span class="cart_span">总金额<span class="b_price">${requestScope.order.price}</span>元</span>
		</div>

</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>


</body>
</html>