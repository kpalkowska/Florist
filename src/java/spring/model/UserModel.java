package java.spring.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="USERS")
public class UserModel {

	private int id;
    private String name;
    private String surname;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="USER_ADDRESS",
        joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="address_id", referencedColumnName="id")}
    )
    private AddressModel address_id;
  
    @OneToOne(cascade=CascadeType.ALL)
    @JoinTable(name="USER_ROLES",
        joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
    )
    private RoleModel role_id;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 @Column(name="NAME", unique = true, nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 @Column(name="SURNAME", unique = true, nullable = false)
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
