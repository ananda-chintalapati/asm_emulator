package com.cnet.asm.emulator.model;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "network_info")
public class NetworkInfo {

	private String domain;
	
	private String ipRange;
	
	private String portInfo;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getIpRange() {
		return ipRange;
	}

	public void setIpRange(String ipRange) {
		this.ipRange = ipRange;
	}

	public String getPortInfo() {
		return portInfo;
	}

	public void setPortInfo(String portInfo) {
		this.portInfo = portInfo;
	}
	
	
}
