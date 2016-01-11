package com.excilys.formation.java.computerDatabase.ui;

import java.util.*;

import com.excilys.formation.java.computerDatabase.service.*;

public class Interface {

	private static ServiceCompany serviceCompany;
	private static ServiceComputer serviceComputer;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		serviceCompany = ServiceCompany.getInstance();
		serviceComputer = ServiceComputer.getInstance();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(
					"Access Menu : \n 1-List computers \n 2-List companies \n 3-Show computer details \n 4-Create a computer \n 5-Update a computer \n 6-Delete a computer \n 7-Exit");
			switch (sc.nextInt()) {
			case 1:
				serviceComputer.afficherComputers(serviceComputer.recupererComputers());
				break;
			case 2:
				serviceCompany.afficherCompanies(serviceCompany.recupererCompanies());
				break;
			case 3:
				serviceComputer.afficherComputer(serviceComputer.recupererComputer());
				break;
			case 4:
				serviceComputer.creerComputer();
				break;
			case 5:
				serviceComputer.majComputer();
				break;
			case 6:
				serviceComputer.supprimerComputer();
				break;
			case 7:
				serviceComputer.closeConnection();
				serviceCompany.closeConnection();
				System.exit(0);
				break;
			default:
				System.out.println("Incorrect entry");
			}
		}
	}

}
