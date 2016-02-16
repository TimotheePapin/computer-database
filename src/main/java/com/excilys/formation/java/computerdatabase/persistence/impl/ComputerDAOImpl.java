package com.excilys.formation.java.computerdatabase.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.computerdatabase.dto.model.PageProperties;
import com.excilys.formation.java.computerdatabase.mapper.MapComputer;
import com.excilys.formation.java.computerdatabase.model.Computer;
import com.excilys.formation.java.computerdatabase.persistence.ComputerDAO;
import com.mysql.jdbc.Statement;

/**
 * The Class ComputerDAOImpl.
 */
@Repository
public class ComputerDAOImpl implements ComputerDAO {

	/**
	 * The jdbc template.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * The map computer.
	 */
	@Autowired
	private MapComputer mapComputer;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAOImpl.class);

	@Override
	public List<Computer> getAll() {
		LOGGER.info("Starting Computer getAll");
		return jdbcTemplate.query("SELECT * FROM computer", mapComputer);
	}

	@Override
	public List<Computer> getPage(PageProperties prop) {
		LOGGER.info("Starting Computer getPage {}", prop);
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.name LIKE ? OR computer.name LIKE ? ORDER BY "
								+ prop.getBy() + " " + prop.getOrder()
								+ " LIMIT ? OFFSET ?");
				statement.setString(1, "%" + prop.getSearch() + "%");
				statement.setString(2, "%" + prop.getSearch() + "%");
				statement.setInt(3, prop.getSize());
				statement.setInt(4, prop.getMin());
				return statement;
			}
		};
		return jdbcTemplate.query(preparedStatement, mapComputer);
	}

	@Override
	public Computer getById(int id) {
		LOGGER.info("Starting Computer getById {}", id);
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id= ?");
				statement.setInt(1, id);
				return statement;
			}
		};
		return jdbcTemplate.query(preparedStatement, mapComputer).get(0);
	}

	@Override
	public Computer getByName(String name) {
		LOGGER.info("Starting Computer getByName");
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(
						"SELECT * FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name= ?");
				statement.setString(1, name);
				return statement;
			}
		};
		return jdbcTemplate.query(preparedStatement, mapComputer).get(0);
	}

	@Override
	public void deleteByName(String name) {
		LOGGER.info("Starting Computer deleteByName");
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM computer where name= ?");
				statement.setString(1, name);
				return statement;
			}
		};
		jdbcTemplate.update(preparedStatement);
	}

	@Override
	public void deleteById(int id) {
		LOGGER.info("Starting Computer deleteById");
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement("DELETE FROM computer where id= ?");
				statement.setInt(1, id);
			return statement;
			}
		};
		jdbcTemplate.update(preparedStatement);
	}

	@Override
	public Computer update(Computer computer) {
		LOGGER.info("Starting Computer update {}", computer);
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?");
			statement.setString(1, computer.getName());
			statement.setTimestamp(2,
					MapComputer.toTimestamp(computer.getIntroduced()));
			statement.setTimestamp(3,
					MapComputer.toTimestamp(computer.getDiscontinued()));
			if (computer.getCompany().getId() == 0) {
				statement.setNull(4, java.sql.Types.BIGINT);
			} else {
				statement.setInt(4, computer.getCompany().getId());
			}
			statement.setInt(5, computer.getId());
			return statement;
			}
		};
		jdbcTemplate.update(preparedStatement);
		return computer;
	}

	@Override
	public Computer add(Computer computer) {
		LOGGER.info("Starting Computer addComputer");
		KeyHolder holder = new GeneratedKeyHolder();
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, computer.getName());
				statement.setTimestamp(2,
						MapComputer.toTimestamp(computer.getIntroduced()));
				statement.setTimestamp(3,
						MapComputer.toTimestamp(computer.getDiscontinued()));
				if (computer.getCompany().getId() == 0) {
					statement.setNull(4, java.sql.Types.BIGINT);
				} else {
					statement.setInt(4, computer.getCompany().getId());
				}
				return statement;
			}
		};
		jdbcTemplate.update(preparedStatement, holder);
		computer.setId(holder.getKey().intValue());
		return computer;
	}

	@Override
	public int getSize(String search) {
		LOGGER.info("Starting Computer getSize");
		StringBuilder stringBuilder = new StringBuilder()
						.append("SELECT COUNT(*) AS count FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.name LIKE ? OR computer.name LIKE ?");
		StringBuilder likeStringBuilder = new StringBuilder().append("%").append(search).append("%");
		return jdbcTemplate.queryForObject(stringBuilder.toString(), Integer.class, new Object[] {likeStringBuilder.toString(), likeStringBuilder.toString()});
	}

	@Override
	public void deleteByCompanyId(int companyId) {
		LOGGER.info("Starting Computer deleteByCompanyId");
		PreparedStatementCreator preparedStatement = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement statement = connection.prepareStatement("DELETE FROM computer where id= ?");
				statement.setInt(1, companyId);
			return statement;
			}
		};
		jdbcTemplate.update(preparedStatement);
	}
}
