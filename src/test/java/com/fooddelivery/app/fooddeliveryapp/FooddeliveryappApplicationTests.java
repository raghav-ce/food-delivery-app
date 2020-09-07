package com.fooddelivery.app.fooddeliveryapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.fooddelivery.app.fooddeliveryapp.controller.FoodDeliveryAppController;
import com.fooddelivery.app.fooddeliveryapp.dto.Menu;
import com.fooddelivery.app.fooddeliveryapp.dto.Restaurant;
import com.fooddelivery.app.fooddeliveryapp.entity.SurgeCheck;

@SpringBootTest
class FooddeliveryappApplicationTests {
	
	@Autowired
	FoodDeliveryAppController FoodDeliveryAppController;
	
	@Test
	void checkPrePopulatonOfRestaurants() {
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
		restaurantList = FoodDeliveryAppController.getRestaurantList();
		if(CollectionUtils.isEmpty(restaurantList)) {
			restaurantList=null;
		}
		assertNotNull(restaurantList, "Restaurants are not pre-paopulated");
	}

	@Test
	void checkMenuSortingByPrice() {
		int filterPrice = 100;
		boolean priceValidation = true;
		List<Menu> menuList = new ArrayList<>();
		menuList = FoodDeliveryAppController.getMenuSortByPrice(filterPrice);
		if(CollectionUtils.isEmpty(menuList)) {
			menuList=null;
			assertNotNull(menuList, "Menu Items are not pre-paopulated");
		}
		for(Menu menu:menuList) {
			if(menu.getFoodRate()>filterPrice) {
				priceValidation=false;
			}
		}
		assertTrue(priceValidation, "The Menu with price more than 100 are displayed");
	}
	
	@Test
	void checkRestaurantSortByRating() {
		float restaurantRating = (float) 4.5;
		boolean ratingValidation = true;
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
		restaurantList = FoodDeliveryAppController.getRestaurantByRating(restaurantRating);
		for(Restaurant restaurant:restaurantList) {
			if(restaurant.getRestaurantRating()<restaurantRating) {
				ratingValidation=false;
			}
		}
		assertTrue(ratingValidation, "The restaurants with below 4.5 ratings are displayed");
	}
	
	@Test
	void checkRestaurantByDestination() {
		String destinationValue = "Vela";
		boolean destinationValidation = true;
		List<Restaurant> restaurantList = new ArrayList<Restaurant>();
		restaurantList = FoodDeliveryAppController.getRestaurantByDestination(destinationValue);
		if(CollectionUtils.isEmpty(restaurantList)) {
			restaurantList=null;
			assertNotNull(restaurantList, "Restaurants are not pre-paopulated");
		}
		for(Restaurant restaurant:restaurantList) {
			if(!restaurant.getRestaurantDestination().contains(destinationValue)) {
				destinationValidation=false;
			}
		}
		assertTrue(destinationValidation, "The destination validation is getting failed");
	}
	
	@Test
	void checkNormalSurgeTimeRate() {
		SurgeCheck surgeCheck = new SurgeCheck("08:00", 100);
		int expectedAmount = 10;
		int surgeAmount = FoodDeliveryAppController.getSurgeAmount(surgeCheck);
		assertSame(expectedAmount, surgeAmount, "The Surge Amount Validation getting failed");
	}
	
	@Test
	void checkSurgeTimeRate() {
		SurgeCheck surgeCheck = new SurgeCheck("08:31", 100);
		int expectedAmount = 20;
		int surgeAmount = FoodDeliveryAppController.getSurgeAmount(surgeCheck);
		assertSame(expectedAmount, surgeAmount, "The Surge Amount Validation getting failed");
	}
	
	@Test
	void checkNoSurgeTimeRate() {
		SurgeCheck surgeCheck = new SurgeCheck("09:31", 100);
		int expectedAmount = 0;
		int surgeAmount = FoodDeliveryAppController.getSurgeAmount(surgeCheck);
		assertSame(expectedAmount, surgeAmount, "The Surge Amount Validation getting failed");
	}
	
}
