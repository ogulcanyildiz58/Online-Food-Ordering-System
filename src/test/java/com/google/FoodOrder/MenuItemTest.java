package com.google.FoodOrder;

import junit.framework.TestCase;

public class MenuItemTest extends TestCase {
	public void testMenuItemCreation() {
        MenuItem item = new MenuItem(1, "Pizza", 200.0, "food");
        assertEquals(1, item.id);
        assertEquals("Pizza", item.name);
        assertEquals(200.0, item.price);
        assertEquals("food", item.category);
    }

    public void testToString() {
        MenuItem item = new MenuItem(2, "Coke", 50.0, "drink");
        String expected = "2 |  Coke |  50.0 TL |  drink";
        assertEquals(expected, item.toString());
    }
}
