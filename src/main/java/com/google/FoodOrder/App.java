package com.google.FoodOrder;

import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class App {
	private List<MenuItem> menu = new ArrayList<>();
    private List<Customer> users=new ArrayList<>();
    
    public void run() {
    	loginMenu();
//    	loadUsers();
//    	singIn();
//    	singUp();

//    	loadMenu();
//    	showMenu();
//    	takeOrder();
    }
    
    public void loginMenu(){
    	Scanner scanner=new Scanner(System.in);
    	int choice;
    	System.out.println("1- sign in");
    	System.out.println("2- sing up");
    	while(true) {
    		try {
				choice=scanner.nextInt();
				while(choice <1 || choice >2) {
					System.out.println("enter a valid number");
					choice=scanner.nextInt();
				}
				break;
			} catch (Exception e) {
				System.out.println("enter a number");
				scanner.next();
			}
    		
    	}
    	
    	switch (choice) {
		case 1:
			loadUsers();
			singIn();
			break;

		case 2:
			singUp();
			break;
		}

    	
    }
    
    public void loadUsers() {  //arraye dosyadakileri  customer nesneleri olarak yüklüyo
    	try {
			CSVReader cReader=new CSVReader(new FileReader("users.csv"));
			String[] line;
			cReader.readNext();
			while((line=cReader.readNext())!=null) {
				users.add(new Customer(line[0], line[1], line[2], line[3]));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

    }
   
    public void singUp() {  //dosyaya yeni kullanıcı ekliyo daha fazla test edilmeli!!
    	try {
			CSVWriter cWriter=new CSVWriter(new FileWriter("users.csv",true));
			cWriter.writeNext(null);

	    	Scanner scanner=new Scanner(System.in);
	    	String username,password,address,phone;
	    	System.out.println("username: ");
	    	username=scanner.next();
	    	System.out.println("password: ");
	    	password=scanner.next();
	    	System.out.println("address: ");
	    	address=scanner.next();
	    	System.out.println("phone: ");
	    	phone=scanner.next();
	    	String[] addUserStrings= {
	    				username,
	    				password,
	    				address,
	    				phone
	    	};
	    	cWriter.writeNext(addUserStrings);
	    	cWriter.flush();
	    	cWriter.close();

    		} catch (Exception e) {
			// TODO: handle exception
		}	
    	
    }
    
    public void singIn() {
    	Scanner scanner=new Scanner(System.in);
    	boolean log=false;
    	String username,password;
    	System.out.println("username: ");
    	username=scanner.next();
    	System.out.println("password: ");
    	password=scanner.next();
    	Customer customer=null;
    	for(Customer user: users) { //laodUser ile arraye yüklediğimiz customer nesnelerini okuyo ve giriş yapıyo
    		if (username.equals(user.getUserName())) {
    			System.out.println(password);
				if (password.equals(user.getPassword())) {
					customer=user;
					log=true;
					break;
				}
			}
			
    	}
    	if (log) {
    		System.out.println("giriş yapıldı\n");
    		customer.customerMenu(this);
    		
			
		}
    	else {
			System.out.println("username or password is wrong");
			singIn();
		}

    	
    }
    
    
    
    
    public void loadMenu() {   //arraye dosyadakileri  MenuItem nesneleri olarak yüklüyo
    	try {
			CSVReader cReader=new CSVReader(new FileReader("menu.csv"));
			String[] line;
			cReader.readNext(); //başlığı atla
			while((line=cReader.readNext())!=null) {
				menu.add(new MenuItem(Integer.parseInt(line[0]),line[1],Integer.parseInt(line[2]),line[3]));
			}
		
			
		} catch (Exception e) {
			System.out.println("hata: "+e.getMessage());
		}

    }
    public void showMenu() {	//arrayden tek tek nesneleri alıp yazdırıyo
    	System.out.println("----------------MENU----------------");
    	for(MenuItem menu: menu) {
    		System.out.println(menu);
    		
    		
    	}
    	
    }
    public void takeOrder() {  //seçilenleri cart.csv e yazıyo henüz bitmedi
    	Scanner scanner=new Scanner(System.in);
    	System.out.println("select a number to add to cart(0 to finish)");
    	int choice;
			
    	while(true) {
    	    try {
    	    	CSVWriter cWriter=new CSVWriter(new FileWriter("cart.csv",true));
    	    	choice=scanner.nextInt();
    	    	while(choice < 0 || choice >menu.size()) {
    	    		System.out.println("enter a valid number");
    	    		choice=scanner.nextInt();
    	    	}
    	    	if (choice == 0) {
    	    		break;
    	    	}    	    	
    	    	
        	    //cart.add(menu.get(choice-1));
        	    for (MenuItem menu: menu) {
        	    	if(choice ==menu.id ) {
        	    		String[] cartStrings= {
        	    				Integer.toString(menu.id),
        	    				menu.name,
        	    				Double.toString(menu.price),
        	    				menu.category
        	    		};
        	    		cWriter.writeNext(cartStrings);
        	    		System.out.println(menu.name+" added to cart");
        	    		cWriter.flush();
        	    	}
        	    }
        	    
        	    cWriter.close();

    	    } 
    	    catch (InputMismatchException e) {
			System.out.println("please enter a number");
			scanner.next();
    	    }
    	    catch (Exception e) {
				// TODO: handle exception
			}

    		
    	}

    }
    
    
}
