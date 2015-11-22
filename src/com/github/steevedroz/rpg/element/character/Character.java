package com.github.steevedroz.rpg.element.character;

import com.github.steevedroz.rpg.element.Element;

public abstract class Character extends Element {
	private static final String CHARACTER_FOLDER = "characters/";
	protected String name;

	public Character(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected String getFolder(String subFolder) {
		StringBuilder str = new StringBuilder();
		str.append(CHARACTER_FOLDER).append(subFolder).append(name).append("/");
		return str.toString();
	}

	public abstract String getFolder();
}
