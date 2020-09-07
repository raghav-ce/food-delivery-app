package com.fooddelivery.app.fooddeliveryapp.service;

import java.time.LocalTime;
import java.util.List;

import com.fooddelivery.app.fooddeliveryapp.dto.CartHeader;
import com.fooddelivery.app.fooddeliveryapp.dto.Menu;
import com.fooddelivery.app.fooddeliveryapp.dto.Restaurant;
import com.fooddelivery.app.fooddeliveryapp.entity.CartData;
import com.fooddelivery.app.fooddeliveryapp.entity.OrderDetails;

public interface FoodAppService {

	/**
	 * getAllRestaurants returns the list of available restaurants
	 *  
	 * @return restaurantList is the list containing restaurants
	 */
	List<Restaurant> getAllRestaurants();

	/**
	 * getRestaurantDetails returns the particular restaurant
	 * 
	 * @param restaurantId is the restaurant id of the restaurant
	 *  
	 * @return Restaurant
	 */
	Restaurant getRestaurantDetails(int restaurantId);

	/**
	 * getRestaurantByRating returns the sorted list of restaurants based on the review
	 * 
	 * @param ratingValue is the filtered rating value 
	 *  
	 * @return restaurantList is the list containing restaurants filtered based on ratings
	 */
	List<Restaurant> getRestaurantByRating(float ratingValue);

	/**
	 * insertCartData returns the list of menu within or equal to given price
	 * @param cartData is a POJO containing values regarding cart
	 *  
	 * @return CartHeader contains the newly added cart details
	 */
	CartHeader insertCartData(CartData cartData);

	/**
	 * updateCartData adds a new single item to the cart or
	 *  	updates the quantity if cartData is already present 
	 * @param cartData is a POJO containing values regarding cart
	 *  
	 * @return CartHeader contains the newly added cart details
	 */
	CartHeader updateCartData(CartData cartData);

	/**
	 * removeSingleItem adds a new single item to the cart or
	 *  	updates the quantity if cartData is already present 
	 * @param cartData is a POJO containing values regarding cart
	 *  
	 * @return CartHeader contains the cart values after removal of an item
	 */
	CartHeader removeCartData(CartData cartData);

	/**
	 * getMenuSortByPrice returns the list of menu within or equal to given price
	 * @param priceValue is filtered price of the food items
	 *  
	 * @return menuList is the list containing menu filtered based on the price condition
	 */
	List<Menu> getMenuSortByPrice(int priceValue);

	/**
	 * placeOrder places the order for the item in the cart
	 * @param cartId is the cart for which the order to be generated
	 *  
	 * @return CartHeader contains the cart values after removal of an item
	 */
	void placeOrder(int cartId);

	/**
	 * getOrderDetail gets the order details placed
	 * @param orderId is the identity of the order to be fetched
	 *  
	 * @return OrderDetails contains the details of the order
	 */
	OrderDetails getOrderDetails(int orderId);

	/**
	 * getSurgeAmount gets the surge amount of the specified time
	 * @param surgeCheck contains the time of order and cart amount
	 *  
	 * @return surgeAmount contains surge amount
	 */
	 int getSurgeAmount(LocalTime currentTime, int orderAmount);

	/**
	 * getRestaurantByRating returns the sorted list of restaurants based on the review
	 * 
	 * @param ratingValue is the filtered rating value 
	 *  
	 * @return restaurantList is the list containing restaurants filtered based on ratings
	 */
	List<Restaurant> getRestaurantByDestination(String destinationValue);
	
	
}
