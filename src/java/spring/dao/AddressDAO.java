package java.spring.dao

import java.util.List;
import java.spring.model.*;

public interface AddressDAO {

	List<AddressModel> getAllAddresses();
	void addAddress(AddressModel address);
	AddressModel findAddressByCity(String name);
	void deleteAddress(AddressModel address);
	void updateAddress(AddressModel address);
}
