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
//    	showCart();
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
    	loginMenu();
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
    	int ıd=0;
			
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
        	    		ıd+=1;
        	    		String[] cartStrings= {
        	    				Integer.toString(ıd),
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
    	showCart();

    }
    public void showCart() {

    	System.out.println("-----------cart-----------");
    	double totalPrice=0;
    	int choice;
    	Scanner scanner=new Scanner(System.in);
    	try {
			CSVReader cReader=new CSVReader(new FileReader("cart.csv"));
			String[] line;
			while((line =cReader.readNext())!=null) {
				int ıd=Integer.parseInt(line[0]);
				String food=line[1];
				Double price=Double.parseDouble(line[2]);
				totalPrice+=price;
				System.out.println(ıd+" "+food+" "+price);

			}
			System.out.println("total price: "+totalPrice);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	System.out.println("1-add");
    	System.out.println("2-delete");
    	System.out.println("3-pay");
    	while(true) {
    		try {
				choice=scanner.nextInt();
				while(choice <1 || choice >3 ) {
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
			takeOrder();
			break;

		case 2:
			deleteItem();
			break;
		case 3:
			pay(totalPrice);
			break;
		}

    	
    	
    }
    
    
    
    
    public void deleteItem() {
    	List<String[]> cartLines = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        

        try (CSVReader cReader = new CSVReader(new FileReader("cart.csv"))) {
            cartLines = cReader.readAll();
        } catch (Exception e) {
            System.out.println("Sepet okunurken hata oluştu: " + e.getMessage());
            return;
        }

        if (cartLines.isEmpty()) {
            System.out.println("Sepetiniz zaten boş.");
            return;
        }

        System.out.println("Silmek istediğiniz ürünün ID numarasını girin:");
        String targetId = scanner.next();
        
        boolean found = false;
        List<String[]> updatedCart = new ArrayList<>();


        for (String[] line : cartLines) {
            if (line[0].equals(targetId) && !found) {
                found = true; 
                System.out.println(line[1] + " sepetten çıkarıldı.");
                continue; 
            }
            updatedCart.add(line);
        }

        if (!found) {
            System.out.println("Belirtilen ID bulunamadı.");
            return;
        }


        try (CSVWriter cWriter = new CSVWriter(new FileWriter("cart.csv"))) {
            cWriter.writeAll(updatedCart);
            cWriter.flush();
        } catch (Exception e) {
            System.out.println("Sepet güncellenirken hata oluştu.");
        }
        showCart();
    }
    
    
    public void pay(double total) {
    	Payment payment;
    	System.out.println("1-cash\n2-cradit card");
    	Scanner scanner=new Scanner(System.in);
    	int choice;
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
			payment=new cashPayment();
			payment.pay(total,this);
			break;
		case 2:
			payment=new creditCardPayment();
			payment.pay(total,this);

			break;
		}
    	
    }
    
    
    
    
   
    
        public List<Customer> getUsers() {
		return users;
	}


	public void setUsers(List<Customer> users) {
		this.users = users;
	}

    
}
