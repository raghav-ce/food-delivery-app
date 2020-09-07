package com.fooddelivery.app.fooddeliveryapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fooddelivery.app.fooddeliveryapp.dto.CartDetail;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;

@Repository
public class CartDetailDaoImpl implements CartDetailDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public void deleteCart(int id) {
			Session currentSesion =  entityManager.unwrap(Session.class);
			Query<CartDetail> theQuery = currentSesion.createQuery("delete from CartDetail where id=:id");
			theQuery.setParameter("id", id);
			theQuery.executeUpdate();
	}

}
