package com.google.FoodOrder;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;


public class AppTest extends TestCase{
	private App app;

    protected void setUp() {
        app = new App();
    }

    public void testSetAndGetUsers() {
        List<Customer> testUsers = new ArrayList<Customer>();
        testUsers.add(new Customer("ali", "111", "ist", "123"));
        
        app.setUsers(testUsers);
        assertEquals(1, app.getUsers().size());
        assertEquals("ali", app.getUsers().get(0).getUserName());
    }
}
