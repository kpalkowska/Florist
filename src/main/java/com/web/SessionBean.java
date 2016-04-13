package com.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.model.AddressModel;
import com.spring.model.ProductModel;
import com.spring.model.RoleModel;
import com.spring.model.UserModel;
import com.spring.service.AddressService;
import com.spring.service.ProductService;
import com.spring.service.RoleService;
import com.spring.service.UserService;

import lombok.Data;

@ManagedBean(name = "sessionBean", eager = true)
@SessionScoped
@Component
public @Data class SessionBean implements Serializable {

	private static final long serialVersionUID = 1549481937223946546L;

	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	private String name;
	private String surname;
	private String login;
	private String password;
	private String zipCode;
	private String city;
	private String street;
	private String number;

	private String roleName;

	private String description;
	private String price;
	private String type;
	private String color;
	private byte[] foto;
	private RoleModel role;
	private String time;
	private List<UserModel> users = new ArrayList<>();
	private List<ProductModel> products = new ArrayList<>();
	private boolean skip;

	@Autowired
	private AddressService addressService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	public String showProducts() {
		setProducts(productService.getAllProducts());

		LOGGER.info("Display all products");
		return "/pages/secure/products?faces-redirect=true";
	}

	public String showUsers() {
		setUsers(userService.getAllUsers());

		LOGGER.info("Display all users");
		return "/pages/secure/list?faces-redirect=true";
	}

	public String createUser() {
		AddressModel address;

		AddressModel a = addressService.exists(zipCode, city, street, number);
		RoleModel r = roleService.exists(roleName);

		if (a == null) {
			address = new AddressModel(zipCode, city, street, number);
		} else
			address = a;

		if (r == null) {
			role = new RoleModel(roleName);
		} else
			role = r;

		boolean successUser = userService.createUser(name, surname, login, password, address, role);

		if (successUser) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success",
					new StringBuilder("User ").append(login).append(" created!").toString()));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
		}

		LOGGER.info("Create new user correct");
		return null;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false;
			return "confirm";
		} else
			return event.getNewStep();
	}

	public String createProduct() {
		boolean successProduct = productService.createProduct(name, description, price, type, color, foto);

		if (successProduct) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success",
					new StringBuilder("Product ").append(name).append(" created!").toString()));
			LOGGER.info("Create new product correct");
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
			LOGGER.error("Error creating product :(");
		}
		return null;
	}

	public void handleFileUpload(FileUploadEvent event) {
		if (event != null) {

			byte[] content = event.getFile().getContents();
			foto = content;
			FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			LOGGER.info("ADD PHOTO!");
		}
	}

}