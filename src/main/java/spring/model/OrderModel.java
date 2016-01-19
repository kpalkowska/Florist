package spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
@NamedQueries({
	@NamedQuery(name = "orders.all", query = "Select o from Orders o")
})
public class OrderModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private UserModel user;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "Addresses", joinColumns = { @JoinColumn(name = "order") }, inverseJoinColumns = { @JoinColumn(name = "address") })
	@Column(name = "address")
	private AddressModel address;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser_id(UserModel user) {
		this.user = user;
	}
	public AddressModel getAddress() {
		return address;
	}
	public void setAddress_id(AddressModel address) {
		this.address = address;
	}
		
		
}
