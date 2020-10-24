/*
 *James Oswald
 *Period 5
 */

import java.awt.Point;
import java.util.ArrayList;

public class Quadratic
{
	public static double getSumOfRoots(double a, double b, double c) throws ArithmeticException
	{
		return -b / a;
	}
	
	public static double getProductOfRoots(double a, double b, double c) throws ArithmeticException
	{
		return c / a;
	}
	
	public static double getPositiveRoot(double a, double b, double c) throws ArithmeticException
	{
		return (-b + Math.pow(Math.pow(b, 2) - (4 * a * c), 0.5)) / (2 * a);
	}
	
	public static double getNegativeRoot(double a, double b, double c) throws ArithmeticException
	{
		return (-b - Math.pow(Math.pow(b, 2) - (4 * a * c), 0.5)) / (2 * a);
	}
	
	public static double getAxisOfSymetry(double a, double b, double c) throws ArithmeticException
	{
		return -b / (2 * a);
	}
	
	public static Point getTurningPoint(double a, double b, double c) throws ArithmeticException
	{
        double x = getAxisOfSymetry(a, b, c);
        double y = a * Math.pow(x, 2) + b * x + c;
        return new Point((int)x, (int)y);
	}
	
   	public static String getNatureOfRoots(double a, double b, double c) 
	{
		int d = (int)(Math.pow(b, 2) - 4 * a * c);
		return d > 0 ? "Real" : (d == 0 ? "Equal" : "Imaginary");
	}
	
	public static double getYintercept(double a, double b, double c)
	{
		return c;
	}
	
	public static String getStringEquation(double a, double b, double c)
	{
		return "y = " + (a / Math.abs(a) == 1 ? "" : "-") + (int)Math.abs(a) + "x^2" + (b / Math.abs(b) == 1 ? " + " : " - ") + (int)Math.abs(b) + "x" + (c / Math.abs(c) == 1 ? " + " : " - ") + (int)Math.abs(c);   
	}
	
	public static ArrayList <String> printAll(double a, double b, double c) throws ArithmeticException
	{
		ArrayList <String> rv = new ArrayList <String> ();
		String nature = Quadratic.getNatureOfRoots(a, b, c);
		rv.add("Sum of roots: " + (int)Quadratic.getSumOfRoots(a, b, c));
		rv.add("Prod of roots: " + (int)Quadratic.getProductOfRoots(a, b, c));
		rv.add("Pos root: " + (nature.equals("Imaginary") ? "Imaginary" : (int)Quadratic.getPositiveRoot(a, b, c)));
		rv.add("Neg root: " + (nature.equals("Imaginary") ? "Imaginary" : (int)Quadratic.getNegativeRoot(a, b, c)));
		rv.add("Axis of sym: " + (int)Quadratic.getAxisOfSymetry(a, b, c));
		Point p = Quadratic.getTurningPoint(a, b, c);
		rv.add("Turning point: (" + (int)p.getX() + ", " + (int)p.getY() + ")");
		rv.add("nature: " + nature);
		rv.add("Y-inter: " + (int)Quadratic.getYintercept(a, b, c));
		rv.add("Equasion: " + Quadratic.getStringEquation(a, b, c));
		return rv;
	}
}