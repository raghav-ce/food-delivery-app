package com.fooddelivery.app.fooddeliveryapp.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CartHeader is the class referring cart_header table
 * @author RAGHAV E
 *
 */
@Entity
@Table(name = "cart_header")
public class CartHeader {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user")
	private String user;
	
	@Column(name = "restaurant_id")
	private int restaurantId;
	
	@Column(name = "cart_status")
	private int cartStatus;
	
	@Column(name = "cart_total_amount")
	private int cartTotalAmount;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private List<CartDetail> cartDetails;
	
	public void addDetails(CartDetail cartDetail) {
		if(cartDetails==null) {
			cartDetails = new ArrayList<>();
		}
		cartDetails.add(cartDetail);
		
	}

	public CartHeader(){
		
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

	public int getCartStatus() {
		return cartStatus;
	}

	public void setCartStatus(int cartStatus) {
		this.cartStatus = cartStatus;
	}

	public int getCartTotalAmount() {
		return cartTotalAmount;
	}

	public void setCartTotalAmount(int cartTotalAmount) {
		this.cartTotalAmount = cartTotalAmount;
	}

	public List<CartDetail> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<CartDetail> cartDetails) {
		this.cartDetails = cartDetails;
	}

	public void clearDetails() {
		cartDetails = new ArrayList<>();
	}

	public String getUser() {
		return user;
	}

	public void setUser(String userId) {
		this.user = userId;
	}
	
}
