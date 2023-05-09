package com.mayank.redisSpring.bean.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.mayank.redisSpring.bean.Product;

@Repository
public class ProductDAO {

	public static final String HASH_KEY = "Product";
	
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate template;
	
	public Product save(Product product) {
		System.out.print("********"+product.getId()+"*********");
		template.opsForHash().put(HASH_KEY, product.getId(), product);
		System.out.print("*****23223***"+product.getId()+"--------");
		return product;
	}
	
	public String deleteById(int id) {
		template.opsForHash().delete(HASH_KEY, id);
		return "deleted product"+id;
	}
	
	public List<Product> findAll() {
		return template.opsForHash().values(HASH_KEY);
	}
	
	public Product findProductById(int id) {
		System.out.println("**********ProductDAO**********");
		return (Product) template.opsForHash().get(HASH_KEY, id);
	}
}
