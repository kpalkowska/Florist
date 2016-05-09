package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.AddressDAO;
import com.spring.model.AddressModel;

@Service(value = "addressService")
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDAO addressDAO;

	@Override
	public void addAddress(AddressModel address) {
		addressDAO.addAddress(address);
	}

	@Override
	public void deleteAddress(AddressModel address) {
		addressDAO.deleteAddress(address);
	}

	@Override
	public List<AddressModel> getAllAddresses() {
		return addressDAO.getAllAddresses();
	}

	@Override
	public void updateAddress(AddressModel address) {
		addressDAO.updateAddress(address);
	}

	@Override
	public AddressModel findAddress(AddressModel address) {
		return addressDAO.findAddress(address);
	}

	@Override
	public AddressModel exists(String zipCode, String city, String street, String number) {
		return addressDAO.existed(zipCode, city, street, number);
	}
}
