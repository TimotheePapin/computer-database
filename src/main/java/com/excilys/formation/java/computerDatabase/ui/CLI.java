package com.excilys.formation.java.computerDatabase.ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.java.computerDatabase.model.Company;
import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.service.ServiceCompany;
import com.excilys.formation.java.computerDatabase.service.ServiceComputer;

/**
 * The Class CLI.
 */
public class CLI {

	/** The service company. */
	private static ServiceCompany serviceCompany;

	/** The service computer. */
	private static ServiceComputer serviceComputer;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		serviceCompany = ServiceCompany.getInstance();
		serviceComputer = ServiceComputer.getInstance();
		Scanner sc = new Scanner(System.in);
		Computer computer;
		while (true) {
			System.out.println(
					"Access Menu : \n 1-List computers \n 2-List companies \n 3-Show computer details \n 4-Create a computer \n 5-Update a computer \n 6-Delete a computer \n 7-Exit");
			switch (sc.nextInt()) {
			case 1:
				List<Computer> computers = serviceComputer.getAll();
				for (Computer comp : computers) {
					System.out.println(comp);
				}
				break;
			case 2:
				List<Company> companies = serviceCompany.getAll();
				for (Company company : companies) {
					System.out.println(company);
				}
				break;
			case 3:
				computer = showComputer(sc);
				if (computer.getId() == 0) {
					System.out.println("This computer isn't in the database.");
				} else {
					System.out.println(computer);
				}
				break;
			case 4:
				computer = addComputer(sc);
				System.out.println(computer);
				break;
			case 5:
				computer = updateComputer(sc);
				System.out.println(computer);
				break;
			case 6:
				deleteComputer(sc);
				break;
			case 7:
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("Incorrect entry");
			}
		}
	}

	/**
	 * Delete computer.
	 *
	 * @param sc the sc
	 */
	private static void deleteComputer(Scanner sc) {
		int rep;
		System.out.println("Delete Computer : \n 1-By name \n 2-By id");
		rep = sc.nextInt();
		if (rep == 1) {
			System.out.println("Enter the name of the computer :");
			sc.nextLine();
			String name = sc.nextLine();
			serviceComputer.deleteByName(name);
		} else if (rep == 2) {
			System.out.println("Enter the id of the computer :");
			int id = sc.nextInt();
			serviceComputer.deleteById(id);
		}
	}

	/**
	 * Update computer.
	 *
	 * @param sc the sc
	 * @return the computer
	 */
	private static Computer updateComputer(Scanner sc) {
		boolean date;
		int rep;
		Computer computer = null;
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		System.out.println("Update Computer");
		computer = showComputer(sc);
		if (computer.getId() == 0) {
			System.out.println("This computer isn't in the database.");
		} else {
			System.out.println(computer);
			boolean notDone = true;
			while (notDone) {
				System.out.println(
						"Update : \n 1-Name \n 2-Introduced \n 3-Discontinued \n 4-ComapnyId \n 5-Finish the update");
				rep = sc.nextInt();
				switch (rep) {
				case 1:
					System.out.println("Enter Computer name :");
					sc.nextLine();
					computer.setName(sc.nextLine());
					break;
				case 2:
					date = false;
					do {
						try {
							System.out.println(
									"Enter Computer introduced (yyyy-mm-dd) (empty if null) :");
							sc.nextLine();
							String introducedString = sc.nextLine();
							if (!introducedString.isEmpty()) {
								String[] temp = introducedString.split("-");
								introduced = LocalDateTime.of(
										Integer.parseInt(temp[0]),
										Integer.parseInt(temp[1]),
										Integer.parseInt(temp[2]), 0, 0, 0, 0);
							}
							computer.setIntroduced(introduced);
							date = true;
						} catch (Exception e) {
							System.out.println("Wrong entry for the date :");
						}
					} while (!date);
					break;
				case 3:
					date = false;
					do {
						try {
							System.out.println(
									"Enter Computer discontinued (yyyy-mm-dd) (empty if null) :");
							sc.nextLine();
							String discontinuedString = sc.nextLine();
							if (!discontinuedString.isEmpty()) {
								String[] temp = discontinuedString.split("-");
								discontinued = LocalDateTime.of(
										Integer.parseInt(temp[0]),
										Integer.parseInt(temp[1]),
										Integer.parseInt(temp[2]), 0, 0, 0, 0);
							}
							computer.setDiscontinued(discontinued);
							date = true;
						} catch (Exception e) {
							System.out.println("Wrong entry for the date :");
						}
					} while (!date);
					break;
				case 4:
					System.out.println("Enter the new value :");
					Company company = computer.getCompany();
					company.setId(sc.nextInt());
					computer.setCompany(company);
					break;
				case 5:
					computer = serviceComputer.update(computer);
					notDone = false;
					break;
				default:
					System.out.println("Incorrect entry");
				}
			}
		}
		return computer;
	}

	/**
	 * Adds the computer.
	 *
	 * @param sc the sc
	 * @return the computer
	 */
	private static Computer addComputer(Scanner sc) {
		boolean date;
		System.out.println("Enter Computer name :");
		sc.nextLine();
		String name = sc.nextLine();
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		date = false;
		do {
			try {
				System.out.println(
						"Enter Computer introduced (yyyy-mm-dd) (empty if null) :");
				String introducedString = sc.nextLine();
				if (!introducedString.isEmpty()) {
					// DateTimeFormatter dateTimeFormatter =
					// DateTimeFormatter.ofPattern("yyyy-mm-dd");
					// dateTimeFormatter.parse(introducedString);
					String[] temp = introducedString.split("-");
					introduced = LocalDateTime.of(Integer.parseInt(temp[0]),
							Integer.parseInt(temp[1]),
							Integer.parseInt(temp[2]), 0, 0, 0, 0);
				}
				date = true;
			} catch (Exception e) {
				System.out.println("Wrong entry for the date :");
			}
		} while (!date);
		date = false;
		do {
			try {
				System.out.println(
						"Enter Computer discontinued (yyyy-mm-dd) (empty if null) :");
				String discontinuedString = sc.nextLine();
				if (!discontinuedString.isEmpty()) {
					String[] temp = discontinuedString.split("-");
					discontinued = LocalDateTime.of(Integer.parseInt(temp[0]),
							Integer.parseInt(temp[1]),
							Integer.parseInt(temp[2]), 0, 0, 0, 0);
				}
				date = true;
			} catch (Exception e) {
				System.out.println("Wrong entry for the date :");
			}
		} while (!date);
		System.out.println("Enter Computer's companyId :");
		int companyId = sc.nextInt();
		System.out.println("Enter Computer's companyName :");
		sc.nextLine();
		String companyName = sc.nextLine();
		Company company = new Company(companyId, companyName);
		Computer computer = new Computer(0, name, introduced, discontinued,
				company);
		return serviceComputer.create(computer);
	}

	/**
	 * Show computer.
	 *
	 * @param sc the sc
	 * @return the computer
	 */
	private static Computer showComputer(Scanner sc) {
		int rep;
		System.out.println("Get Computer : \n 1-By name \n 2-By id");
		rep = sc.nextInt();
		if (rep == 1) {
			System.out.println("Enter the name of the computer :");
			sc.nextLine();
			String name = sc.nextLine();
			return serviceComputer.getByName(name);
		} else if (rep == 2) {
			System.out.println("Enter the id of the computer:");
			int id = sc.nextInt();
			return serviceComputer.getById(id);
		}
		return null;
	}

}
