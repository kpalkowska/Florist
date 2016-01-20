package spring.service;
import spring.model.AddressModel;
import java.util.List;

public interface AddressService {
	void addAddress(AddressModel address);
	List<AddressModel> getAllAddresses();
	void deleteAddress(AddressModel address);
	void updateAddress(AddressModel address);
	AddressModel findAddress(AddressModel id);
}
