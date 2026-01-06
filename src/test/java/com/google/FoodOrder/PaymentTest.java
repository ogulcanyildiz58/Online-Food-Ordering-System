package com.google.FoodOrder;

import junit.framework.TestCase;

public class PaymentTest extends TestCase {
	public void testPaymentInterfaceImplementation() {

        Payment cash = new cashPayment();
        Payment card = new creditCardPayment();
        
        assertNotNull("Cash payment nesnesi oluşturulabilmeli", cash);
        assertNotNull("Credit card payment nesnesi oluşturulabilmeli", card);
    }
    
    public void testCartItemCalculation() {
        MenuItem item = new MenuItem(1, "Pasta", 100.0, "food");

        assertEquals(100.0, item.price);
    }
}
