package com.github.steevedroz.rpg.voronoi.biome;

import java.util.HashMap;
import java.util.Map;

public enum Environment {
	NATURAL {
		@Override
		public Map<Biome, Integer> getBiomes() {
			HashMap<Biome, Integer> biomes = new HashMap<Biome, Integer>();

			biomes.put(Biome.GRASS, 100);
			biomes.put(Biome.MOUNTAIN, 25);
			biomes.put(Biome.WATER, 35);
			biomes.put(Biome.FOREST, 80);
			biomes.put(Biome.DESERT, 11);

			return biomes;
		}

		@Override
		public String getName() {
			return "Naturel";
		}
	},
	VERDANT {
		@Override
		public Map<Biome, Integer> getBiomes() {
			HashMap<Biome, Integer> biomes = new HashMap<Biome, Integer>();

			biomes.put(Biome.GRASS, 100);
			biomes.put(Biome.MOUNTAIN, 3);
			biomes.put(Biome.WATER, 65);
			biomes.put(Biome.FOREST, 95);
			biomes.put(Biome.DESERT, 0);

			return biomes;
		}

		@Override
		public String getName() {
			return "Verdoyant";
		}
	},
	SCORCHED_EARTH {
		@Override
		public Map<Biome, Integer> getBiomes() {
			HashMap<Biome, Integer> biomes = new HashMap<Biome, Integer>();

			biomes.put(Biome.GRASS, 0);
			biomes.put(Biome.MOUNTAIN, 70);
			biomes.put(Biome.WATER, 2);
			biomes.put(Biome.FOREST, 0);
			biomes.put(Biome.DESERT, 100);

			return biomes;
		}

		@Override
		public String getName() {
			return "Terre brûlée";
		}
	},
	ISLANDS {
		@Override
		public Map<Biome, Integer> getBiomes() {
			HashMap<Biome, Integer> biomes = new HashMap<Biome, Integer>();

			biomes.put(Biome.GRASS, 15);
			biomes.put(Biome.MOUNTAIN, 2);
			biomes.put(Biome.WATER, 100);
			biomes.put(Biome.FOREST, 1);
			biomes.put(Biome.DESERT, 1);

			return biomes;
		}

		@Override
		public String getName() {
			return "Iles";
		}
	};

	public static void define(Environment environment) {
		for (Map.Entry<Biome, Integer> entry : environment.getBiomes()
				.entrySet()) {
			entry.getKey().setChances(entry.getValue());
		}
	}

	public abstract Map<Biome, Integer> getBiomes();

	public abstract String getName();
}
