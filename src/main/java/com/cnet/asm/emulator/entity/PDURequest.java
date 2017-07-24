package com.cnet.asm.emulator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
	
//	@OneToMany(mappedBy = "pduRequest", orphanRemoval = true, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//	@JsonIgnoreProperties("pduRequestEntity")
//	private Set<Device> deviceEntity;
	

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

//	public Set<Device> getDeviceEntity() {
//		return deviceEntity;
//	}
//
//	public void setDeviceEntity(Set<Device> deviceEntity) {
//		this.deviceEntity = deviceEntity;
//	}
	
	
	
}
