/**
 * Secant method of determining the roots of a polynomial. 
 * @author Rhume
 * @date  April 5, 2021
 */
package com.secant;

import java.util.*;

public class Secant {

	private static Scanner input = new Scanner(System.in);
	private static List<Double> coefficients = new ArrayList<>();

	/**
	 * Enter the order and coefficients of the function. If coefficient doesn't
	 * exist, enter 0.
	 */
	private static void inputFunction() {
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
	private static double function(double x) {
		double result = 0.0;
		for (int index = 0, order = coefficients.size()-1; index < coefficients.size(); order--, index++) {
			result += coefficients.get(index) * (Math.pow(x, order));
		}
		return result;
	}

	public static void main(String[] args) {

		inputFunction();
		
		double xn1; // This represent x_n-1
		double xn, x, precision;

		System.out.print("Enter the start of the interval: ");
		xn1 = input.nextDouble();

		System.out.print("Enter the end of the interval: ");
		xn = input.nextDouble();

		System.out.print("Enter the precision of the interval (e.g 0.000001): ");
		precision = input.nextDouble();

		long startTime = System.currentTimeMillis();
		
		if (function(xn1) * function(xn) > 0.0) {
			System.out.println("Functions result have same sign value ...");
			//return;
		}

		x = xn1;
		int iterations = 0;
		while (Math.abs(function(x)) > precision) {
			
			System.out.println("x" + iterations + ": " + x + " f(x"+ iterations + "): " + function(x));
			
			x = xn - (function(xn) * (xn-xn1))/(function(xn) - function(xn1));
			xn1 = xn;
			xn = x;
			iterations++;
		}
		
		long stopTime = System.currentTimeMillis();
		long timeUsed = (stopTime - startTime);
		System.out.println("The root of the equation is " +
					Math.round(x*1000000)/1000000.0);		// Answer is rounded to 6 decimal places.
		System.out.println("Time used is " + timeUsed + " milliseconds.");
	}
}
