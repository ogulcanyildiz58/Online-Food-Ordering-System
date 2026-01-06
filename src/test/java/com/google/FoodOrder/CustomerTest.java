package com.google.FoodOrder;

import junit.framework.TestCase;

public class CustomerTest extends TestCase {
	public void testCustomerInheritanceAndFields() {
        Customer customer = new Customer("ogulcan", "1234", "ist", "536");
        

        assertEquals("ogulcan", customer.getUserName());
        
 
        assertEquals("ist", customer.getAddress());
        assertEquals("536", customer.getPhone());
        
        customer.setAddress("ankara");
        assertEquals("ankara", customer.getAddress());
    }
}
