package com.cnet.asm.emulator.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cnet.asm.emulator.entity.Device;

public interface DeviceRepository extends CrudRepository<Device, Integer> {

	List<Device> findByReqNumber(String requestNumber);
	
	//List<Device> findByPduRequest(PDURequest pduRequest);
	
	List<Device> findByIpAddressStartingWith(String ipAddress);
}
