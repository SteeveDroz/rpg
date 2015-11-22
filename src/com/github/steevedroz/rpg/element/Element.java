package com.github.steevedroz.rpg.element;

import com.github.steevedroz.rpg.util.Location;

import javafx.scene.image.Image;

public abstract class Element {
	protected Image image;
	protected Location location;

	public Element() {
	}

	public Image getImage() {
		return image;
	}

	public Location getLocation() {
		return location;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
