package com.fooddelivery.app.fooddeliveryapp.dao;

import java.util.List;

import com.fooddelivery.app.fooddeliveryapp.dto.CartDetail;

/**
 * CartDetailDao is the class for accessing CartDetail table
 * 
 * @author RAGHAV E
 *
 */
public interface CartDetailDao {
	
	/**
	 * deleteCart performs delete operation by id
	 * 
	 * @param id
	 */
	public void deleteCart(int id);
	
}
