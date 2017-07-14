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
	@Path("/pdu")
	public void saveNewASMRequest(AssetRequest assetRequest) {
		asmService.saveAssetDetails(assetRequest);
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
