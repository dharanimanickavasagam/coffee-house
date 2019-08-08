package com.coffeehouse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coffeehouse.model.entity.CustomerEntity;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
 
	public List<CustomerEntity> findAll(); 
 
	public CustomerEntity findById(long id);
	
	public CustomerEntity findByMobileNumber(Long mobileNumber);
 
}
