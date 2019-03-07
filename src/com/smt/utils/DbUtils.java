package com.smt.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtils {
    //声明Connection对象
	private static Connection connection = null;
    //驱动程序名
	static String driver = "com.mysql.jdbc.Driver";
    //URL指向要访问的数据库名mydata
	static String url = "jdbc:mysql://localhost:3306/db_movie";
    //MySQL配置时的用户名
	static String user = "root";
    //MySQL配置时的密码
	static String password = "abc123";
    
    public static Connection getConn() {
    	try {
    		Class.forName(driver);
    		connection = DriverManager.getConnection(url, user, password);
    		if(connection!=null) {
//    			System.out.println("数据库连接成功");
    		}
    	} catch (Exception e) {
    		System.err.println("数据库连接失败");
		}
    	
    	return connection;
    }
    
    
    public static void main(String[] args) {
		DbUtils dbUtils = new DbUtils();
		dbUtils.getConn();
	}
    
    }
