package com.github.steevedroz.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>Triangle</code> class is a basic representation of a 2D triangle.
 * 
 * @author Steeve Droz
 *
 */
public class Triangle {
	/**
	 * The first corner of the triangle.
	 */
	private Point p1;
	/**
	 * The second corner of the triangle.
	 */
	private Point p2;
	/**
	 * The third corner of the triangle.
	 */
	private Point p3;

	/**
	 * Default constructor. Should not be used verbatim because the three points
	 * are located on the origin.
	 */
	public Triangle() {
		this(Point.ORIGIN, Point.ORIGIN, Point.ORIGIN);
	}

	/**
	 * This constructor creates a triangle with the coordinates of the three
	 * corners.
	 * 
	 * @param p1
	 *            The first corner.
	 * @param p2
	 *            The second corner.
	 * @param p3
	 *            The third corner.
	 */
	public Triangle(Point p1, Point p2, Point p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("/Triangle: ").append(p1).append(", ").append(p2)
				.append(", ").append(p3).append("\\");
		return str.toString();
	}

	/**
	 * Returns the circle that passes through the three corners.
	 * 
	 * @return The circumcircle.
	 */
	public Circle getCircumcircle() {
		return new Circle(this);
	}

	/**
	 * Returns the circle that is tangent to the three sides.
	 * 
	 * @return The incircle.
	 */
	public Circle getIncircle() {
		Point center = p1.average(p2, p3);
		Point onCircle = p1.average(p2);
		return new Circle(center, onCircle);
	}

	/**
	 * Returns true if the point is one of the corners.
	 * 
	 * @param p
	 *            The point to check against.
	 * @return Whether it is a corner.
	 */
	public boolean isPoint(Point p) {
		return p.equals(p1) || p.equals(p2) || p.equals(p3);
	}

	/**
	 * Filters the list of triangles to keep only those that are adjacent to
	 * this triangle. Adjacent means that two corners are in common.
	 * <b>Warning:</b> the triangle can be adjacent but in this triangle.
	 * 
	 * @param others
	 *            The list of triangles to filter.
	 * @return The filtered list of triangles.
	 */
	public List<Triangle> getAdjacents(List<Triangle> others) {
		List<Triangle> adjascents = new ArrayList<Triangle>();
		for (Triangle triangle : others) {
			int sharedPoints = 0;
			for (Point point : triangle.getPoints()) {
				if (isPoint(point)) {
					sharedPoints++;
				}
			}
			if (sharedPoints == 2) {
				adjascents.add(triangle);
			}
		}
		return adjascents;
	}

	/**
	 * Returns an array containing the three corners of this triangle.
	 * 
	 * @return The corners of the triangle.
	 */
	public Point[] getPoints() {
		Point[] points = { p1, p2, p3 };
		return points;
	}

	/**
	 * Returns the first corner of the triangle.
	 * 
	 * @return The first corner of the triangle.
	 */
	public Point getP1() {
		return p1;
	}

	/**
	 * Returns the second corner of the triangle.
	 * 
	 * @return The second corner of the triangle.
	 */
	public Point getP2() {
		return p2;
	}

	/**
	 * Returns the third corner of the triangle.
	 * 
	 * @return The third corner of the triangle.
	 */

	public Point getP3() {
		return p3;
	}

	/**
	 * Sets the first corner of the triangle.
	 * 
	 * @param p1
	 *            The corner to set.
	 */
	public void setP1(Point p1) {
		this.p1 = p1;
	}

	/**
	 * Sets the second corner of the triangle.
	 * 
	 * @param p2
	 *            The corner to set.
	 */
	public void setP2(Point p2) {
		this.p2 = p2;
	}

	/**
	 * Sets the third corner of the triangle.
	 * 
	 * @param p3
	 *            The corner to set.
	 */

	public void setP3(Point p3) {
		this.p3 = p3;
	}
}
