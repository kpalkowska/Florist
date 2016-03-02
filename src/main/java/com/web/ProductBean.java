package com.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.DragDropEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.model.ProductModel;
import com.spring.service.LogService;
import com.spring.service.ProductService;

import lombok.Data;

@ManagedBean(name = "dndProductsView")
@ViewScoped
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
    
//    public byte[] getImage(Long productId) {
//        return service.findProductById(productId).getFoto();
//    }
//    
    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String productId = context.getExternalContext().getRequestParameterMap().get("id");
            ProductModel product = service.findProductById(Long.valueOf(productId));
            return new DefaultStreamedContent(new ByteArrayInputStream(product.getFoto()));
        }
    }
    
    
    
}
