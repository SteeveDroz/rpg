package com.github.steevedroz.rpg.eventhandler;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;

public enum KeyType {
	Q {
		@Override
		public KeyCode getCode() {
			return KeyCode.Q;
		}

		@Override
		public void action() {
			Platform.exit();
		}
	},
	NO_KEY {
		@Override
		public KeyCode getCode() {
			return KeyCode.UNDEFINED;
		}

		@Override
		public void action() {
			// Do nothing.
		}
	};

	public static KeyType create(KeyCode keyCode) {
		for (KeyType type : values()) {
			if (type.getCode().equals(keyCode)) {
				return type;
			}
		}
		System.out.println(keyCode);
		return NO_KEY;
	}

	public abstract KeyCode getCode();

	public abstract void action();
}
