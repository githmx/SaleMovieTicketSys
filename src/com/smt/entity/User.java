package com.smt.entity;

public class User {
	private Integer id;
	private String name;
	private String password;
	private Integer sex;
	private String tel;
	private Double  balance;
	
	
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public User() {

	}
	
	public User(String name, String password, Integer sex, String tel) {
		super();
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", tel=" + tel + "]";
	}
	
	
	
}
