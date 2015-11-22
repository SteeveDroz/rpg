package com.github.steevedroz.rpg.voronoi.biome;

import com.github.steevedroz.utils.Point;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public enum Structure {
	START, ROAD, INN;

	public static Structure createStructure(Point point) {
		if (point.distance(Point.ORIGIN) < 4) {
			return START;
		}
		// TODO Handle structures
		return null;
	}

	public Node getNode(int width, Biome biome) {
		Rectangle node = new Rectangle(width, width);
		node.setFill(Color.DARKGRAY);
		return node;
	}
}
