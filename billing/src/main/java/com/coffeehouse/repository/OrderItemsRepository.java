package com.coffeehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffeehouse.model.entity.OrderEntity;
import com.coffeehouse.model.entity.OrderItemsEntity;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Long> {

	public List<OrderItemsEntity> findByOrderEntity(OrderEntity orderId);

}
