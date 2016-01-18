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

		@Column(name = "order_id")
		private Integer id;
		@Column(name = "user_id")
		private UserModel user_id;
		
		@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinTable(name = "Addresses", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = { @JoinColumn(name = "address_id") })
		@Column(name = "address_id")
		private AddressModel address_id;

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public UserModel getUser_id() {
			return user_id;
		}
		public void setUser_id(UserModel user_id) {
			this.user_id = user_id;
		}
		public AddressModel getAddress_id() {
			return address_id;
		}
		public void setAddress_id(AddressModel address_id) {
			this.address_id = address_id;
		}
		
		
}
