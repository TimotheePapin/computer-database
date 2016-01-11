package com.excilys.formation.java.computerDatabase.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.java.computerDatabase.mapper.MapComputer;
import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.persistence.SQLComputer;

@SuppressWarnings("serial")
public class ServiceComputer implements Serializable {

	private static ServiceComputer _instance = null;

	private static SQLComputer sqlComputer;
	private static MapComputer mapComputer;

	Scanner sc = new Scanner(System.in);

	private ServiceComputer() {
		sqlComputer = SQLComputer.getInstance();
		mapComputer = MapComputer.getInstance();
	}

	public void afficherComputer(Computer computer) {
		System.out.println(computer.getId() + " - " + computer.getName() + " : " + computer.getIntroduced() + " ; "
				+ computer.getDiscontinued() + " ; " + computer.getCompanyId());
	}

	public void afficherComputers(List<Computer> computers) {
		for (Computer computer : computers) {
			afficherComputer(computer);
		}
	}

	public List<Computer> recupererComputers() {
		return mapComputer.mapcomputers(sqlComputer.recupComputers());
	}

	public Computer recupererComputer () {
		ResultSet result;
		System.out.println("Get Computer : \n 1-By name \n 2-By id \n 3-Return to Menu");
		if (sc.nextInt() == 1) {
			System.out.println("Enter the name of the computer :");
			String name = sc.nextLine();
			result  = sqlComputer.recupComputer(name);
		}
		else if (sc.nextInt() == 2) {
			System.out.println("Enter the id of the computer:");
			int id = sc.nextInt();
			result  = sqlComputer.recupComputer(id);
		}
		else {
			return null;
		}
		try {
			if (result.wasNull()) {
				System.out.println("This computer isn't in the database.");
				return null;
			}
			else{
				return mapComputer.mapcomputer(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void supprimerComputer () {
		System.out.println("Delete Computer : \n 1-By name \n 2-By id \n 3-Return to Menu");
		if (sc.nextInt() == 1) {
			System.out.println("Enter the name of the computer :");
			String name = sc.nextLine();
			sqlComputer.supprComputer(name);
		}
		else if (sc.nextInt() == 2) {
			System.out.println("Enter the id of the computer :");
			int id = sc.nextInt();
			sqlComputer.supprComputer(id);
		}
	}
	
	public void creerComputer() {
		System.out.println("Enter Computer name :");
		String name = sc.nextLine();
		System.out.println("Enter Computer introduced :");
		String introduced = sc.nextLine();
		System.out.println("Enter Computer discontinued :");
		String discontinued = sc.nextLine();
		System.out.println("Enter Computer companyId :");
		int companyId = sc.nextInt();
		sqlComputer.creerComputer(name,introduced,discontinued,companyId);
	}
	
	public void majComputer () {
		ResultSet result;
		Computer computer;
		System.out.println("Update Computer : \n 1-By name \n 2-By id \n 3-Return to Menu");
		if (sc.nextInt() == 1) {
			System.out.println("Enter the name of the computer :");
			String name = sc.nextLine();
			result  = sqlComputer.recupComputer(name);
		}
		else if (sc.nextInt() == 2) {
			System.out.println("Enter the id of the computer :");
			int id = sc.nextInt();
			result  = sqlComputer.recupComputer(id);
		}
		else {
			return;
		}
		try {
			if (result.wasNull()) {
				System.out.println("This computer isn't in the database.");
			}
			else{
				computer = mapComputer.mapcomputer(result);
				afficherComputer(computer);
				while (sc.nextInt() == 4) {
					System.out.println(
							"Update : \n 1-Introduced \n 2-Discontinued \n 3-ComapnyId \n 4-Finish the update");
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Enter the new value :");
						computer.setIntroduced(sc.nextLine());
						break;
					case 2:
						System.out.println("Enter the new value :");
						computer.setDiscontinued(sc.nextLine());
						break;
					case 3:
						System.out.println("Enter the new value :");
						computer.setCompanyId(sc.nextInt());
						break;
					case 4 :
						break;
					default:
						System.out.println("Incorrect entry");
					}
				}
				sqlComputer.updateComputer(computer.getId(),computer.getName(),computer.getIntroduced(),computer.getDiscontinued(),computer.getCompanyId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	synchronized public static ServiceComputer getInstance() {
		if (_instance == null) {
			_instance = new ServiceComputer();
		}
		return _instance;
	}
}
