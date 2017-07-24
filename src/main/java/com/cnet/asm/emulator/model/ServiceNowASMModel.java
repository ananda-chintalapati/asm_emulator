package com.cnet.asm.emulator.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ServiceNowASMModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5087781760650976524L;

	private String u_manufacturer;
	
	private String u_model;
	
	private String u_serial_number;
	
	private String u_mac_address;
	
	private String u_ip_address;
	
	private String u_device_type;
	
	private String u_domain;
	
	private String u_status;
	
	private String u_req_number;
	
	private String u_ctask_no;

	public String getU_manufacturer() {
		return u_manufacturer;
	}

	public void setU_manufacturer(String u_manufacturer) {
		this.u_manufacturer = u_manufacturer;
	}

	public String getU_model() {
		return u_model;
	}

	public void setU_model(String u_model) {
		this.u_model = u_model;
	}

	public String getU_serial_number() {
		return u_serial_number;
	}

	public void setU_serial_number(String u_serial_number) {
		this.u_serial_number = u_serial_number;
	}

	public String getU_mac_address() {
		return u_mac_address;
	}

	public void setU_mac_address(String u_mac_address) {
		this.u_mac_address = u_mac_address;
	}

	public String getU_ip_address() {
		return u_ip_address;
	}

	public void setU_ip_address(String u_ip_address) {
		this.u_ip_address = u_ip_address;
	}

	public String getU_device_type() {
		return u_device_type;
	}

	public void setU_device_type(String u_device_type) {
		this.u_device_type = u_device_type;
	}

	public String getU_domain() {
		return u_domain;
	}

	public void setU_domain(String u_domain) {
		this.u_domain = u_domain;
	}

	public String getU_status() {
		return u_status;
	}

	public void setU_status(String u_status) {
		this.u_status = u_status;
	}

	public String getU_req_number() {
		return u_req_number;
	}

	public void setU_req_number(String u_req_number) {
		this.u_req_number = u_req_number;
	}

	public String getU_ctask_no() {
		return u_ctask_no;
	}

	public void setU_ctask_no(String u_ctask_no) {
		this.u_ctask_no = u_ctask_no;
	}

	@Override
	public String toString() {
		return "ServiceNowASMModel [u_manufacturer=" + u_manufacturer + ", u_model=" + u_model + ", u_serial_number="
				+ u_serial_number + ", u_mac_address=" + u_mac_address + ", u_ip_address=" + u_ip_address
				+ ", u_device_type=" + u_device_type + ", u_domain=" + u_domain + ", u_status=" + u_status
				+ ", u_req_number=" + u_req_number + ", u_ctask_no=" + u_ctask_no + "]";
	}

	 public String toJsonString() {
		 ObjectWriter ow = new ObjectMapper().writer().withoutRootName();
		 String json = null;
		try {
			json = ow.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		 return json;
	 }
	
}
