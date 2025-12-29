package com.google.FoodOrder;

import java.io.FileWriter;
import java.util.Scanner;

public class creditCardPayment implements Payment{
	@Override 
	public void pay(double amount,App app) {
		System.out.println("credit card number: ");
		Scanner scanner=new Scanner(System.in);
		String num;
		while(true) {
			try {
				num=scanner.next();
				while(num.length()!=16) {
					System.out.println("Credit card number must be 16 digits.");
					num=scanner.next();
				}
				break;
			} catch (Exception e) {
				System.out.println("enter credit card number");
			}
		}
		System.out.println(amount + " TL is being charged to the credit card");
		clearCart(app);

	}
	public void clearCart(App app) {
		try {
			FileWriter fWriter=new FileWriter("cart.csv",false);
			fWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		app.showMenu();
	}

}
