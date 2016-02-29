package com.spring.service;

import java.awt.Image;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.spring.dao.ProductDAO;
import com.spring.model.ProductModel;

@Component
@Service(value="productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ProductModel> getAllProducts() {
		 return getSessionFactory().getCurrentSession().getNamedQuery("products.all").list();
	}

	@Override
	@Transactional
	public void addProduct(ProductModel product) {
		 getSessionFactory().getCurrentSession().persist(product);
		
	}

	@Override
	@Transactional
	public ProductModel findProduct(ProductModel product) {
		return (ProductModel) sessionFactory.getCurrentSession().get(ProductModel.class, product.getId());
		
	}

	@Override
	@Transactional
	public void deleteProduct(ProductModel product) {
		getSessionFactory().getCurrentSession().delete(product);	
	}

	@Override
	@Transactional
	public void updateProduct(ProductModel product) {
		 getSessionFactory().getCurrentSession().merge(product);
	}

	@Override
	public boolean createProduct(String name, String description, String price, byte[] foto) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(description) || StringUtils.isEmpty(price)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid name"));
		}  else {
			ProductModel product = new ProductModel(name, description, price, foto);
			productDAO.addProduct(product);
			return true;
		}
		
		return false;
	}

}

