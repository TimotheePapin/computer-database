package com.excilys.formation.java.computerdatabase.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.java.computerdatabase.ComputerDAO;
import com.excilys.formation.java.computerdatabase.properties.PageProperties;
import com.excilys.formation.java.computerdatabase.enumeration.Order;
import com.excilys.formation.java.computerdatabase.model.Computer;

/**
 * The Class ComputerDAOImpl.
 */
@Repository
@SuppressWarnings("unchecked")
public class ComputerDAOImpl implements ComputerDAO {

	@Autowired
	private SessionFactory sf;

	/**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ComputerDAOImpl.class);

	@Override
	public List<Computer> getAll() {
		LOGGER.info("Starting Computer getAll");
		Session session = sf.getCurrentSession();
		return session.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
				.list();
	}

	@Override
	public List<Computer> getPage(PageProperties prop) {
		LOGGER.info("Starting Computer getPage {}", prop);
		Session session = sf.getCurrentSession();
		org.hibernate.criterion.Order order = null;
		if (prop.getOrder() == Order.ASC) {
			order = org.hibernate.criterion.Order.asc(prop.getBy().toString());
		} else {
			order = org.hibernate.criterion.Order.desc(prop.getBy().toString());
		}
		StringBuilder stringBuilder = new StringBuilder("%")
				.append(prop.getSearch()).append("%");
		Criterion computerCrit = Restrictions.like("computer.name",
				stringBuilder.toString());
		Criterion companyCrit = Restrictions.like("company.name",
				stringBuilder.toString());
		LogicalExpression or = Restrictions.or(computerCrit, companyCrit);
		return session.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
				.addOrder(order).add(or).setFirstResult(prop.getMin())
				.setMaxResults(prop.getSize()).list();
	}

	@Override
	public Computer getById(int id) {
		LOGGER.info("Starting Computer getById {}", id);
		Session session = sf.getCurrentSession();
		return (Computer) session.get(Computer.class, id);

	}

	@Override
	public Computer getByName(String name) {
		LOGGER.info("Starting Computer getByName");
		Session session = sf.getCurrentSession();
		Criterion computerName = Restrictions.like("computer.name", name);
		return (Computer) session.createCriteria(Computer.class, "computer")
				.add(computerName).list().get(0);
	}

	@Override
	public void deleteByName(String name) {
		LOGGER.info("Starting Computer deleteByName");
		Session session = sf.getCurrentSession();
		Criterion computerName = Restrictions.like("computer.name", name);
		List<Computer> computers = (List<Computer>) session
				.createCriteria(Computer.class, "computer").add(computerName)
				.list();
		if (!computers.isEmpty()) {
			session.delete(computers.get(0));
		}
	}

	@Override
	public void deleteById(int id) {
		LOGGER.info("Starting Computer deleteById");
		Session session = sf.getCurrentSession();
		Computer computer = (Computer) session.get(Computer.class, id);
		if (computer != null) {
			session.delete(computer);
		}
	}

	@Override
	public Computer update(Computer computer) {
		LOGGER.info("Starting Computer update {}", computer);
		Session session = sf.getCurrentSession();
		if (computer.getCompany() != null
				&& computer.getCompany().getId() == 0) {
			computer.setCompany(null);
		}
		return (Computer) session.merge(computer);
	}

	@Override
	public Computer add(Computer computer) {
		LOGGER.info("Starting Computer addComputer {}", computer);
		Session session = sf.getCurrentSession();
		if (computer.getCompany() != null
				&& computer.getCompany().getId() == 0) {
			computer.setCompany(null);
		}
		computer.setId((int) session.save(computer));
		return computer;
	}

	@Override
	public int getSize(String search) {
		LOGGER.info("Starting Computer getSize");
		Session session = sf.getCurrentSession();
		StringBuilder stringBuilder = new StringBuilder("%").append(search)
				.append("%");
		Criterion computerCrit = Restrictions.like("computer.name",
				stringBuilder.toString());
		Criterion companyCrit = Restrictions.like("company.name",
				stringBuilder.toString());
		LogicalExpression or = Restrictions.or(computerCrit, companyCrit);
		return ((Long) session.createCriteria(Computer.class, "computer")
				.createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
				.add(or).setProjection(Projections.rowCount()).uniqueResult())
						.intValue();
	}

	@Override
	public List<Computer> getByCompanyId(int companyId) {
		LOGGER.info("Starting Computer getByCompanyId");
		Session session = sf.getCurrentSession();
		return session.createCriteria(Computer.class)
				.add(Restrictions.like("company.id", companyId)).list();
	}
}
