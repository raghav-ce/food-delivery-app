package com.fooddelivery.app.fooddeliveryapp.dao;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fooddelivery.app.fooddeliveryapp.dto.Restaurant;

@Repository
public class RestaurantDetailsDaoImpl implements RestaurantDetailsDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Restaurant> getAllRestaurants() {
		Session currentSesion =  entityManager.unwrap(Session.class);
		Query<Restaurant> theQuery = currentSesion.createQuery("from Restaurant", Restaurant.class);
		List<Restaurant> employeeList = theQuery.getResultList();
		return employeeList;
	}

	@Override
	public Restaurant getRestaurantDetails(int restaurantId) {
		Session currentSesion =  entityManager.unwrap(Session.class);
		Restaurant restaurantDetails = currentSesion.get(Restaurant.class, restaurantId);
		return restaurantDetails;
	}

	@Override
	public List<Restaurant> getRestaurantByRating(float ratingValue) {
		Session currentSesion =  entityManager.unwrap(Session.class);
		Query<Restaurant> theQuery = currentSesion.createQuery("from Restaurant where restaurantRating>=:ratingValue order by restaurantRating DESC");
		theQuery.setParameter("ratingValue", ratingValue);
		List<Restaurant> restaurantList = theQuery.getResultList();
		return restaurantList;
	}
	
	@Override
	public List<Restaurant> getRestaurantByDestination(String destinationValue) {
		Session currentSesion =  entityManager.unwrap(Session.class);
		Query<Restaurant> theQuery = currentSesion
				.createQuery("from Restaurant where restaurantDestination like CONCAT('%',:destinationValue,'%')");
		theQuery.setParameter("destinationValue", destinationValue);
		List<Restaurant> restaurantList = theQuery.getResultList();
		return restaurantList;
	}

}
