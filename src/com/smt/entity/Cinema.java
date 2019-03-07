package com.smt.entity;

public class Cinema {
	private Integer id;
	private String name;
	private String addr;
	private String city;
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
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Cinema() {

	}
	public Cinema(String name, String addr, String city) {
		super();
		this.name = name;
		this.addr = addr;
		this.city = city;
	}
	@Override
	public String toString() {
		return "Cinema [id=" + id + ", name=" + name + ", addr=" + addr + ", city=" + city + "]";
	}
	
	
	
	
}
