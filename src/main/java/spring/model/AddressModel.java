package spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.Id;


@Data
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
	
	public AddressModel(String zipKode, String city, String street, String number){
		this.zipKode = zipKode;
		this.city = city;
		this.street = street;
		this.number = number;
	}
	
}
