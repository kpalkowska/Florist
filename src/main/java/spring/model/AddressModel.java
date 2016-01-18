package spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;

import org.springframework.transaction.annotation.Transactional;
@Transactional
@Entity
@Table(name = "Addresses")
@NamedQueries({
	@NamedQuery(name = "addresses.all", query = "Select a from Addresses a")
})
public class AddressModel {

	@Column(name = "address_id")
	private Integer id;
	@Column(name = "zipKode")
	private String zipKode;
	@Column(name = "city")
	private String city;
	@Column(name = "street")
	private String street;
	@Column(name = "number")
	private String number;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
