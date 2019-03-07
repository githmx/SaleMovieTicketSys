package com.smt.entity;

import java.sql.Timestamp;

//ÆÀÂÛ
public class Comment {
	private Integer id;
	private String content;
	private Integer movieId;
	private Timestamp timestamp;
	private Integer userId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Comment() {
		super();
	}
	public Comment(String content, Integer movieId, Timestamp timestamp, Integer userId) {
		super();
		this.content = content;
		this.movieId = movieId;
		this.timestamp = timestamp;
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", movieId=" + movieId + ", timestamp=" + timestamp
				+ ", userId=" + userId + "]";
	}

	
	
	
}
