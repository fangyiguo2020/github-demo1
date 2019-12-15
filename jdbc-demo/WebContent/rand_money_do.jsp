<%@ page language="java" import="com.beans.*,com.dao.*,java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	<body>
		
		<h1>随机分配补 游标的使用</h1>
		<hr />
			<%
				StudentDao dao=new StudentDao();
				List<StudentMoney> stuList=dao.getStudetnMoneyList();
				request.setAttribute("stuList",stuList);
			%>
			
		<table>
			<tr>
				<th>id</th>
				<th>名字</th>
				<th>补助金额</th>
			</tr>
			
			<c:forEach var="stu" items ="${stuList }">
				<tr>
					<td>${stu.studentId }</td>
					<td>${stu.studentName }</td>
					<td>${stu.money }</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>