package com.fooddelivery.app.fooddeliveryapp.dto;

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
 * Restaurant is the class referring restaurant table
 * @author RAGHAV E
 *
 */
@Entity
@Table(name = "restaurant")
public class Restaurant {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "restaurant_name")
	private String restaurantName;
	
	@Column(name = "restaurant_description")
	private String restaurantDescription;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id")
	List<Menu> menuItems;
	
	@Column(name = "restaurant_rating")
	private float restaurantRating;
	
	@Column(name = "restaurant_destination")
	private String restaurantDestination;
	
	public Restaurant() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantDescription() {
		return restaurantDescription;
	}

	public void setRestaurantDescription(String restaurantDescription) {
		this.restaurantDescription = restaurantDescription;
	}

	public List<Menu> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<Menu> menuItems) {
		this.menuItems = menuItems;
	}

	public float getRestaurantRating() {
		return restaurantRating;
	}

	public void setRestaurantRating(float restaurantRating) {
		this.restaurantRating = restaurantRating;
	}

	public String getRestaurantDestination() {
		return restaurantDestination;
	}

	public void setRestaurantDestination(String restaurantDestination) {
		this.restaurantDestination = restaurantDestination;
	}

}
