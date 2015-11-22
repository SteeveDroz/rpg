package com.github.steevedroz.utils;

public class Circle {
	private Point center;
	private double radius;

	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, Point onCircle) {
		this(center, center.distance(onCircle));
	}

	public Circle(Point p1, Point p2, Point p3) {
		Line line1 = new Line(p2.average(p3), p2).perpendicular();
		Line line2 = new Line(p1.average(p3), p1).perpendicular();
		this.center = line1.intersect(line2);
		this.radius = center.distance(p1);
	}

	public Circle(Triangle triangle) {
		this(triangle.getP1(), triangle.getP2(), triangle.getP3());
	}

	public boolean isInCircle(Point point) {
		return center.distance(point) < radius;
	}

	public Point getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
}
