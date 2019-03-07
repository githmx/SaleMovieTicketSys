package com.smt.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.smt.dao.CinemaDao;
import com.smt.dao.MovieDao;
import com.smt.dao.MovieHallDao;
import com.smt.entity.Cinema;
import com.smt.entity.MovieHall;

public class MovieHallModule {
	private static MovieHallDao movieHallDao = new MovieHallDao();
	private static CinemaDao cinemaDao = new CinemaDao();
	private static Scanner scanner = new Scanner(System.in);
	private static Map<Integer, Cinema> cinemaIdMap = new HashMap<>();
	
	
	//影厅管理主页
	public static void showMovieHallMenu() {
		System.out.println("请选择影院");
		//获取影院列表
		List<Cinema> cinemas = cinemaDao.selectAllCinema();
		Integer i = 1;
		for (Cinema cinema : cinemas) {
			System.out.print(i + ".");
			System.out.print(cinema.getName() + "--");
			System.out.print(cinema.getAddr() + "--");
			System.out.print(cinema.getCity() + "\n");
			cinemaIdMap.put(i, cinema);
			i++;
		}
		System.out.println("0.返回");
		System.out.print("请选择:");
		Integer intChoice = scanner.nextInt();
		if(intChoice>=1) {
			Cinema currentCinema = cinemaIdMap.get(intChoice);
			System.out.print("当前影院:");
			System.out.print(currentCinema.getName() + "--");
			System.out.print(currentCinema.getAddr() + "--");
			System.out.print(currentCinema.getCity() + "\n");
			System.out.println("1.添加影厅");
			System.out.println("2.影厅列表");
			System.out.print("请选择:");
			Integer Choice = scanner.nextInt();
			switch (Choice) {
			case 1:
				addMovieHall(currentCinema.getId());
				break;
			case 2:
				listAllMovieHall(currentCinema.getId());
				break;
			default:
				break;
				}	
			
			
		}else {
			MainApp.adminMenu();
		}
		
	}
	
	//添加影厅
	public static void addMovieHall(Integer cinemaId) {
		System.out.print("请输入影厅名");
		String name = scanner.next();
		MovieHall movieHall = new MovieHall(name, cinemaId);
		Boolean blnResult = movieHallDao.insertMovieHall(movieHall);
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
			addMovieHall(cinemaId);
			break;
		case 2:
			MainApp.adminMenu();
			break;
		default:
			break;
			}
		
	}
	
	
	//查看影厅
	public static void listAllMovieHall(Integer cinemaId) {
		List<MovieHall> movieHalls = movieHallDao.selectAllMovieHall(cinemaId);
		Integer i = 1;
		for (MovieHall movieHall : movieHalls) {
			System.out.print(i + ".");
			System.out.print(movieHall.getName());
			i++;
		}
		
		System.out.println("\n\n0.返回");
		if(0==scanner.nextInt()) {
			MainApp.adminMenu();
		}
	}
	
	
	
	public static void main(String[] args) {
		showMovieHallMenu();

	}
	
	
}
