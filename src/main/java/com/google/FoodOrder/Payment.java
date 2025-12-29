package com.google.FoodOrder;

public interface Payment {
	void pay(double amount,App app);
	void clearCart(App app);

}
