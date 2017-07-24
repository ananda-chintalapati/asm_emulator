package com.cnet.asm.emulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "request")
public class Request {

	private String reqNumber;
	
	private String ctaskNumber;
	
	private String ipList;
	
	private String status;
	
	private String pduNumber;
	
	@JsonProperty("chasis_info")
	private ChasisInfo chasisInfo;
	
	@JsonProperty("network_info")
	private NetworkInfo networkInfo;
	
	@JsonProperty("pdu_info")
	private PduInfo pduInfo;
	
	@JsonProperty("router_info")
	private RouterInfo routerInfo;
	
	@JsonProperty("server_info")
	private ServerInfo serverInfo;
	
	@JsonProperty("switch_info")
	private SwitchInfo switchInfo;

	public String getReqNumber() {
		return reqNumber;
	}

	public void setReqNumber(String reqNumber) {
		this.reqNumber = reqNumber;
	}

	public String getCtaskNumber() {
		return ctaskNumber;
	}

	public void setCtaskNumber(String ctaskNumber) {
		this.ctaskNumber = ctaskNumber;
	}

	public String getIpList() {
		return ipList;
	}

	public void setIpList(String ipList) {
		this.ipList = ipList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPduNumber() {
		return pduNumber;
	}

	public void setPduNumber(String pduNumber) {
		this.pduNumber = pduNumber;
	}

	public ChasisInfo getChasisInfo() {
		return chasisInfo;
	}

	public void setChasisInfo(ChasisInfo chasisInfo) {
		this.chasisInfo = chasisInfo;
	}

	public NetworkInfo getNetworkInfo() {
		return networkInfo;
	}

	public void setNetworkInfo(NetworkInfo networkInfo) {
		this.networkInfo = networkInfo;
	}

	public PduInfo getPduInfo() {
		return pduInfo;
	}

	public void setPduInfo(PduInfo pduInfo) {
		this.pduInfo = pduInfo;
	}

	public RouterInfo getRouterInfo() {
		return routerInfo;
	}

	public void setRouterInfo(RouterInfo routerInfo) {
		this.routerInfo = routerInfo;
	}

	public ServerInfo getServerInfo() {
		return serverInfo;
	}

	public void setServerInfo(ServerInfo serverInfo) {
		this.serverInfo = serverInfo;
	}

	public SwitchInfo getSwitchInfo() {
		return switchInfo;
	}

	public void setSwitchInfo(SwitchInfo switchInfo) {
		this.switchInfo = switchInfo;
	}

	@Override
	public String toString() {
		return "Request [reqNumber=" + reqNumber + ", ctaskNumber=" + ctaskNumber + ", ipList=" + ipList + ", status="
				+ status + ", pduNumber=" + pduNumber + ", chasisInfo=" + chasisInfo + ", networkInfo=" + networkInfo
				+ ", pduInfo=" + pduInfo + ", routerInfo=" + routerInfo + ", serverInfo=" + serverInfo + ", switchInfo="
				+ switchInfo + "]";
	}
	
	
}
