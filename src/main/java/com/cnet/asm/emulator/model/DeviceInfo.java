package com.cnet.asm.emulator.model;

public class DeviceInfo {

	private String manufacturer;
	
	private String deviceType;
	
	private String modelNumber;
	
	private int qty;

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "DeviceInfo [manufacturer=" + manufacturer + ", deviceType=" + deviceType + ", modelNumber="
				+ modelNumber + ", qty=" + qty + "]";
	}
	
	
}
