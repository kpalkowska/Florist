package java.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity 
@Table(name = "Users")
@NamedQueries({
	@NamedQuery(name = "users.all", query = "Select u from Users u")
})
public class UserModel {

	private int id;
    private String name;
    private String surname;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AddressModel address_id;
  
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private RoleModel role_id;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public AddressModel getAddress_id() {
		return address_id;
	}
	public void setAddress_id(AddressModel address_id) {
		this.address_id = address_id;
	}
	public RoleModel getRole_id() {
		return role_id;
	}
	public void setRole_id(RoleModel role_id) {
		this.role_id = role_id;
	}
}
