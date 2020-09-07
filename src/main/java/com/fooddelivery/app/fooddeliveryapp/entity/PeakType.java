package com.fooddelivery.app.fooddeliveryapp.entity;

/**
 * PeakType is the class containing the information of the surge percentage
 * @author RAGHAV E
 *
 */
public enum PeakType {
	
	NO_PEAK(0),
	
	NORMAL(10),
	
	PEAK(20);

	private final int peakPercentage;
	
	 PeakType(int peakPercentage) {
		 this.peakPercentage = peakPercentage;
	 }

	public int getPeakPercentage() {
		return peakPercentage;
	}
	 
}
