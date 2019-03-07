package com.smt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smt.entity.MovieHall;
import com.smt.entity.User;
import com.smt.utils.DbUtils;

public class UserDao {

	//添加用户
	public Boolean insertUser(User user) {
		String sql = "insert into user(name,password,sex,tel)"
				+ " values(?,?,?,?)";
		Connection conn = DbUtils.getConn();
		PreparedStatement pstm;
		Boolean blnResult = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,user.getName());
			pstm.setString(2, user.getPassword());
			pstm.setInt(3, user.getSex());
			pstm.setString(4, user.getTel());
			Integer intResult = pstm.executeUpdate();
			if(1 == intResult)
				blnResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blnResult; 
	}
	
	
	//查询用户是否存在
	public User selectUserByNameAndPassword(String name,String password) {
		String sql = "select * from user where name='" + name + "' and password='" + password + "'";
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		User user = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getInt("sex"));
				user.setTel(rs.getString("tel"));
				user.setBalance(rs.getDouble("balance"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;	
	}
	
	
	//充值
	public Boolean updateBalance(Double balance,Integer userId) {
		String sql = "update user set balance=? where id=?";
		Connection conn = DbUtils.getConn();
		PreparedStatement pstm;
		Boolean blnResult = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setDouble(1, balance);
			pstm.setInt(2, userId);
			Integer intResult = pstm.executeUpdate();
			if(1 == intResult)
				blnResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blnResult; 
	}
	
	
	//修改信息
	public Boolean updateUserInfo(String name,String tel,Integer sex,Integer userId) {
		String sql = "update user set name=?,tel=?,sex=? where id=?";
		Connection conn = DbUtils.getConn();
		PreparedStatement pstm;
		Boolean blnResult = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,name);
			pstm.setString(2, tel);
			pstm.setInt(3, sex);
			pstm.setInt(4, userId);
			Integer intResult = pstm.executeUpdate();
			if(1 == intResult)
				blnResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blnResult; 
	}	
	
}
