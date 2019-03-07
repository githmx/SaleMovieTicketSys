package com.smt.entity;


//Ó°Ìü
public class MovieHall {
	private Integer id;
	private String name;
	private Integer cinemaId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getcinemaId() {
		return cinemaId;
	}
	public void setcinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}
	
	
	public MovieHall() {

	}
	
	public MovieHall(String name, Integer cinemaId) {
		super();
		this.name = name;
		this.cinemaId = cinemaId;
	}
	@Override
	public String toString() {
		return "movie_hall [id=" + id + ", name=" + name + ", cinemaId=" + cinemaId + "]";
	}
	
	
	
}
