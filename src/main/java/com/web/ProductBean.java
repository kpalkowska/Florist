package com.web;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.DragDropEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.spring.dao.AddressDAO;
import com.spring.dao.OrderDAO;
import com.spring.model.AddressModel;
import com.spring.model.OrderModel;
import com.spring.model.Product2OrderModel;
import com.spring.model.ProductModel;
import com.spring.model.UserModel;
import com.spring.security.AppUser;
import com.spring.service.OrderService;
import com.spring.service.Product2OrderService;
import com.spring.service.ProductService;
import com.spring.service.UserService;
import com.web.email.EmailBean;

import lombok.Data;

@ManagedBean(name = "productBean", eager = true)
@SessionScoped
@Component
public @Data class ProductBean implements Serializable {

	private static final long serialVersionUID = 6022001178289508303L;

	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	@Autowired
	private ProductService service;

	@Autowired
	private EmailBean email;

	@Autowired
	private OrderService orderService;

	@Autowired
	private Product2OrderService p2oService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private AddressDAO addressDAO;
	
	private ProductModel selectedProduct;

	private List<ProductModel> products;
	private List<ProductModel> droppedProducts;
	private List<OrderModel> orders;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private String street;
	private String number;
	private String zipKode;
	private String city;
	
	boolean successOrder = false;
	boolean checked;

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

	public void remove(ProductModel product) {
	    try {
	        droppedProducts.remove(product);
	        products.add(product);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public String submitOrder() {
		return "/pages/unsecure/newOrder?faces-redirect=true";
	}

	public String createOrder() {
		AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserModel user = null;
		
		if(appUser.getUsername() != null)
			user = userService.findUserByLogin(appUser.getUsername());
		
		Date today = Calendar.getInstance().getTime();
		String dateString = dateFormat.format(today);
		
		OrderModel newOrder = new OrderModel();
		Product2OrderModel p2o = new Product2OrderModel();
		AddressModel newAddress = new AddressModel();
		
		if(droppedProducts.size() != 0){
			newOrder.setUsers(user);
			
			if(street != null && number != null && zipKode != null && city != null){
				newAddress.setStreet(street);
				newAddress.setNumber(number);
				newAddress.setZipCode(zipKode);
				newAddress.setCity(city);
				
				if(!addressDAO.exists(zipKode, city, street, number)){
					addressDAO.addAddress(newAddress);
					newOrder.setAddress(newAddress);
				}
			}
			else
				newOrder.setAddress(user.getAddress());
			
			newOrder.setDate(dateString);
			orderDAO.addOrder(newOrder);
		}

		for(ProductModel product : droppedProducts){
			p2o.setProduct(product);
			successOrder = p2oService.createProduct2Order(product, newOrder);
		}

		if (successOrder) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Success", new StringBuilder("Order ").append("submited!").toString()));
			LOGGER.info("Create new Products2Order");
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Something went wrong."));
			LOGGER.error("Error creating Products2Order");
		}

		setProducts(service.getAllProducts());
		droppedProducts.clear();

		return "/pages/secure/products?faces-redirect=true";
	}

	public void submitOrderAndEmail() {
		createOrder();
		if(successOrder)
			email.sendEmail();
	}
}
