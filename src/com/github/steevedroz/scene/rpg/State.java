package com.github.steevedroz.scene.rpg;

import com.github.steevedroz.rpg.Main;

public enum State {
	SPLASH {
		@Override
		public RpgScene getScene(Main parent) {
			return new Splash(parent);
		}
	},
	MENU {
		@Override
		public RpgScene getScene(Main parent) {
			return new Menu(parent);
		}
	},
	GAME {
		@Override
		public RpgScene getScene(Main parent) {
			return new Game(parent);
		}
	};

	public abstract RpgScene getScene(Main parent);
}
