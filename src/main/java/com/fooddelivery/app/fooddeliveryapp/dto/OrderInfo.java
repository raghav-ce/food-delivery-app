package com.fooddelivery.app.fooddeliveryapp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrderInfo is the class referring order_info table
 * @author RAGHAV E
 *
 */
@Entity
@Table(name = "order_info")
public class OrderInfo {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "restaurant_id")
	private int restaurantId;
	
	@Column(name = "user")
	private String user;
	
	@Column(name = "cart_id")
	private int cartId;
	
	@Column(name = "order_final_amount")
	private int orderFinalAmount;
	
	@Column(name = "order_payment_status")
	private int orderPaymentStatus;
	
	@Column(name = "delivery_status")
	private int deliveryStatus;
	
	@Column(name = "order_status")
	private int orderStatus;
	
	@Column(name = "surge_amount")
	private int surgeAmount;
	
	public OrderInfo() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getOrderFinalAmount() {
		return orderFinalAmount;
	}

	public void setOrderFinalAmount(int orderFinalAmount) {
		this.orderFinalAmount = orderFinalAmount;
	}

	public int getOrderPaymentStatus() {
		return orderPaymentStatus;
	}

	public void setOrderPaymentStatus(int orderPaymentStatus) {
		this.orderPaymentStatus = orderPaymentStatus;
	}

	public int getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getSurgeAmount() {
		return surgeAmount;
	}

	public void setSurgeAmount(int surgeAmount) {
		this.surgeAmount = surgeAmount;
	}
	
}
