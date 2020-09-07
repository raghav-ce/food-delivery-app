package com.fooddelivery.app.fooddeliveryapp.entity;

import com.fooddelivery.app.fooddeliveryapp.dto.CartHeader;
import com.fooddelivery.app.fooddeliveryapp.dto.OrderInfo;
import com.fooddelivery.app.fooddeliveryapp.dto.Restaurant;

/**
 * OrderDetails is the class containing the information of the order
 * @author RAGHAV E
 *
 */
public class OrderDetails {
	
	private OrderInfo order;
	
	private Restaurant restaurant;
	
	private CartHeader cartHeader;
	
	public OrderDetails() {
		
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public CartHeader getCartHeader() {
		return cartHeader;
	}

	public void setCartHeader(CartHeader cartHeader) {
		this.cartHeader = cartHeader;
	}

}
