package com.devices;

public class Sensor extends BasedDevice{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	double currentTemperature;
	double currentGas;
	double currentHudmity;
	boolean isLowBattery;

	
	public Sensor() {
		super();
	}
	public Sensor(double currentTemperature, double currentGas, double currentHudmity, boolean isLowBattery) {
		super();
		this.currentTemperature = currentTemperature;
		this.currentGas = currentGas;
		this.currentHudmity = currentHudmity;
		this.isLowBattery = isLowBattery;
	}
	public double getCurrentTemperature() {
		return currentTemperature;
	}
	public void setCurrentTemperature(double currentTemperature) {
		this.currentTemperature = currentTemperature;
	}
	public double getCurrentGas() {
		return currentGas;
	}
	public void setCurrentGas(double currentGas) {
		this.currentGas = currentGas;
	}
	public double getCurrentHudmity() {
		return currentHudmity;
	}
	public void setCurrentHudmity(double currentHudmity) {
		this.currentHudmity = currentHudmity;
	}
	public boolean isLowBattery() {
		return isLowBattery;
	}
	public void setLowBattery(boolean isLowBattery) {
		this.isLowBattery = isLowBattery;
	}
	@Override
	public String toString() {
		return "Sensor [currentTemperature=" + currentTemperature + ", currentGas=" + currentGas + ", currentHudmity="
				+ currentHudmity + ", isLowBattery=" + isLowBattery + "]";
	}
	

}
