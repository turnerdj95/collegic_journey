/*
 * File: Weight.java
 * Author: Derek Turner
 * Date: October 6, 2020
 * Purpose: This program reads weights from an input file and outputs the min, max, and average.
 */

import javax.swing.JFileChooser;
import java.io.*;
import java.util.*;
import javax.swing.filechooser.FileSystemView;


public class Project1 {

	public static void main(String[] args) throws Exception {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		int returnValue = jfc.showOpenDialog(null);
		Weight[] test = new Weight[25];
		Weight minimum = new Weight(0);
		Weight maximum = new Weight(0);
		Weight average = new Weight(0);
		double[] temp = new double[25];
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();

			Scanner input = new Scanner(selectedFile);
			
			for (int i=0; i<=25;i++) { // Grab weights from input file
				if (input.hasNextLine()) {
					if (i==25) {
						System.out.println("Too many lines.  Adjust input file to no more than 25 lines");
						System.out.println("Exiting...");
						System.exit(1);
					}
					temp[i] = input.nextDouble();
				}
			}
			
			for (int i=0; i < temp.length; i++) { // Assign weights to objects
				test[i] = new Weight(temp[i]);
			}
			
			// Add results to corresponding Weight objects
			minimum.addTo(findMinimum(test,test.length));
			maximum.addTo(findMaximum(test,test.length));
			average.addTo(findAverage(test,test.length));
			
			// Print results
			System.out.println("Smallest weight: " + minimum.toString());
			System.out.println("Largest weight: " + maximum.toString());
			System.out.println("Average weight: " + average.toString());
		}
		
		//File selectedFile = jfc.getSelectedFile();

		
	}
	
	private static double findMinimum(Weight[] weights, int valid) { // Finds minimum of weight array.
		// Make double array filled with weights from Weight objects
		double weightValues[] = fillWeights(weights, valid);
		// Compute minimum
		double min = weightValues[0];
		for(int i = 0; i < valid; i++) {
			if (weights[i].lessThan(min)) {
				min = weightValues[i];
			}
		}
		return min;
	}
	
	private static double findMaximum(Weight[] weights, int valid) { // Finds maximum of weight array.
		// Make double array filled with weights from Weight objects
		double weightValues[] = fillWeights(weights, valid);
		//Compute maximum
		double max = weightValues[0];
		for(int i = 0; i < valid; i++) {
			if (!weights[i].lessThan(max)) {
				max = weightValues[i];
			}
		}
		return max;
	}
	
	private static double findAverage(Weight[] weights, int valid) { // Finds average of weight array.
		// Make double array filled with weights from Weight objects
		double weightValues[] = fillWeights(weights, valid);
		// Compute sum
		double sum = 0;
		for(int i = 0; i < valid; i++) {
			sum = sum + weightValues[i];
		}
		// Return average
		return sum/valid;
	}
	
	private static double[] fillWeights(Weight[] weights, int valid) { // Takes Weight objects and returns weight in pounds.
		double values[] = new double[valid];
		for (int i = 0; i < valid; i++) {
			values[i] = weights[i].addTo(0);
			values[i] = values[i]/16;
		}
		return values;
	}
}
