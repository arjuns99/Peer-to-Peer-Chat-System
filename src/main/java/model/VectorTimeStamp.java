/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import interfaces.VectorInterface;

/**
 *
 * VTS Class has all of the logic allowing serialized methods/returns of VTS
 *
 */
public class VectorTimeStamp implements VectorInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int[] vectorArray;
	public int userId;

	public VectorTimeStamp(int userId) {
		vectorArray = new int[100];
		this.userId = userId;
	}

	public void increaseVectorTimeStamp(int index) {
		vectorArray[index]++;
	}

	public void setVectorTimeStamp(int index, int newValue) {
		vectorArray[index] = newValue;
	}

	public int getVectorTimeStamp() {
		return vectorArray[userId];
	}

	public int getUserId() {
		return userId;
	}

}
