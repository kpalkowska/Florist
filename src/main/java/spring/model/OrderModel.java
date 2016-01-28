package spring.model;

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

@Data
@Entity
@Table(name = "Orders")
@NamedQueries({
	@NamedQuery(name = "orders.all", query = "Select o from OrderModel o")
})
public class OrderModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String date;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "users")
	private UserModel users;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private AddressModel address;

	public OrderModel(String date, UserModel user, AddressModel address) {
		this.date = date;
		this.users = user;
		this.address = address;
	}

	public OrderModel(String date, UserModel user) {
		this.date = date;
		this.users = user;
	}
		
}
