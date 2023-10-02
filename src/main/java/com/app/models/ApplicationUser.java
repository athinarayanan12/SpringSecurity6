package com.app.models;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="users")
public class ApplicationUser implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true)
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role_junction",
		joinColumns = {@JoinColumn(name="user_id")},
		inverseJoinColumns  = {@JoinColumn(name="role_id")}
	)
	private Set<Role> authority;
	
	
	public ApplicationUser() {
		super();
		// TODO Auto-generated constructor stub
		
	}

	public ApplicationUser(Integer id, String username, String password, Set<Role> authority) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.authority = authority;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAuthority(Set<Role> authority) {
		this.authority = authority;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authority;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
