package com.excilys.formation.java.computerdatabase.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.java.computerdatabase.UserDAO;
import com.excilys.formation.java.computerdatabase.UserService;
import com.excilys.formation.java.computerdatabase.model.Authority;
import com.excilys.formation.java.computerdatabase.model.UserDetail;

@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserDAO userDAO;

	@Override
	@Transactional(readOnly = true)
	public UserDetail getByUsername(String username) {
		return userDAO.getByUsername(username);
	}

	@Override
	@Transactional
	public UserDetail add(UserDetail user) {
		return userDAO.add(user);
	}

	@Override
	@Transactional
	public void deleteByUsername(String username) {
		userDAO.deleteByUsername(username);

	}

	@Override
	@Transactional
	public UserDetail update(UserDetail user) {
		return userDAO.update(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDetail> getAll() {
		return userDAO.getAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserDetail userDetail = userDAO.getByUsername(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for(Authority role : userDetail.getUserRole()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return new User(userDetail.getUsername(),userDetail.getPassword(),grantedAuthorities );
	}

}
