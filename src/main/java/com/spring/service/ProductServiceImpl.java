package com.spring.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.ProductModel;

@Service
public class ProductServiceImpl implements ProductService{
	
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

}
