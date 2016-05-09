package com.spring.service;

import java.util.List;

import com.spring.model.AddressModel;

public interface AddressService {
	void addAddress(AddressModel address);

	List<AddressModel> getAllAddresses();

	void deleteAddress(AddressModel address);

	void updateAddress(AddressModel address);

	AddressModel findAddress(AddressModel id);

	AddressModel exists(String zipCode, String city, String street, String number);
}
