package com.excilys.formation.java.computerdatabase;

import java.util.List;

import com.excilys.formation.java.computerdatabase.model.UserDetail;

/**
 * The Interface UserDAO.
 */
public interface UserDAO {

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	List<UserDetail> getAll();

	/**
	 * Gets the by username.
	 *
	 * @param Username the username
	 * @return the by username
	 */
	UserDetail getByUsername(String username);

	/**
	 * Adds the.
	 *
	 * @param UserDetail the user detail
	 * @return the user detail
	 */
	UserDetail add(UserDetail userDetail);

	/**
	 * Delete by username.
	 *
	 * @param Username the username
	 */
	void deleteByUsername(String username);

	/**
	 * Update.
	 *
	 * @param UserDetail the user detail
	 * @return the user detail
	 */
	UserDetail update(UserDetail userDetail);

}
