package finalReview;

import java.util.ArrayList;

import finalReview.Q2Shape.Shape;

/*
 * a) Does this work? 
 * b) Why would this be an undesirable solution?
 */

public class Q3Extension {
	class Picture extends ArrayList<Shape> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Picture() {
			super();
		}

		public double findTotalArea() {
			double total = 0.0;
			for (Shape s : this) {
				total += s.getArea();
			}
			return total;
		}
	}

}
