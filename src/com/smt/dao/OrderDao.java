package com.smt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.smt.entity.Movie;
import com.smt.entity.Order;
import com.smt.utils.DbUtils;

public class OrderDao {
	//查詢所有訂單
	public List<Order> selectAllOrder(){
		String sql = "SELECT\r\n" + 
				"movie_ticket.id AS ticketId,user.name AS userName,movie.id AS movieId,movie.name AS movieName,\r\n" + 
				"cinema.name AS cinemaName,movie_hall.name AS hallName,movie_ticket.seat_id AS seat,play.price AS price,\r\n" + 
				"play.play_time AS playTime,movie_ticket.status AS STATUS\r\n" + 
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
				"    WHERE user.id = 1;";
		Connection conn = DbUtils.getConn();
		Statement stm = null;
		List<Order> orders = null;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			orders = new ArrayList<Order>();
			while (rs.next()) {
				Order temp = new Order();
				temp.setTicketId(rs.getInt("ticketId"));
				temp.setUserName(rs.getString("userName"));
				temp.setMovieName(rs.getString("movieName"));
				temp.setCinemaName(rs.getString("cinemaName"));
				temp.setHallName(rs.getString("hallName"));
				temp.setSeat(rs.getInt("seat"));
				temp.setPlayTime(rs.getString("playTime"));
				temp.setPrice(rs.getDouble("price"));
				temp.setStatus(rs.getInt("status"));
				orders.add(temp);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}
	
}
