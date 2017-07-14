package com.cnet.asm.emulator.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PDU_REQUEST")
public class PDURequest {

	@Id
	@Column(name="REQ_NUMBER")
	private String reqNumber;
	
	@Column(name="PDU_NUMBER")
	private String pduNumber;
	
	@Column(name="PDU_LOCATION")
	private String pduLocation;
	
	@OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "reqNumber", nullable = false)
	private List<Device> deviceEntity = new ArrayList<>();
	

	public String getReqNumber() {
		return reqNumber;
	}

	public void setReqNumber(String reqNumber) {
		this.reqNumber = reqNumber;
	}

	public String getPduNumber() {
		return pduNumber;
	}

	public void setPduNumber(String pduNumber) {
		this.pduNumber = pduNumber;
	}

	public String getPduLocation() {
		return pduLocation;
	}

	public void setPduLocation(String pduLocation) {
		this.pduLocation = pduLocation;
	}

	public List<Device> getDeviceEntity() {
		return deviceEntity;
	}

	public void setDeviceEntity(List<Device> deviceEntity) {
		this.deviceEntity = deviceEntity;
	}
	
	
	
}
