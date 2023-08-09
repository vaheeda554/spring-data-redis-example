package com.example.redis.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.redis.entity.Customer;

@Repository
public class CustomerDao {
	@Autowired
	private RedisTemplate template;
	public static final String HASH_KEY="Customer";
	
	public Customer save(Customer customer) {
		template.opsForHash().put(HASH_KEY,customer.getId(),customer);
		return customer;
		
	}
	public List<Customer> findall(){
		return template.opsForHash().values(HASH_KEY);
		
	}
	public Customer findCustomerById(int id) {
		System.out.println("called findById from DB");
		return (Customer)template.opsForHash().get(HASH_KEY,id);
		
	}
public String deleteCustomer(int id) {
	 template.opsForHash().delete(HASH_KEY,id);;
		return "Customer removed";
		
	}
	
}

