package spring.service;

import java.util.List;

import spring.model.ProductModel;

public interface ProductService {
	
	List<ProductModel> getAllProducts();
	void addProduct(ProductModel product);
	ProductModel findProduct(ProductModel product);
	void deleteProduct(ProductModel product);
	void updateProduct(ProductModel product);
}
