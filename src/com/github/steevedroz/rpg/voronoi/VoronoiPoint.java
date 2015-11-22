package com.github.steevedroz.rpg.voronoi;

import java.util.Random;

import com.github.steevedroz.rpg.voronoi.biome.Biome;
import com.github.steevedroz.utils.Point;

public class VoronoiPoint extends Point {
	protected Biome biome;

	public VoronoiPoint(double x, double y, Biome biome) {
		super(x, y);
		this.biome = biome;
	}

	public VoronoiPoint(double x, double y) {
		this(x, y, new Random().nextInt());
	}

	public VoronoiPoint(double x, double y, int seed) {
		this(x, y,
				randomBiome(new Random((x + "_" + y + "_" + seed).hashCode())));
	}

	private static Biome randomBiome(Random rand) {
		return Biome.getBiome(rand.nextDouble());
	}

	public Biome getBiome() {
		return biome;
	}

	public void setBiome(Biome biome) {
		this.biome = biome;
	}
}
