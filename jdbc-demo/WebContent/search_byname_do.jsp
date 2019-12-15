<%@ page language="java" import="com.beans.*,com.dao.*,java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
 <h1>根据名称(索引列) 进行查询</h1>
 <hr />
 
 <%
   request.setCharacterEncoding("utf-8");
   long begin=System.currentTimeMillis();
   
 	String stuName=request.getParameter("stuName");
 	if(stuName!=null){
 		StudentDao dao=new StudentDao();
		StudentInfo stu=dao.getStudentByName(stuName);
		request.setAttribute("stu", stu);
 	}
 	
 	long end=System.currentTimeMillis();
 	
 	request.setAttribute("time", end-begin);
 %>
 
 <form action="search_byname_do.jsp" method="post" >
    学生账号(字符串): <input name="stuName" value="${stu.stuName }" >
    <input type="submit" value="查询 " >
 </form>
 
 <hr />
 学生的的ID是: <label>${ stu.id }</label> <br />
 学生的名字是: <label>${ stu.stuName }</label>  <br />
 学生的性别是: <label>${ stu.gender }</label> <br />
 学生的密码是: <label>${ stu.password }</label> <br />

 本次查询,一共用时 <label>${time} 毫秒</label> <br />
 
 <!-- 创建索引  alter table student add index index_name (stuname) -->
 
  
</body>
</html>