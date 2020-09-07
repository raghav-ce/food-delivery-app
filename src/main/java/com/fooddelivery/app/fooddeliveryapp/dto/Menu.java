package com.fooddelivery.app.fooddeliveryapp.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Menu is the class referring menu table
 * @author RAGHAV E
 *
 */
@Entity
@Table(name = "menu")
public class Menu {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "food_name")
	private String foodName;
	
	@Column(name = "food_rate")
	private int foodRate;

	public Menu() {
		
	}

	public Menu(int id, String foodName, int foodRate) {
		this.id = id;
		this.foodName = foodName;
		this.foodRate = foodRate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodRate() {
		return foodRate;
	}

	public void setFoodRate(int foodRate) {
		this.foodRate = foodRate;
	}
	
	
	
}
