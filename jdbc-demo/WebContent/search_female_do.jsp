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
		
		<h1>从视图中查询</h1>
		<hr />
			<%
				StudentDao dao=new StudentDao();
				List<StudentInfo> stuList=dao.getFemaleStudent();
				request.setAttribute("stuList",stuList);
			%>
			
		<table>
			<tr>
				<th>id</th>
				<th>名字</th>
				<th>性别</th>
				<th>学校ID</th>
				<th>密码</th>
			</tr>
			
			<c:forEach var="stu" items ="${stuList }">
				<tr>
					<td>${stu.id }</td>
					<td>${stu.stuName }</td>
					<td>${stu.gender }</td>
					<td>${stu.schoolId} </td>
					<td>${stu.password} </td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>