package com.wechat.event;

public class LocationEvent extends BaseEvent {
	// 地理位置纬度
	private double Latitude;
	// 地理位置经度
	private double Longitude;
	// 地理位置精度
	private double Precision;

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public double getPrecision() {
		return Precision;
	}

	public void setPrecision(double precision) {
		Precision = precision;
	}

}
