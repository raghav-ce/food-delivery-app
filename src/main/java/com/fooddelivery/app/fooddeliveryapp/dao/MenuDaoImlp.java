package com.fooddelivery.app.fooddeliveryapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fooddelivery.app.fooddeliveryapp.dto.Menu;

@Repository
public class MenuDaoImlp implements MenuDao {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Menu> getMenuSortByPrice(int priceValue) {
		Session currentSesion =  entityManager.unwrap(Session.class);
		Query<Menu> theQuery = currentSesion.createQuery("from Menu where foodRate<=:priceValue order by foodRate DESC");
		theQuery.setParameter("priceValue", priceValue);
		List<Menu> menuList = theQuery.getResultList();
		return menuList;
	}

	@Override
	public Menu getMenuItem(int menuId) {
		Session currentSesion =  entityManager.unwrap(Session.class);
		Menu menuDetails = currentSesion.get(Menu.class, menuId);
		return menuDetails;
	}
}
