package com.github.steevedroz.rpg.eventhandler;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyEventHandler implements EventHandler<KeyEvent> {

	@Override
	public void handle(KeyEvent event) {
		KeyType type = KeyType.create(event.getCode());
		type.action();
	}
}
