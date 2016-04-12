package com.spring.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.*;

import java.util.List;

@Repository
@Transactional
public class AddressDAOImpl extends HibernateDaoSupport implements AddressDAO {

	@Autowired
	public AddressDAOImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void addAddress(AddressModel address) {
		getHibernateTemplate().save(address);
	}

	@Override
	public void deleteAddress(AddressModel address) {
		getHibernateTemplate().delete(address);
	}

	@Override
	public void updateAddress(AddressModel address) {
		getHibernateTemplate().update(address);
	}

	@Override
	public boolean exists(String zipCode, String city, String street, String number) {
		return getHibernateTemplate().execute(new HibernateCallback<Boolean>() {
			@Override
			public Boolean doInHibernate(Session session) throws HibernateException {
				Long count = (Long) session.createQuery(AddressModel.ADDRESS_DAO).setParameter("zipCode", zipCode)
						.setParameter("city", city).setParameter("street", street).setParameter("number", number)
						.uniqueResult();
				return count > 0;
			}
		});
	}
	
	@Override
	public AddressModel existed(String zipCode, String city, String street, String number) {
		return getHibernateTemplate().execute(new HibernateCallback<AddressModel>() {
			@Override
			public AddressModel doInHibernate(Session session) throws HibernateException {
				AddressModel address = (AddressModel) session.createQuery(AddressModel.ADDRESS_DAO).setParameter("zipCode", zipCode)
						.setParameter("city", city).setParameter("street", street).setParameter("number", number)
						.uniqueResult();
				return address;
			}
		});
	}

	@Override
	public List<AddressModel> getAllAddresses() {
		return getHibernateTemplate().loadAll(AddressModel.class);
	}
	
	@Override
	public AddressModel findAddress(AddressModel address) {
		return (AddressModel) getHibernateTemplate().get(AddressModel.class, address.getId());
	}

}
