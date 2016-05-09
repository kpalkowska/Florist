package com.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Orders")
@NamedQueries({ @NamedQuery(name = OrderModel.ORDERS_ALL, query = "Select o from OrderModel o"),
		@NamedQuery(name = OrderModel.ORDER_EXISTS, query = "Select o from OrderModel o where o.date = :date and o.users = :users and o.address = :address") })
public class OrderModel {

	public static final String ORDERS_ALL = "orders.all";
	public static final String ORDER_EXISTS = "order.exists";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private @NonNull String date;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "users")
	private @NonNull UserModel users;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private @NonNull AddressModel address;

	public OrderModel(String date, UserModel user) {
		this.date = date;
		this.users = user;
	}

}
