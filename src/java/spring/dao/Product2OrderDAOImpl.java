package java.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.spring.model.*;

@Repository
@Transactional
public class Product2OrderDAOImpl implements Product2OrderDAO {
	  	
		@Autowired
	    private SessionFactory sessionFactory;

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public void addProduct2Order(Product2OrderModel product2Order) {
	        getSessionFactory().getCurrentSession().save(product2Order);
	    }

	    public void deleteProduct2Order(Product2OrderModel product2Order) {
	        getSessionFactory().getCurrentSession().delete(product2Order);
	    }

	    public void updateProduct2Order(Product2OrderModel product2Order) {
	        getSessionFactory().getCurrentSession().update(product2Order);
	    }

	    public List<Product2OrderModel> getAllProduct2Orders() {
	        List list = getSessionFactory().getCurrentSession().createQuery("from Products2Orders").list();
	        return list;
	    }

}
