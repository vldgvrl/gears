/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gears;

import java.util.ArrayList;

/**
 *
 * @author vld
 */
public class ResultsOfBadElements {

	ArrayList<Gear> badElements;
	int maxGears;

	public ResultsOfBadElements(ArrayList<Gear> badEl, int maxGears) {
		badElements = badEl;
		this.maxGears = maxGears;
	}

	public ArrayList<Gear> getBadElements() {
		return badElements;
	}

	public ArrayList<Gear> bestResult() {
		return badElements;
	}

	

	public int getMaxGears() {
		return maxGears;
	}

	
	

}
