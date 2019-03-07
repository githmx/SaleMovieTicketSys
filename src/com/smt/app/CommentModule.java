package com.smt.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

import com.smt.dao.CommentDao;
import com.smt.entity.Comment;

//评论
public class CommentModule {
	private static Scanner scanner = new Scanner(System.in);
	private static CommentDao commentDao = new CommentDao();
	
	//添加评论
	public static void addComment(Integer movieId,Integer userId) {
		System.out.println("请输入评论内容：");
		String content = scanner.next();
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setMovieId(movieId);
		comment.setUserId(userId);
		comment.setTimestamp(new Timestamp(System.currentTimeMillis()));
		Boolean blnResult = commentDao.insertComment(comment);
		if(blnResult) {
			System.out.println("添加成功");
		}else {
			System.out.println("添加失败");
		}
	
		System.out.println("0.返回");
		Integer intchoice = scanner.nextInt();
		if(0 == intchoice) {
			MovieTicketModule.userMovieTickets();
		}
	}
	
	
	
	//查看评论
	public static void listAllComment(Integer movieId) {
		ResultSet rs = commentDao.selectAllCommentByMovieId(movieId);
		try {
			if(!rs.wasNull()) {

				Integer i = 1;
				while (rs.next()) {
					System.out.print(rs.getString("userName") + "|");
					System.out.print(rs.getString("content") + "|");
					System.out.println(rs.getTimestamp("timestamp"));
					i++;
				}
			}else {
				System.out.println("----暂无评论----");
			}
			System.out.println("0.返回电影详情");
			Integer intChoice = scanner.nextInt();
			if(0 == intChoice) {
				MovieTicketModule.showMovieInfo(MovieTicketModule.movieList, MovieTicketModule.index);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
