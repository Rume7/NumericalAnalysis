package com.secant;

import java.util.*;

public class Secant {
	
	private static Scanner input = new Scanner(System.in);
	private static List<Double> coefficients = new ArrayList<>();
	
	/**
	 * Enter the order and coefficients of the function.
	 * If coefficient doesn't exist, enter 0.
	 */
	public void inputFunction() {
		System.out.print("Enter the order of the function: ");
		int order = input.nextInt();
		
		System.out.print("Enter the coefficient of terms of the function: ");
		
		while(order >= 0) {
			double value = input.nextDouble();
			coefficients.add(value);
			order--;
		}
	}
	
	private static double function(double x) {
		double result = 0.0;
		for (int index = 0; index < coefficients.size(); index++) {
			result += coefficients.get(index) * x;
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		double xn1, xn, x, precision;
		
		System.out.print("Enter the start of the interval: ");
		xn1 = input.nextDouble();
		
		System.out.print("Enter the end of the interval: ");
		xn = input.nextDouble();
		
		System.out.print("Enter the start of the interval: ");
		precision = input.nextDouble();

		if (function(xn1) * function(xn) > 0.0) {
			System.out.println();
		}
	}

}
