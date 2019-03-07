package com.smt.app;

import java.util.Scanner;

public class MainApp {
	public static void main(String[] args) {
		MainApp.showMenu();
	}
	
	
	/**
	 * 1.启动界面
	 */
	private static void showMenu() {
		System.out.println("----------------------启动----------------------");
		String menuStr = "欢迎进入万达购票系统\n"
						+ "1.登录\n"
						+ "2.注册\n"
						+ "3.后台\n"
						+ "4.退出";
		System.out.println(menuStr);
		System.out.print("请选择:");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		
		switch (choice) {
		case 1:
			//登陆
			UserModule.userLogin();
			break;
		case 2:
			//注册
			UserModule.userRegister();
			break;
		case 3:
			//后台
			adminMenu();
			break;
		case 4:
			//退出
			System.exit(0);
			break;
		default:
			break;
		}
	}
	
	//后台首页
	public static void adminMenu() {
		System.out.println("----------------------【后台首页】----------------------");
		String adminMenuStr =  "1.电影管理\n"
							+ "2.影院管理\n"
							+ "3.影厅管理\n"
							+ "4.场次管理\n"
							+ "5.订单管理【影票】\n\n"
							+ "6.退出";
		
		System.out.println(adminMenuStr);
		System.out.print("请选择:");
		Scanner scanner = new Scanner(System.in);
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			//电影管理
			MovieModule.adminMovieMenu();
			break;
		case 2:
			//影院管理
			CinemaModule.showCinemaMenu();
			break;
		case 3:
			//影厅管理
			MovieHallModule.showMovieHallMenu();
			break;
		case 4:
			//场次管理
			PlaysModule.showPlayMenu();
			break;
		case 5:
			//订单管理【影票】
			OrderModule.showAllOrders();
			break;
			
		case 6:
			//退出
			System.exit(0);
		default:
			break;
		}
		
		
	}
	
	
	
	
	
	
	
	
}
