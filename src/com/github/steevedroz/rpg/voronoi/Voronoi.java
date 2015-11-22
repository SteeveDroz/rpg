package com.github.steevedroz.rpg.voronoi;

import java.util.List;
import java.util.Random;

import com.github.steevedroz.rpg.voronoi.biome.Biome;
import com.github.steevedroz.utils.Point;

public class Voronoi {

	public Voronoi() {
	}

	public Biome getBiome(Point point, int seed, double noise) {
		Chunk chunk = Chunk.createChunk(point, seed);
		List<VoronoiPoint> surroundingPoints = chunk.getSurroundingPoints();

		// To cast a List<Child extends Parent> into a List<Parent>, one needs
		// to use:
		// List<Parent> parentList = (List<Parent>) (List<?>) childList;
		// And to cast it the other way:
		// List<Child> childList = (List<Child>) (List<?>) parentList;
		// The (List<?>) cast requires the SuppressWarning.
		@SuppressWarnings("unchecked")
		List<VoronoiPoint> closestPoints = (List<VoronoiPoint>) (List<?>) (point
				.closest((List<Point>) (List<?>) surroundingPoints, 2));

		Random rand = new Random(
				(point.x + "_" + point.y + "_" + seed).hashCode());
		if (closestPoints.size() > 1) {
			return rand.nextDouble() < noise ? closestPoints.get(1).getBiome()
					: closestPoints.get(0).getBiome();
		}
		if (closestPoints.size() > 0) {
			return closestPoints.get(0).getBiome();
		}
		System.out.println("No surroundings!");
		return Biome.getMainBiome();
	}
}
