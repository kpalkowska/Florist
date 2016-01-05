package java.spring.service;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hsqldb.rights.Address;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressServiceImpl implements AddressService  {
	 @Autowired
	    private AddressDAO addressDAO;

	    @Autowired
		private SessionFactory sessionFactory;

		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}
		
		
		@Override
		public void addAddress(Address address) {
			sessionFactory.getCurrentSession().persist(address);
		}

		@Override
		public void deleteAddress(Address address) {
			sessionFactory.getCurrentSession().delete(address);	
		}
		
		@Override
		public List<Address> getAllAddresses() {
			return sessionFactory.getCurrentSession().getNamedQuery("addresses.all").list();
		}
		@Override
		public void updateAddress(Address address) {
			sessionFactory.getCurrentSession().merge(address);
		}
		
		
}
