package com.fooddelivery.app.fooddeliveryapp.dao;

import java.util.List;

import com.fooddelivery.app.fooddeliveryapp.dto.Menu;

public interface MenuDao {

	/**
	 * getMenuSortByPrice returns the list of menu within or equal to given price
	 * @param priceValue is filtered price of the food items
	 *  
	 * @return menuList is the list containing menu filtered based on the price condition
	 */
	List<Menu> getMenuSortByPrice(int priceValue);
	
	/**
	 * getMenuItem reads the values from menu table
	 * @param menuId is the id value of the menu
	 * 
	 * @return Menu is the read value
	 */
	Menu getMenuItem(int menuId);
}
