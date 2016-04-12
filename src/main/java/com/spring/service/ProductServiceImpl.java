package com.spring.service;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.spring.dao.ProductDAO;
import com.spring.model.ProductModel;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public List<ProductModel> getAllProducts() {
		return productDAO.getAllProducts();
	}

	@Override
	public void addProduct(ProductModel product) {
		productDAO.addProduct(product);

	}

	@Override
	public ProductModel findProduct(ProductModel product) {
		return productDAO.findProduct(product);
	}

	@Override
	public void deleteProduct(ProductModel product) {
		productDAO.deleteProduct(product);
	}

	@Override
	public void updateProduct(ProductModel product) {
		productDAO.updateProduct(product);
	}

	@Override
	public boolean createProduct(String name, String description, String price, String type, String color,
			byte[] foto) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(description) || StringUtils.isEmpty(price)
				|| StringUtils.isEmpty(type) || StringUtils.isEmpty(color)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid name"));
		} else {
			ProductModel product = new ProductModel(name, description, price, type, color, foto);
			productDAO.addProduct(product);
			return true;
		}
		return false;
	}

	@Override
	public List<ProductModel> findProductByName(String name) {
		return productDAO.findProductByName(name);
	}

	@Override
	public List<ProductModel> findProductByType(String type) {
		return productDAO.findProductByType(type);
	}

	@Override
	public List<ProductModel> findProductByColor(String color) {
		return productDAO.findProductByColor(color);
	}

	@Override
	public ProductModel findProductById(long productId) {
		return productDAO.findProductById(productId);
	}

	@Override
	public ProductModel findProductByTypeRose() {
		return productDAO.findProductByTypeRose();
	}

	@Override
	public ProductModel findProductByTypeTulips() {
		return productDAO.findProductByTypeTulip();
	}
}
