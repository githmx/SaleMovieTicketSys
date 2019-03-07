package com.smt.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.smt.dao.MovieDao;
import com.smt.entity.Movie;
import com.smt.utils.DateUtil;

//电影管理模块(界面)
public class MovieModule {
	
	private static MovieDao movieDao = new MovieDao();
	private static Scanner scanner = new Scanner(System.in);
	private static Integer currentPage = 1;
	private static Map<Integer, List<Movie>> pageTeam;
	/**
	 * 1.电影管理选项菜单
	 */
	public static void adminMovieMenu() {
		System.out.println("----------------------【后台首页】----------------------");
		String adminMenuStr =  "1.电影列表\n"
							+ "2.添加电影\n"
							+ "3.返回\n"

							+ "6.退出";
		
		System.out.println(adminMenuStr);
		System.out.print("请选择:");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			//电影列表
			listMovie();
			break;
		case 2:
			//添加电影
			addMovie();
			break;
		case 3:
			//返回
			MainApp.adminMenu();
			break;
		case 6:
			//退出
			System.exit(0);
		default:
			break;
		}
		
	}
	
	
	
	
	
	//添加电影
	public static void addMovie() {
		System.out.println("请输入电影名:");
		String name = scanner.next();
		System.out.println("请输入演员:");
		String actors = scanner.next();
		System.out.println("请输入类型:");
		String type = scanner.next();
		System.out.println("请输入时长:");
		String duration = scanner.next();
		System.out.println("请输入上映时间(如：2017-10-10):");
		String releaseTimeStr = scanner.next();
		Date releaseTime = DateUtil.str2date(releaseTimeStr);
		Movie movie = new Movie();
		movie.setName(name);
		movie.setActors(actors);
		movie.setType(type);
		movie.setDuration(Double.parseDouble(duration));
		movie.setReleaseTime(releaseTime);
		Boolean blnResult = movieDao.insertMovie(movie);
//		System.out.println(blnResult);
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
			addMovie();
			break;
		case 2:
			MainApp.adminMenu();
			break;
		default:
			break;
		}
	
	}
	
	//查看电影
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
		
		showMovieList(pageTeam);	
		
	}
	
	//显示电影列表
	private static void showMovieList(Map<Integer, List<Movie>> pageTeam) {
		Integer totalPage = pageTeam.size();
		System.out.println("----------------------电影列表----------------------");
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
			showMovieInfo(pageTeam.get(currentPage), choice);
		}
		
		switch (choice) {
		case 7:
			//上一页
			currentPage --;
			if(currentPage < 1) {
				System.out.println("没有上一页");
				currentPage ++;
				showMovieList(pageTeam);
			}else {
				showMovieList(pageTeam);
			}
			break;
		case 8:
			//下一页
			currentPage ++;
			if(currentPage > totalPage) {
				System.out.println("没有下一页");
				currentPage--;
				showMovieList(pageTeam);
			}else {
				showMovieList(pageTeam);
			}
			break;
		case 9:
			//返回
			adminMovieMenu();
			break;
		default:
			break;
		}
	}
	
	
	//显示电影详细信息
	private static void showMovieInfo(List<Movie> movieList,Integer index) {
		System.out.println("----------------------电影详情----------------------");
		Movie movie = movieList.get(index-1); 
		System.out.println(movie.getName());
		System.out.println("演员:" + movie.getActors());
		System.out.println("时长:" + movie.getDuration() + "分钟");
		System.out.println("类型:" + movie.getType());
		System.out.println("上映时间:" + movie.getReleaseTime());
		System.out.println("评分:" + ((movie.getScore()==0)?"暂无评分":movie.getScore()));
		System.out.println("\n1.返回");
		Integer intChoice = scanner.nextInt();
		if(1==intChoice) {
			showMovieList(pageTeam);
		}
	}
	
	
	

	
	
	//测试
//	public static void main(String[] args) {
//		MovieModule.listMovie();
//	}
//	
	
	
}
