/**
 * @author Rhume
 * @date   April 6, 2021
 */
package com.newton;

import java.util.*;

public class NewtonRaphson {

	private static Scanner input = new Scanner(System.in);
	private List<Double> polynomialCoefficients = new ArrayList<>();	
	
	private static double epsilon = 0.0001;
	
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
	
	private List<Double> getCoefficientsOfFunctionDerivative(List<Double> coefficients) {
		List<Double> coeffOfPolynomialDerivative = new ArrayList<>();
		
		for (int index = 0, order = coefficients.size()-1; index < coefficients.size(); order--, index++) {
			double value = order * coefficients.get(index);
			coeffOfPolynomialDerivative.add(value);
		}
		return coeffOfPolynomialDerivative;
	}
	
	private double getFunctionOfDerivative(double x) {
		double result = 0.0;
		
		List<Double> derivative = getCoefficientsOfFunctionDerivative(polynomialCoefficients);
		result = function(derivative, x);
		return result;
	}
	
	private void newtonRaphson(double x) {
		System.out.println("Started computing...");
		double h = function(polynomialCoefficients, x) / function(getCoefficientsOfFunctionDerivative(polynomialCoefficients), x);
		//double h = function(polynomialCoefficients, x)/getFunctionOfDerivative(x);
		
		while (Math.abs(h) >= epsilon ) {
			x = x - h;
			h = function(polynomialCoefficients, x)/function(getCoefficientsOfFunctionDerivative(polynomialCoefficients), x);
		}
		
		System.out.println("The value of the root is: " + Math.round(x * 100000)/100000);
	}
	
	public static void main(String[] args) {
		
		NewtonRaphson solution = new NewtonRaphson();
		solution.inputFunction();
		
		System.out.print("Enter your initial guess: ");
		double initialValue = input.nextDouble();
		solution.newtonRaphson(initialValue);
	}
}
