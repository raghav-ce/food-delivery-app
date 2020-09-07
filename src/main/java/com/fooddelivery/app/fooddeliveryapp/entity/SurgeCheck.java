package com.fooddelivery.app.fooddeliveryapp.entity;

/**
 * PeakType is the class containing the information of the surge check
 * @author RAGHAV E
 *
 */
public class SurgeCheck {

	private String currentTime;
	
	private int cartAmount;
	
	public SurgeCheck() {
		
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public int getCartAmount() {
		return cartAmount;
	}

	public void setCartAmount(int cartAmount) {
		this.cartAmount = cartAmount;
	}

	public SurgeCheck(String currentTime, int cartAmount) {
		this.currentTime = currentTime;
		this.cartAmount = cartAmount;
	}
	
}
