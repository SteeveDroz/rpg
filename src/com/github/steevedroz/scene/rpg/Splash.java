package com.github.steevedroz.scene.rpg;

import com.github.steevedroz.rpg.Main;
import com.github.steevedroz.utils.Point;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Splash extends RpgScene {
	private static final Point SIZE = new Point(800, 600);
	private static final long SPLASH_TIME_IN_SECONDS = 1;

	public Splash(Main parent) {
		super(parent, SIZE);
		createComponents();
		new Thread() {
			public void run() {
				try {
					Thread.sleep(1000 * SPLASH_TIME_IN_SECONDS);
				} catch (InterruptedException e) {
				} finally {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							parent.setState(com.github.steevedroz.scene.rpg.State.MENU);
						}
					});
				}
				;
			}
		}.start();
	}

	private void createComponents() {
		Label label = new Label("SPLASH SCREEN");
		addChild(label);
	}
}
