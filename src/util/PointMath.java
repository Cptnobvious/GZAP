package util;

public class PointMath {

	public static double distance2Points(double x1, double y1, double x2, double y2){
		double x = x1 - x2;
		double y = y1 - y2;
		
		x = x * x;
		y = y * y;
		
		x = x + y;
		
		return Math.sqrt(x);
	}
	
}
