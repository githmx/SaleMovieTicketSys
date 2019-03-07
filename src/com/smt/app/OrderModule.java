package com.smt.app;

import java.util.List;
import java.util.Scanner;

import com.smt.dao.OrderDao;
import com.smt.entity.Order;

//訂單
public class OrderModule {
	private static Scanner scanner = new Scanner(System.in);
	private static OrderDao orderDao = new OrderDao();
	
	
	//所有訂單
	public static void showAllOrders() {
		List<Order> orders = orderDao.selectAllOrder();
		Integer i = 1;
		System.out.println("------------------所有訂單-------------");
		for (Order order : orders) {
			System.out.print(i + ".");
			System.out.print(order.getUserName() + "  ");
			System.out.print(order.getMovieName() + "  ");
			System.out.print(order.getCinemaName() + "  ");
			System.out.print(order.getHallName() + "  ");
			System.out.print(order.getPlayTime() + "  ");
			System.out.print(order.getSeat()+ "號  ");
			System.out.println(order.getStatus()==1?"已支付":"未支付");
			i++;
		}
		
		System.out.println("0.返回");
		if(0 == scanner.nextInt()) {
			MainApp.adminMenu();
		}
	}
}
