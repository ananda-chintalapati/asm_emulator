package com.cnet.asm.emulator.repository;

import org.springframework.data.repository.CrudRepository;

import com.cnet.asm.emulator.entity.PDURequest;

public interface PDURepository extends CrudRepository<PDURequest, String>  {

}
