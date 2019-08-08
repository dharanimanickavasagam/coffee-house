package com.coffeehouse.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import com.coffeehouse.model.dto.CreateOrderRequestDto;
import com.coffeehouse.model.dto.CustomerDto;
import com.coffeehouse.model.dto.OrderDetailsDto;
import com.coffeehouse.model.dto.OrderItemsDto;
import com.coffeehouse.model.dto.OrdersDto;
import com.coffeehouse.model.entity.CustomerEntity;
import com.coffeehouse.model.entity.OrderEntity;
import com.coffeehouse.model.entity.OrderItemsEntity;
import com.coffeehouse.repository.CustomerRepository;
import com.coffeehouse.repository.OrderItemsRepository;
import com.coffeehouse.repository.OrderRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class BillOrderServiceTest {
	
	@MockBean
	private OrderRepository mockOrderRepository;
	
	@MockBean
	private OrderItemsRepository mockOrderItemsRepository;
	
	@MockBean
	private CustomerRepository mockCustomerRepository;

	
	@Configuration
	static class Config{
		@Bean
		public BillOrderService billOrderService(){
			return new BillOrderService();
		}
	}
	
	@Autowired
	private BillOrderService serviceUnderTest;
	
	@Captor
	ArgumentCaptor<OrderEntity> orderEntityArgumentCaptor;
	
	@Captor
	ArgumentCaptor<OrderEntity> orderEntityArgumentCaptorForCustomer;
	
	@Captor
	ArgumentCaptor<CustomerEntity> customerEntityArgumentCaptor;
	
	

	@Captor
	ArgumentCaptor<OrderItemsEntity> orderItemsEntityArgumentCaptor; 
	
	PodamFactory podamFactory = new PodamFactoryImpl();
	
	private long mockOrderId;
	private OrderEntity mockOrderEntity;
	private OrderItemsEntity mockOrderItemsEntity;
	private List<OrderItemsEntity> mockOrderItemsEntityList;

	
	private CreateOrderRequestDto mockCreateOrderRequestDto;
	private OrderDetailsDto mockOrderDetailsDto; 
	private OrdersDto mockOrdersDto; 
	private CustomerDto mockCustomerDto; 
	private OrderItemsDto mockOrderItemsDto; 
	private Set<OrderItemsDto> mockOrderItemsDtoSet; 
	
	
	private Integer mockTotalPrice; 
	private Double mockTax; 
	private Double mockTenderedAmount; 
	
	private long mockCustomerId;
	private String mockCustomerName; 
	private long mockCustomerMobile; 
	
	private Integer mockProductId;
	private String mockProductName;
	private Integer mockUnitPrice;
	private String mockQuantity;
	private Integer mockNetPrice;
	private CustomerEntity mockCustomerEntity;
	private CustomerEntity mockCustomerEntityForFindAll;
	private List<CustomerEntity> mockCustomerEntityList;
	private OrderItemsEntity mockOrderItemsEntities; 
	
	
	@Before
	public void setup(){
		
		
		//createOrder()
		mockCreateOrderRequestDto = new CreateOrderRequestDto();
		mockTotalPrice= 34; 
		mockTax=78.90; 
		mockTenderedAmount= 98.0; 
		mockOrdersDto = new OrdersDto(mockTotalPrice,mockTax,mockTenderedAmount); 
		
		mockCustomerId=9876L;
		mockCustomerName="dharani"; 
		mockCustomerMobile = 123456789L;
		mockCustomerDto = new CustomerDto(mockCustomerName,mockCustomerMobile,mockCustomerId); 
		
		mockProductId =4;
		mockProductName="hi";
		mockUnitPrice=18;
		mockQuantity="6";
		mockNetPrice=78;
		
		mockOrderItemsDto = new OrderItemsDto(mockProductId,mockProductName,mockUnitPrice,mockQuantity,mockNetPrice); 
		mockOrderItemsDtoSet = new HashSet<OrderItemsDto>();
		mockOrderItemsDtoSet.add(mockOrderItemsDto);
		
		mockOrderDetailsDto = new OrderDetailsDto(mockOrdersDto,mockCustomerDto,mockOrderItemsDtoSet);
		mockCreateOrderRequestDto.setOrderDetailsDto(mockOrderDetailsDto);
		
		
		mockCustomerEntity = new CustomerEntity();
		mockCustomerEntity.setId(mockCustomerId);
		mockCustomerEntity.setName(mockCustomerName);
		mockCustomerEntity.setMobileNumber(mockCustomerMobile);
		
		mockOrderId = 12334;
		mockOrderEntity = new OrderEntity();
		mockOrderEntity.setId(mockOrderId);
		mockOrderEntity.setCustomerEntity(mockCustomerEntity);
		
		
		mockOrderItemsEntity = new OrderItemsEntity();
		mockOrderItemsEntityList = new ArrayList<OrderItemsEntity>();
		mockOrderItemsEntityList.add(mockOrderItemsEntity);
		
		
		mockCustomerEntityForFindAll = new CustomerEntity();
		mockCustomerEntityList = new ArrayList<CustomerEntity>();
		mockCustomerEntityList.add(mockCustomerEntityForFindAll);
		
		mockOrderItemsEntities = new OrderItemsEntity(); 
		mockOrderItemsEntityList = new ArrayList<OrderItemsEntity>();
		mockOrderItemsEntityList.add(mockOrderItemsEntity);
		mockOrderItemsEntityList.add(mockOrderItemsEntity);
		
		when(mockOrderItemsRepository.findByOrderEntity(mockOrderEntity)).thenReturn(mockOrderItemsEntityList);
		when(mockCustomerRepository.findAll()).thenReturn(mockCustomerEntityList);
		when( mockOrderItemsRepository.saveAll(mockOrderItemsEntityList)).thenReturn(mockOrderItemsEntityList); 
	}
	
	
	@Test
	public void InvokeSaveAllWithExpectedOrderItemsEntitiesWhenCreateorderIsCalled() { 
		serviceUnderTest.createOrder(mockCreateOrderRequestDto);
		
		verify(mockOrderItemsRepository).saveAll((Iterable<OrderItemsEntity>) orderItemsEntityArgumentCaptor.capture());
		//OrderItemsEntity actualOrderItemsEntity = orderItemsEntityArgumentCaptor.getValues();
		
		//OrderEntity actualOrderEntity = orderEntityArgumentCaptorForCustomer.getValues();
		
		//assertEquals((double)Double.valueOf(mockTotalPrice), actualOrderEntity.getTotalPrice(),1e-8);
		//assertEquals(mockTax,actualOrderEntity.getTax(),1e-8);
		//assertEquals(mockTenderedAmount,actualOrderEntity.getTenderedAmount(),1e-8);
		// assertEquals(mockCustomerId, (long)actualOrderEntity.getCustomerEntity().getId()); 
	}
	
	
	@Test
	public void InvokeSaveWithExpectedOrderEntityWhenCreateorderIsCalled() { 
		serviceUnderTest.createOrder(mockCreateOrderRequestDto);
		
		verify(mockOrderRepository,times(1)).save(orderEntityArgumentCaptorForCustomer.capture()); 
		OrderEntity actualOrderEntity = orderEntityArgumentCaptorForCustomer.getValue();
		
		assertEquals((double)Double.valueOf(mockTotalPrice), actualOrderEntity.getTotalPrice(),1e-8);
		assertEquals(mockTax,actualOrderEntity.getTax(),1e-8);
		assertEquals(mockTenderedAmount,actualOrderEntity.getTenderedAmount(),1e-8);
		// assertEquals(mockCustomerId, (long)actualOrderEntity.getCustomerEntity().getId()); 
	}
	
	
	@Test
	public void InvokeFindAllWhenFindAllCustomersWithOrderDetailsIsCalled() { 
		serviceUnderTest.findAllCustomersWithOrderDetails(); 
		verify(mockCustomerRepository,times(1)).findAll(); 
		
		Set<CustomerEntity> customerEntitySet = serviceUnderTest.findAllCustomersWithOrderDetails();
		assertFalse(customerEntitySet.isEmpty());
	}
	
	
	@Test 
	public void shouldInvokeWhenFindByMobileNumberWithExpectedMobileNumberWhenCreateOrderIsCalled() { 
		serviceUnderTest.createOrder(mockCreateOrderRequestDto);		
		verify(mockCustomerRepository,times(1)).findByMobileNumber(mockCustomerMobile); 
	}
	
	
	
	
	@Test
	public void InvokeSaveWithExpectedCustomerEntityWhenCreateorderIsCalled() { 
		serviceUnderTest.createOrder(mockCreateOrderRequestDto);
		
		verify(mockCustomerRepository,times(1)).save(customerEntityArgumentCaptor.capture()); 
		CustomerEntity actualCustomerEntity = customerEntityArgumentCaptor.getValue();
		assertEquals(mockCustomerName, actualCustomerEntity.getName());
		assertEquals(mockCustomerMobile, actualCustomerEntity.getMobileNumber());
	}
	
	@Test
	public void shouldInvokeDeleteByIdWithExpectedOrderIdWhenDeleteOrderIsCalled(){
		serviceUnderTest.deleteOrder(mockOrderId);
		verify(mockOrderRepository, times(1)).deleteById(mockOrderId);
	}
	
	@Test
	public void shouldInvokeFindByOrderEntityWithExpectedOrderIdWhenGetOrderItemsByIdIsCalled(){		
		serviceUnderTest.getOrderItemsById(mockOrderEntity);		
		verify(mockOrderItemsRepository, times(1)).findByOrderEntity(mockOrderEntity);
	}
	
	@Test
	public void shouldReturnNotEmptyOrderItemsEntitySetWhenGetOrderItemsByIdIsCalled(){	
		Set<OrderItemsEntity> orderItemsEntitySet = serviceUnderTest.getOrderItemsById(mockOrderEntity);
		assertFalse(orderItemsEntitySet.isEmpty());
	}
	
	@Test
	public void shouldInvokeFindByOrderEntityWithExpectedArgumentsWhenGetOrderItemsByIdIsCalled(){
		serviceUnderTest.getOrderItemsById(mockOrderEntity);
	
		verify(mockOrderItemsRepository, times(1)).findByOrderEntity(orderEntityArgumentCaptor.capture());
		OrderEntity actualOrderEntity = orderEntityArgumentCaptor.getValue();
		assertEquals(mockOrderEntity.getId(), actualOrderEntity.getId());
	}
	
	

}
