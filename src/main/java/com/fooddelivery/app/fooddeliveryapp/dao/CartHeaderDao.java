package com.fooddelivery.app.fooddeliveryapp.dao;

import com.fooddelivery.app.fooddeliveryapp.dto.CartHeader;

public interface CartHeaderDao  {

	/**
	 * insertCartHeader inserts the values into cart header table
	 * @param cartHeader is the value to be inserted
	 * 
	 * @return CartHeader is the inserted value with id
	 */
	CartHeader insertCartHeader(CartHeader cartHeader);
	
	/**
	 * getCartHeader reads the values from cart header table
	 * @param cartId is the id value of the cart
	 * 
	 * @return CartHeader is the read value
	 */
	CartHeader getCartHeader(int cartId);

	/**
	 * deleteCart performs delete operation by id
	 * 
	 * @param cartId contains the id to be deleted 
	 */
	void deleteCartHeader(int cartId);
}
