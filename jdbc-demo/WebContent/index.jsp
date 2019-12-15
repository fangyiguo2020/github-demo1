<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> </title>

 <style>
 	ul li{
 		margin:10px;
 	}
 </style>
</head>

<body>

  <h1>学生管理系统</h1>
  <hr />
  
  <ul>
  	<li><a href="search_all_do.jsp">查看所有学生 (三表关联)</a></li>
  	<li><a href="search_female_do.jsp">查看所有女学生(查询视图)</a></li>
  	<li><a href="search_byid_do.jsp">根据id查询学生信息(调用存储过程)</a></li>
  	<li><a href="add_student.jsp">添加学生(触发器)</a></li>
  	<li><a href="rand_money_do.jsp">随机给学生分补助(游标)</a></li>
  	<li><a href="del_student_do.jsp">删除学生全部信息(事务)</a></li>
  	<li><a href="search_byname_do.jsp">根据名称查询(利用索引加快速度)</a></li>
  </ul>

</body>
</html>