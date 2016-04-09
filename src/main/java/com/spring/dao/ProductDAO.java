package com.spring.dao;

import java.util.List;

import com.spring.model.*;

public interface ProductDAO {

	List<ProductModel> getAllProducts();

	void addProduct(ProductModel product);

	void deleteProduct(ProductModel product);

	void updateProduct(ProductModel product);

	List<ProductModel> findProductByName(String name);

	ProductModel findProductByType(String type);

	ProductModel findProductByColor(String color);

}
