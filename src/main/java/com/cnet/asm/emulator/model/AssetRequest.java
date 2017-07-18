package com.cnet.asm.emulator.model;

public class AssetRequest {

	private String pduNumber;
	
	private String reqNumber;
	
	private String deviceType;
	
	private String modelNumber;
	
	private int qty;
	
	private String ipStart;
	
	private String domain;
	
	private String mfrName;


	public String getPduNumber() {
		return pduNumber;
	}

	public void setPduNumber(String pduNumber) {
		this.pduNumber = pduNumber;
	}

	public String getReqNumber() {
		return reqNumber;
	}

	public void setReqNumber(String reqNumber) {
		this.reqNumber = reqNumber;
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

	public String getIpStart() {
		return ipStart;
	}

	public void setIpStart(String ipStart) {
		this.ipStart = ipStart;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getMfrName() {
		return mfrName;
	}

	public void setMfrName(String mfrName) {
		this.mfrName = mfrName;
	}
	
	
}
