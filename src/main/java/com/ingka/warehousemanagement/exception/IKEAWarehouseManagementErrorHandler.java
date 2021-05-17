package com.ingka.warehousemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IKEAWarehouseManagementErrorHandler extends RuntimeException {

	public IKEAWarehouseManagementErrorHandler(String warehouseManagementService){
		super(String.format("%s not found", warehouseManagementService));
	}

}