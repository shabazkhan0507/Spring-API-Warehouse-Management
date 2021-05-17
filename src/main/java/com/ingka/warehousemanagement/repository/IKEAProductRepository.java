package com.ingka.warehousemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ingka.warehousemanagement.model.IKEAProduct;

/**
 * Created by Shabaz Mohsin G on 01/03/18.
 * This use JpaRepository that extends the PagingAndSortingRepository that extends CRUDRepository.
 */
@Repository
public interface IKEAProductRepository extends JpaRepository<IKEAProduct, Long> {

}
