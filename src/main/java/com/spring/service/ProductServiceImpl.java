package com.spring.service;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
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
		return sessionFactory.getCurrentSession().get(ProductModel.class, product.getId());
		
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
	public boolean createProduct(String name, String description, String price, String type, String color, byte[] foto) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(description) || StringUtils.isEmpty(price) || StringUtils.isEmpty(type)|| StringUtils.isEmpty(color)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid name"));
		}  else {
			ProductModel product = new ProductModel(name, description, price, type, color, foto);
			productDAO.addProduct(product);
			return true;
		}		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ProductModel> findProductByName(String name) {
				return sessionFactory.getCurrentSession().getNamedQuery("products.getByName").setString("name", name).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ProductModel> findProductByType(String type) {
		return sessionFactory.getCurrentSession().getNamedQuery("products.getByType").setString("type", type).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ProductModel> findProductByColor(String color) {
		return sessionFactory.getCurrentSession().getNamedQuery("products.getByColor").setString("color", color).list();
	}

	@Override
	@Transactional
	public ProductModel findProductById(long productId) {
		return (ProductModel) sessionFactory.getCurrentSession().getNamedQuery("products.byId").setLong("id", productId).uniqueResult();
	}

	@Override
	@Transactional
	public String getImageByProductId(long productId) {
		String p = (String) sessionFactory.getCurrentSession().getNamedQuery("foto.byProductId").setLong("id", productId).uniqueResult();
		byte[] b= p.getBytes();
		byte[] b2=Base64.encode(b);
		return new String(b2);
	}	
}

