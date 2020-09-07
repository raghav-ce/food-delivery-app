package com.fooddelivery.app.fooddeliveryapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.fooddelivery.app.fooddeliveryapp.dto.CartHeader;

@Repository
public class CartHeaderDaoImpl implements CartHeaderDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public CartHeader insertCartHeader(CartHeader cartHeader) {
			Session currentSesion =  entityManager.unwrap(Session.class);
			currentSesion.saveOrUpdate(cartHeader);
			return getCartHeader(cartHeader);
		}
	
	@Override
	public CartHeader getCartHeader(int cartId) {
			Session currentSesion =  entityManager.unwrap(Session.class);
			return currentSesion.get(CartHeader.class, cartId);
		}
	
	public CartHeader getCartHeader(CartHeader cartHeader) {
		Session currentSesion =  entityManager.unwrap(Session.class);
		Query<CartHeader> theQuery = currentSesion.createQuery("from CartHeader where user like :userId and cartStatus=0");
		theQuery.setParameter("userId", cartHeader.getUser());
		List<CartHeader> cartHeaderList = theQuery.getResultList();
		if(CollectionUtils.isEmpty(cartHeaderList)) {
			return cartHeader;
		}
		return cartHeaderList.get(0);
	}

	@Override
	public void deleteCartHeader(int cartId) {
		Session currentSesion =  entityManager.unwrap(Session.class);
		Query<CartHeader> theQuery = currentSesion.createQuery("delete from CartHeader where id=:id");
		theQuery.setParameter("id", cartId);
		theQuery.executeUpdate();
	}
}
