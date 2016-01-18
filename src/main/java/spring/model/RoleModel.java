package spring.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
@NamedQueries({
	@NamedQuery(name = "roles.all", query = "Select r from Roles r")
})
public class RoleModel {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;
	 
	 private String role;
	 
	 @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	 private Set<UserModel> userRoles;

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

	public Set<UserModel> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserModel> userRoles) {
		this.userRoles = userRoles;
	}
	 
	 
}
