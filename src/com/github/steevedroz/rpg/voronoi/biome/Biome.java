package com.github.steevedroz.rpg.voronoi.biome;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public enum Biome {
	GRASS {
		@Override
		public Color getColor() {
			return Color.GREEN;
		}

		@Override
		public String getName() {
			return "Herbe";
		}
	},
	MOUNTAIN {
		@Override
		public Color getColor() {
			return Color.GRAY;
		}

		@Override
		public String getName() {
			return "Montagne";
		}
	},
	WATER {
		@Override
		public Color getColor() {
			return Color.BLUE;
		}

		@Override
		public String getName() {
			return "Eau";
		}
	},
	FOREST {
		@Override
		public Color getColor() {
			return Color.BROWN;
		}

		@Override
		public String getName() {
			return "Forêt";
		}
	},
	DESERT {
		@Override
		public Color getColor() {
			return Color.YELLOW;
		}

		@Override
		public String getName() {
			return "Désert";
		}
	},
	VOID {

		@Override
		public Color getColor() {
			return Color.BLACK;
		}

		@Override
		public int getChances() {
			return 0;
		}

		@Override
		public boolean isChangable() {
			return false;
		}

		@Override
		public String getName() {
			return "Vide";
		}
	};

	private int chances = 100;
	private Structure structure = null;

	public static Biome getBiome(double value) {
		double tmp = 0.0;
		for (Biome biome : values()) {
			tmp += biome.getProbability();
			if (tmp >= value) {
				return biome;
			}
		}
		return null;
	}

	public abstract Color getColor();

	public int getChances() {
		return this.chances;
	}

	public Structure getStructure() {
		return structure;
	}

	public double getProbability() {
		int totalChances = 0;
		for (Biome biome : values()) {
			totalChances += biome.getChances();
		}
		return (double) getChances() / totalChances;
	}

	public Node getNode(int width) {
		Group group = new Group();

		Rectangle node = new Rectangle(width, width);
		node.setFill(getColor());
		group.getChildren().add(node);

		if (getStructure() != null) {
			Node structure = getStructure().getNode(width, this);
			group.getChildren().add(structure);
		}

		return group;
	}

	public void setChances(int chances) {
		this.chances = chances;
	}

	public static Biome getMainBiome() {
		Biome main = null;
		for (Biome biome : values()) {
			if (main == null || biome.getChances() > main.getChances()) {
				main = biome;
			}
		}
		return main;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public boolean isChangable() {
		return true;
	}

	public abstract String getName();
}
