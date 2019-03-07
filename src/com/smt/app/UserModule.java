package com.smt.app;

import java.util.Scanner;

import com.smt.dao.MovieDao;
import com.smt.dao.UserDao;
import com.smt.entity.Movie;
import com.smt.entity.MovieTicket;
import com.smt.entity.User;

public class UserModule {
	private static UserDao userDao = new UserDao();
	private static MovieDao movieDao = new MovieDao();
	private static Scanner scanner = new Scanner(System.in);
	public static Integer userId;
	public static User currentUser = new User();
	public static Double currentBalance;
	
	//用户注册
	public static void userRegister() {
		System.out.println("-------------------注册-----------------------");
		System.out.println("请输入账号:");
		String name = scanner.next();
		System.out.println("请输入密码:");
		String password = scanner.next();
		System.out.println("请再次输入密码:");
		String passwordagain = scanner.next();
		if(!password.equals(passwordagain)) {
			System.out.println("两次密码不一致，请重新输入");
			userRegister();
		}
		
		System.out.println("请输入性别(男or女):");
		Integer sex = scanner.next() == "男"?1:0;
		System.out.println("请输入电话:");
		String tel = scanner.next();
		
		User user = new User(name, password, sex, tel);
		boolean blnResult = userDao.insertUser(user);
		if(blnResult) {
			
			System.out.println("注册成功");
			System.out.println("1.登录");
			System.out.println("0.退出");
			Integer intChoice = scanner.nextInt();
			switch (intChoice) {
			case 1:
				//登录
				userLogin();
				break;
			case 0:
				System.exit(0);
				break;
			default:
				break;
			}
		}else {
			System.out.println("注册失败");
			System.out.println("1.重新注册");
			System.out.println("0.退出");
			Integer intChoice = scanner.nextInt();
			switch (intChoice) {
			case 1:
				userRegister();
				break;
			case 0:
				System.exit(0);
				break;
			default:
				break;
			}
		}
	}
	
	//用户登录
	public static void userLogin() {
		System.out.println("-------------------登录-----------------------");
		System.out.println("请输入账号:");
		String name = scanner.next().trim();
		System.out.println("请输入密码");
		String password = scanner.next().trim();
		User user = userDao.selectUserByNameAndPassword(name, password);
		if(null == user) {
			System.out.println("登录失败");
		}else {
			System.out.println("登录成功");
			userId = user.getId();
			currentUser = user;
			currentBalance = user.getBalance();
			showOnlineMainMenu();
		}
	}
	
	
	//登录后的首页
	public static void showOnlineMainMenu() {
		System.out.println("-------------------登录成功【首页】-----------------------");
		System.out.println("1.购票");
		System.out.println("2.电影查询");
		System.out.println("3.充值");
		System.out.println("4.我的电影票【评论】");
		System.out.println("5.个人信息");
		System.out.println("6.退出");
		System.out.print("请选择:");
		Integer intChoice = scanner.nextInt();
		switch (intChoice) {
		case 1:
			//购票
			MovieTicketModule.listMovie();
			break;
		case 2:
			//电影查询
			searchMovie();
			break;
		case 3:
			//充值
			MovieTicketModule.recharge();
			break;
		case 4:
			//我的电影票【评论】
			MovieTicketModule.userMovieTickets();
			break;
		case 5:
			//个人信息
			userInfo();
			break;
		case 6:
			//退出
			System.exit(0);
			break;
		default:
			break;
		}
		
		
	}
	
	
	
	
	
	//查询电影
	public static void searchMovie() {
		System.out.println("-------------------电影查询-----------------------");
		System.out.print("输入电影名称");
		System.out.println("\n\n0.返回");
		String movieName = scanner.next();
		System.out.println(!"0".equals(movieName));
		if(!"0".equals(movieName)) {
			Movie movie = movieDao.searchMovieByName(movieName);
//			System.out.println(movie);
			if(null == movie.getId()) {
				System.out.println("没有找到此电影。\r\n" + 
						"查询失败，重新进行操作：");
				searchMovie();
			}else {
				System.out.println("----------------------电影详情----------------------");
				System.out.println(movie.getName());
				System.out.println("演员:" + movie.getActors());
				System.out.println("时长:" + movie.getDuration() + "分钟");
				System.out.println("类型:" + movie.getType());
				System.out.println("上映时间:" + movie.getReleaseTime());
				System.out.println("评分:" + ((movie.getScore()==0)?"暂无评分":movie.getScore()));
				System.out.println("\n1.返回");
				searchMovie();
			}
		}else {
			showOnlineMainMenu();
		}
		
	}
	
	//用户信息
	public static void userInfo() {
		System.out.println("-------------------个人信息-----------------------");
		System.out.println("账号：" + currentUser.getName());
		System.out.println("电话：" + currentUser.getTel());
		System.out.println("性别：" + (currentUser.getSex()==1?"男":"女"));
		System.out.println("余额：" + currentBalance);
		
		System.out.println("\n1.修改");
		System.out.println("0.返回");	
		Integer choice = scanner.nextInt();
		if (1 == choice) {
			modifyUserInfo();
		}else {
			showOnlineMainMenu();
		}
		
	}
	
	//修改用户信息
	public static void modifyUserInfo() {
		System.out.println("请输入");
		System.out.println("账号：");
		String name = scanner.next();
		System.out.println("电话：");
		String tel = scanner.next();
		System.out.println("性别：");
		Integer sex = scanner.next()=="男"?1:0;
		Boolean blnResult = userDao.updateUserInfo(name, tel, sex, userId);
		if (blnResult) {
			System.out.println("修改成功");
			currentUser.setName(name);
			currentUser.setTel(tel);
			currentUser.setSex(sex);
		}
		
//		System.out.println("0.返回");
		showOnlineMainMenu();
	}
	
	
	
}
