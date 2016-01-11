package com.excilys.formation.java.computerDatabase.ui;

import java.util.*;

public class Interface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println(
					"Access Menu : \n 1-List computers \n 2-List companies \n 3-Show computer details \n 4-Create a computer \n 5-Update a computer \n 6-Delete a computer \n 7-Exit");
			switch (sc.nextInt()) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				System.exit(0);
				break;
			default:
				System.out.println("Saisie Incorrecte");
			}
		}
	}

}
