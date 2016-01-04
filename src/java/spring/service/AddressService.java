package java.spring.service;
import java.util.List;

import org.hsqldb.rights.Address;

public interface AddressService {
	void addAddress(Address address);
	List<Address> getAllAddresses();
	void deleteAddress(Address address);
	void updateAddress(Address address);
}
