package com.coupon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coupon.beans.Customer;

public interface CustomersRepository extends JpaRepository<Customer, Integer>{
	
	List<Customer> findCustomerByCustomerID(int customerId);
	Customer findCustomerByEmailAndPassword(String email, String password);

}
