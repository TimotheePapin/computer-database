package com.excilys.formation.java.computerdatabase.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.computerdatabase.UserDAO;
import com.excilys.formation.java.computerdatabase.model.Authority;
import com.excilys.formation.java.computerdatabase.model.UserDetail;

@Repository
@SuppressWarnings("unchecked")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sf;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAOImpl.class);

	@Override
	public UserDetail getByUsername(String username) {
		LOGGER.info("Starting UserDetail getByName {}", username);
		Session session = sf.getCurrentSession();
		return (UserDetail) session.createCriteria(UserDetail.class).add(Restrictions.eq("username", username)).uniqueResult();
	}

	@Override
	public UserDetail add(UserDetail userDetail) {
		LOGGER.info("Starting UserDetail add {}", userDetail);
		Session session = sf.getCurrentSession();
		session.save(userDetail);
		for (Authority role : userDetail.getUserRole()) {
			session.save(role);
		}
		return userDetail;
	}

	@Override
	public void deleteByUsername(String username) {
		LOGGER.info("Starting UserDetail deleteByName {}", username);
		Session session = sf.getCurrentSession();
		Criterion userDetailName = Restrictions.like("username", username);
		List<UserDetail> UserDetails = (List<UserDetail>) session.createCriteria(UserDetail.class,"users")
				.add(userDetailName).list();
		if(!UserDetails.isEmpty()) {
			session.delete(UserDetails.get(0));
		}
	}

	@Override
	public UserDetail update(UserDetail userDetail) {
		LOGGER.info("Starting UserDetail update {}", userDetail);
		deleteByUsername(userDetail.getUsername());
		return add(userDetail);
	}

	@Override
	public List<UserDetail> getAll() {
		LOGGER.info("Starting UserDetail getAll");
		Session session = sf.getCurrentSession();
		return session.createCriteria(UserDetail.class).list();
	}
}