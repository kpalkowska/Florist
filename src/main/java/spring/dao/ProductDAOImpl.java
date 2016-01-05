package spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.model.*;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {
	  	
		@Autowired
	    private SessionFactory sessionFactory;

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public void addProduct(ProductModel product) {
	        getSessionFactory().getCurrentSession().save(product);
	    }

	    public void deleteProduct(ProductModel product) {
	        getSessionFactory().getCurrentSession().delete(product);
	    }

	    public void updateProduct(ProductModel product) {
	        getSessionFactory().getCurrentSession().update(product);
	    }

	    public ProductModel getProductByName(String name) {
	        List list = getSessionFactory().getCurrentSession().createQuery("from Products where name=?").setParameter(0, name).list();
	        return (ProductModel)list.get(0);
	    }

	    public List<ProductModel> getAllProducts() {
	        List list = getSessionFactory().getCurrentSession().createQuery("from Products").list();
	        return list;
	    }

		@Override
		public ProductModel findProductByName(String name) {
			// TODO Auto-generated method stub
			return null;
		}

}
