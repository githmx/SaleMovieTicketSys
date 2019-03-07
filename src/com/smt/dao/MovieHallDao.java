package com.smt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smt.entity.Cinema;
import com.smt.entity.MovieHall;
import com.smt.utils.DbUtils;

public class MovieHallDao {
	//添加影厅
	public Boolean insertMovieHall(MovieHall movieHall) {
		String sql = "insert into movie_hall(name,cinema_id)"
				+ " values(?,?)";
		Connection conn = DbUtils.getConn();
		PreparedStatement pstm;
		Boolean blnResult = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, movieHall.getName());
			pstm.setInt(2, movieHall.getcinemaId());
			Integer intResult = pstm.executeUpdate();
			if(1 == intResult)
				blnResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blnResult; 
	}
	
	//所有某影院下的影厅
	public List<MovieHall> selectAllMovieHall(Integer cinemaId){
		String sql = "select * from movie_hall where cinema_id='" + cinemaId + "'";
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		List<MovieHall> movieHalls = null;
		try {
			stm = conn.createStatement();
		
			ResultSet rs = stm.executeQuery(sql);
			movieHalls = new ArrayList<MovieHall>();
			while (rs.next()) {
				MovieHall temp = new MovieHall();
				temp.setId(rs.getInt("id"));
				temp.setName(rs.getString("name"));
				temp.setcinemaId(rs.getInt("cinema_id"));
				movieHalls.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieHalls;	
}
	
	
}
