import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import spring.model.AddressModel;
import spring.service.AddressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-context.xml" })
@Transactional("txManager")
public class AddressTest {
	@Autowired
//	Manager manager;
	AddressService addressService;

	private final String zipKode = "82-300";
	private final String city = "Elblag";
	private final String street = "Kwiatowa";
	private final String number = "7";
	
	private final String zipKode2 = "83-500";
	private final String city2 = "Costam";
	private final String street2 = "Taka";
	private final String number2 = "9";
	
	@Test
	public void addAddresCheck() {

		int n = addressService.getAllAddresses().size();
		AddressModel address = new AddressModel( zipKode, city, street, number);
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		AddressModel retrievedAddress = addressService.findAddressById(address);
		assertEquals(address.getId(), retrievedAddress.getId());
		assertEquals(zipKode, retrievedAddress.getZipKode());
		assertEquals(city, retrievedAddress.getCity());
		assertEquals(street, retrievedAddress.getStreet());
		assertEquals(number, retrievedAddress.getNumber());

		assertEquals(n+1, addressService.getAllAddresses().size());
	}
	
	@Test
	public void deleteAddressCheck(){
	
		int n = addressService.getAllAddresses().size();
		AddressModel address = new AddressModel( zipKode, city, street, number);
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		AddressModel retrievedAddress = addressService.findAddressById(address);
		assertEquals(address.getId(), retrievedAddress.getId());
		assertEquals(zipKode, retrievedAddress.getZipKode());
		assertEquals(city, retrievedAddress.getCity());
		assertEquals(street, retrievedAddress.getStreet());
		assertEquals(number, retrievedAddress.getNumber());

		assertEquals(n+1, addressService.getAllAddresses().size());
		
		addressService.deleteAddress(address);
		assertEquals(n, addressService.getAllAddresses().size());
	}
	
	@Test 
	public void updateAddressCheck(){
		
		int n = addressService.getAllAddresses().size();
		AddressModel address = new AddressModel( zipKode, city, street, number);
		address.setZipKode(zipKode);
		address.setCity(city);
		address.setStreet(street);
		address.setNumber(number);

		addressService.addAddress(address);
		
		AddressModel retrievedAddress = addressService.findAddressById(address);
		assertEquals(address.getId(), retrievedAddress.getId());
		assertEquals(zipKode, retrievedAddress.getZipKode());
		assertEquals(city, retrievedAddress.getCity());
		assertEquals(street, retrievedAddress.getStreet());
		assertEquals(number, retrievedAddress.getNumber());

		assertEquals(n+1, addressService.getAllAddresses().size());
		
		retrievedAddress.setZipKode(zipKode2);
		retrievedAddress.setCity(city2);
		retrievedAddress.setStreet(street2);
		retrievedAddress.setNumber(number2);
		addressService.updateAddress(retrievedAddress);
		
		AddressModel retrievedAddress2 = addressService.findAddressById(address);
		assertEquals(retrievedAddress.getId(), retrievedAddress2.getId());
		assertEquals(zipKode2, retrievedAddress2.getZipKode());
		assertEquals(city2, retrievedAddress2.getCity());
		assertEquals(street2, retrievedAddress2.getStreet());
		assertEquals(number2, retrievedAddress2.getNumber());
		
	}
}
