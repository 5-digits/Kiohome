package com.devices;

public class SmartPlug extends BasedDevice{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean status;
	int numberOfPlug;
	
	public SmartPlug() {
		super();
	}
	public SmartPlug(boolean status, int numberOfPlug) {
		super();
		this.status = status;
		this.numberOfPlug = numberOfPlug;
	}
	
	
	public SmartPlug(String deviceID, String deviceName,
			String deviceMac_addr, String description) {
		super(deviceID, deviceName, deviceMac_addr, description);
	}
	public boolean getCurrentStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getNumberOfPlug() {
		return numberOfPlug;
	}
	public void setNumberOfPlug(int numberOfPlug) {
		this.numberOfPlug = numberOfPlug;
	}
	public SmartPlug(boolean status) {
		this.status = status;
	}
	
	

}
