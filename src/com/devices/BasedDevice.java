package com.devices;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.helperClass.Constants;


public class BasedDevice implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private String deviceID;
	private String deviceName;
	private String deviceType;
	private String deviceMac_addr="xx:xx:xx";
	private String description;
	private String status;

	


	public BasedDevice() {
		super();
	}

	public BasedDevice(String deviceID, String deviceName, String deviceType,
			String deviceMac_addr) {
		super();
		this.deviceID = deviceID;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.deviceMac_addr = deviceMac_addr;
	}
	



	public BasedDevice(String deviceID, String deviceName,
			String deviceMac_addr, String description, String status) {
		super();
		this.deviceID = deviceID;
		this.deviceName = deviceName;
		this.deviceMac_addr = deviceMac_addr;
		this.description = description;
		this.status = status;
	}

	public BasedDevice(String deviceID, String deviceName, String deviceType,
			String deviceMac_addr, String description, String status) {
		super();
		this.deviceID = deviceID;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.deviceMac_addr = deviceMac_addr;
		this.description = description;
		this.status = status;
	}

	public BasedDevice(String deviceID, String deviceName, String deviceType) {
		super();
		this.deviceID = deviceID;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		
	}
	
	

	public String getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeviceMac_addr() {
			return deviceMac_addr;
		}

	public void setDeviceMac_addr(String deviceMac_addr) {
		this.deviceMac_addr = deviceMac_addr;
	}
	
	
	@Override
	public String toString() {
		return "Devices [deviceID=" + deviceID + ", deviceName=" + deviceName
				+ ", deviceType=" + deviceType + "]";
	}

	public List<NameValuePair> convertDeviceInforToParam(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(Constants.TAG_DEV_ID, deviceID));
		params.add(new BasicNameValuePair(Constants.TAG_DEV_MAC_ADDR, deviceMac_addr));
		params.add(new BasicNameValuePair(Constants.TAG_DEV_TYPE, deviceType));

		return params;
	}


	
	
	
}
