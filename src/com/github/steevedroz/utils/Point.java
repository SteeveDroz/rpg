package com.github.steevedroz.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The <code>Point</code> class is a very basic representation of a 2D object
 * with an x and a y coordinates.
 * 
 * @author Steeve Droz
 *
 */
public class Point {
	/**
	 * A point located at the origin: its coordinates are (0; 0).
	 */
	public static final Point ORIGIN = new Point(0, 0);

	/**
	 * The x coordinate.
	 */
	public double x;

	/**
	 * The y coordinate.
	 */
	public double y;

	/**
	 * This constructor creates a new point at the given coordinates.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This constructor creates a copy of an existing point.
	 * 
	 * @param p
	 *            The point to copy.
	 */
	public Point(Point p) {
		this(p.x, p.y);
	}

	/**
	 * This constructor creates a point with default coordinates at (0; 0).
	 */
	public Point() {
		this(Point.ORIGIN);
	}

	/**
	 * This method generates a gradient vector of length 1 with the provided
	 * angle.
	 * 
	 * @param angle
	 *            The angle of the gradient.
	 * @return A point at distance 1 from the origin.
	 */
	public static Point fromAngle(double angle) {
		return new Point(Math.cos(angle), Math.sin(angle));
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("(").append(x).append(";").append(y).append(")");
		return str.toString();
	}

	/**
	 * Clones this point and returns a copy of itself.
	 * 
	 * @return A copy of this point.
	 */
	public Point cloneOf() {
		return new Point(this.x, this.y);
	}

	/**
	 * Returns a new point that is the addition of this point and the given one.
	 * 
	 * @param p
	 *            The point to add to this one.
	 * @return The sum of the two points.
	 */
	public Point add(Point p) {
		return new Point(x + p.x, y + p.y);
	}

	/**
	 * Returns a new point that is the subtraction of the given point from this
	 * one.
	 * 
	 * @param p
	 *            The point to subtract from this one.
	 * @return The difference between the two points.
	 */
	public Point sub(Point p) {
		return new Point(x - p.x, y - p.y);
	}

	/**
	 * Multiplies the coordinates of this point by the factor and returns a new
	 * point corresponding to that.
	 * 
	 * @param factor
	 *            The factor, the ratio between the returned point and this one.
	 * @return An amplified point.
	 */
	public Point mul(double factor) {
		return new Point(x * factor, y * factor);
	}

	/**
	 * Shortcut to {@link #mul(double) multiply} this point coordinates by a
	 * factor of the form 1/n.
	 * 
	 * @param factor
	 *            The factor.
	 * @return A diminished point.
	 */
	public Point div(double factor) {
		return mul(1 / factor);
	}

	/**
	 * Returns the dot product of this point and the given one. The dot product
	 * is x<sub>1</sub> * x<sub>2</sub> + y<sub>1</sub> * y<sub>2</sub>.
	 * 
	 * @param p
	 *            The point to use in the calculation with this one.
	 * @return The dot product.
	 */
	public double dot(Point p) {
		return p.x * x + p.y * y;
	}

	/**
	 * Returns the euclidian distance between this point and the given one.
	 * 
	 * @param p
	 *            The provided point.
	 * @return An absolute distance.
	 */
	public double distance(Point p) {
		return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
	}

	/**
	 * Returns the middle point between this point and all the provided ones.
	 * 
	 * @param points
	 *            The list of points
	 * @return The middle point.
	 */
	public Point average(Point... points) {
		Point average = cloneOf();
		for (Point point : points) {
			average = average.add(point);
		}
		return average.div(points.length + 1);
	}

	/**
	 * Calculates and returns the image of this point in the first quadrant,
	 * using x-axis and y-axis symmetries.
	 * 
	 * @return A point with positive coordinates.
	 */
	public Point absolute() {
		Point p = cloneOf();
		p.x = Math.abs(p.x);
		p.y = Math.abs(p.y);
		return p;
	}

	/**
	 * Calculates and returns this point after a rotation around the origin by a
	 * certain angle.
	 * 
	 * @param angle
	 *            The angle in radian and counterclockwise.
	 * @return The point after rotation.
	 */
	public Point rotate(double angle) {
		return new Point(x * Math.cos(angle) - y * Math.sin(angle), x
				* Math.sin(angle) + y * Math.cos(angle));
	}

	/**
	 * Calculates and returns this point after a rotation around a given point
	 * by a certain angle.
	 * 
	 * @param center
	 *            The point around which to do the rotation.
	 * @param angle
	 *            The angle in radian and counterclockwise.
	 * @return The point after rotation.
	 */
	public Point rotate(Point center, double angle) {
		Point point = new Point(this);
		point = point.sub(center);
		point = point.rotate(angle);
		point = point.add(center);
		return point;
	}

	public List<Point> closest(List<Point> points, int limit) {
		Collections.sort(points, createComparator());
		return points.size() <= limit ? points : points.subList(0, limit);
	}

	private Comparator<Point> createComparator() {
		return new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				return (int) (distance(p1) - distance(p2));
			}
		};
	}
}
