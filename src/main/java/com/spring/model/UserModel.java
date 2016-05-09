package com.spring.model;

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

import com.spring.model.AddressModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "Users")
@NamedQueries({ @NamedQuery(name = UserModel.USERS_ALL, query = "Select u from UserModel u"),
		@NamedQuery(name = UserModel.USER_BY_LOGIN, query = "Select u from UserModel u where u.login = :login"),
		@NamedQuery(name = UserModel.USER_BY_ID, query = "Select u.login from UserModel u where u.id = :id") })
public class UserModel {

	public static final String USERS_ALL = "users.all";
	public static final String USER_BY_LOGIN = "user.byLogin";
	public static final String USER_BY_ID = "user.byID";
	public static final String USER_EXISTS = "select count(*) from UserModel user where user.login = :login";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private @NonNull String name;
	private @NonNull String surname;
	private @NonNull String login;
	private @NonNull String password;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "address")
	private @NonNull AddressModel address;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	private @NonNull RoleModel role;

	public UserModel(String username) {
		this.login = username;
	}

}
