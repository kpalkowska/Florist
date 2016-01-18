package spring.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;
@Transactional
@Entity 
@Table(name = "Addresses")
@NamedQueries({
	@NamedQuery(name = "addresses.all", query = "Select a from Addresses a")
})
public class AddressModel {

	private Integer id;
	private String zipKode;
	private String city;
	private String street;
	private String number;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getZipKode() {
		return zipKode;
	}
	public void setZipKode(String zipKode) {
		this.zipKode = zipKode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
}
