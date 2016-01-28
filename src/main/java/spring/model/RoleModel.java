package spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Roles")
@NamedQueries({
	@NamedQuery(name = "roles.all", query = "Select r from RoleModel r")
})
public class RoleModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String role;
	
	public RoleModel(String role) {
		this.role = role;
	} 
	 
}
