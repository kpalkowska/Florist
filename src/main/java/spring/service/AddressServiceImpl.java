package spring.service;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import spring.dao.AddressDAO;
import spring.model.AddressModel;

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
		public void addAddress(AddressModel address) {
			sessionFactory.getCurrentSession().persist(address);
		}

		@Override
		public void deleteAddress(AddressModel address) {
			sessionFactory.getCurrentSession().delete(address);	
		}
		
		@Override
		public List<AddressModel> getAllAddresses() {
			return sessionFactory.getCurrentSession().getNamedQuery("addresses.all").list();
		}

		@Override
		public void updateAddress(AddressModel address) {
			// TODO Auto-generated method stub
			
		}
				
}
