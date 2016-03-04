package com.excilys.formation.java.computerdatabase.console;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.excilys.formation.java.computerdatabase.console.service.ConsoleService;
import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;
import com.excilys.formation.java.computerdatabase.mapper.MapComputer;
import com.excilys.formation.java.computerdatabase.model.Company;
import com.excilys.formation.java.computerdatabase.model.Computer;

/**
 * The Class CLI.
 */
@Component
public class CLI {

	@Autowired
	private ConsoleService consoleService;

	public CLI() {
		super();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"console-context.xml");
		CLI cli = factory.getBean(CLI.class);
		cli.displayMenu();
	}

	private void displayMenu() {
		Scanner sc = new Scanner(System.in);
		ComputerDTO computerDTO;
		while (true) {
			System.out.println(
					"Access Menu : \n 1-List computers \n 2-List companies \n 3-Show computer details \n 4-Create a computer ");
			System.out.println(
					" 5-Update a computer \n 6-Delete a computer \n 7-Delete everything linked to a company \n 8-Exit");
			switch (sc.nextInt()) {
			case 1:
				List<ComputerDTO> computers = consoleService.getAllComputer();
				for (ComputerDTO comp : computers) {
					System.out.println(comp);
				}
				break;
			case 2:
				List<Company> companies = consoleService.getAllCompany();
				for (Company company : companies) {
					System.out.println(company);
				}
				break;
			case 3:
				computerDTO = showComputer(sc);
				if (computerDTO.getId() == 0) {
					System.out.println("This computer isn't in the database.");
				} else {
					System.out.println(computerDTO);
				}
				break;
			case 4:
				computerDTO = addComputer(sc);
				System.out.println(computerDTO);
				break;
			case 5:
				computerDTO = updateComputer(sc);
				System.out.println(computerDTO);
				break;
			case 6:
				deleteComputer(sc);
				break;
			case 7:
				deleteByCompany(sc);
				break;
			case 8:
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("Incorrect entry");
			}
		}
	}

	private void deleteByCompany(Scanner sc) {
		System.out.println("Enter the id of the company :");
		int rep = sc.nextInt();
		consoleService.deleteCompany(rep);
	}

	/**
	 * Delete computer.
	 *
	 * @param sc the sc
	 */
	private void deleteComputer(Scanner sc) {
		System.out.println("Enter the id of the computer :");
		int rep = sc.nextInt();
		consoleService.deleteComputer(rep);
	}

	/**
	 * Update computer.
	 *
	 * @param sc the sc
	 * @return the computer
	 */
	private ComputerDTO updateComputer(Scanner sc) {
		int rep;
		boolean date = false;
		Computer computer = null;
		ComputerDTO computerDTO = null;
		System.out.println("Update Computer");
		computerDTO = showComputer(sc);
		if (computerDTO.getId() == 0) {
			System.out.println("This computer isn't in the database.");
		} else {
			System.out.println(computerDTO);
			boolean notDone = true;
			computer = MapComputer.dtoToComputer(computerDTO);
			while (notDone) {
				System.out.println(
						"Update : \n 1-Name \n 2-Introduced \n 3-Discontinued \n 4-ComapnyId \n 5-Finish the update");
				rep = sc.nextInt();
				sc.nextLine();
				switch (rep) {
				case 1:
					System.out.println("Enter Computer name :");
					computer.setName(sc.nextLine());
					break;
				case 2:
					date = false;
					do {
						try {
							System.out.println(
									"Enter Computer introduced (MM/dd/yyyy) (empty if null) :");
							String introducedString = sc.nextLine();
							computer.setIntroduced(
									MapComputer.toDateTime(introducedString));
							date = true;
						} catch (DateTimeParseException e) {
							System.out.println("Wrong entry for the date :");
						}
					} while (!date);
					break;
				case 3:
					date = false;
					do {
						try {
							System.out.println(
									"Enter Computer discontinued (MM/dd/yyyy) (empty if null) :");
							String discontinuedString = sc.nextLine();
							computer.setDiscontinued(
									MapComputer.toDateTime(discontinuedString));
							date = true;
						} catch (DateTimeParseException e) {
							System.out.println("Wrong entry for the date :");
						}
					} while (!date);
					break;
				case 4:
					System.out.println("Enter the new value :");
					Company company = computer.getCompany();
					company.setId(sc.nextInt());
					sc.nextLine();
					computer.setCompany(company);
					break;
				case 5:
					computerDTO = consoleService.updateComputer(computer);
					notDone = false;
					break;
				default:
					System.out.println("Incorrect entry");
				}
			}
		}
		return computerDTO;
	}

	/**
	 * Adds the computer.
	 *
	 * @param sc the sc
	 * @return the computer
	 */
	private ComputerDTO addComputer(Scanner sc) {
		System.out.println("Enter Computer name :");
		sc.nextLine();
		String name = sc.nextLine();
		LocalDateTime introduced = null;
		LocalDateTime discontinued = null;
		boolean date = false; 
		do {
			try {
				System.out.println(
						"Enter Computer introduced (MM/dd/yyyy) (empty if null) :");
				String introducedString = sc.nextLine();
				introduced = MapComputer.toDateTime(introducedString);
				date = true;
			} catch (DateTimeParseException e) {
				System.out.println("Wrong entry for the date :");
			}
		} while(!date);
		do {
			try {
				System.out.println(
						"Enter Computer discontinued (MM/dd/yyyy) (empty if null) :");
				String discontinuedString = sc.nextLine();
				discontinued = MapComputer.toDateTime(discontinuedString);
				date = true;
			} catch (DateTimeParseException e) {
				System.out.println("Wrong entry for the date :");
			}
		} while(!date);
		System.out.println("Enter Computer's companyId :");
		int companyId = sc.nextInt();
		sc.nextLine();
		Company company = new Company(companyId, "");
		Computer computer = new Computer(0, name, introduced, discontinued,
				company);
		return consoleService.createComputer(computer);
	}

	/**
	 * Show computer.
	 *
	 * @param sc the sc
	 * @return the computer
	 */
	private ComputerDTO showComputer(Scanner sc) {
		System.out.println("Enter the id of the computer:");
		int id = sc.nextInt();
		return consoleService.getComputerById(id);
	}

}
