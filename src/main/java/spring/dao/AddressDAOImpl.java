package spring.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.model.*;
import java.util.List;

@Repository
@Transactional
public class AddressDAOImpl implements AddressDAO {
	  	
		
	
		@Autowired
	    private SessionFactory sessionFactory;

	    public SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }

	    public void addAddress(AddressModel address) {
	        getSessionFactory().getCurrentSession().save(address);
	    }

	    public void deleteAddress(AddressModel address) {
	        getSessionFactory().getCurrentSession().delete(address);
	    }

	    public void updateAddress(AddressModel address) {
	        getSessionFactory().getCurrentSession().update(address);
	    }

	    public AddressModel getAddressByCity(String name) {
	        List list = getSessionFactory().getCurrentSession().createQuery("from Addresses where city=?").setParameter(0, name).list();
	        return (AddressModel)list.get(0);
	    }

	    public List<AddressModel> getAllAddresses() {
	        List list = getSessionFactory().getCurrentSession().createQuery("from Addresses").list();
	        return list;
	    }

		@Override
		public AddressModel findAddressByCity(String name) {
			// TODO Auto-generated method stub
			return null;
		}

}
