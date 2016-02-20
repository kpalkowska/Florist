package com.spring.dao;

import java.util.List;

import com.spring.model.*;

public interface AddressDAO {

	List<AddressModel> getAllAddresses();
	void addAddress(AddressModel address);
	void deleteAddress(AddressModel address);
	void updateAddress(AddressModel address);
	boolean exists(String zipKode, String city, String street, String number);
}
