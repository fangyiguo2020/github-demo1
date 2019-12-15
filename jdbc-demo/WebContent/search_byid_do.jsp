<%@ page language="java" import="com.beans.*,com.dao.*,java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
 <h1>根据ID 调用存诸过程查询</h1>
 <hr />
 
 <%
 	String id=request.getParameter("id");
 	if(id!=null){
 		StudentDao dao=new StudentDao();
		StudentInfo stu=dao.getStuNameById(Integer.parseInt(id));
		request.setAttribute("stu", stu);
 	}
 %>
 
 <form action="search_byid_do.jsp"  >
    学生id (整型): <input name="id" value="${param.id }" >
    <input type="submit" value="查询 " >
 </form>
 
 <hr />
 学生的名字是: <label>${ stu.stuName }</label>  <br />
 学生的性别是: <label>${ stu.gender }</label> <br />
 学生的密码是: <label>${ stu.password }</label> <br />
 学生的的ID是: <label>${ stu.id }</label>
  
</body>
</html>