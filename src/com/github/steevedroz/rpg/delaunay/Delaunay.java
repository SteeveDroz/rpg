package com.github.steevedroz.rpg.delaunay;

import java.util.ArrayList;
import java.util.List;

import com.github.steevedroz.utils.Line;
import com.github.steevedroz.utils.Point;
import com.github.steevedroz.utils.Triangle;

//TODO All Javascript
public class Delaunay {
	private List<Point> points;
	private List<Triangle> triangles;

	public Delaunay() {
		this.points = new ArrayList<Point>();
		this.triangles = new ArrayList<Triangle>();
	}

	public List<Point> getPoints() {
		return points;
	}

	public List<Triangle> getTriangles() {
		return triangles;
	}

	public void addPoint(Point point) {
		if (points.size() == 2) {
			triangles.add(new Triangle(point, points.get(0), points.get(1)));
		}
		List<Triangle> conflictTriangles = new ArrayList<Triangle>();
		List<Line> surroundingLines = new ArrayList<Line>();
		for (Triangle triangle : triangles) {
			if (triangle.getCircumcircle().isInCircle(point)) {
				conflictTriangles.add(triangle);
			}
		}
		for (Triangle triangle : conflictTriangles) {
			for (Triangle adjacent : triangle.getAdjacents(triangles)) {
				if (!conflictTriangles.contains(adjacent)) {
					if (triangle.isPoint(adjacent.getP1())) {
						surroundingLines.add(new Line(adjacent.getP2(),
								adjacent.getP3()));
					} else if (triangle.isPoint(adjacent.getP2())) {
						surroundingLines.add(new Line(adjacent.getP1(),
								adjacent.getP3()));
					} else if (triangle.isPoint(adjacent.getP3())) {
						surroundingLines.add(new Line(adjacent.getP1(),
								adjacent.getP2()));
					}
				}
			}
			triangles.remove(triangle);
		}
		for (Line line : surroundingLines) {
			triangles.add(new Triangle(line.getP1(), line.getP2(), point));
		}
		if (conflictTriangles.size() == 0) {
			List<Point> closest = point.closest(points, 2);
			if (closest.size() == 2) {
				Triangle triangle = new Triangle(point, closest.get(0),
						closest.get(1));
				triangles.add(triangle);
			}
		}
		points.add(point);
	}

	public void addPoint(double x, double y) {
		addPoint(new Point(x, y));
	}

	public Point removePoint(Point point) {
		return points.remove(point) ? point : null;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
}
