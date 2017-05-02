/**
 * 
 */
package com.taskmanager.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskmanager.data.ProductDetails;
import com.taskmanager.repository.ProductRepository;

/**
 * @author dwarakak
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductDetails> getProductList(){
		return productRepository.getProductList();
	}
}
