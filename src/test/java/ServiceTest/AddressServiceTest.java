package ServiceTest;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.AddressModel;
import com.spring.service.AddressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context-test.xml"})
@Transactional("txManager")
public class AddressServiceTest {
	
	@Autowired
	AddressService addressService;

	private final String zipCode = "82-300";
	private final String city = "Elblag";
	private final String street = "Kwiatowa";
	private final String number = "7";
	
	private final String zipCode2 = "83-500";
	private final String city2 = "Costam";
	private final String street2 = "Taka";
	private final String number2 = "9";
	
	@Rollback(true)
	@Test
	public void addAddresCheck() {

		int n = addressService.getAllAddresses().size();
		AddressModel address = new AddressModel(zipCode, city, street, number);
		address.setZipCode(zipCode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		AddressModel retrievedAddress = addressService.findAddress(address);
		assertEquals(address.getId(), retrievedAddress.getId());
		assertEquals(zipCode, retrievedAddress.getZipCode());
		assertEquals(city, retrievedAddress.getCity());
		assertEquals(street, retrievedAddress.getStreet());
		assertEquals(number, retrievedAddress.getNumber());

		assertEquals(n+1, addressService.getAllAddresses().size());
	}
	
	@Rollback(true)
	@Test
	public void deleteAddressCheck(){
	
		int n = addressService.getAllAddresses().size();
		AddressModel address = new AddressModel(zipCode, city, street, number);
		address.setZipCode(zipCode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		AddressModel retrievedAddress = addressService.findAddress(address);
		assertEquals(address.getId(), retrievedAddress.getId());
		assertEquals(zipCode, retrievedAddress.getZipCode());
		assertEquals(city, retrievedAddress.getCity());
		assertEquals(street, retrievedAddress.getStreet());
		assertEquals(number, retrievedAddress.getNumber());

		assertEquals(n+1, addressService.getAllAddresses().size());
		
		addressService.deleteAddress(address);
		assertEquals(n, addressService.getAllAddresses().size());
	}
	
	@Rollback(true)
	@Test 
	public void updateAddressCheck(){
		
		int n = addressService.getAllAddresses().size();
		AddressModel address = new AddressModel(zipCode, city, street, number);
		address.setZipCode(zipCode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		AddressModel retrievedAddress = addressService.findAddress(address);
		assertEquals(address.getId(), retrievedAddress.getId());
		assertEquals(zipCode, retrievedAddress.getZipCode());
		assertEquals(city, retrievedAddress.getCity());
		assertEquals(street, retrievedAddress.getStreet());
		assertEquals(number, retrievedAddress.getNumber());

		assertEquals(n+1, addressService.getAllAddresses().size());
		
		retrievedAddress.setZipCode(zipCode2);
		retrievedAddress.setCity(city2);
		retrievedAddress.setStreet(street2);
		retrievedAddress.setNumber(number2);
		addressService.updateAddress(retrievedAddress);
		
		AddressModel retrievedAddress2 = addressService.findAddress(address);
		assertEquals(retrievedAddress.getId(), retrievedAddress2.getId());
		assertEquals(zipCode2, retrievedAddress2.getZipCode());
		assertEquals(city2, retrievedAddress2.getCity());
		assertEquals(street2, retrievedAddress2.getStreet());
		assertEquals(number2, retrievedAddress2.getNumber());
		
	}
}
