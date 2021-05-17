package com.ingka.warehousemanagement.controller;

import com.ingka.warehousemanagement.exception.IKEAWarehouseManagementErrorHandler;
import com.ingka.warehousemanagement.model.*;
import com.ingka.warehousemanagement.repository.*;
import com.ingka.warehousemanagement.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.List;

/**
 * Inventory Controller which provides REST API for Inventory Service 
 * GET, POST, PUT and DELETE
 * Created by Shabaz Mohsin G  on 17/05/2021.
 */
@RestController
@RequestMapping("/api/v1.0/warehouseamanagement/inventory")
public class IKEAInventoryController {

	/** REST Services for Inventory --START */
	
	@Autowired
	private IKEAInventoryRepository inventoryRepository;
	
	@PersistenceContext 
	private EntityManager entityManager;
	
	@GetMapping("/list")
	public List<IKEAInventory> getAllInventory() {
		return inventoryRepository.findAll();
	}

	@GetMapping("/{id}")
	public IKEAInventory getInventoryById(@PathVariable(value = IKEAConstants.A_ID) Long id) {
		return inventoryRepository.findById(id)
				.orElseThrow(() -> new IKEAWarehouseManagementErrorHandler(IKEAConstants.S_INVENTORY));
	}

	@PostMapping("")
	public List<IKEAInventory> createInventory(@Valid @RequestBody IKEAInventoryList inventoryList) {
		return inventoryRepository.saveAll(inventoryList.getInventory());
	}
	
	@PutMapping("/{id}")
	public IKEAInventory updateInventory(@PathVariable(value = IKEAConstants.A_ID) Long id, @Valid @RequestBody IKEAInventory inventoryDetails) {

		IKEAInventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new IKEAWarehouseManagementErrorHandler(IKEAConstants.S_INVENTORY));
		inventory.setName(inventoryDetails.getName());
		inventory.setArtId(inventoryDetails.getArtId());
		inventory.setStock(inventoryDetails.getStock());

		IKEAInventory updatedInventory = inventoryRepository.save(inventory);
		return updatedInventory;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<IKEAInventory> deleteInventory(@PathVariable(value = IKEAConstants.A_ID) Long id) {

		IKEAInventory inventory = inventoryRepository.findById(id)
				.orElseThrow(() -> new IKEAWarehouseManagementErrorHandler(IKEAConstants.S_INVENTORY));
		
		inventoryRepository.delete(inventory);
		return ResponseEntity.ok().build();
	}
	/** REST Services for Inventory --END */
	
}

