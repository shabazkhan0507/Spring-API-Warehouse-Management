package com.ingka.warehousemanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ingka.warehousemanagement.util.IKEAConstants;
import com.ingka.warehousemanagement.util.IKEADataBaseAttributes;

/**
 * Inventory - mapped to inventory Data entity
 * Created by Shabaz Mohsin G  17/05/2021
 */

@Entity
@Table(name = IKEADataBaseAttributes.T_INVENTORY)
public class IKEAInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = IKEADataBaseAttributes.C_ART_ID)
	@JsonProperty(IKEAConstants.A_ART_ID)
	private String artId;

	@Column(name = IKEADataBaseAttributes.C_INVENTORY_NAME)
	private String name;

	@Column(name = IKEADataBaseAttributes.C_INVENTORY_STOCK)
	private String stock;

	public String getArtId() {
		return artId;
	}

	public void setArtId(String artId) {
		this.artId = artId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}
}
