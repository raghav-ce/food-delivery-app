package com.fooddelivery.app.fooddeliveryapp.entity;

/**
 * CartData is the class containing the information of the cart
 * @author RAGHAV E
 *
 */
public class CartData {
	
	private int cartId;
	
	private String userId;
	
	private int restaurantId;
	
	private int menuId;
	
	public CartData() {
		
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
