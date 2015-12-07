package java.spring.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hsqldb.rights.User;

@Entity
@Table(name="ROLES")
public class RoleModel {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;
	 
	 @OneToMany(cascade=CascadeType.ALL)
	    @JoinTable(name="USER_ROLES",
	        joinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")},
	        inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")}
	    )
	 private String role;
	 
	 private Set<User> userRoles;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<User> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<User> userRoles) {
		this.userRoles = userRoles;
	}
	 
	 
}
