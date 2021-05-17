package com.ingka.warehousemanagement.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ingka.warehousemanagement.util.*;
/**
 * Associate Articles - mapped to contain_articles Data entity
 * Created by Shabaz Mohsin G  17/05/2021
 */

@Entity
@Table(name = IKEADataBaseAttributes.T_CONTAIN_ARTICLES)
public class IKEAAssociateArticles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = IKEADataBaseAttributes.C_ART_ID)
	@JsonProperty(IKEAConstants.A_ART_ID)
	private String artId;

	@Column(name = IKEADataBaseAttributes.C_AMOUNT_OF)
	@JsonProperty(IKEAConstants.A_AMOUNT_OF)
	private String amountOf;

	@ManyToOne(targetEntity = IKEAProduct.class,fetch = FetchType.LAZY)
	@JsonBackReference
	private IKEAProduct product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtId() {
		return artId;
	}

	public void setArtId(String artId) {
		this.artId = artId;
	}

	public String getAmountOf() {
		return amountOf;
	}

	public void setAmountOf(String amountOf) {
		this.amountOf = amountOf;
	}

	public IKEAProduct getProduct() {
		return product;
	}

	public void setProduct(IKEAProduct product) {
		this.product = product;
	}

}
