/**
 * 
 */
package com.taskmanager.data;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author dwarakak
 * Product details.
 */
@Document(collection="Products")
public class ProductDetails {
	
	private String productId;
	
	private String productName;
	
	private boolean active;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
