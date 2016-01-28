package spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Id;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
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
	
	private @NonNull String zipKode;
	private @NonNull String city;
	private @NonNull String street;
	private @NonNull String number;
	
}
