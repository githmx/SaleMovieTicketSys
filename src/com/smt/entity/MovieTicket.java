package com.smt.entity;

public class MovieTicket {
	private Integer id;
	private Integer seatId;
	private Integer playId;
	private Integer userId;
	private Integer payPrice;
	private Integer status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSeatId() {
		return seatId;
	}
	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	public Integer getPlayId() {
		return playId;
	}
	public void setPlayId(Integer playId) {
		this.playId = playId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPayPrice() {
		return payPrice;
	}
	public void setPayPrice(Integer payPrice) {
		this.payPrice = payPrice;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public MovieTicket(Integer seatId, Integer playId, Integer userId, Integer payPrice, Integer status) {
		super();
		this.seatId = seatId;
		this.playId = playId;
		this.userId = userId;
		this.payPrice = payPrice;
		this.status = status;
	}
	
	public MovieTicket() {}
	@Override
	public String toString() {
		return "MovieTicket [id=" + id + ", seatId=" + seatId + ", playId=" + playId + ", userId=" + userId
				+ ", payPrice=" + payPrice + ", status=" + status + "]";
	}
	
	
	
}
