package com.spring.service;

import java.util.List;

import org.primefaces.model.StreamedContent;

import com.spring.model.ProductModel;

public interface ProductService {
	
	List<ProductModel> getAllProducts();
	void addProduct(ProductModel product);
	ProductModel findProduct(ProductModel product);
	void deleteProduct(ProductModel product);
	void updateProduct(ProductModel product);
	boolean createProduct(String name, String description, String price, String type, String color, byte[] foto);
	
	ProductModel findProductById(long productId);
	
	List<ProductModel> findProductByName(String name);
	List<ProductModel> findProductByType(String type);
	List<ProductModel> findProductByColor(String color);
	ProductModel findProductByTypeRose();
	ProductModel findProductByTypeTulips();
	StreamedContent findProductFoto(int id);
}
