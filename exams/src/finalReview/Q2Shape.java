package finalReview;

import java.awt.Point;

public class Q2Shape {
	
	/*
	 * Suppose we have the interface at right. 
	 * You might have classes such as Line or Circle or Square 
	 * that implement the interface, as in the example below.
	 * 
	 * Create a class Picture that contains a list of shapes 
	 * and has these public methods, plus a constructor that 
	 * creates an empty Picture:
	 */

	interface Shape {
		double getArea();

		void draw();
	}

	class Circle implements Shape { // example only
		private Point center;
		private double radius;

		public Circle(Point p, double r) {
			radius = r;
			center = p;
		}

		public double getArea() {
			return Math.PI * radius * radius;
		}

		public void draw() {
			// does graphical stuff to draw, details not shown...
		}
	}
	
	class Picture {
		// adds a Shape to this Picture
		public void add(Shape s) {
			
		}
		// finds the total area of all shapes in this Picture (zero if empty)
		public double findTotalArea() {
			return 0;
			
		}
	}

}
