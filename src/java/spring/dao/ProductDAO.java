package java.spring.dao;

import java.util.List;
import java.spring.model.*;


public interface ProductDAO {
	
	List<ProductModel> getAllProducts();
	void addProduct(ProductModel product);
	ProductModel findProductByName(String name);
	void deleteProduct(ProductModel product);
	void updateProduct(ProductModel product);
}


