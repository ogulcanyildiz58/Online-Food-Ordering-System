package com.google.FoodOrder;


public class MenuItem {
	public String name,category;
    public double price;
    public int id;

    
	public MenuItem(int id,String name,double price,String category) {
		this.id=id;
		this.name=name;
		this.price=price;
		this.category=category;
	}

    public String toString() {
        return id+" |  "+name + " |  " + price + " TL |  " + category;
    }
    
}
