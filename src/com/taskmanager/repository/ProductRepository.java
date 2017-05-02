/**
 * 
 */
package com.taskmanager.repository;

import java.util.List;

import com.taskmanager.data.ProductDetails;

/**
 * @author dwarakak
 *
 */
public interface ProductRepository {
	
	public void createProduct();
	
	public List<ProductDetails> getProductList();

}
