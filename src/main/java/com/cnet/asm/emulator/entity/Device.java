package com.cnet.asm.emulator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DEVICE")
public class Device {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="MFR_NAME")
	private String mfrName;
	
	@Column(name="DEVICE_TYPE")
	private String deviceType;
	
	@Column(name="MODEL_NUMBER")
	private String modelNumber;
	
	@Column(name="SERIAL_NUMBER")
	private String serialNumber;
	
	@Column(name="MAC_ADDRESS")
	private String macAddress;
	
	@Column(name="IP_ADDRESS")
	private String ipAddress;
	
	@Column(name="DOMAIN")
	private String domain;
	
	@Column(name="STATUS")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="REQ_NUMBER", referencedColumnName="REQ_NUMBER")
	private PDURequest pduRequestEntity;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMfrName() {
		return mfrName;
	}

	public void setMfrName(String mfrName) {
		this.mfrName = mfrName;
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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PDURequest getPduRequestEntity() {
		return pduRequestEntity;
	}

	public void setPduRequestEntity(PDURequest pduRequestEntity) {
		this.pduRequestEntity = pduRequestEntity;
	}
	
	
	
}
