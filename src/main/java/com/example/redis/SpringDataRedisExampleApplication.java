package com.example.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.entity.Customer;
import com.example.redis.repository.CustomerDao;

@SpringBootApplication
@RestController
@RequestMapping("/customers")
@EnableCaching
public class SpringDataRedisExampleApplication {
	
	@Autowired
	private CustomerDao dao;
	
	@PostMapping
	public Customer save(@RequestBody Customer customer) {
		return dao.save(customer);
	}
	@GetMapping
	public List<Customer> getAllCustomers(){
		return dao.findall();
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(key="#id",value="Customer")
	public String remove(@PathVariable int id) {
		return dao.deleteCustomer(id);
	}
	
	@GetMapping("/{id}")
	@Cacheable(key="#id",value="Customer")
	public Customer findCustomer(@PathVariable int id) {
		
		return dao.findCustomerById(id);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisExampleApplication.class, args);
	}

}
