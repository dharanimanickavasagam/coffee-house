package com.coffeehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffeehouse.model.entity.ProductEntity;
import com.coffeehouse.repository.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public ProductRepository getOrdersRepository() {
		return productRepository;
	}

	public List<ProductEntity> findAll() { 
		return productRepository.findAll();
	}

	public void save(ProductEntity product) { 
		productRepository.save(product);
	}
	
	public void delete(Long id) { 
		 productRepository.deleteById(id);
		
	}
}
