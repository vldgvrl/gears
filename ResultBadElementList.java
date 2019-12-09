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
public class ResultBadElementList {

	private ArrayList<ResultsOfBadElements> allResults; 
	private int maxResult=0;
	private int maxResultId = 0;
	
	public ResultBadElementList() {
		allResults = new ArrayList<>();
	}

	public void pushResult(ArrayList<Gear> result, int max){

		allResults.add(new ResultsOfBadElements(result, max));
	}

	public ArrayList<Gear> middleResult(){
		for (ResultsOfBadElements r : allResults){
			if (r.maxGears > maxResult){
				maxResult = r.maxGears;
				maxResultId = allResults.indexOf(r);
			}
		}
		return allResults.get(maxResultId).getBadElements();
	}

	public int getMax() {
		return maxResult;
	}




	
}
