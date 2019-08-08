package com.coffeehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffeehouse.model.entity.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> { 
	
	public List<ProductEntity> findAll();
	
	
}
