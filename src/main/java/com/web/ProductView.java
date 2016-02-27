package com.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.model.ProductModel;
import com.spring.service.LogService;
import com.spring.service.ProductService;

import lombok.Data;

@ManagedBean(name = "dndProductsView")
@ViewScoped
public @Data class ProductView implements Serializable {

	private static final long serialVersionUID = 6022001178289508303L;
	
	@Autowired
	private LogService logService;
	
	@ManagedProperty("#{productService}")
    private ProductService service;
 
    private List<ProductModel> products;
     
    private List<ProductModel> droppedProducts;
     
    private ProductModel selectedProduct;
     
    @PostConstruct
    public void init() {
        products = service.getAllProducts();
        droppedProducts = new ArrayList<ProductModel>();
    }
     
    public void onProductDrop(DragDropEvent ddEvent) {
        
    	ProductModel product = ((ProductModel) ddEvent.getData());
  
        droppedProducts.add(product);
        products.remove(product);
       
    }
}
