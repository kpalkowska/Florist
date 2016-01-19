package spring.model;

import javax.jdo.annotations.Join;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    private String name;
    private String surname;
    
//  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  @JoinColumn
    @Column(name = "address")
    private long address;
  
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "role")
    private long role;
    
    public UserModel(String Name, String Surname, long Address, long Role) {
		this.name = Name;
		this.surname = Surname;
		this.address = Address;
		this.role = Role;
	}

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
	public long getAddress() {
		return address;
	}
	public void setAddress(long address) {
		this.address = address;
	}
	public long getRole() {
		return role;
	}
	public void setRole(long role) {
		this.role = role;
	}
}
