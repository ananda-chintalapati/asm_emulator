package com.cnet.asm.emulator.client;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cnet.asm.emulator.entity.Device;
import com.cnet.asm.emulator.model.ServiceNowASMModel;
import com.mysql.jdbc.StringUtils;

@Component
public class ServiceNowClient {
	
	public String SN_URL = "https://dev21652.service-now.com/api/now/table/u_asset_temp";
	
	public String SN_USER = "aims";
	
	public String SN_PWD = "aims";

	
	public Object callServiceNow(String url, ServiceNowASMModel data) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", createHeaders(SN_USER, SN_PWD));
		HttpEntity<String> requestData = new HttpEntity<String>(data.toJsonString(), headers);
		ResponseEntity<String> response = restTemplate.exchange(SN_URL, HttpMethod.POST, requestData,
				String.class);
		return response;
	}
	
	public ServiceNowASMModel sendDeviceDataToSN(List<Device> deviceList) {
		
		for(Device device:deviceList) {
			ServiceNowASMModel snModel = new ServiceNowASMModel();
			snModel.setU_device_type(device.getDeviceType());
			if(!StringUtils.isNullOrEmpty(device.getDomain())) {
				snModel.setU_domain(device.getDomain());
			}else {
				snModel.setU_domain("");
			}
			snModel.setU_ip_address(device.getIpAddress());
			snModel.setU_mac_address(device.getMacAddress());
			snModel.setU_manufacturer(device.getMfrName());
			snModel.setU_model(device.getModelNumber());
			snModel.setU_req_number(device.getReqNumber());
			snModel.setU_serial_number(device.getSerialNumber());
			snModel.setU_status(device.getStatus());
			snModel.setU_ctask_no(device.getcTaskNumber());
			callServiceNow(SN_URL, snModel);
		}
		return null;
	}
	
	private String createHeaders(String username, String password){
		   String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		   return "Basic " + new String( encodedAuth );
		}
}


