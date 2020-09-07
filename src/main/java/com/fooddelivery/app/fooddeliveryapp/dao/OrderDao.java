package com.fooddelivery.app.fooddeliveryapp.dao;

import com.fooddelivery.app.fooddeliveryapp.dto.OrderInfo;

public interface OrderDao {

	/**
	 * insertOrder inserts the values into order table
	 * @param orderInfo is the value to be inserted
	 * 
	 */
	void insertOrder(OrderInfo orderInfo);

	/**
	 * getOrderDetail reads the values from order table
	 * @param orderId is the id value of the order
	 * 
	 * @return Order is the read value
	 */
	OrderInfo getOrderDetail(int orderId);

}
