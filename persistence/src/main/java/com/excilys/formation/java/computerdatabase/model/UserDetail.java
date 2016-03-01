package com.excilys.formation.java.computerdatabase.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserDetail {

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 255)
	private String username;
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	@OneToMany(mappedBy = "user")
	private Set<Authority> userRole = new HashSet<>(0);

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Authority> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<Authority> userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", userRole=" + userRole + "]";
	}

}
