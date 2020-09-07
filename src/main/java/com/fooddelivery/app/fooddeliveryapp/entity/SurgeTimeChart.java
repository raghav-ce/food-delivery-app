package com.fooddelivery.app.fooddeliveryapp.entity;

import java.time.LocalTime;

/**
 * SurgeTimeChart is the class containing the information of the surge timings
 * @author RAGHAV E
 *
 */
public enum SurgeTimeChart {
	
	MORNING_NORMAL_SURGE(LocalTime.of(8, 0),LocalTime.of(8, 30), PeakType.NORMAL),
	
	MORNING_SURGE(LocalTime.of(8, 31),LocalTime.of(9, 0),PeakType.PEAK),
	
	MORNING_SECONDARY_SURGE(LocalTime.of(9, 1),LocalTime.of(9, 30),PeakType.NORMAL),
	
	MID_DAY_NORMAL_SURGE(LocalTime.of(12, 30),LocalTime.of(13, 0), PeakType.NORMAL),
	
	MID_DAY_SURGE(LocalTime.of(13, 1),LocalTime.of(13, 30),PeakType.PEAK),
	
	MID_DAY_SECONDARY_SURGE(LocalTime.of(13, 31),LocalTime.of(14, 00), PeakType.NORMAL),
	
	DAY_END_NORMAL_SURGE(LocalTime.of(20, 0),LocalTime.of(20, 30), PeakType.NORMAL),
	
	DAY_END_SURGE(LocalTime.of(20, 31),LocalTime.of(21, 0),PeakType.PEAK),
	
	DAY_END_SECONDARY_SURGE(LocalTime.of(21, 1),LocalTime.of(21, 30),PeakType.NORMAL);

	private SurgeTimeChart(LocalTime minTime, LocalTime maxTime, PeakType peakType) {
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.peakType = peakType;
	}

	private final LocalTime minTime;
	
	private final LocalTime maxTime;
	
	private PeakType peakType;

	public PeakType getPeakType() {
		return peakType;
	}

	public void setPeakType(PeakType peakType) {
		this.peakType = peakType;
	}

	public LocalTime getMinTime() {
		return minTime;
	}

	public LocalTime getMaxTime() {
		return maxTime;
	}
	
}
