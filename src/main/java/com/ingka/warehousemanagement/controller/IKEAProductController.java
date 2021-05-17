package com.ingka.warehousemanagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingka.warehousemanagement.exception.IKEAWarehouseManagementErrorHandler;
import com.ingka.warehousemanagement.model.IKEAAssociateArticles;
import com.ingka.warehousemanagement.model.IKEAProduct;
import com.ingka.warehousemanagement.model.IKEAProductList;
import com.ingka.warehousemanagement.util.*;
import com.ingka.warehousemanagement.repository.IKEAAssociateArticlesRepository;
import com.ingka.warehousemanagement.repository.IKEAProductRepository;

/**
 * Product Controller which provides REST API for Product Service 
 * GET, POST, PUT and DELETE
 * Created by Shabaz Mohsin G  on 17/05/2021.
 */
@RestController
@RequestMapping("/api/v1.0/warehouseamanagement/product")
public class IKEAProductController {

	/** REST Services for Product --START */
	@Autowired
	private IKEAProductRepository productRepository;
	@Autowired
    private IKEAAssociateArticlesRepository articlesRepository;
	
	@PersistenceContext 
	private EntityManager entityManager;
	
	@GetMapping("/list")
	public List<IKEAProduct> getAllProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/{id}")
	public IKEAProduct getProductById(@PathVariable(value = IKEAConstants.A_ID) Long id) {
		IKEAProduct product = productRepository.findById(id)
				.orElseThrow(() -> new IKEAWarehouseManagementErrorHandler(IKEAConstants.S_PRODUCT));
		product.getAssociateArticles();
		return product;
	}
	
	@PostMapping("")
    public List<IKEAProduct> createProduct(@Valid @RequestBody IKEAProductList productList) {
        List<IKEAProduct> savedProducts = new ArrayList<IKEAProduct>();
        for(IKEAProduct p1 : productList.getProducts()) {
            Set<IKEAAssociateArticles> articles = p1.getAssociateArticles();
            p1.setAssociateArticles(null);
            IKEAProduct product1 = productRepository.save(p1);
            product1.setAssociateArticles(null);
            if(articles != null) {
                articles.forEach(article -> {
                    article.setProduct(product1);
                });
            }
            articlesRepository.saveAll(articles);
            savedProducts.add(product1);
        }
        return savedProducts;
    }

	@PutMapping("/{id}")
	public IKEAProduct updateProduct(@PathVariable(value = IKEAConstants.A_ID) Long id, @Valid @RequestBody IKEAProduct productDetails) {

		IKEAProduct product = productRepository.findById(id)
				.orElseThrow(() -> new IKEAWarehouseManagementErrorHandler(IKEAConstants.S_PRODUCT));
		
        Set<IKEAAssociateArticles> articles = productDetails.getAssociateArticles();
        Map<String,String> map = new HashMap<>();
        articles.forEach(article -> {
            map.put(article.getArtId(), article.getAmountOf());
        });
        IKEAProduct product1 = productRepository.save(product);
        articles = product.getAssociateArticles();
        product1.setAssociateArticles(null);
        if(articles != null) {
            articles.forEach(article -> {
            	article.setProduct(product1);
                article.setAmountOf(map.get(article.getArtId()));
                //articlesRepository.save(article);
            });
        }
        articlesRepository.saveAll(articles);
        
		return product1;
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<IKEAProduct> deleteProduct(@PathVariable(value = IKEAConstants.A_ID) Long id) {

		IKEAProduct product = productRepository.findById(id)
				.orElseThrow(() -> new IKEAWarehouseManagementErrorHandler(IKEAConstants.S_PRODUCT));
		
		Set<IKEAAssociateArticles> articles = product.getAssociateArticles();
        articlesRepository.deleteAll(articles);
		
		productRepository.delete(product);
		return ResponseEntity.ok().build();
	}
	/** REST Services for Product --END */
}
