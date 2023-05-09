package com.mayank.redisSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayank.redisSpring.bean.Product;
import com.mayank.redisSpring.bean.repo.ProductDAO;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductDAO productDAO;
	
	@PostMapping
	public Product save(@RequestBody Product product) {
		productDAO.save(product);
		return product;
	}
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable int id) {
		productDAO.deleteById(id);
		return "deleted product"+id;
	}
	
	@GetMapping
	public List<Product> findAll() {
		return productDAO.findAll();
	}
	
	@GetMapping("/{id}")
	@Cacheable(key = "#id",value = "Product")
	public Product findProductById(@PathVariable  int id) {
		return (Product) productDAO.findProductById(id);
	}
}
