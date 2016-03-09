package com.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.model.ProductModel;
import com.spring.service.LogService;
import com.spring.service.ProductService;

import lombok.Data;

@ManagedBean(name = "productBean")
@SessionScoped
public @Data class ProductBean implements Serializable {

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
      
    public String getFoto() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return "";
        }
        else {
            String productId = context.getExternalContext().getRequestParameterMap().get("productId");
            String foto = service.getImageByProductId(Long.valueOf(productId));
            logService.logInfo(foto);
            return foto;
        }
    }
    
    
    
}
