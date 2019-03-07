package com.smt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smt.entity.Comment;
import com.smt.utils.DbUtils;

public class CommentDao {

	
	//添加评论
	public Boolean insertComment(Comment comment) {
		String sql = "insert into comment(content,movie_id,timestamp,user_id)"
				+ " values(?,?,?,?)";
		Connection conn = DbUtils.getConn();
		PreparedStatement pstm;
		Boolean blnResult = false;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, comment.getContent());
			pstm.setInt(2, comment.getMovieId());
			pstm.setTimestamp(3, comment.getTimestamp());
			pstm.setInt(4, comment.getUserId());
			
			Integer intResult = pstm.executeUpdate();
			if(1 == intResult)
				blnResult = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blnResult; 
	}
	
	
	
	//评论列表
	public ResultSet selectAllCommentByMovieId(Integer movieId) {
		String sql = "SELECT\r\n" + 
				"movie.name AS movieName,user.name AS userName,comment.content AS content,comment.timestamp AS TIMESTAMP\r\n" + 
				"FROM\r\n" + 
				"    movie\r\n" + 
				"    INNER JOIN COMMENT \r\n" + 
				"        ON (movie.id = comment.movie_id)\r\n" + 
				"    INNER JOIN USER \r\n" + 
				"        ON (user.id = comment.user_id)\r\n" + 
				"    WHERE movie.id =" + movieId;
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
	
	
}
