package com.fooddelivery.app.fooddeliveryapp.dao;

import java.util.List;

import com.fooddelivery.app.fooddeliveryapp.dto.Restaurant;

public interface RestaurantDetailsDao {
	
	/**
	 * getAllRestaurants returns the list of all restaurants
	 * 
	 * @return restaurantList
	 */
	List<Restaurant> getAllRestaurants();

	/**
	 * getOrderDetail reads the values from restaurant table
	 * @param restaurantId is the id value of the restaurant
	 * 
	 * @return Restaurant is the read value
	 */
	Restaurant getRestaurantDetails(int restaurantId);

	/**
	 * getOrderDetail reads the values from restaurant table by ratings
	 * @param ratingValue is the filter value of rating
	 * 
	 * @return restaurantList
	 */
	List<Restaurant> getRestaurantByRating(float ratingValue);

	/**
	 * getOrderDetail reads the values from restaurant table by destination
	 * @param destinationValue is the filter value of destination
	 * 
	 * @return restaurantList
	 */
	List<Restaurant> getRestaurantByDestination(String destinationValue);

}
