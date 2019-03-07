package com.smt.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smt.entity.Cinema;
import com.smt.entity.Movie;
import com.smt.utils.DbUtils;

public class CinemaDao {
	
	//添加影院
	public Boolean insertCinema(Cinema cinema) {
		String sql = "insert into cinema(name,addr,city)"
				+ " values(?,?,?)";
		Connection conn = DbUtils.getConn();
		PreparedStatement pstm;
		Boolean blnResult = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, cinema.getName());
			pstm.setString(2, cinema.getAddr());
			pstm.setString(3, cinema.getCity());
			Integer intResult = pstm.executeUpdate();
			if(1 == intResult)
				blnResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blnResult; 
	}
	
	
	public List<Cinema> selectAllCinema(){
		String sql = "select * from cinema";
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		List<Cinema> cinemas = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			cinemas = new ArrayList<Cinema>();
			while (rs.next()) {
				Cinema temp = new Cinema();
				temp.setId(rs.getInt("id"));
				temp.setName(rs.getString("name"));
				temp.setAddr(rs.getString("addr"));
				temp.setCity(rs.getString("city"));
				cinemas.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cinemas;
	}
	
	
	
	//通过电影id列出影院
	public List<Cinema> selectCinemaByMovieId(Integer movieId){
		List<Cinema> cinemas = new ArrayList<Cinema>();
		String sql = "SELECT\r\n" + 
				"cinema.name,cinema.id,cinema.addr,cinema.city\r\n" + 
				"FROM\r\n" + 
				"    play\r\n" + 
				"    INNER JOIN movie\r\n" + 
				"        ON (play.movie_id = movie.id)\r\n" + 
				"    INNER JOIN cinema\r\n" + 
				"        ON (play.cinema_id = cinema.id) \r\n" + 
				"    INNER JOIN movie_hall\r\n" + 
				"	ON (cinema.id = movie_hall.cinema_id)\r\n" + 
				"    WHERE movie.id=" + movieId;
		
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		ResultSet rs = null;
			try {
				stm = conn.createStatement();
				 rs = stm.executeQuery(sql);
				 while (rs.next()) {
					Cinema cinema = new Cinema();
					cinema.setId(rs.getInt("id"));
					cinema.setName(rs.getString("name"));
					cinema.setAddr(rs.getString("addr"));
					cinema.setCity(rs.getString("city"));
					cinemas.add(cinema);
				}
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return cinemas;
		
	}
	
}
