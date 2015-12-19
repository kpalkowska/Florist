package java.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity 
@NamedQueries({
	@NamedQuery(name = "orders.all", query = "Select o from Orders o")
})
public class OrderModel {

		private Integer id;
		private UserModel user_id;
		private AddressModel address_id;

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
