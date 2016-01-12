package com.excilys.formation.java.computerDatabase.service;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import com.excilys.formation.java.computerDatabase.mapper.MapComputer;
import com.excilys.formation.java.computerDatabase.model.Computer;
import com.excilys.formation.java.computerDatabase.persistence.impl.DAOComputerImpl;

@SuppressWarnings("serial")
public class ServiceComputer implements Serializable {

	private static ServiceComputer _instance = null;

	private static DAOComputerImpl sqlComputer;

	Scanner sc = new Scanner(System.in);

	private ServiceComputer() {
		sqlComputer = DAOComputerImpl.getInstance();
	}

	public void afficherComputer(Computer computer) {
		System.out.println(computer.getId() + " - " + computer.getName() + " : " + computer.getIntroduced() + " ; "
				+ computer.getDiscontinued() + " ; " + computer.getCompanyId());
	}

	public void afficherComputers(List<Computer> computers) {
		for (Computer computer : computers) {
			afficherComputer(computer);
		}
		computers.size();
	}

	public List<Computer> recupererComputers() {
		return mapComputer.mapcomputers(sqlComputer.recupComputers());
	}

	public Computer recupererComputer () {
		ResultSet result;
		System.out.println("Get Computer : \n 1-By name \n 2-By id \n 3-Return to Menu");
		int rep = sc.nextInt();
		if (rep == 1) {
			System.out.println("Enter the name of the computer :");
			sc.nextLine();
			String name = sc.nextLine();
			result  = sqlComputer.recupComputer(name);
		}
		else if (rep == 2) {
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
		int rep = sc.nextInt();
		if (rep == 1) {
			System.out.println("Enter the name of the computer :");
			sc.nextLine();
			String name = sc.nextLine();
			sqlComputer.supprComputer(name);
		}
		else if (rep == 2) {
			System.out.println("Enter the id of the computer :");
			int id = sc.nextInt();
			sqlComputer.supprComputer(id);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void creerComputer() {
		Timestamp introduced=null;
		Timestamp discontinued=null;
		boolean date = false;
		System.out.println("Enter Computer name :");
		String name = sc.nextLine();
		do {
			try {
				System.out.println("Enter Computer introduced (yyyy-mm-dd) :");
				String introducedString = sc.nextLine();
				String[] temp = introducedString.split("-");
				introduced = new Timestamp(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2]), 0, 0, 0, 0);
				System.out.println("Enter Computer discontinued (yyyy-mm-dd) :");
				String discontinuedString = sc.nextLine();
				temp = discontinuedString.split("-");
				discontinued = new Timestamp(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2]), 0, 0, 0, 0);
				date =true;
			} catch (Exception e){ 
				System.out.println("Wrong entry for one of the dates :");
			}
		}while (!date);
		System.out.println("Enter Computer companyId :");
		int companyId = sc.nextInt();
		sqlComputer.creerComputer(name,introduced,discontinued,companyId);
	}
	
	@SuppressWarnings("deprecation")
	public void majComputer () {
		ResultSet result;
		int res=0;
		boolean date =false;
		Computer computer;
		Timestamp introduced=null;
		Timestamp discontinued=null;
		System.out.println("Update Computer : \n 1-By name \n 2-By id \n 3-Return to Menu");
		int rep = sc.nextInt();
		if (rep == 1) {
			System.out.println("Enter the name of the computer :");
			sc.nextLine();
			String name = sc.nextLine();
			result  = sqlComputer.recupComputer(name);
		}
		else if (rep == 2) {
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
				String introducedString = computer.getIntroduced();
				String[] temp = introducedString.split("-");
				introduced = new Timestamp(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2].substring(0, 1)), 0, 0, 0, 0);
				String discontinuedString = computer.getDiscontinued();
				temp = discontinuedString.split("-");
				discontinued = new Timestamp(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2].substring(0, 1)), 0, 0, 0, 0);
				while (res != 4) {
					System.out.println(
							"Update : \n 1-Introduced \n 2-Discontinued \n 3-ComapnyId \n 4-Finish the update");
					res=sc.nextInt();
					switch (res) {
					case 1:
						date=false;
						do {
							try {
								System.out.println("Enter the new value (yyyy-mm-dd) :");
								sc.nextLine();
								introducedString = sc.nextLine();
								temp = introducedString.split("-");
								introduced = new Timestamp(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2]), 0, 0, 0, 0);
								date=true;
							} catch (Exception e){ 
								System.out.println("Wrong entry for the date :");
							}
						}while (!date);
								break;
					case 2:
						date=false;
						do {
							try {
								System.out.println("Enter the new value (yyyy-mm-dd):");
								sc.nextLine();
								discontinuedString = sc.nextLine();
								temp = discontinuedString.split("-");
								discontinued = new Timestamp(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1])-1, Integer.parseInt(temp[2]), 0, 0, 0, 0);
								date=true;
							} catch (Exception e){ 
								System.out.println("Wrong entry for the date :");
							}
						}while (!date);
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
				sqlComputer.updateComputer(computer.getId(),computer.getName(),introduced,discontinued,computer.getCompanyId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		sqlComputer.fermeConnection();
	}

	synchronized public static ServiceComputer getInstance() {
		if (_instance == null) {
			_instance = new ServiceComputer();
		}
		return _instance;
	}
}
