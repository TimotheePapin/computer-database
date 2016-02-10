package com.excilys.formation.java.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.computerdatabase.mapper.MapCompany;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.persistence.CompanyDAO;

/**
 * The Class CompanyDAOImpl.
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {

	@Autowired
	private JdbcTemplate jdbcTempalte;

	@Autowired
	private MapCompany mapCompany;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompanyDAOImpl.class);

	@Override
	public List<Company> getAll() {
		LOGGER.info("Starting Company getAll");
		return jdbcTempalte.query("SELECT * FROM company", mapCompany);
	}

	@Override
	public Company getByName(String name) {
		LOGGER.info("Starting Company getByName");
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM company WHERE name=?");
				statement.setString(1, name);
				return statement;
			}
		};
		return jdbcTempalte.query(preparedStatement, mapCompany).get(0);
	}

	@Override
	public void deleteById(int id) {
		LOGGER.info("Starting Company deleteById");
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement("DELETE FROM company where id= ?");
			statement.setInt(1, id);
			return statement;
			}
		};
		jdbcTempalte.update(preparedStatement);
	}

	@Override
	public Company add(Company company) {
		LOGGER.info("Starting Company addCompany");
		KeyHolder holder = new GeneratedKeyHolder();
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO company (name) VALUES (?)",
						Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, company.getName());
			return statement;	
			}
		};
		jdbcTempalte.update(preparedStatement, holder);
		company.setId(holder.getKey().intValue());
		return company;
	}
}
