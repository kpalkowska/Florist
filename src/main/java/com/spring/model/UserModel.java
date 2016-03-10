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
@NamedQueries({
	@NamedQuery(name = "users.all", query = "Select u from UserModel u"),
	@NamedQuery(name = "user.byLogin", query = "Select u from UserModel u where u.login = :login")
})
public class UserModel {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
