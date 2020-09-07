package com.fooddelivery.app.fooddeliveryapp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CartDetail is the class referring cart_detail table
 * @author RAGHAV E
 *
 */
@Entity
@Table(name = "cart_detail")
public class CartDetail {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "food_id")
	private int foodId;
	
	@Column(name = "food_quantity")
	private int foodQuantity;
	
	@Column(name = "food_rate")
	private int foodRate;
	
	@Column(name = "food_amount")
	private int foodAmount;
	
	public CartDetail() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}

	public int getFoodRate() {
		return foodRate;
	}

	public void setFoodRate(int foodRate) {
		this.foodRate = foodRate;
	}

	public int getFoodAmount() {
		return foodAmount;
	}

	public void setFoodAmount(int foodAmount) {
		this.foodAmount = foodAmount;
	}

}
