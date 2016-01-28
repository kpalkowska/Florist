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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity 
@Table(name = "Users")
@NamedQueries({
	@NamedQuery(name = "users.all", query = "Select u from UserModel u")
})
public class UserModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    private String name;
    private String surname;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address")
    private AddressModel address;
  
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "role")
    private RoleModel role;
    
    public UserModel(String Name, String Surname, AddressModel Address, RoleModel Role) {
		this.name = Name;
		this.surname = Surname;
		this.address = Address;
		this.role = Role;
	}

}
