package com.smt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smt.entity.MovieHall;
import com.smt.entity.Play;
import com.smt.entity.PlayAndHall;
import com.smt.utils.DbUtils;

public class PlayDao {
	
	//添加场次
	public Boolean insertPlay(Play play) {
		String sql = "insert into play(movie_id,play_time,price,hall_id,cinema_id)"
				+ " values(?,?,?,?,?)";
		Connection conn = DbUtils.getConn();
		PreparedStatement pstm;
		Boolean blnResult = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, play.getMovieId());
			pstm.setString(2, play.getPlayTime());
			pstm.setInt(3, play.getPrice());
			pstm.setInt(4, play.getHallId());
			pstm.setInt(5, play.getCinemaId());
			Integer intResult = pstm.executeUpdate();
			if(1 == intResult)
				blnResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blnResult; 
	}
	
	
	//查看场次
	public List<Play> selectAllPlayByCinemaId(Integer cinemaId){
		String sql = "select * from movie_hall where cinema_id='" + cinemaId + "'";
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		List<Play> plays = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			plays = new ArrayList<Play>();
			while (rs.next()) {
				Play temp = new Play();
				temp.setId(rs.getInt("id"));
				temp.setMovieId(rs.getInt("movie_id"));
				temp.setPlayTime(rs.getString("play_time"));
				temp.setPrice(rs.getInt("price"));
				temp.setHallId(rs.getInt("hall_id"));
				temp.setCinemaId(rs.getInt("cinema_id"));
				plays.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plays;	
	}
	
	//所有场次
	public ResultSet selectAllPlay() {
		String sql = "SELECT movie.name,cinema.name,movie_hall.name,play.play_time\r\n" + 
				"FROM\r\n" + 
				"    play\r\n" + 
				"    INNER JOIN movie \r\n" + 
				"        ON (play.movie_id = movie.id)\r\n" + 
				"    INNER JOIN `db_movie`.`movie_hall` \r\n" + 
				"        ON (play.hall_id = movie_hall.id)\r\n" + 
				"    INNER JOIN db_movie.cinema \r\n" + 
				"        ON (movie_hall.cinema_id = cinema.id)";
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		ResultSet rs = null;
			try {
				stm = conn.createStatement();
				 rs = stm.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rs;
	}
	
	
	
	//查询场次 通过movieid cinemaid
	public List<PlayAndHall> selectPlayAndHallNameByMovieIdAndCinemaId(Integer movieId,Integer cinemaId){
		List<PlayAndHall> playAndHalls = new ArrayList<PlayAndHall>();
		String sql = "SELECT\r\n" + 
				"play.id,movie_hall.name,play.play_time,play.price\r\n" + 
				"FROM\r\n" + 
				"    play\r\n" + 
				"    INNER JOIN movie\r\n" + 
				"        ON (play.movie_id = movie.id)\r\n" + 
				"    INNER JOIN cinema\r\n" + 
				"        ON (play.cinema_id = cinema.id) \r\n" + 
				"    INNER JOIN movie_hall\r\n" + 
				"	ON (cinema.id = movie_hall.cinema_id)\r\n" + 
				"    WHERE movie.id="+ movieId +" AND cinema.id=" + cinemaId;
		
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		ResultSet rs = null;
			try {
				stm = conn.createStatement();
				 rs = stm.executeQuery(sql);
				 while (rs.next()) {
					 PlayAndHall playAndHall = new PlayAndHall();
					playAndHall.setId(rs.getInt("id"));
					playAndHall.setName(rs.getString("name"));
					playAndHall.setPlayTime(rs.getString("play_time"));
					playAndHall.setPrice(rs.getInt("price"));
					playAndHalls.add(playAndHall);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return playAndHalls;
	}
	
}
