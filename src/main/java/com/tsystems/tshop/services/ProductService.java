package com.tsystems.tshop.services;

import com.tsystems.tshop.domain.Product;

public interface ProductService {

	Product getProductById(final Long id);
	
}
