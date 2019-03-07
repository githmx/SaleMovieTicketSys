package com.smt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smt.entity.MovieTicket;
import com.smt.entity.UserTicket;
import com.smt.utils.DbUtils;

//购买电影票
public class MovieTicketDao {

	//添加订单
	public Boolean insertMovieTicket(MovieTicket movieTicket) {
		String sql = "insert into movie_ticket(seat_id,play_id,user_id,pay_price)"
				+ " values(?,?,?,?)";
		Connection conn = DbUtils.getConn();
		PreparedStatement pstm;
		Boolean blnResult = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, movieTicket.getSeatId());
			pstm.setInt(2, movieTicket.getPlayId());
			pstm.setInt(3, movieTicket.getUserId());
			pstm.setInt(4, movieTicket.getPayPrice());
			Integer intResult = pstm.executeUpdate();
			if(1 == intResult)
				blnResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blnResult; 
	}
	
	
	//查看已选座位  通过场次id
	public List<Integer> selectAllSeatByPlayId(Integer playId){
		List<Integer> seats = new ArrayList<Integer>();
		String sql = "select seat_id from movie_ticket where play_id=" + playId;
		
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		ResultSet rs = null;
			try {
				stm = conn.createStatement();
				 rs = stm.executeQuery(sql);
				 while (rs.next()) {
					 seats.add(rs.getInt("seat_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return seats;
		
	}
	
	
	//用户的电影票
	public List<UserTicket> selectTicketByUserId(Integer userId){
		List<UserTicket> userTickets = new ArrayList<UserTicket>();
		String sql = "SELECT\r\n" + 
				"movie.id AS movieId,movie.name AS movieName,cinema.name AS cinemaName,movie_hall.name AS hallName,movie_ticket.seat_id AS seat,play.price AS price,play.play_time AS playTime\r\n" + 
				"FROM\r\n" + 
				"    USER\r\n" + 
				"    INNER JOIN movie_ticket \r\n" + 
				"        ON (user.id = movie_ticket.user_id)\r\n" + 
				"    INNER JOIN play \r\n" + 
				"        ON (movie_ticket.play_id = play.id)\r\n" + 
				"    INNER JOIN movie_hall \r\n" + 
				"        ON (play.hall_id = movie_hall.id)\r\n" + 
				"    INNER JOIN `movie` \r\n" + 
				"        ON (play.movie_id = movie.id)\r\n" + 
				"    INNER JOIN cinema \r\n" + 
				"        ON (movie_hall.cinema_id = cinema.id)\r\n" + 
				"    WHERE user.id =" + userId;
		
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		ResultSet rs = null;
			try {
				stm = conn.createStatement();
				 rs = stm.executeQuery(sql);
				 while (rs.next()) {
					 UserTicket ticket = new UserTicket();
					 ticket.setMovieId(rs.getInt("movieId"));
					 ticket.setMovieName(rs.getString("movieName"));
					 ticket.setCinemaName(rs.getString("cinemaName"));
					 ticket.setHallName(rs.getString("hallName"));
					 ticket.setSeat(rs.getInt("seat"));
					 ticket.setPlayTime(rs.getString("playTime"));
					 ticket.setPrice(rs.getDouble("price"));
					 userTickets.add(ticket);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return userTickets;
	}

}
