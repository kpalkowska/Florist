package spring.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import spring.model.ProductModel;

@Component
public class ProductServiceImpl implements ProductService{

	@Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	@Transactional
	public List<ProductModel> getAllProducts() {
		 List list = getSessionFactory().getCurrentSession().createQuery("from ProductModel").list();
	        return list;
	}

	@Override
	@Transactional
	public void addProduct(ProductModel product) {
		 getSessionFactory().getCurrentSession().save(product);
		
	}

	@Override
	@Transactional
	public ProductModel findProductById(ProductModel id) {
		return (ProductModel) sessionFactory.getCurrentSession().get(ProductModel.class, id.getId());
		
	}

	@Override
	@Transactional
	public void deleteProduct(ProductModel product) {
		getSessionFactory().getCurrentSession().delete(product);	
	}

	@Override
	@Transactional
	public void updateProduct(ProductModel product) {
		 getSessionFactory().getCurrentSession().update(product);
	}

}
