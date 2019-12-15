<%@ page language="java" import="com.beans.*,com.dao.*,java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
 <h1>删除某个学生的全部信息 事务</h1>
 <hr />
 
 <%
 	String id=request.getParameter("id");
 	if(id!=null){
 		StudentDao dao=new StudentDao();
		dao.delStudentById(Integer.parseInt(id));
		request.setAttribute("msg", "用户的基本信息,  年级信息, 补助信息删除成功!  ");
 	}
 %>
 
 <form action="del_student_do.jsp"  >
    学生id: <input name="id" value="${param.id }" >
    <input type="submit" value="删除 "  onclick="return confirm('确认要删除该学生的全部信息吗')">
 </form>
 
 <hr />
  ${msg }
  
</body>
</html>