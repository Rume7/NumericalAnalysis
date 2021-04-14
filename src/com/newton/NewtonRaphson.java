/**
 * @author Rhume
 * @date   April 6, 2021
 */
package com.newton;

import java.util.*;

public class NewtonRaphson {

	private static Scanner input = new Scanner(System.in);
	private List<Double> polynomialCoefficients = new ArrayList<>();	
	
	private static double epsilon = 0.000001;
	
	/**
	 * Enter the order and coefficients of the function. If coefficient doesn't
	 * exist, enter 0.
	 */
	private void inputFunction() {
		System.out.print("Enter the order of the polynomial: ");
		int order = input.nextInt();

		System.out.print("Enter the coefficient of terms of the function: ");

		while (order >= 0) {
			double value = input.nextDouble();
			polynomialCoefficients.add(value);
			order--;
		}
	}
	
	/**
	 * The function method to compute the value of the function given variable x
	 * 
	 * @param x
	 * @return result of inputing variable x
	 */
	private double function(List<Double> coeff, double x) {
		double result = 0.0;
		for (int index = 0, order = coeff.size()-1; index < coeff.size(); order--, index++) {
			result += coeff.get(index) * (Math.pow(x, order));
		}
		return result;
	}
	
	/**
	 * Compute the derivative of the polynomial and stores them in an Arraylist
	 * @param coefficients
	 * @return An Arraylist of the coefficients.
	 */
	private List<Double> getCoefficientsOfFunctionDerivative(List<Double> coefficients) {
		List<Double> coeffOfPolynomialDerivative = new ArrayList<>();
		
		for (int index = 0, order = coefficients.size()-1; index < coefficients.size(); order--, index++) {
			double value = order * coefficients.get(index);
			coeffOfPolynomialDerivative.add(value);
		}
		return coeffOfPolynomialDerivative;
	}
	
	/**
	 * Compute the root of the equation using the Newton-Raphson method	
	 * @param x0
	 */
	private void newtonRaphson(double x0) {
		double xx = x0;
		double h;
		
		while (true) {
			h = function(polynomialCoefficients, xx)/function(getCoefficientsOfFunctionDerivative(polynomialCoefficients), xx);
			xx = xx - h;
			//System.out.println("h: " + h + " xx: " + xx);
			if (Math.abs(h) <= epsilon) {
				break;
			}			
		}		
		System.out.println("The value of the root is: " + Math.round(xx * 1000.0)/1000.0);
	}
	
	public static void main(String[] args) {
		
		NewtonRaphson solution = new NewtonRaphson();
		solution.inputFunction();
		
		System.out.print("Enter your initial guess: ");
		double initialValue = input.nextDouble();
		
		long startTime = System.nanoTime();
		solution.newtonRaphson(initialValue);
		
		long timeToRun = (System.nanoTime() - startTime)/1000;
		System.out.println("Time used is " + timeToRun + " milliseconds.");
		
	}
}
