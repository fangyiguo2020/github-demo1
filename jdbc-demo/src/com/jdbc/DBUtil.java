package com.jdbc;
import java.sql.*;

public class DBUtil {	
	//静态代码块,在类加载的时候执行,而且只执行一次
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	private static String url="jdbc:mysql://localhost:3306/stusystem?useUnicode=true&characterEncoding=utf-8";
	private static String user="root";
	private static String password="root";
	
	private DBUtil(){} 
	
	public static Connection getConn(){
	
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static void close(ResultSet rs,Statement stm,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}
