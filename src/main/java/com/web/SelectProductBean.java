package com.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.model.ProductModel;
import com.spring.service.LogService;
import com.spring.service.ProductService;

import lombok.Data;

@ManagedBean(name = "selectProductBean", eager = true)
@SessionScoped
@Component
public @Data class SelectProductBean implements Serializable {

	private static final long serialVersionUID = 1549481937223946546L;
	
	private String name;
	private String description;
	private String price;
	private String type;
	private String color;
	private byte[] foto;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private LogService logService;
	
	private List<ProductModel> products = new ArrayList<>();
		
	public String showProductsByName(){
		logService.logInfo("showProducts :: starting...");
		setProducts(productService.findProductByName(name));
		logService.logInfo("showProducts :: complete");

		
		//poprawic widok
		return "/pages/secure/products?faces-redirect=true"; 
	
	}
	
	public String showProductsByColor(){
		logService.logInfo("showProducts :: starting...");
		setProducts(productService.findProductByColor(color));
		logService.logInfo("showProducts :: complete");

		
		//poprawic widok
		return "/pages/secure/products?faces-redirect=true"; 
	
	}
	
	public String showProductsByType(){
		logService.logInfo("showProducts :: starting...");
		setProducts(productService.findProductByType(type));
		logService.logInfo("showProducts :: complete");

		
		//poprawic widok
		return "/pages/secure/products?faces-redirect=true"; 
	}

}
