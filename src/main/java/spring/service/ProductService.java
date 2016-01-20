package spring.service;

import java.util.List;

import spring.model.ProductModel;

public interface ProductService {
	
	List<ProductModel> getAllProducts();
	void addProduct(ProductModel product);
	ProductModel findProductById(ProductModel id);
	void deleteProduct(ProductModel product);
	void updateProduct(ProductModel product);
}
