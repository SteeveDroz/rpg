package com.github.steevedroz.utils;

/**
 * The <code>Line</code> class is a basic representation of a 2D line. It is
 * defined by two points.
 * 
 * @author Steeve Droz
 *
 */
public class Line {
	/**
	 * The first point on the line.
	 */
	private Point p1;
	/**
	 * The second point on the line.
	 */
	private Point p2;

	/**
	 * Default constructor. Should not be used verbatim because both points are
	 * located on the origin.
	 */
	public Line() {
		p1 = new Point();
		p2 = new Point();
	}

	/**
	 * Constructs a line with two points.
	 * 
	 * @param p1
	 *            The first point on the line.
	 * @param p2
	 *            The second point on the line.
	 */
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	/**
	 * This constructor allows to create a line with a point and a vector.
	 * 
	 * @param p1
	 *            A point on the line.
	 * @param deltaX
	 *            The x coordinate of the vector.
	 * @param deltaY
	 *            The y coordinate of the vector.
	 */
	public Line(Point p1, double deltaX, double deltaY) {
		this(p1, p1.add(new Point(deltaX, deltaY)));
	}

	/**
	 * This constructor can be used to create a line with a point and an angle.
	 * 
	 * @param p1
	 *            A point on the line.
	 * @param angle
	 *            The angle of the line, can't be equal to ±infinity.
	 */
	public Line(Point p1, double angle) {
		this(p1, p1.add(new Point(Math.tan(angle), 1)));
	}

	/**
	 * Constructs a copy of a line.
	 * 
	 * @param line
	 *            The line to copy.
	 */
	public Line(Line line) {
		this(line.p1, line.p2);
	}

	/**
	 * Clones this line and returns it.
	 * 
	 * @return A copy of the line.
	 */
	public Line cloneOf() {
		Line line = new Line();
		line.p1 = p1.cloneOf();
		line.p2 = p2.cloneOf();
		return line;
	}

	/**
	 * This method calculates a perpendicular line to this line that crosses
	 * this line on <code>p1</code>.
	 * 
	 * @return A perpendicular line.
	 */
	public Line perpendicular() {
		Point p = p2.cloneOf();
		p.rotate(p1, Math.PI / 2);
		return new Line(p1, p);
	}

	/**
	 * Returns the <code>a</code> value of the line given in the form of
	 * <code>a * x + b = y</code>.
	 * 
	 * @return The angle of the line.
	 */
	public double getA() {
		double a = (p2.y - p1.y) / (p2.x - p1.x);
		return a;
	}

	/**
	 * Returns the <code>b</code> value of the line given in the form of
	 * <code>a * x + b = y</code>.
	 * 
	 * @return The y coordinate of the line when x = 0.
	 */
	public double getB() {
		double b = p1.y - (getA() * p1.x);
		return b;
	}

	/**
	 * Returns the first point of the line.
	 * 
	 * @return The first point of the line.
	 */
	public Point getP1() {
		return p1;
	}

	/**
	 * Returns the second point of the line.
	 * 
	 * @return The second point of the line.
	 */
	public Point getP2() {
		return p2;
	}

	/**
	 * Sets the first point of the line.
	 * 
	 * @param p1
	 *            The point to set.
	 */
	public void setP1(Point p1) {
		this.p1 = p1;
	}

	/**
	 * Sets the second point of the line.
	 * 
	 * @param p2
	 *            The point to set.
	 */
	public void setP2(Point p2) {
		this.p2 = p2;
	}

	/**
	 * Calculates the intersection of this line and another one.
	 * 
	 * @param line
	 *            The line that this intersects.
	 * @return The point where they meet.
	 */
	public Point intersect(Line line) {
		double x = (line.getB() - this.getB()) / (this.getA() - line.getA());
		double y = this.getA() * x + this.getB();
		return new Point(x, y);
	}
}
