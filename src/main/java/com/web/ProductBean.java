package com.web;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.spring.model.AddressModel;
import com.spring.model.OrderModel;
import com.spring.model.ProductModel;
import com.spring.model.UserModel;
import com.spring.security.AppUser;
import com.spring.service.OrderService;
import com.spring.service.Product2OrderService;
import com.spring.service.ProductService;
import com.spring.service.UserService;

import lombok.Data;

@ManagedBean(name = "productBean", eager = true)
@SessionScoped
@Component
public @Data class ProductBean implements Serializable {

	private static final long serialVersionUID = 6022001178289508303L;
	
	@Autowired
    private ProductService service;
	
	@Autowired
    private OrderService orderService;
	
	@Autowired
    private Product2OrderService p2oService;
	
	@Autowired
    private UserService userService;
	
    private ProductModel selectedProduct;
 
    private List<ProductModel> products;
    private List<ProductModel> droppedProducts;
    private List<OrderModel> orders;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date today = Calendar.getInstance().getTime();
    String dateString = dateFormat.format(today);

	
	private AppUser appUser;
    private String login;
    
    private UserModel user;
    private AddressModel address;
     
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
    
    public String submitOrder(){
    	return "/pages/unsecure/newOrder?faces-redirect=true";
    }
    
	public String createOrder(){
		appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		login = (Objects.nonNull(appUser)) ? appUser.getUsername() : null;
		user = userService.findUserByLogin(login);
		address = user.getAddress();
        orders = orderService.getAllOrders();
		
		boolean successOrder = orderService.createOrder(dateString, user, address);
		OrderModel order = orderService.exists(dateString, user, address);
		
		for(int i=0; i < droppedProducts.size(); i++)
			p2oService.createProduct2Order(droppedProducts.get(i), order);
		
		if (successOrder) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", new StringBuilder("Order ").append("submited!").toString()));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
		}

		setProducts(service.getAllProducts());
		droppedProducts.clear();
		
		return "/pages/secure/products?faces-redirect=true";
	}
}
