package com.ingka.warehousemanagement.model;


import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ingka.warehousemanagement.util.IKEADataBaseAttributes;
import com.ingka.warehousemanagement.util.IKEAConstants;

/**
 * Product - mapped to product Data entity
 * Created by Shabaz Mohsin G  17/05/2021
 */

@Entity
@Table(name = IKEADataBaseAttributes.T_PRODUCT)
public class IKEAProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = IKEAConstants.A_PRODUCT, targetEntity = IKEAAssociateArticles.class)
	@JsonProperty(IKEADataBaseAttributes.T_CONTAIN_ARTICLES)
	@JsonManagedReference 
	private Set<IKEAAssociateArticles> associateArticles;
	
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

	public Set<IKEAAssociateArticles> getAssociateArticles() {
		return associateArticles;
	}

	public void setAssociateArticles(Set<IKEAAssociateArticles> associateArticles) {
		this.associateArticles = associateArticles;
	}
}
