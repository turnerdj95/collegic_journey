/*
 * File: Weight.java
 * Author: Derek Turner
 * Date: October 6, 2020
 * Purpose: This program stores weights and methods on those weights.
 */

import java.text.DecimalFormat;

public class Weight {
	private int pounds;
	private double ounces;
	private DecimalFormat round = new DecimalFormat("###.###");
	private int CONVERT = 16;
	
	public Weight(double weight) { // Constructor takes weight in pounds
		normalize(weight);
	}
	
	public boolean lessThan(double weight) { //Takes weight in pounds and tests if weight object is less than the passed weight.
		//Convert to ounces
		double passedWeight = toOunces(weight);
		double objectWeight = toOunces();
		// return result
		return objectWeight < passedWeight;
	}
	
	public double addTo(double weight) { // Takes a weight in pounds and adds it to the weight object's weight. Returns new object weight in oz.
		//Convert to ounces
		double passedWeight = toOunces(weight);
		double objectWeight = toOunces();
		//Add passed ounces to object
		objectWeight = passedWeight + objectWeight;
		// Apply new weight to weight object, normalizing in the process
		normalize(objectWeight);
		return objectWeight;
	}
	
	public double divide(double weight) { // Takes a weight in pounds and divides the weight object by the weight supplied.  Returns new weight in oz.
		// Convert to oz
		double passedWeight = toOunces(weight);
		double objectWeight = toOunces();
		//Divide
		objectWeight = objectWeight / passedWeight;
		// Apply new weight to weight object, normalizing in the process.
		normalize(objectWeight);
		return objectWeight;
	}
	
	public String toString() {
		return pounds + "lbs " + sigFig(ounces) + " oz";
	}
	
	private double toOunces() { // Converts object's current weight into ounces.
		return ((pounds * CONVERT) + ounces);
	}
	
	private double toOunces(double weight) { // Takes a weight in pounds and converts to ounces
		return weight * CONVERT;
	}
	
	private void normalize(double weightInOunces) { // Takes a weight in oz, normalizes it, then sets it to the Weight Object.
		pounds = (int)(weightInOunces/CONVERT);
		ounces = weightInOunces%CONVERT;
	}
	
	private String sigFig(double num) {
		return round.format(num);
	}
}








