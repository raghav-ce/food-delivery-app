package com.fooddelivery.app.fooddeliveryapp.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fooddelivery.app.fooddeliveryapp.dao.CartDetailDao;
import com.fooddelivery.app.fooddeliveryapp.dao.CartHeaderDao;
import com.fooddelivery.app.fooddeliveryapp.dao.MenuDao;
import com.fooddelivery.app.fooddeliveryapp.dao.RestaurantDetailsDao;
import com.fooddelivery.app.fooddeliveryapp.dto.CartDetail;
import com.fooddelivery.app.fooddeliveryapp.dto.CartHeader;
import com.fooddelivery.app.fooddeliveryapp.dto.Menu;
import com.fooddelivery.app.fooddeliveryapp.dto.OrderInfo;
import com.fooddelivery.app.fooddeliveryapp.dto.Restaurant;
import com.fooddelivery.app.fooddeliveryapp.entity.CartData;
import com.fooddelivery.app.fooddeliveryapp.entity.OrderDetails;
import com.fooddelivery.app.fooddeliveryapp.entity.PeakType;
import com.fooddelivery.app.fooddeliveryapp.entity.SurgeTimeChart;
import com.fooddelivery.app.fooddeliveryapp.dao.OrderDao;

@Service
public class FoodAppServiceImpl implements FoodAppService{
	
	@Autowired
	private RestaurantDetailsDao restaurantDetailsDao;
	
	@Autowired
	private CartHeaderDao cartHeaderDao;
	
	@Autowired
	private CartDetailDao cartDetailDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private OrderDao orderDao;

	@Override
	@Transactional
	public List<Restaurant> getAllRestaurants() {
		return restaurantDetailsDao.getAllRestaurants();
	}

	@Override
	@Transactional
	public Restaurant getRestaurantDetails(int restaurantId) {
		return restaurantDetailsDao.getRestaurantDetails(restaurantId);
	}

	@Override
	@Transactional
	public List<Restaurant> getRestaurantByRating(float ratingValue) {
		return restaurantDetailsDao.getRestaurantByRating(ratingValue);
	}
	
	@Override
	@Transactional
	public List<Restaurant> getRestaurantByDestination(String destinationValue) {
		return restaurantDetailsDao.getRestaurantByDestination(destinationValue);
	}

	@Override
	@Transactional
	public CartHeader insertCartData(CartData cartData) {
		CartHeader cartHeader = new CartHeader();
		int totalAmount = 0;
		cartHeader.setUser(cartData.getUserId());
		cartHeader.setRestaurantId(cartData.getRestaurantId());
		cartHeader.setCartStatus(0);
		Menu menuItem = menuDao.getMenuItem(cartData.getMenuId());
		CartDetail cartDetail = newCartDetail(menuItem);
		cartHeader.addDetails(cartDetail);
		for(CartDetail cartDetailValue:cartHeader.getCartDetails()) {
			totalAmount+=cartDetailValue.getFoodRate();
		}
		cartHeader.setCartTotalAmount(totalAmount);
		cartHeader = cartHeaderDao.insertCartHeader(cartHeader);
		return cartHeader;
	}

	/**
	 * newCartDetail creates a new entry for cart detail
	 * @param menuItem
	 * @return CartDetail
	 */
	private CartDetail newCartDetail(Menu menuItem) {
		CartDetail cartDetail = new CartDetail();
		cartDetail.setFoodId(menuItem.getId());
		cartDetail.setFoodQuantity(1);
		cartDetail.setFoodRate(menuItem.getFoodRate());
		cartDetail.setFoodAmount(cartDetail.getFoodQuantity()*cartDetail.getFoodRate());
		return cartDetail;
	}

	@Override
	@Transactional
	public CartHeader updateCartData(CartData cartData) {
		int totalAmount = 0;
		CartHeader cartHeader = cartHeaderDao.getCartHeader(cartData.getCartId());
		List<CartDetail> cartDetail = cartHeader.getCartDetails();
		cartDetail = cartDetail.stream().filter(filter->
			(filter.getFoodId()==cartData.getMenuId())
		).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(cartDetail)) {
			Menu menuItem = menuDao.getMenuItem(cartData.getMenuId());
			CartDetail cartDetailNew = newCartDetail(menuItem);
			cartHeader.addDetails(cartDetailNew);
		} else {
			cartHeader.getCartDetails().stream().forEach(action->{
				if(action.getFoodId()==cartData.getMenuId()) {
					action.setFoodQuantity(action.getFoodQuantity()+1);
					action.setFoodAmount(action.getFoodQuantity()*action.getFoodRate());
				}
			});
		}
		for(CartDetail cartDetailValue:cartHeader.getCartDetails()) {
			totalAmount+=cartDetailValue.getFoodAmount();
		}
		cartHeader.setCartTotalAmount(totalAmount);
		cartHeader = cartHeaderDao.insertCartHeader(cartHeader);
		return cartHeader;
	}

	@Override
	@Transactional
	public CartHeader removeCartData(CartData cartData) {
		int totalAmount = 0;
		CartHeader cartHeader = cartHeaderDao.getCartHeader(cartData.getCartId());
		List<CartDetail> cartDetail = cartHeader.getCartDetails();
		List<CartDetail> updatedCartDetail = new ArrayList<>();
		List<CartDetail> removeCartDetail = new ArrayList<>();
		cartDetail = cartDetail.stream().filter(filter->
			(filter.getFoodId()==cartData.getMenuId())
		).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(cartDetail)) {
			throw new RuntimeException("The Mentioned food item is not found in the cart");
		} else {
			cartHeader.getCartDetails().stream().map(action->{
				if(action.getFoodId()==cartData.getMenuId()) {
					action.setFoodQuantity(action.getFoodQuantity()-1);
					action.setFoodAmount(action.getFoodQuantity()*action.getFoodRate());
				}
				return action;
			}).collect(Collectors.toList());
			removeCartDetail = cartHeader.getCartDetails().stream().filter(filter->
			(filter.getFoodQuantity()==0)
			).collect(Collectors.toList());
			updatedCartDetail = cartHeader.getCartDetails().stream().filter(filter->
			(filter.getFoodQuantity()!=0)
			).collect(Collectors.toList());
			if(!CollectionUtils.isEmpty(removeCartDetail)) {
				cartHeader.clearDetails();
				for(CartDetail cartDetailValue:updatedCartDetail) {
					cartHeader.addDetails(cartDetailValue);
				}
				cartDetailDao.deleteCart(removeCartDetail.get(0).getId());
			}
		}
		for(CartDetail cartDetailValue:cartHeader.getCartDetails()) {
			totalAmount+=cartDetailValue.getFoodAmount();
		}
		cartHeader.setCartTotalAmount(totalAmount);
		if(CollectionUtils.isEmpty(updatedCartDetail)) {
			cartHeaderDao.deleteCartHeader(cartHeader.getId());
			return null;
		} else {
			return cartHeaderDao.insertCartHeader(cartHeader);
		}
	
	}

	@Override
	@Transactional
	public List<Menu> getMenuSortByPrice(int priceValue) {
		return menuDao.getMenuSortByPrice(priceValue);
	}

	@Override
	@Transactional
	public void placeOrder(int cartId) {
		CartHeader cartHeader = cartHeaderDao.getCartHeader(cartId);
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setCartId(cartHeader.getId());
		orderInfo.setRestaurantId(cartHeader.getRestaurantId());
		orderInfo.setUser(cartHeader.getUser());
		cartHeader.setCartStatus(1);
		cartHeaderDao.insertCartHeader(cartHeader);
		int surgeAmount = getSurgeAmount(LocalTime.now(), cartHeader.getCartTotalAmount());
		orderInfo.setSurgeAmount(surgeAmount);
		orderInfo.setOrderFinalAmount(cartHeader.getCartTotalAmount()+surgeAmount);
		orderDao.insertOrder(orderInfo);
	}

	@Override
	@Transactional
	public OrderDetails getOrderDetails(int orderId) {
		OrderDetails orderDetails = new OrderDetails();
		OrderInfo orderDetail = orderDao.getOrderDetail(orderId);
		Restaurant restaurantDetails = restaurantDetailsDao.getRestaurantDetails(orderDetail.getRestaurantId());
		CartHeader cartValues = cartHeaderDao.getCartHeader(orderDetail.getCartId());
		orderDetails.setOrder(orderDetail);
		orderDetails.setRestaurant(restaurantDetails);
		orderDetails.setCartHeader(cartValues);
		return orderDetails;
	}
	
	@Override
	public int getSurgeAmount(LocalTime currentTime, int orderAmount) {
		PeakType peakType;
		if(isBetweenSurge(currentTime)||isAtSurge(currentTime)) {
			peakType = PeakType.PEAK;
		} else if(isNormalSurge(currentTime)||isAtNormalSurge(currentTime)) {
			peakType = PeakType.NORMAL;
		} else {
			peakType = PeakType.NO_PEAK;
		}
		int peakPecentage = peakType.getPeakPercentage();
		int peakAmount = (peakPecentage*orderAmount)/100;
		return peakAmount;
	}

	/**
	 * isAtNormalSurge checks whether the time is under normal surge
	 * @param currentTime
	 * @return boolean
	 */
	private boolean isAtNormalSurge(LocalTime currentTime) {
		return ((currentTime.equals(SurgeTimeChart.MORNING_NORMAL_SURGE.getMinTime()))
				||(currentTime.equals(SurgeTimeChart.MORNING_SECONDARY_SURGE.getMaxTime()))
				||(currentTime.equals(SurgeTimeChart.MID_DAY_NORMAL_SURGE.getMinTime()))
				||(currentTime.equals(SurgeTimeChart.MID_DAY_SECONDARY_SURGE.getMaxTime()))
				||(currentTime.equals(SurgeTimeChart.DAY_END_NORMAL_SURGE.getMinTime()))
				||(currentTime.equals(SurgeTimeChart.DAY_END_SECONDARY_SURGE.getMaxTime())));
	}

	/**
	 * isAtSurge checks whether the time is under peak surge
	 * @param currentTime
	 * @return boolean
	 */
	private boolean isAtSurge(LocalTime currentTime) {
		return ((currentTime.equals(SurgeTimeChart.MORNING_SURGE.getMinTime()))
				||(currentTime.equals(SurgeTimeChart.MORNING_SURGE.getMaxTime()))
				||(currentTime.equals(SurgeTimeChart.MID_DAY_SURGE.getMinTime()))
				||(currentTime.equals(SurgeTimeChart.MID_DAY_SURGE.getMaxTime()))
				||(currentTime.equals(SurgeTimeChart.DAY_END_SURGE.getMinTime()))
				||(currentTime.equals(SurgeTimeChart.DAY_END_SURGE.getMaxTime())));
	}


	/**
	 * isNormalSurge checks whether the time is under normal surge
	 * @param currentTime
	 * @return boolean
	 */
	private boolean isNormalSurge(LocalTime currentTime) {
		return (currentTime.isAfter(SurgeTimeChart.MORNING_NORMAL_SURGE.getMinTime())&&
				currentTime.isBefore(SurgeTimeChart.MORNING_SECONDARY_SURGE.getMaxTime()))
				|| (currentTime.isAfter(SurgeTimeChart.MID_DAY_NORMAL_SURGE.getMinTime())&&
						currentTime.isBefore(SurgeTimeChart.MID_DAY_SECONDARY_SURGE.getMaxTime()))
				||(currentTime.isAfter(SurgeTimeChart.DAY_END_NORMAL_SURGE.getMinTime())&&
						currentTime.isBefore(SurgeTimeChart.DAY_END_SECONDARY_SURGE.getMaxTime()));
	}

	/**
	 * isBetweenSurge checks whether the time is under peak surge
	 * @param currentTime
	 * @return boolean
	 */
	private boolean isBetweenSurge(LocalTime currentTime) {
		return (currentTime.isAfter(SurgeTimeChart.MORNING_SURGE.getMinTime())&&
				currentTime.isBefore(SurgeTimeChart.MORNING_SURGE.getMaxTime()))
				|| (currentTime.isAfter(SurgeTimeChart.MID_DAY_SURGE.getMinTime())&&
						currentTime.isBefore(SurgeTimeChart.MID_DAY_SURGE.getMaxTime()))
				||(currentTime.isAfter(SurgeTimeChart.DAY_END_SURGE.getMinTime())&&
						currentTime.isBefore(SurgeTimeChart.DAY_END_SURGE.getMaxTime()));
	}

}
