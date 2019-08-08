package com.coffeehouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coffeehouse.model.entity.ProductEntity;
import com.coffeehouse.service.ProductService;


@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

	@Autowired
	public ProductService productService;


	@GetMapping
	public ResponseEntity<?> findAll() { 
		List<ProductEntity> products= productService.findAll();
		return new ResponseEntity(products, HttpStatus.OK); 
	}

	@PostMapping
	public ResponseEntity<?>saveOrders(@RequestBody ProductEntity products) { 
		productService.save(products);
		return new ResponseEntity("Product added succcessfully", HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam("id") Long id) { 
		productService.delete(id);
		return new ResponseEntity("Product deleted succcessfully", HttpStatus.OK);
	}
/*
	@PutMapping 
	public ResponseEntity<updateProduct(@RequestBody OrdersEntity orders) { 
		productService.
		return new ResponseEntity("Product deleted succcessfully", HttpStatus.OK);
	}*/

}
