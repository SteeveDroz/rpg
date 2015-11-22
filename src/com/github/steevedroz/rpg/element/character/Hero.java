package com.github.steevedroz.rpg.element.character;

public class Hero extends Character {
	private static final String HERO_FOLDER = "heroes/";

	public Hero(String name) {
		super(name);
	}

	@Override
	public String getFolder() {
		return getFolder(HERO_FOLDER);
	}

}
