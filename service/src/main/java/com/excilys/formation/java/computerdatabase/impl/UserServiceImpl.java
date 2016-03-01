package com.excilys.formation.java.computerdatabase.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

@EnableWebSecurity
@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional(readOnly = true)
	public UserDetail getByUsername(String username) {
		return userDAO.getByUsername(username);
	}

	@Override
	public UserDetail add(UserDetail user) {
		return userDAO.add(user);
	}

	@Override
	public void deleteByUsername(String username) {
		userDAO.deleteByUsername(username);
	}

	@Override
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
		LOGGER.info("Starting loadUserByUsername");
		UserDetail userDetail = userDAO.getByUsername(username);
		if(userDetail != null) {
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			for (Authority role : userDetail.getUserRole()) {
				grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
			}
			User user = new User(userDetail.getUsername(), userDetail.getPassword(),
					grantedAuthorities);
			LOGGER.info(user.toString());
			return user;
		}
		throw new UsernameNotFoundException("User not found");
	}

}
