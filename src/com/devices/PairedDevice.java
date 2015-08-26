package com.devices;

public class PairedDevice extends BasedDevice{


	private static final long serialVersionUID = 1L;
	private String data;
	private String description;
	private String mac_addr;
	

	public PairedDevice() {
		super();
	}
	public PairedDevice(String data, String description, String mac_addr) {
		super();
		this.data = data;
		this.description = description;
		this.mac_addr = mac_addr;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMac_addr() {
		return mac_addr;
	}
	public void setMac_addr(String mac_addr) {
		this.mac_addr = mac_addr;
	}
	@Override
	public String toString() {
		return "PairedDevice [data=" + data + ", description=" + description
				+ ", mac_addr=" + mac_addr + "]";
	}

}
