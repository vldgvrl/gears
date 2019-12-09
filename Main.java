/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gears;

import java.util.Scanner;

/**
 *
 * @author vld
 */
public class Main {

	public static void main(String[] args) {
		
		GearSystem gearSystem = new GearSystem();
		int number = 0;

		try{
			while(number == 0){
				Scanner scanner = new Scanner(System.in);
				System.out.println("Enter the gear number: ");
				int fGear = Integer.parseInt(scanner.next());
				if (fGear==-1){
					number=1;
				}else {
					System.out.println("Enter the second gear number: ");
					int sGear = Integer.parseInt(scanner.next());
					gearSystem.addData(fGear, sGear);
				}
			}


			gearSystem.addData();
			gearSystem.recursion();
			gearSystem.getResult();
			gearSystem.checkSpin();
			//gearSystem.getSolution();
			gearSystem.bestResult();

		}catch (Exception e){
			System.out.println("Error. Try to run the program again");
		}

	}
	
}
