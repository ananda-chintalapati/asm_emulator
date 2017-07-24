package com.cnet.asm.emulator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cnet.asm.emulator.entity.Device;
import com.cnet.asm.emulator.entity.PDURequest;
import com.cnet.asm.emulator.model.DeviceInfo;
import com.cnet.asm.emulator.model.NetworkInfo;
import com.cnet.asm.emulator.model.Request;
import com.cnet.asm.emulator.repository.DeviceRepository;
import com.cnet.asm.emulator.repository.PDURepository;
import com.mysql.jdbc.StringUtils;


@Component
@Transactional
public class AssetManagementService {
	
	@Autowired
	DeviceRepository deviceRepository;
	
	@Autowired
	PDURepository pduRepository;

	@Transactional
	public Iterable<Device> saveAssetDetails(Request assetRequest) {
		savePduRequest(assetRequest.getPduNumber(),
											  assetRequest.getReqNumber());
		persistDeviceData(assetRequest);
		return getDeviceList(assetRequest.getReqNumber());
	}
	
	public List<Device> getDeviceList(String reqNumber) {
		PDURequest pduRequest = new PDURequest();
		pduRequest.setReqNumber(reqNumber);
		List<Device> deviceList = deviceRepository.findByReqNumber(reqNumber);
		return deviceList;
	}
	
	private void persistDeviceData(Request assetRequest) {
		saveDeviceData(assetRequest.getChasisInfo(), assetRequest.getReqNumber(), assetRequest.getCtaskNumber());
		saveDeviceData(assetRequest.getRouterInfo(), assetRequest.getReqNumber(), assetRequest.getCtaskNumber());
		saveDeviceData(assetRequest.getServerInfo(), assetRequest.getReqNumber(), assetRequest.getCtaskNumber());
		saveDeviceData(assetRequest.getSwitchInfo(), assetRequest.getReqNumber(), assetRequest.getCtaskNumber());
	}
	
	public Iterable<Device> savePDUData(Request assetRequest) {
		saveDeviceData(assetRequest.getPduInfo(), assetRequest.getReqNumber(),
					   assetRequest.getCtaskNumber());
		return getDeviceList(assetRequest.getReqNumber());
	}
	
	private Iterable<Device> saveDeviceData(DeviceInfo deviceInfo, String reqNumber, String cTaskNumber) {
		System.out.println("Device Info " + deviceInfo.toString());
		List<Device> deviceList = new ArrayList<>();
		IntStream.range(0, deviceInfo.getQty()).forEach(
				index -> {
					Device device = new Device();
					device.setDeviceType(deviceInfo.getDeviceType());
					device.setMacAddress(randomMACAddress());
					device.setMfrName(deviceInfo.getDeviceType());
					device.setModelNumber(deviceInfo.getModelNumber());
					device.setSerialNumber(UUID.randomUUID().toString());
					device.setStatus("OK");
					//device.setPduRequestEntity(pduRequest);
					device.setReqNumber(reqNumber);
					device.setcTaskNumber(cTaskNumber);
					deviceList.add(device);
				});
		return deviceRepository.save(deviceList);
	}
	
	@Transactional
	public Iterable<Device> saveNetworkDetails(Request assetRequest) {
		List<Device> deviceList = getDeviceList(assetRequest.getReqNumber());
		NetworkInfo networkInfo = assetRequest.getNetworkInfo();
		String[] ipRange = networkInfo.getIpRange().split("\\-");
		List<String> ipList = getIPAddressList(ipRange[0], ipRange[1], 0);
		for(Device device:deviceList) {
			device.setDomain(networkInfo.getDomain());
			device.setIpAddress(ipList.get(deviceList.indexOf(device)));
		}
		deviceRepository.save(deviceList);
		return getDeviceList(assetRequest.getReqNumber());
	}
	
	private PDURequest savePduRequest(String pduNumber, String reqNumber) {
		System.out.println("Req Number : "+ reqNumber);
		PDURequest pduRequest = new PDURequest();
		pduRequest.setPduLocation("");
		pduRequest.setPduNumber(pduNumber);
		pduRequest.setReqNumber(reqNumber);
		return pduRepository.save(pduRequest);
	}
	
	private String randomMACAddress(){
	    Random rand = new Random();
	    byte[] macAddr = new byte[6];
	    rand.nextBytes(macAddr);
	    macAddr[0] = (byte)(macAddr[0] & (byte)254);
	    StringBuilder sb = new StringBuilder(18);
	    for(byte b : macAddr){
	        if(sb.length() > 0)
	            sb.append(":");
	        sb.append(String.format("%02x", b));
	    }
	    return sb.toString();
	}

	private List<String> getIPAddressList(String ipStart, String ipEnd, int qty) {
		if(StringUtils.isNullOrEmpty(ipStart)) {
			new Exception("IP range missing");
		}
		StringJoiner joiner = new StringJoiner(".");
		String[] ipStartOcts = ipStart.split("\\.");
		joiner.add(ipStartOcts[0]).add(ipStartOcts[1]).add(ipStartOcts[2]);
		if(!StringUtils.isNullOrEmpty(ipEnd)) {
			String[] ipEndOcts = ipEnd.split("\\.");
			qty = Integer.parseInt(ipEndOcts[3]) - Integer.parseInt(ipStartOcts[3]);
		}
		String baseIP = joiner.toString();
		int lastIP = getLastIssuedIPAddress(baseIP);
		if(lastIP + qty > 254) {
			new Exception("Not enough IP addresses are available");
		}
		List<String> ipList = new ArrayList<>();
		IntStream.range(0, qty).forEach(
				index -> ipList.add(baseIP + "." + (lastIP+index)));
		return ipList;
	}
	
	private int getLastIssuedIPAddress(String ipSearchValue) {
		
		List<Device> deviceList = deviceRepository.findByIpAddressStartingWith(ipSearchValue);
		List<String> ipList = deviceList.stream().map(Device::getIpAddress).collect(Collectors.toList());
		int lastIP = 1;
		for(String ip:ipList) {
			int index = Integer.parseInt(ip.split(".")[3]);
			lastIP = (lastIP < index)?index:lastIP;
		}
		return lastIP;
	}
}
