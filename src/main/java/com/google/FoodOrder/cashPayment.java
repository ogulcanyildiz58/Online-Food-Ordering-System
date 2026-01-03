package com.google.FoodOrder;

import java.io.FileWriter;


public class cashPayment implements Payment {
	@Override
	public void pay(double amount,App app) {
		System.out.println(amount+" TL cash payment selected please pay upon delivery");
		clearCart(app);
	}
	public void clearCart(App app) {
		try {
			FileWriter fWriter=new FileWriter("cart.csv",false);
		fWriter.close();
		} catch (Exception e) {

		}

		app.showMenu();
		app.showCart();
		
		app.takeOrder();
		
	}

}
