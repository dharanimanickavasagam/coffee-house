package com.coffeehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffeehouse.model.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>{


}
