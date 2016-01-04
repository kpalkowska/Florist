package java.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.spring.model.*;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO {
	  	
		@Autowired
	    private SessionFactory sessionFactory;

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public void addOrder(OrderModel order) {
	        getSessionFactory().getCurrentSession().save(order);
	    }

	    public void deleteOrder(OrderModel order) {
	        getSessionFactory().getCurrentSession().delete(order);
	    }

	    public void updateOrder(OrderModel order) {
	        getSessionFactory().getCurrentSession().update(order);
	    }

	    public OrderModel getOrderByName(String name) {
	        List list = getSessionFactory().getCurrentSession().createQuery("from Orders where name=?").setParameter(0, name).list();
	        return (OrderModel)list.get(0);
	    }

	    public List<OrderModel> getAllOrders() {
	        List list = getSessionFactory().getCurrentSession().createQuery("from Orders").list();
	        return list;
	    }

}
