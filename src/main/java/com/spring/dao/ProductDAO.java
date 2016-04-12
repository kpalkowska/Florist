package com.spring.dao;

import java.util.List;

import com.spring.model.*;

public interface ProductDAO {

	List<ProductModel> getAllProducts();

	void addProduct(ProductModel product);

	void deleteProduct(ProductModel product);

	void updateProduct(ProductModel product);

	List<ProductModel> findProductByType(String type);

	List<ProductModel> findProductByColor(String color);

	ProductModel findProduct(ProductModel product);
	
	List<ProductModel> findProductByName(String name);

	ProductModel findProductById(long productId);

	ProductModel findProductByTypeRose();

	ProductModel findProductByTypeTulip();

}
