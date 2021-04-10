/**
 * @author Rhume
 * @date April 10, 2021
 */
package com.bisection;

import java.util.*;

public class Bisection {
	
	private static Scanner input = new Scanner(System.in);
	private static List<Double> coefficients = new ArrayList<>();
	
	private static double epsilon = 0.00000001;	// precision.
	
	/**
	 * Input the coefficients of the polynomial
	 */
	private void inputPolynomial() {
		System.out.print("Enter the order of the polynomial: ");
		int order = input.nextInt();

		System.out.print("Enter the coefficient of terms of the function: ");

		while (order >= 0) {
			double value = input.nextDouble();
			coefficients.add(value);
			order--;
		}
	}

	/**
	 * The function method to compute the value of the function given variable x
	 * 
	 * @param x
	 * @return result of inputing variable x
	 */
	private double function(double x) {
		double result = 0.0;
		for (int index = 0, order = coefficients.size()-1; index < coefficients.size(); order--, index++) {
			result += coefficients.get(index) * (Math.pow(x, order));
		}
		return result;
	}
	
	private void calculateRoot() {
		double a, b;
		// Ask user for a and b until f(a) * f(b) > 0
		do {
			System.out.print("Enter the start of the interval: ");
			a = input.nextDouble();
			System.out.print("Enter the end of the interval: ");
			b = input.nextDouble();
			
			if (function(a) * function(b) >= 0) {
				System.out.println("Sorry, the root is not within the 2 numbers.\nDo try again.");
			}
		} while(function(a)*function(b) >= 0);
		
		long startTime = System.nanoTime();
		double root = bisectionMethod(a, b);
		long timeUsed = System.nanoTime() - startTime;
		System.out.printf("\nThe root is: %.6f.\n", root);
		System.out.println("Time used is " + timeUsed/1000 + " milliseconds.");
	}
	
	private double bisectionMethod(double start, double end) {
		double middle = (start + end)/2.0;;
		
		while (Math.abs(start - end) > epsilon) {			
			//System.out.println("x: " + middle);
			
			if ((function(start) * function(middle) == 0.0) || (function(end) * function(middle) == 0.0)) {
				break;
			} else if (function(start) * function(middle) > 0.0) {
				start = middle;
			} else {
				end = middle;
			}	
			middle = (start + end)/2.0;
		}
		return middle;
	}
	
	public static void main(String[] args) { 		
		Bisection bisection = new Bisection();
		bisection.inputPolynomial();
		bisection.calculateRoot();
	}
}
