/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gears;

/**
 *
 * @author vld
 */
public class Gear {

	public int first;
	public int second;

	public Gear(int first, int second) {

		this.first = first;
		this.second = second;

	}

	public String toString(){

		return first + "," + second;	
	}

	
	
}
