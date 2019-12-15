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
	<h1>学生列表</h1>
	<hr />
	<%
		StudentDao dao=new StudentDao();
		String stuName=request.getParameter("stuName");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		int schoolId =Integer.parseInt(request.getParameter("schoolId"));
		
		StudentInfo stu=new StudentInfo();
		stu.setStuName(stuName);
		stu.setPassword(password);
		stu.setGender(gender);
		stu.setSchoolId(schoolId);
		
		dao.addStudent(stu);
		
		List<StudentInfo> stuList=dao.getStudentList();
		request.setAttribute("stuList",stuList);
		
		List<StudentGrade> stuGradeList=dao.getStudentGradeList();
		request.setAttribute("stuGradeList",stuGradeList);
	%>
	
	<table>
		<tr>
			<th>id</th>
			<th>名字</th>
			<th>性别</th>
			<th>学校(来自school表)</th>
			<th>学校地址(来自school表)</th>
			<th>学校等级(来自level_info表)</th>
			<th>等级备注(来自level_info表)</th>
		</tr>
		
		<c:forEach var="stu" items ="${stuList }">
			<tr>
				<td>${stu.id }</td>
				<td>${stu.stuName }</td>
				<td>${stu.gender }</td>
				<td>${stu.schoolName} </td>
				<td>${stu.address} </td>
				<td>${stu.levelName} </td>
				<td>${stu.note} </td>
			</tr>
		</c:forEach>
	</table>
	
     <h1>学生班级信息</h1>
     <hr />
    	<table>
		<tr>
			<th>id</th>
			<th>年级</th>
			<th>添加时间</th>
		</tr>
		
		<c:forEach var="stu" items ="${stuGradeList }">
			<tr>
				<td>${stu.student_Id }</td>
				<td>${stu.gradeName }</td>
				<td>${stu.addTime }</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>