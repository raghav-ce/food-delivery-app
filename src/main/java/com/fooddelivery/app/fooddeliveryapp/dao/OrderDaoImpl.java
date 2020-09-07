package com.fooddelivery.app.fooddeliveryapp.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fooddelivery.app.fooddeliveryapp.dto.OrderInfo;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void insertOrder(OrderInfo orderInfo) {
		Session currentSesion =  entityManager.unwrap(Session.class);
		currentSesion.saveOrUpdate(orderInfo);
	}

	@Override
	public OrderInfo getOrderDetail(int orderId) {
		Session currentSesion =  entityManager.unwrap(Session.class);
		OrderInfo orderDetails = currentSesion.get(OrderInfo.class, orderId);
		return orderDetails;
	}

}
