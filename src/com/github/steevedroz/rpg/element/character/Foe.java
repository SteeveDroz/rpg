package com.github.steevedroz.rpg.element.character;

public class Foe extends Character {
	private static final String FOE_FOLDER = "foes/";

	public Foe(String name) {
		super(name);
	}

	@Override
	public String getFolder() {
		return getFolder(FOE_FOLDER);
	}

}
