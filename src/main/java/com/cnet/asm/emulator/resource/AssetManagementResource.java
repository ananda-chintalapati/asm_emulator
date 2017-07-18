package com.cnet.asm.emulator.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnet.asm.emulator.entity.Device;
import com.cnet.asm.emulator.model.AssetRequest;
import com.cnet.asm.emulator.service.AssetManagementService;

@Component
@Path("/emulator")
public class AssetManagementResource {
	
	@Autowired
	AssetManagementService asmService;
	
	@POST
	@Path("/scan")
	public List<Device> scanDevices(AssetRequest assetRequest) {
		asmService.saveAssetDetails(assetRequest);
		return asmService.getDeviceList(assetRequest.getReqNumber());
	}
	
	
	@POST
	@Path("/pdu")
	public List<Device> pduScan(AssetRequest assetRequest) {
		asmService.saveAssetDetails(assetRequest);
		return asmService.getDeviceList(assetRequest.getReqNumber());
	}
	
	@POST
	@Path("/network")
	public List<Device> addNetworkDetails(AssetRequest assetRequest) {
		asmService.saveAssetDetails(assetRequest);
		return asmService.getDeviceList(assetRequest.getReqNumber());
	}
	
	@POST
	@Path("/ilomcheck")
	public List<Device> checkPing(AssetRequest assetRequest) {
		asmService.saveAssetDetails(assetRequest);
		return asmService.getDeviceList(assetRequest.getReqNumber());
	}
	
	@POST
	@Path("/bootstrap")
	public List<Device> bootstrapDevice(AssetRequest assetRequest) {
		asmService.saveAssetDetails(assetRequest);
		return asmService.getDeviceList(assetRequest.getReqNumber());
	}
	
	
	@GET
	@Path("/{reqNumber}")
	public List<Device> getDeviceList(String reqNumber) {
		return asmService.getDeviceList(reqNumber);
	}
	
	@GET
	@Path("/hello")
	public String hello() {
		return "Welcome to ASM Emulator";
	}
}
