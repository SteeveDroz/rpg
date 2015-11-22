package com.github.steevedroz.scene.rpg;

import com.github.steevedroz.rpg.Main;
import com.github.steevedroz.utils.Point;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class RpgScene extends Scene {
	protected Main parent;

	public RpgScene(Main parent, Point size) {
		super(new StackPane(), size.x, size.y);
		this.parent = parent;
	}

	public Main getParent() {
		return parent;
	}

	public ObservableList<Node> getChildren() {
		return ((Pane) super.getRoot()).getChildren();
	}

	public boolean addChild(Node node) {
		return getChildren().add(node);
	}
}
