package com.excilys.formation.java.computerdatabase;

import java.util.List;

import com.excilys.formation.java.computerdatabase.model.UserDetail;

/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<UserDetail> getAll();

	/**
	 * Gets the by username.
	 *
	 * @param username the username
	 * @return the by username
	 */
	UserDetail getByUsername(String username);

	/**
	 * Adds the user
	 *
	 * @param user the user
	 * @return the user
	 */
	UserDetail add(UserDetail user);

	/**
	 * Delete by username.
	 *
	 * @param username the username
	 */
	void deleteByUsername(String username);

	/**
	 * Update.
	 *
	 * @param user the user
	 * @return the user
	 */
	UserDetail update(UserDetail user);

}
