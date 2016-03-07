package com.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

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
	
	public String showProductsByName(String name){
		this.name = name;
		logService.logInfo("showProducts :: starting...");
		setProducts(productService.findProductByName(name));
		logService.logInfo("showProducts :: complete");

		return "/pages/secure/productCases?faces-redirect=true"; 
	
	}
	
	public String showProductsByColor(String color){
		this.color = color;
		logService.logInfo("showProducts :: starting...");
		setProducts(productService.findProductByColor(color));
		logService.logInfo("showProducts :: complete");

		
		//poprawic widok
		return "/pages/secure/productCases?faces-redirect=true"; 
	
	}
	
	public String showProductsByType(String type){
		this.type = type;
		logService.logInfo("showProducts :: starting...");
		setProducts(productService.findProductByType(type));
		logService.logInfo("showProducts :: complete");

		return "/pages/secure/productCases?faces-redirect=true"; 
	}
	
	public String showAll(){
		logService.logInfo("showProducts :: starting...");
		setProducts(productService.getAllProducts());
		logService.logInfo("showProducts :: complete");

		return "/pages/secure/productCases?faces-redirect=true"; 
	}

}
