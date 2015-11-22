package com.github.steevedroz.rpg.voronoi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.steevedroz.utils.Point;

public class Chunk {
	public static int SIZE = 16;

	private int x;
	private int y;
	private int seed;

	Chunk() {
	}

	public Chunk(int x, int y, int seed) {
		this.x = x;
		this.y = y;
		this.seed = seed;
	}

	public static Chunk createChunk(Point point, int seed) {
		int x = (int) point.x / SIZE;
		if (point.x < 0) {
			x--;
		}
		int y = (int) point.y / SIZE;
		if (point.y < 0) {
			y--;
		}
		return new Chunk(x, y, seed);
	}

	public Chunk cloneOf() {
		Chunk chunk = new Chunk();
		chunk.x = x;
		chunk.y = y;
		chunk.seed = seed;
		return chunk;
	}

	public List<VoronoiPoint> getPoints() {
		List<VoronoiPoint> points = new ArrayList<VoronoiPoint>();
		Random rand = new Random((x + "_" + y + "_" + seed).hashCode());
		for (int i = 0; i < rand.nextInt(3); i++) {
			points.add(new VoronoiPoint((x + rand.nextDouble()) * SIZE,
					(y + rand.nextDouble()) * SIZE, seed));
		}
		return points;
	}

	public List<VoronoiPoint> getSurroundingPoints() {
		List<VoronoiPoint> points = getPoints();
		for (Direction direction : Direction.values()) {
			points.addAll(getSurroundingChunk(direction).getPoints());
		}
		return points;
	}

	public Chunk getSurroundingChunk(Direction direction) {
		Chunk chunk = this.cloneOf();
		chunk.x += direction.getPoint().x;
		chunk.y += direction.getPoint().y;
		return chunk;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSeed() {
		return seed;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}
}
