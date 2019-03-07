package com.smt.entity;


public class Play {
	private Integer id;
	private Integer movieId;
	private String playTime;
	private Integer price;
	private Integer hallId;
	private Integer cinemaId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getHallId() {
		return hallId;
	}
	public void setHallId(Integer hallId) {
		this.hallId = hallId;
	}
	public Integer getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}
	public Play() {
		super();
	}
	public Play(Integer movieId, String playTime, Integer price, Integer hallId, Integer cinemaId) {
		super();
		this.movieId = movieId;
		this.playTime = playTime;
		this.price = price;
		this.hallId = hallId;
		this.cinemaId = cinemaId;
	}
	@Override
	public String toString() {
		return "Play [id=" + id + ", movieId=" + movieId + ", playTime=" + playTime + ", price=" + price + ", hallId="
				+ hallId + ", cinemaId=" + cinemaId + "]";
	}

	
	
	
	
	
}
