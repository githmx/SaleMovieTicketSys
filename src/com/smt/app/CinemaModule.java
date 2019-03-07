package com.smt.app;

import java.util.List;
import java.util.Scanner;

import com.smt.dao.CinemaDao;
import com.smt.entity.Cinema;

public class CinemaModule {
	
	private static CinemaDao cinemaDao = new CinemaDao();
	private static Scanner scanner = new Scanner(System.in);
	
	//影院菜单
	public static void showCinemaMenu(){
		System.out.println("----------------------【影院管理】----------------------");
		String adminMenuStr =  "1.影院列表\n"
							+ "2.添加影院\n"
							+ "3.返回\n";
		
		System.out.println(adminMenuStr);
		System.out.print("请选择:");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			//影院列表
			listCinema();
			break;
		case 2:
			//添加影院
			addCinema();
			break;
		case 3:
			//返回
			MainApp.adminMenu();
			break;
		}
	}
	
	
	
	//添加影院
	public static void addCinema() {
		System.out.println("----------------------添加影院----------------------");
		System.out.println("请输入影院名:");
		String name = scanner.next();
		System.out.println("请输入地址:");
		String addr = scanner.next();
		System.out.println("请输入城市:");
		String city = scanner.next();
		Cinema cinema = new Cinema(name, addr, city);
		Boolean blnResult = cinemaDao.insertCinema(cinema);
		if(blnResult)
			System.out.println("添加成功");
		else
			System.out.println("添加失败");
		System.out.println("--------------------------------------------");
		String adminMenuStr =  "1.继续添加\n"
							 + "2.返回\n";
		System.out.println(adminMenuStr);
		System.out.print("请选择:");
		Integer intChoice = scanner.nextInt();
		switch (intChoice) {
		case 1:
			addCinema();
			break;
		case 2:
			showCinemaMenu();
			break;
		default:
			break;
		}
	
	}
	
	
	//影院列表
	public static void listCinema() {
		List<Cinema> cinemas = cinemaDao.selectAllCinema();
		System.out.println("----------------------影院列表----------------------");
		for (Cinema cinema : cinemas) {
			System.out.println("--------------------");
			System.out.println(cinema.getName());
			System.out.println("  " + cinema.getAddr());
			System.out.println("  " + cinema.getCity());
		}
		System.out.println("1.返回");
		System.out.print("请输入:");
		Integer intChoice = scanner.nextInt();
		if(1 == intChoice) {
			showCinemaMenu();
		}
		
	}
	
	
	//测试
	public static void main(String[] args) {
		showCinemaMenu();
	}
	
	
}
