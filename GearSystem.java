package gears;


import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vld
 */
public class GearSystem {
	
	ArrayList <Integer> helpPl = new ArrayList<>();
	ArrayList <Integer> helpMin = new ArrayList<>();
	ArrayList <Gear> massive = new ArrayList<>();
	ArrayList <Gear> massiveUndo;

	private int headElement = 1;
	
	private int prevDelEl = 0;
	private int badElementNumber = 0;
	private int newBadElementNumber = 0;
	Gear badElement; 
	Gear alternativeBadElement; 
	ArrayList<Gear> badElementList;
	ArrayList<Gear> newBadElList = new ArrayList<>();
	ArrayList<Gear> tempBadElList = new ArrayList<>();

	ArrayList<Gear> badElementAlternativeList;
	ResultBadElementList allResults;


	int changeBadEl = 0;

	boolean spin = true;
	int maxResult = 0;

	public GearSystem() {
		helpPl.add(headElement);
		
	}

	void addData(int fGear, int sGear) {

		massive.add(new Gear(fGear, sGear));

	}
	public void addData(){

/*		
		massive.add(new Gear(1,2));
		massive.add(new Gear(1,3));
		massive.add(new Gear(3,2));
		massive.add(new Gear(3,4));
		massive.add(new Gear(4,7));
		massive.add(new Gear(7,8));
		massive.add(new Gear(3,5));
		massive.add(new Gear(5,6));
		massive.add(new Gear(6,9));
		massive.add(new Gear(4,10));
		massive.add(new Gear(4,11));
		massive.add(new Gear(10,11));
		massive.add(new Gear(11,12));
		massive.add(new Gear(12,13));
		massive.add(new Gear(11,14));
		massive.add(new Gear(1,17));
		massive.add(new Gear(2,17));

		massive.add(new Gear(1,2));
		massive.add(new Gear(1,3));
		massive.add(new Gear(2,3));
		massive.add(new Gear(3,4));
		massive.add(new Gear(3,5));
		massive.add(new Gear(4,5));
		massive.add(new Gear(5,6));
		massive.add(new Gear(5,7));
		massive.add(new Gear(6,7));
		massive.add(new Gear(6,10));
		massive.add(new Gear(7,9));
		massive.add(new Gear(7,8));

		*/
		sort();

		allResults = new ResultBadElementList();
		massiveUndo = new ArrayList<>(massive);

	}

	private void sort() {
		boolean swapped = true;
		int j = 0;
		Gear tmp;
		System.out.println("Sorted list");
		for (int i=0; i<massive.size(); i++){
			if (massive.get(i).first > massive.get(i).second){
				Gear turn = new Gear(massive.get(i).second, massive.get(i).first);
				massive.add(i, turn);
				massive.remove(i+1);
			}
		}

		System.out.println("Changed list:");
		for (Gear g : massive){
			System.out.println(g.toString());
		}
		System.out.println("****** ");
			
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < massive.size()- j; i++) {

				if  (massive.get(i).first > massive.get(i+ 1).first) {
					tmp = massive.get(i);
					massive.add(i, massive.get(i+1));
					massive.remove(i+1);
					massive.add(i+1, tmp);
					massive.remove(i+2);
					swapped = true;
				}
				
			}

		}

		System.out.println("After sorting:");
		for (Gear g : massive){
			System.out.println(g.toString());
		}
		System.out.println("*******");

	}

			/*	for (Gear g: massive) {
					if(massive.get(i).first == g.second){
						newMassive.add(new Gear(g.second, g.first));
						g.first = 0;
						g.second = 0;
					}
				}

			}else{
			System.out.println("massive size: "+massive.size());

			System.out.println(massive.get(i).toString());

			for (Gear g:newMassive){
				System.out.println(g.toString());
			}
		}*/



	void clean() {
		if(changeBadEl != 1){

			this.massive = new ArrayList<>(massiveUndo);
			helpPl.clear();
			helpPl.add(headElement);
			helpMin.clear();
			System.out.println("Trying to get solution....");
		}else{
			this.massive = new ArrayList<>(massiveUndo);
			for (int i=0; i<this.massive.size(); i++){
				if(this.massive.get(i).first < badElementNumber || 
					this.massive.get(i).second < badElementNumber){

					this.massive.remove(i);
					i--;

				}
			}
			helpPl.clear();
			headElement = badElementNumber; 
			helpPl.add(headElement);
			helpMin.clear();
			System.out.println(this.massive);
			System.out.println("Bad Elements: ");
			System.out.println(badElementList);
			System.out.println("Trying to get solution....");
			
		}
	}

	public void recursion(){

		if(newBadElList != null){

			for (Gear dEl : newBadElList){
				for (int i=0; i<massive.size(); i++){
					if (dEl == massive.get(i)){
						massive.remove(i);
						i--;
					}
				}
			}
			

		}else if(badElementList != null){

			for (Gear dEl : badElementList){
				for (int i=0; i<massive.size(); i++){
					if (dEl == massive.get(i)){
						massive.remove(i);
						i--;
					}
				}
			}
		}


		for (Gear g : massive){

			/***
			 * helpPl.get(helpPl.size()-1 ) return the last element in 
			 * the list
			 */
			if(g.first == helpPl.get(helpPl.size()-1) ){
				helpMin.add(g.second);
			}
		}

		for (Gear g : massive){

			for (int gMin : helpMin){

				if (g.first == gMin){
					helpPl.add(g.second);
					massive.remove(g);
					recursion();
					return;
				}
			}

		}
	}

	void getResult() {
		//System.out.println(helpPl);
		//System.out.println(helpMin);
		badElementList = new ArrayList<>();
		badElementAlternativeList = new ArrayList<>();
	}

	void checkSpin() {
		System.out.println("Check spin:");
		int badEl = 0;

			for (int el : helpPl){
				for (int anEl : helpMin){

					if(el == anEl && badEl== 0){
						badEl = el;
						this.badElementNumber = el;
						spin = false;

					}

				}
			}

		for (Gear badG: massiveUndo){
				if (badG.first == badEl|| badG.second == badEl){
					badElement =badG;
					badElementList.add(badElement);
				}
		}

		if (badEl==0){
			spin = true;
		}


		if (spin){
			System.out.println("System is working well!");
		}else{
			System.out.println("System is not working, "
				+ "because of the element number " + badEl + " el: " + badElement.toString());
			System.out.println(badElementList);
		}
	}

	void getSolution() {
		if (spin){
			System.out.println("\n****Solution:*****\n");
			System.out.println("Deleted elements: ");
			if (changeBadEl == 0){

				for (Gear delEl : badElementList){
					System.out.println(">" + delEl.first + "," + delEl.second);
				}

				maxResult = helpPl.size()+helpMin.size();
				allResults.pushResult(badElementList, maxResult);
				System.out.println("The avarage of working parts: " + maxResult );
			}else{
				for (Gear delEl : tempBadElList){
					System.out.println(">" + delEl.first + "," + delEl.second);
				}

				maxResult = helpPl.size()+helpMin.size();
				allResults.pushResult(tempBadElList, maxResult);
				System.out.println("The avarage of working parts: " + maxResult );
				
			}
			//tryToimprove();
			changeBadEl = 0;


			//System.out.println(helpPl);
			//System.out.println(helpMin);
		}else{
			clean();
			recursion();
			checkSpin();
			getSolution();
		}
	}

	void tryToimprove() {
		System.out.println("Trying to improve result....");

		int indToDel = 0;

		if(changeBadEl == 0){
			badElementList = new ArrayList<>(allResults.middleResult());
			newBadElementNumber = badElementList.get(1).first;
		}else if (changeBadEl == 1){
			// badElementList = new ArrayList<>(allResults.middleResult());
			System.out.println(badElementList);
			for(int i=0; i<badElementList.size(); i++){
				if (badElementList.get(i).first < newBadElementNumber|| 
					badElementList.get(i).second < newBadElementNumber){
					badElementList.remove(i);
					i--;
				}
			}
			newBadElementNumber = badElementList.get(1).first;
			newBadElList.clear();
		}else{
			
		}
			for (Gear badG: massiveUndo){
					if (badG.first == this.newBadElementNumber || badG.second == this.newBadElementNumber){
						newBadElList.add(badG);
						tempBadElList.add(badG);
					}
			}

		//badElementList.remove(0);

		spin = false;
		changeBadEl ++;
		getSolution();
		
		allResults.pushResult(badElementList, maxResult);

	}

	void bestResult() {

		System.out.println("The best solution is to delete those elements: ");


		System.out.println(allResults.middleResult());
		System.out.println("The avarage of working parts: " + allResults.getMax());
		


	}



}
