package com.google.FoodOrder;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Customer extends User{
	private String address,phone;

	public Customer(String username, String password,String address,String phone) {
        super(username,password);
        this.address = address;
        this.phone = phone;

    }
	
	public void customerMenu(App app) {
		Scanner scanner=new Scanner(System.in);
		int choice;
		System.out.println("name: "+getUserName());
		System.out.println("address: "+getAddress());
		System.out.println("phone number: "+getPhone());
		System.out.println("-------------");
		System.out.println("1-order");
		System.out.println("2-setting menu");
		while(true) {
			try {
				choice=scanner.nextInt();
				while(choice <1 || choice > 2) {
					System.out.println("enter a valid number");
					choice =scanner.nextInt();
				}
				break;
			} catch (Exception e) {
				System.out.println("enter a number");
			}
		}
		switch (choice) {
		case 1:
			app.loadMenu();
			app.showMenu();
			app.takeOrder();
			break;

		case 2:
			settingsMenu();
			break;
		}
		
	}
	public int settingsMenu() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("1-change username");
		System.out.println("2-change password");
		System.out.println("3-change adress");
		System.out.println("4-change phone");
		int chose;
		while(true) {
			try {
				chose=scanner.nextInt();
				while(chose < 1 || chose >4) {
					System.out.println("enter a valid number");
				}
				break;
			} catch (InputMismatchException e) {
				System.out.println("enter a number");
				scanner.next();
			}
		}

		return chose;
		
	}
	
	public void settings() {
		Scanner scanner=new Scanner(System.in);
		String change;
		switch (settingsMenu()) {
		case 1:
			System.out.println("enter new username");
			change=scanner.next();
			setUserName(change);
			break;
		case 2:
			System.out.println("enter new password");
			change=scanner.next();
			setPassword(change);
			break;
			
		case 3:
			System.out.println("enter new adress");
			change=scanner.next();
			setAddress(change);
			break;
			
		case 4:
			System.out.println("enter new phone");
			change=scanner.next();
			setPhone(change);
			break;
			
		}

	}
	public void showInfo() {
		System.out.println("username: "+getUserName());
		System.out.println("username: "+getPassword());
		System.out.println("username: "+getAddress());
		System.out.println("username: "+getPhone());
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

 
}
