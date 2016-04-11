package com.spring.model;

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
@NamedQueries({ @NamedQuery(name = "addresses.all", query = "Select a from AddressModel a"),
		@NamedQuery(name = "addresses.byCity", query = "Select a from AddressModel a where a.city = :city"),
		@NamedQuery(name = "address.exists", query = "Select address from AddressModel address where address.zipCode = :zipCode and address.city = :city and address.street = :street and address.number = :number") })
public class AddressModel {

	public static final String ADDRESS_DAO = "select count(*) from AddressModel address where address.zipCode = :zipCode and address.city = :city and address.street = :street and address.number = :number";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private @NonNull String zipCode;
	private @NonNull String city;
	private @NonNull String street;
	private @NonNull String number;

}
