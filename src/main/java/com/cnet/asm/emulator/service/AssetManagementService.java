package com.cnet.asm.emulator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnet.asm.emulator.entity.Device;
import com.cnet.asm.emulator.entity.PDURequest;
import com.cnet.asm.emulator.model.AssetRequest;
import com.cnet.asm.emulator.repository.DeviceRepository;
import com.cnet.asm.emulator.repository.PDURepository;
import com.mysql.jdbc.StringUtils;


@Component
public class AssetManagementService {
	
	@Autowired
	DeviceRepository deviceRepository;
	
	@Autowired
	PDURepository pduRepository;

	public void saveAssetDetails(AssetRequest assetRequest) {
		PDURequest pduRequest = savePduRequest(assetRequest.getPduNumber(),
											  assetRequest.getReqNumber());
		saveDeviceData(assetRequest, pduRequest);
	}
	
	public List<Device> getDeviceList(String reqNumber) {
		List<Device> deviceList = deviceRepository.findByReqNumber(reqNumber);
		return deviceList;
	}
	
	private Iterable<Device> saveDeviceData(AssetRequest assetRequest, PDURequest pduRequest) {
		List<String> ipList = getIPAddressList(assetRequest.getIpStart(), assetRequest.getQty());
		List<Device> deviceList = new ArrayList<>();
		IntStream.range(1, assetRequest.getQty()).forEach(
				index -> {
					Device device = new Device();
					device.setDeviceType(assetRequest.getDeviceType());
					device.setDomain(assetRequest.getDomain());
					device.setIpAddress(ipList.get(index));
					device.setMacAddress(randomMACAddress());
					device.setMfrName(assetRequest.getDeviceType());
					device.setModelNumber(assetRequest.getModelNumber());
					device.setSerialNumber(UUID.randomUUID().toString());
					device.setStatus("OK");
					device.setPduRequestEntity(pduRequest);
					deviceList.add(device);
				});
		return deviceRepository.save(deviceList);
	}
	
	private PDURequest savePduRequest(String pduNumber, String reqNumber) {
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

	private List<String> getIPAddressList(String ipStart, int qty) {
		if(StringUtils.isNullOrEmpty(ipStart)) {
			new Exception("IP range missing");
		}
		StringJoiner joiner = new StringJoiner(",");
		String[] ipOcts = ipStart.split(".");
		joiner.add(ipOcts[0]).add(ipOcts[1]).add(ipOcts[2]);
		int lastIP = getLastIssuedIPAddress(joiner.toString());
		if(lastIP + qty > 254) {
			new Exception("Not enough IP addresses are available");
		}
		List<String> ipList = new ArrayList<>();
		IntStream.range(1, qty).forEach(
				index -> ipList.add(joiner.add(lastIP+index+"").toString()));
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
