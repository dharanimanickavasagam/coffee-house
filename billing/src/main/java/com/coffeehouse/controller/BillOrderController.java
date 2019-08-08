package com.coffeehouse.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coffeehouse.model.dto.CreateOrderRequestDto;
import com.coffeehouse.model.entity.CustomerEntity;
import com.coffeehouse.model.entity.OrderEntity;
import com.coffeehouse.model.entity.OrderItemsEntity;
import com.coffeehouse.service.BillOrderService;

@RestController
@RequestMapping("/billorder")
@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
public class BillOrderController {

	@Autowired
	private BillOrderService billOrderService;


	@GetMapping
	public ResponseEntity<?> getOrders() {
		
		Set<CustomerEntity> customerEntitySet = billOrderService.findAllCustomersWithOrderDetails();
		return new ResponseEntity<Object>(customerEntitySet, HttpStatus.OK); 
	}
	
	
	@GetMapping("/{orderId}")

	public ResponseEntity<?> getOrderItemsById(@PathVariable OrderEntity orderId) {
	
		Set<OrderItemsEntity> orderItemsEntity = billOrderService.getOrderItemsById(orderId);
		return new ResponseEntity<Object>(orderItemsEntity, HttpStatus.OK); 
	}

	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestDto orderRequestDto) {

		billOrderService.createOrder(orderRequestDto);
		return new ResponseEntity<Object>( orderRequestDto, HttpStatus.OK); 
	}


	@DeleteMapping
	public ResponseEntity<?> deleteOrder(@RequestParam long orderId) {

		billOrderService.deleteOrder(orderId);
		return new ResponseEntity<Object>(HttpStatus.OK); 
	}


}
