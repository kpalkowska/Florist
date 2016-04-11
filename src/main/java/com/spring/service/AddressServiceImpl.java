package com.spring.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.AddressModel;

@Component
@Transactional
public class AddressServiceImpl implements AddressService {

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

	@SuppressWarnings("unchecked")
	@Override
	public List<AddressModel> getAllAddresses() {
		return sessionFactory.getCurrentSession().getNamedQuery("addresses.all").list();
	}

	@Override
	public void updateAddress(AddressModel address) {
		sessionFactory.getCurrentSession().merge(address);
	}

	@Override
	public AddressModel findAddress(AddressModel address) {
		return (AddressModel) sessionFactory.getCurrentSession().get(AddressModel.class, address.getId());
	}

	@Override
	public AddressModel exists(String zipCode, String city, String street, String number) {
		return (AddressModel) sessionFactory.getCurrentSession().getNamedQuery("address.exists")
				.setString("zipCode", zipCode).setString("city", city).setString("street", street)
				.setString("number", number).uniqueResult();
	}
}
