package spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name = "Addresses")
@NamedQueries({
	@NamedQuery(name = "addresses.all", query = "Select a from AddressModel a"),
	@NamedQuery(name = "addresses.byCity", query = "Select a from AddressModel a where a.city = :city")
})
public class AddressModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String zipKode;
	private String city;
	private String street;
	private String number;
	
	public AddressModel(){}
	
	public AddressModel(String ZipKode, String City, String Street, String Number){
		this.zipKode = ZipKode;
		this.city = City;
		this.street = Street;
		this.number = Number;
	}
	
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
