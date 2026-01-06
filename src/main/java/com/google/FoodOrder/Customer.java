package com.google.FoodOrder;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

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

			settings(app);
			break;
		}
		
	}
	public int settingsMenu() {

		Scanner scanner=new Scanner(System.in);
		System.out.println("0-back");
		System.out.println("1-change username");
		System.out.println("2-change password");
		System.out.println("3-change adress");
		System.out.println("4-change phone");
		int chose;
		while(true) {
			try {
				chose=scanner.nextInt();
				while(chose < 0 || chose >4) {
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

	
	public void settings(App app) {
		Scanner scanner=new Scanner(System.in);
		String change;
		String oldName=getUserName();
		switch (settingsMenu()) {
		case 0:
			customerMenu(app);
			break;
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
		updateUsers(oldName,app);
		

	}
	
	public void updateUsers(String oldName,App app) {
		try {
			CSVReader cReader=new CSVReader(new FileReader("users.csv"));
			List<String[]> users=cReader.readAll();
			cReader.close();
			for(String[] line:users) {
				if (line[0].equals(oldName)) {
					line[0] = getUserName();
					line[1] = getPassword();
	                line[2] = getAddress();
	                line[3] = getPhone();
					break;
					
				}
			}
			CSVWriter cWriter=new CSVWriter(new FileWriter("users.csv"));
			cWriter.writeAll(users);
			cWriter.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		showInfo(app);
		
	}
	public void showInfo(App app) {
		System.out.println("username: "+getUserName());
		System.out.println("password: "+getPassword());
		System.out.println("address: "+getAddress());
		System.out.println("phone: "+getPhone());
		customerMenu(app);

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
