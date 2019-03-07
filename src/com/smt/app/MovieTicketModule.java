package com.smt.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.smt.dao.CinemaDao;
import com.smt.dao.MovieDao;
import com.smt.dao.MovieHallDao;
import com.smt.dao.MovieTicketDao;
import com.smt.dao.PlayDao;
import com.smt.dao.UserDao;
import com.smt.entity.Cinema;
import com.smt.entity.Movie;
import com.smt.entity.MovieTicket;
import com.smt.entity.PlayAndHall;
import com.smt.entity.User;
import com.smt.entity.UserTicket;

//电影票模块 
public class MovieTicketModule {
	private static Scanner scanner = new Scanner(System.in);
	private static MovieTicketDao movieTicketDao = new MovieTicketDao();
	private static MovieDao movieDao = new MovieDao();
	private static CinemaDao cinemaDao = new CinemaDao();
	private static MovieHallDao movieHallDao = new MovieHallDao();
	private static PlayDao playDao = new PlayDao();
	private static UserDao userDao = new UserDao();
	
	private static Integer currentPage = 1;
	private static Map<Integer, List<Movie>> pageTeam;
	
	private static Integer movieId = 0;
	private static Integer cinemaId = 0;
	private static Integer playId = 0;
	
	private static String movieName = "";
	private static String cinemaName = "";
	private static String movieHallName = "";
	private static String playTime = "";
	private static Integer seat = 0;
	private static Integer price = 0;
	
	public static List<Movie> movieList = new ArrayList<>();
	public static Integer index;
	
	//电影列表
	public static void listMovie() {
		List<Movie> movies = movieDao.selectAllMovie();
//		Map<Integer, List<Movie>> pageTeam = new HashMap<>();     //分页
		pageTeam = new HashMap<>();
		int page = 1;
		List<Movie> tempList = new ArrayList<Movie>();
		for(int i=0;i<movies.size();i++) {
			tempList.add(movies.get(i));
			if((i+1)%5==0) {
				pageTeam.put(page, tempList);
				page++;
				tempList = new ArrayList<Movie>();
			}
		}
		pageTeam.put(page, tempList);	
		
		purchaseTicket(pageTeam);	
		
	}
	
	
	
	//购票
	private static void purchaseTicket(Map<Integer, List<Movie>> pageTeam) {
		Integer totalPage = pageTeam.size();
		System.out.println("-------------------电影-----------------------");
		System.out.println("请选择电影:");
		try {
			for(int i=0;i<5;i++) {
				System.out.println(i+1 + ":" + pageTeam.get(currentPage).get(i).getName());
			}
		} catch (Exception e) {
		}
		
		String adminMenuStr =  "\n7.上一页\n"
							+ "8.下一页\n"
							+ "9.返回\n";
		System.out.println(adminMenuStr);
		System.out.print("请选择:");
		int choice = scanner.nextInt();
		if(choice > 0 && choice < 6) {
			movieList = pageTeam.get(currentPage);
			index = choice;
			showMovieInfo(pageTeam.get(currentPage), choice);
		}
		
		switch (choice) {
		case 7:
			//上一页
			currentPage --;
			if(currentPage < 1) {
				System.out.println("没有上一页");
				currentPage ++;
				purchaseTicket(pageTeam);
			}else {
				purchaseTicket(pageTeam);
			}
			break;
		case 8:
			//下一页
			currentPage ++;
			if(currentPage > totalPage) {
				System.out.println("没有下一页");
				currentPage--;
				purchaseTicket(pageTeam);
			}else {
				purchaseTicket(pageTeam);
			}
			break;
		case 9:
			//返回
			UserModule.showOnlineMainMenu();
			break;
		default:
			break;
		}
	}	
	
	
	//显示电影详细信息
	public static void showMovieInfo(List<Movie> movieList,Integer index) {
		System.out.println("----------------------电影详情----------------------");
		Movie movie = movieList.get(index-1); 
		System.out.println(movie.getName());
		System.out.println("演员:" + movie.getActors());
		System.out.println("时长:" + movie.getDuration() + "分钟");
		System.out.println("类型:" + movie.getType());
		System.out.println("上映时间:" + movie.getReleaseTime());
		System.out.println("评分:" + ((movie.getScore()==0)?"暂无评分":movie.getScore()));
		
		System.out.println("\n1.购票");
		System.out.println("2.查看评论");
		System.out.println("0.返回");
		Integer intChoice = scanner.nextInt();
		switch (intChoice) {
		case 1:
			//购票
			movieId = movie.getId();
			movieName = movie.getName();
			choiceCinemaByMovieId(movieId);
			break;
		case 2:
			//查看评论
			movieId = movie.getId();
			movieName = movie.getName();
			System.out.println(movieName);
			CommentModule.listAllComment(movieId);
			break;
		case 0:
			
			purchaseTicket(pageTeam);
			currentPage = 1;
			break;
		default:
			break;
		}
	}
	
	
	//选择影院
	public static void choiceCinemaByMovieId(Integer movieId) {
		List<Cinema> cinemas = cinemaDao.selectCinemaByMovieId(movieId);
		
		Integer i = 1; 
		for (Cinema cinema : cinemas) {
			System.out.print(i + ".");
			System.out.println(cinema.getName());
			System.out.println("  " + cinema.getAddr());
			i++;
		}
		
		System.out.println("0.返回");
		System.out.print("请选择:");
		Integer intChoice = scanner.nextInt();
		if(intChoice>=1) {
			Cinema currentCinema = cinemas.get(intChoice-1);
			cinemaId = currentCinema.getId();
			cinemaName = currentCinema.getName();
			System.out.print("当前影院:");
			System.out.print(currentCinema.getName() + "--");
			System.out.print(currentCinema.getAddr() + "--");
			System.out.print(currentCinema.getCity() + "\n");
			//选择场次
			choicePlayByMovieIdAndCinemaId();
		}else {
			listMovie();
		}
		
	}
	
	
	
	//选择场次
	public static void choicePlayByMovieIdAndCinemaId() {
		List<PlayAndHall> playAndHalls = playDao.selectPlayAndHallNameByMovieIdAndCinemaId(movieId, cinemaId);
		System.out.println("-------------------场次选择-----------------------");
		Integer i = 1;
		for (PlayAndHall playAndHall : playAndHalls) {
			System.out.print(i + ".");
			System.out.print(playAndHall.getName() + "---");
			System.out.print(playAndHall.getPlayTime() + "---");
			System.out.print(playAndHall.getPrice() + " RMB");
			i++;
		}
		
		System.out.println("\n0.返回");
		System.out.print("请选择:");
		Integer intChoice = scanner.nextInt();
		if(intChoice>=1) {
			PlayAndHall playAndHall = playAndHalls.get(intChoice-1);
			playId = playAndHall.getId();
			movieHallName = playAndHall.getName();
			playTime = playAndHall.getPlayTime();
			price = playAndHall.getPrice();
			//座位
			choiceSeat();
		}else {
			
			choiceCinemaByMovieId(movieId);
		}
		
		
		
	}
	
	
	//选择座位
	public static void choiceSeat() {
		List<Integer> selectedSeats = movieTicketDao.selectAllSeatByPlayId(playId); 
		System.out.println("-------------------座位选择-----------------------");
		System.out.println("        ===========大屏幕===========");
		for(int i = 1 ; i< 10; i++) {
			System.out.print(" ");
			for(int j = 1; j < 8; j++) {
				System.out.print(" ");
				if(selectedSeats.contains(i*10 + j)) {
					System.out.print("[xx] ");
				}else {
					
					System.out.print("[" + i + j + "] ");
				}
			}
			System.out.println();
		}
		
		
		System.out.println("\n直接输入座位号选择或\n0.返回");
		System.out.println("\n请选择:");
		Integer intChoice = scanner.nextInt();
		if(0 == intChoice) {
			
			choicePlayByMovieIdAndCinemaId();
		}else {
			//生成电影票
			seat = intChoice;
			genMovieTicket();
		}
		
	}
	
	//生成电影票
	public static void genMovieTicket() {
		System.out.println("-------------------电影票-----------------------");
		System.out.println("电影:" + movieName);
		System.out.println("影院:" + cinemaName);
		System.out.println("影厅:" + movieHallName);
		System.out.println("场次:" + playTime);
		System.out.println("座位:" + seat);
		System.out.println("价格:" + price + "RMB");
		
		
		System.out.println("1.购买--【yes：完成，no：提示余额不足】");
		System.out.println("2.充值");
		System.out.println("0.返回");
		Integer intChoice = scanner.nextInt();
		switch (intChoice) {
		case 1:
			//购买
			order();
			break;
		case 2:
			//充值
			recharge();
			break;
		case 0:
			//返回
			choiceSeat();
			break;
		default:
			break;
		}
		
		
		
		
	}
	
	//购买
	public static void order() {
		//余额
		Double  balance = UserModule.currentBalance;
		if(balance < price) {
			//充值
			System.out.println("余额不足");
			System.out.println("1.充值");
			System.out.println("0.退出");
			Integer choice = scanner.nextInt();
			if (1 == choice) {
				//充值
				recharge();
				
			}else {
				System.out.println("退出成功");
				System.exit(0);;
			}
		}else {
			//下单
			MovieTicket movieTicket = new MovieTicket(seat, playId, UserModule.userId, price, 1);
			Boolean blnResult = movieTicketDao.insertMovieTicket(movieTicket);
			if(blnResult) {
				System.out.println("购买成功");
				UserModule.currentBalance -= price;
				userDao.updateBalance(UserModule.currentBalance, UserModule.userId);
			}else
				System.out.println("购买失败");
			System.out.println("--------------------------------------------");
			String adminMenuStr =  "1.继续购买\n"
								 + "2.返回\n";
			System.out.println(adminMenuStr);
			System.out.print("请选择:");
			Integer intChoice = scanner.nextInt();
			switch (intChoice) {
			case 1:
				choiceSeat();
				break;
			case 2:
				UserModule.showOnlineMainMenu();
				break;
			default:
				break;
				}
			
			
		}
		

	}
	
	//充值
	public static void recharge() {
		System.out.println("-------------------充值-----------------------");
		
		System.out.print("请输入充值的金额(RMB):");
		Double balance = scanner.nextDouble();
		UserModule.currentBalance += balance;
		
		Boolean blnResult = userDao.updateBalance(UserModule.currentBalance, UserModule.userId);
		if (blnResult) {
			System.out.println("---充值成功-----");
			System.out.println("当前余额:" + UserModule.currentBalance + "元");
		}
		System.out.println("0.返回首页");
		Integer  choice = scanner.nextInt();
		if(0 == choice) {
			UserModule.showOnlineMainMenu();
		}
		
	}
	
	
	//我的电影票
	public static void userMovieTickets() {
		System.out.println("-------------------我的电影票-----------------------");
		List<UserTicket> userTickets = movieTicketDao.selectTicketByUserId(UserModule.userId);
		Integer i = 1;
		for (UserTicket userTicket : userTickets) {
			System.out.println(i + ".电影：" + userTicket.getMovieName());
			System.out.println("  影院：" + userTicket.getCinemaName());
			System.out.println("  影厅：" + userTicket.getHallName());
			System.out.println("  座位：" + userTicket.getSeat());
			System.out.println("  价格："+ userTicket.getPrice());
			i++;
		}
		System.out.println("请选择电影票添加评论：");
		System.out.println("0.返回");
		Integer intChoice = scanner.nextInt();
		if(0 != intChoice) {
			//添加评论
//			System.out.println("请输入评论：");

			CommentModule.addComment(userTickets.get(intChoice-1).getMovieId(), UserModule.userId);
			
		}else {
			UserModule.showOnlineMainMenu();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		listMovie();
//		choiceSeat();
	}
	
	
}
