/**
 * 
 */
package com.taskmanager.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.taskmanager.data.ProductDetails;

/**
 * @author dwarakak
 *
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private MongoOperations mongoOperations;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taskmanager.repository.ProductRepository#createProduct()
	 */
	@Override
	public void createProduct() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taskmanager.repository.ProductRepository#getProductList()
	 */
	@Override
	public List<ProductDetails> getProductList() {
		final List<ProductDetails> productList = mongoOperations.find(
				new Query(Criteria.where("active").is(true)),
				ProductDetails.class);
		return productList;
	}

}
