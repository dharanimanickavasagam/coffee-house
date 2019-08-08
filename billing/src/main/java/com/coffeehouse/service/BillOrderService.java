package com.coffeehouse.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coffeehouse.model.dto.CreateOrderRequestDto;
import com.coffeehouse.model.dto.OrderItemsDto;
import com.coffeehouse.model.entity.CustomerEntity;
import com.coffeehouse.model.entity.OrderEntity;
import com.coffeehouse.model.entity.OrderItemsEntity;
import com.coffeehouse.repository.CustomerRepository;
import com.coffeehouse.repository.OrderItemsRepository;
import com.coffeehouse.repository.OrderRepository;

@Service
public class BillOrderService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemsRepository orderItemsRepository;


	public void createOrder(CreateOrderRequestDto orderRequestDto) {

		/**
		 * Step 1. Find if customer already exists using phone number 
		 * Step 2. If customer is not available, create new customer; if customer
		 * 			available, use the customer id to create order 
		 * Step 3. Create orders table using customer id from Step 2 
		 * Step 4. Populate order_items data 
		 * Step 5. Return the entire object back to controller
		 **/


		// Step 1 - Find if customer already exists using phone number 
	
		CustomerEntity customerEntity = customerRepository
				.findByMobileNumber(orderRequestDto.getOrderDetailsDto().getCustomerDto()
						.getCustomerMobile());


		// Step 2 - If customer is not available, create new customer; if customer
		//  			available, use the customer id to create order 

		if (customerEntity == null) {
			customerEntity = new CustomerEntity();
			customerEntity.setName(orderRequestDto.getOrderDetailsDto().getCustomerDto().getCustomerName());
			customerEntity.setMobileNumber(orderRequestDto.getOrderDetailsDto().getCustomerDto().getCustomerMobile());

			customerEntity = customerRepository.save(customerEntity);
		}



		// Step 3 - Create orders(orders table) using customer id from Step 2

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setTotalPrice(orderRequestDto.getOrderDetailsDto().getOrdersDto().getTotalPrice()
				.doubleValue());

		orderEntity.setTax(orderRequestDto.getOrderDetailsDto().getOrdersDto().getTax()
				.doubleValue());

		orderEntity.setTenderedAmount(orderRequestDto.getOrderDetailsDto().getOrdersDto().getTenderedAmount()
				.doubleValue());

		orderEntity.setCustomerEntity(customerEntity);

		orderEntity = orderRepository.save(orderEntity);

		// Step 4 -  Step 4. Populate order_items data
		Set<OrderItemsEntity> orderItemsEntities = new HashSet<OrderItemsEntity>();

		for(OrderItemsDto orderItemsDto: orderRequestDto.getOrderDetailsDto().getOrderItemsDto()) {
			OrderItemsEntity orderItemsEntity = new OrderItemsEntity();

			orderItemsEntity.setProductId(orderItemsDto.getProductId());
			orderItemsEntity.setProductName(orderItemsDto.getProductName());
			orderItemsEntity.setQuantity(Integer.parseInt(orderItemsDto.getQuantity()));
			orderItemsEntity.setUnitPrice(orderItemsDto.getUnitPrice());
			orderItemsEntity.setNetPrice(orderItemsDto.getNetPrice());
			orderItemsEntity.setOrderEntity(orderEntity);

			orderItemsEntities.add(orderItemsEntity);
		}

		orderItemsRepository.saveAll(orderItemsEntities);
	}

	public void deleteOrder(long orderId) {
		orderRepository.deleteById(orderId);		
	}

	public Set<CustomerEntity> findAllCustomersWithOrderDetails() {		
		List<CustomerEntity> customerEntityList =  customerRepository.findAll(); 
		Set<CustomerEntity> customerEntitySet = new HashSet<CustomerEntity>(); 
		
		for(CustomerEntity customerEntity : customerEntityList) { 
			customerEntitySet.add(customerEntity);
		}
	
		return customerEntitySet; 
	}

	
	public Set<OrderItemsEntity> getOrderItemsById(OrderEntity orderId) {
		
		List<OrderItemsEntity> orderItemsEntityList = orderItemsRepository.findByOrderEntity(orderId);
		Set<OrderItemsEntity> orderItemsEntitySet = new HashSet<OrderItemsEntity>(); 
		
		for(OrderItemsEntity orderItemsEntity : orderItemsEntityList) { 
			orderItemsEntitySet.add(orderItemsEntity);
		}
		return orderItemsEntitySet;

	}

}
