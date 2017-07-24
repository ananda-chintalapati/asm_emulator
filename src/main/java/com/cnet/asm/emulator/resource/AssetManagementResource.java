package com.cnet.asm.emulator.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cnet.asm.emulator.client.ServiceNowClient;
import com.cnet.asm.emulator.entity.Device;
import com.cnet.asm.emulator.model.Request;
import com.cnet.asm.emulator.service.AssetManagementService;

@RestController
@Path("/emulator")
@Produces(MediaType.APPLICATION_JSON)
public class AssetManagementResource {
	
	@Autowired
	AssetManagementService asmService;
	
	@Autowired
	ServiceNowClient snClient;
	
	@POST
	@Path("/scan")
	public List<Device> scanDevices(Request assetRequest) {
		System.out.println("Input request " + assetRequest.toString());
		asmService.saveAssetDetails(assetRequest);
		List<Device> deviceList = asmService.getDeviceList(assetRequest.getReqNumber());
		snClient.sendDeviceDataToSN(deviceList);
		return deviceList;
	}
	
	
	@POST
	@Path("/pdu")
	public List<Device> pduScan(Request assetRequest) {
		asmService.savePDUData(assetRequest);
		List<Device> deviceList = asmService.getDeviceList(assetRequest.getReqNumber());
		snClient.sendDeviceDataToSN(deviceList);
		return deviceList;
	}
	
	@POST
	@Path("/network")
	public List<Device> addNetworkDetails(Request assetRequest) {
		asmService.saveNetworkDetails(assetRequest);
		List<Device> deviceList = asmService.getDeviceList(assetRequest.getReqNumber());
		snClient.sendDeviceDataToSN(deviceList);
		return deviceList;
	}
	
	@POST
	@Path("/ilomcheck")
	public List<Device> checkPing(Request assetRequest) {
		//asmService.saveAssetDetails(assetRequest);
		List<Device> deviceList = asmService.getDeviceList(assetRequest.getReqNumber());
		snClient.sendDeviceDataToSN(deviceList);
		return deviceList;
	}
	
	@POST
	@Path("/bootstrap")
	public List<Device> bootstrapDevice(Request assetRequest) {
		//asmService.saveAssetDetails(assetRequest);
		List<Device> deviceList = asmService.getDeviceList(assetRequest.getReqNumber());
		snClient.sendDeviceDataToSN(deviceList);
		return deviceList;
	}
	
	
	@GET
	@Path("/{reqNumber}")
	public List<Device> getDeviceList(@PathParam("reqNumber") String reqNumber) {
		List<Device> deviceList = asmService.getDeviceList(reqNumber);
		snClient.sendDeviceDataToSN(deviceList);
		return deviceList;
	}
	
	@GET
	@Path("/hello")
	public String hello() {
		return "Welcome to ASM Emulator";
	}
}
