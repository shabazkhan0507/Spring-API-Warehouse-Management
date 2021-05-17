package com.ingka.warehousemanagement.model;


import java.util.List;

/**
 * Inventory List POJO Class
 * Created by Shabaz Mohsin G on 17/05/2021.
 */

public class IKEAInventoryList {
	
	private List<IKEAInventory> inventory;

	public List<IKEAInventory> getInventory() {
		return inventory;
	}

	public void setInventory(List<IKEAInventory> inventory) {
		this.inventory = inventory;
	}

}
