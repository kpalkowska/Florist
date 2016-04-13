package com.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Roles")
@NamedQueries({ @NamedQuery(name = RoleModel.ROLES_ALL, query = "Select r from RoleModel r"),
		@NamedQuery(name = RoleModel.ROLE_EXISTS, query = "Select r from RoleModel r where r.role = :role") })
public class RoleModel {

	public static final String ROLES_ALL = "roles.all";
	public static final String ROLE_EXISTS = "role.exists";

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private @NonNull String role;

}
