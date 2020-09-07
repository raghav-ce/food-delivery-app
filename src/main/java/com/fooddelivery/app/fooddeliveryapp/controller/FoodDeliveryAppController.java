package com.fooddelivery.app.fooddeliveryapp.controller;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fooddelivery.app.fooddeliveryapp.dto.CartHeader;
import com.fooddelivery.app.fooddeliveryapp.dto.Menu;
import com.fooddelivery.app.fooddeliveryapp.dto.Restaurant;
import com.fooddelivery.app.fooddeliveryapp.entity.CartData;
import com.fooddelivery.app.fooddeliveryapp.entity.OrderDetails;
import com.fooddelivery.app.fooddeliveryapp.entity.SurgeCheck;
import com.fooddelivery.app.fooddeliveryapp.service.FoodAppService;

/**
 * FoodDeliveryAppController is the controller class for providing 
 * validated data for food delivery application
 * 
 * @author RAGHAV E
 *
 */
@RestController
@RequestMapping("/foodappapi")
public class FoodDeliveryAppController {
	
	@Autowired
	private FoodAppService restaurantDetailsService;
	
	/**
	 * getRestaurantList returns the list of available restaurants
	 *  
	 * @return restaurantList is the list containing restaurants
	 */
	@GetMapping("/restaurants")
	public List<Restaurant> getRestaurantList(){
		List<Restaurant> restaurantList = restaurantDetailsService.getAllRestaurants();
		return restaurantList;
		
	}

	/**
	 * getRestaurantMenu returns the list of the menu available in particular restaurant
	 * 
	 * @param restaurantId is the restaurant id of the restaurant
	 *  
	 * @return menuList is the list of menus
	 */
	@GetMapping("/restaurants/{restaurantId}")
	public List<Menu> getRestaurantMenu(@PathVariable int restaurantId){
		Restaurant restaurantDetail = restaurantDetailsService.getRestaurantDetails(restaurantId);
		return restaurantDetail.getMenuItems();
		
	}
	
	/**
	 * getRestaurantByRating returns the sorted list of restaurants based on the review
	 * 
	 * @param ratingValue is the filtered rating value 
	 *  
	 * @return restaurantList is the list containing restaurants filtered based on ratings
	 */
	@GetMapping("/restaurants/rating/{ratingValue}")
	public List<Restaurant> getRestaurantByRating(@PathVariable float ratingValue){
		List<Restaurant> restaurantList = restaurantDetailsService.getRestaurantByRating(ratingValue);
		return restaurantList;
	}
	
	/**
	 * getRestaurantByRating returns the sorted list of restaurants based on the review
	 * 
	 * @param ratingValue is the filtered rating value 
	 *  
	 * @return restaurantList is the list containing restaurants filtered based on ratings
	 */
	@GetMapping("/restaurants/destination/{destinationValue}")
	public List<Restaurant> getRestaurantByDestination(@PathVariable String destinationValue){
		List<Restaurant> restaurantList = restaurantDetailsService.getRestaurantByDestination(destinationValue);
		return restaurantList;
	}

	/**
	 * getMenuSortByPrice returns the list of menu within or equal to given price
	 * @param priceValue is filtered price of the food items
	 *  
	 * @return menuList is the list containing menu filtered based on the price condition
	 */
	@GetMapping("/menus/price/{priceValue}")
	public List<Menu> getMenuSortByPrice(@PathVariable int priceValue){
		List<Menu> menuList = restaurantDetailsService.getMenuSortByPrice(priceValue);
		return menuList;
	}
	
	/**
	 * insertCartData returns the list of menu within or equal to given price
	 * @param cartData is a POJO containing values regarding cart
	 *  
	 * @return CartHeader contains the newly added cart details
	 */
	@PostMapping("/carts")
	public CartHeader insertCartData(@RequestBody CartData cartData){
		return restaurantDetailsService.insertCartData(cartData);
		
	}
	
	/**
	 * addSingleItem adds a new single item to the cart or
	 *  	updates the quantity if cartData is already present 
	 * @param cartData is a POJO containing values regarding cart
	 *  
	 * @return CartHeader contains the newly added cart details
	 */
	@PutMapping("/carts")
	public CartHeader addSingleItem(@RequestBody CartData cartData){
		return restaurantDetailsService.updateCartData(cartData);
		
	}
	
	/**
	 * removeSingleItem adds a new single item to the cart or
	 *  	updates the quantity if cartData is already present 
	 * @param cartData is a POJO containing values regarding cart
	 *  
	 * @return CartHeader contains the cart values after removal of an item
	 */
	@DeleteMapping("/carts")
	public CartHeader removeSingleItem(@RequestBody CartData cartData){
		return restaurantDetailsService.removeCartData(cartData);
	}
	
	/**
	 * placeOrder places the order for the item in the cart
	 * @param cartId is the cart for which the order to be generated
	 *  
	 * @return CartHeader contains the cart values after removal of an item
	 */
	@PostMapping("/orders/{cartId}")
	public void placeOrder(@PathVariable int cartId){
		restaurantDetailsService.placeOrder(cartId);
	}

	/**
	 * getOrderDetail gets the order details placed
	 * @param orderId is the identity of the order to be fetched
	 *  
	 * @return OrderDetails contains the details of the order
	 */
	@GetMapping("/orders/{orderId}")
	public OrderDetails getOrderDetail(@PathVariable int orderId){
		return restaurantDetailsService.getOrderDetails(orderId);
	}
	
	/**
	 * getSurgeAmount gets the surge amount of the specified time
	 * @param surgeCheck contains the time of order and cart amount
	 *  
	 * @return surgeAmount contains surge amount
	 */
	@GetMapping("/surge")
	public int getSurgeAmount(@RequestBody SurgeCheck surgeCheck){
		return restaurantDetailsService.getSurgeAmount(LocalTime.parse(surgeCheck.getCurrentTime()), surgeCheck.getCartAmount());
	}
	
}
