package com.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.StudentGrade;
import com.beans.StudentInfo;
import com.beans.StudentMoney;
import com.jdbc.DBUtil;


public class StudentDao {
	
	//查询所有学生,三表关联
	public List<StudentInfo> getStudentList(){
		List<StudentInfo> stuList=new ArrayList<StudentInfo>();
		
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConn();
			String sql="select a.id,a.stuname,a.gender , b.schoolName,b.address ,c.levelName,c.note from student a left join school b  on a.schoolId=b.id  left join level_info c on b.levelId=c.id";
			stm=conn.prepareStatement(sql);
			
			rs=stm.executeQuery();
			while(rs.next()) {
				StudentInfo stu=new StudentInfo();
				stu.setId(rs.getInt("id"));
				stu.setStuName(rs.getString("stuName"));
				stu.setGender(rs.getString("gender"));
				stu.setSchoolName(rs.getString("schoolName"));
				stu.setAddress(rs.getString("address"));
				stu.setLevelName(rs.getString("levelName"));
				stu.setNote(rs.getString("note"));
				
				stuList.add(stu);
			}
		} 
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			DBUtil.close(rs, stm, conn);
		}
		return stuList; 
	}

	 //查询所有女学生,从视图中查询
		public List<StudentInfo> getFemaleStudent(){
			List<StudentInfo> stuList=new ArrayList<StudentInfo>();
			
			Connection conn=null;
			PreparedStatement stm=null;
			ResultSet rs=null;
			try {
				conn=DBUtil.getConn();
				String sql="select * from v_student_nv";
				stm=conn.prepareStatement(sql);
				
				rs=stm.executeQuery();
				while(rs.next()) {
					StudentInfo stu=new StudentInfo();
					stu.setId(rs.getInt("id"));
					stu.setStuName(rs.getString("stuName"));
					stu.setGender(rs.getString("gender"));
					stu.setSchoolId(rs.getInt("schoolId"));
					stu.setPassword(rs.getString("password"));
					stuList.add(stu);
				}
			} 
			catch(Exception ex) {
				ex.printStackTrace();
			}
			finally {
				DBUtil.close(rs, stm, conn);
			}
			return stuList; 
		}
		
	  //根据id查询学生,调用存储过程
		public StudentInfo  getStuNameById(int id) {
			StudentInfo stu=null;
			Connection conn=null;
			CallableStatement stm=null;
			ResultSet rs=null;
			try {
				conn=DBUtil.getConn();
				stm=conn.prepareCall("{call proc_getname_byid(?)}");
				stm.setInt(1, id);  //给输入参数传值
				rs=stm.executeQuery();

			    if(rs.next()) {
			    	stu=new StudentInfo();
			    	stu.setId(rs.getInt("id"));
			    	stu.setStuName(rs.getString("stuName"));
			    	stu.setGender(rs.getString("gender"));
			    	stu.setPassword(rs.getString("password"));
			    }

			}	catch(Exception ex) {
				ex.printStackTrace();
			}
			finally {
				DBUtil.close(rs, stm, conn);
			}
			
			return stu;
		}
		
	  //添加学生(同时触发器会触发,往学生班级表添数据)
		public int addStudent(StudentInfo stu) {
			int result=0;
			Connection conn=null;
			PreparedStatement stm=null;
			try {
				conn=DBUtil.getConn();
				String sql="insert into student(stuName, password,gender,schoolId) values (?,?,?,?) ";
				stm=conn.prepareStatement(sql);
				stm.setString(1, stu.getStuName());
				stm.setString(2, stu.getPassword());
				stm.setString(3, stu.getGender());
				stm.setInt(4, stu.getSchoolId());
				result=stm.executeUpdate();
			}catch(Exception ex) {
				ex.printStackTrace();
			}finally {
				DBUtil.close(null, stm, conn);
			}
			return result;
			
		}
		
		//查询所有的学生年级信息
	   public List<StudentGrade> getStudentGradeList(){
		   List<StudentGrade> list=new ArrayList<StudentGrade>();
		   Connection conn=null;
			PreparedStatement stm=null;
			ResultSet rs=null;
			try {
				
				conn=DBUtil.getConn();
				String sql="select * from student_grade";
				stm=conn.prepareStatement(sql);
				
				rs=stm.executeQuery();
				while(rs.next()) {
					StudentGrade stu=new StudentGrade();
					stu.setStudent_Id(rs.getInt("student_Id"));
					stu.setGradeName(rs.getString("gradeName"));
					stu.setAddTime(rs.getString("addTime"));
					list.add(stu);
				}
			} 
			catch(Exception ex) {
				ex.printStackTrace();
			}
			finally {
				DBUtil.close(rs, stm, conn);
			}
		   return list;
	   }
		
	   
	 //查询所有的学生的补助信息
	   public List<StudentMoney> getStudetnMoneyList(){
		   List<StudentMoney> list=new ArrayList<StudentMoney>();
		   Connection conn=null;
			PreparedStatement stm=null;
			ResultSet rs=null;
			CallableStatement stmCall=null;
			try {
				conn=DBUtil.getConn();
				
				//先执行存储过程(内部用到了游标) ,生成每个学生的补助信息
				stmCall=conn.prepareCall("{call proc_stu_money()}") ;
				stmCall.execute();
						
				String sql="select * from student_money";
				stm=conn.prepareStatement(sql);
				
				rs=stm.executeQuery();
				while(rs.next()) {
					StudentMoney stu=new StudentMoney();
					stu.setStudentId(rs.getInt("studentId"));
					stu.setStudentName(rs.getString("studentName"));
					stu.setMoney(rs.getInt("money"));
					list.add(stu);
				}
			} 
			catch(Exception ex) {
				ex.printStackTrace();
			}
			finally {
				DBUtil.close(rs, stm, conn);
			}
		   return list;
	   }
		
	   
	   //根据id删除全部学生信息,有事务控制
		public void delStudentById(int id) {
			Connection conn=null;
			Statement stm=null;
			try {
				conn=DBUtil.getConn();
				conn.setAutoCommit(false);  //开启事务
				
				String sql1="delete from student_money where studentId=" +id;
				stm=conn.createStatement();
				stm.execute(sql1);

				String sql2="delete from student_grade where student_Id=" +id;
				stm.execute(sql2);
				
				String sql3="delete from student where id="+id;
				stm.execute(sql3);
				
				conn.commit(); //提交事务
			
			}catch(Exception ex) {
				try {
					conn.rollback(); //回滚事务
				} catch (SQLException e) {
					e.printStackTrace();
				} 
				ex.printStackTrace();
			}finally {
				DBUtil.close(null, stm, conn);
			}
		}
		
		 //根据名字查询学生信息
		public StudentInfo getStudentByName(String stuName) {
			StudentInfo stu=null;
			Connection conn=null;
			PreparedStatement stm=null;
			ResultSet rs=null;
			try {
				conn=DBUtil.getConn();
			
				String sql="select * from student where stuName=?";
				stm=conn.prepareStatement(sql);
				stm.setString(1, stuName);
				rs=stm.executeQuery();
				
			  if(rs.next()) {
			    	stu=new StudentInfo();
			    	stu.setId(rs.getInt("id"));
			    	stu.setStuName(rs.getString("stuName"));
			    	stu.setGender(rs.getString("gender"));
			    	stu.setPassword(rs.getString("password"));
				    }
				
			}catch(Exception ex) {
				try {
					conn.rollback(); //回滚事务
				} catch (SQLException e) {
					e.printStackTrace();
				} 
				ex.printStackTrace();
			}finally {
				DBUtil.close(null, stm, conn);
			}
			
			return stu;
			
		}
}
