package com.github.steevedroz.rpg;

import com.github.steevedroz.scene.rpg.RpgScene;
import com.github.steevedroz.scene.rpg.State;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage stage;
	private long frameRate;

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		stage.setTitle("RPG");
		setState(State.SPLASH);
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public long getFrameRate() {
		return frameRate;
	}

	public RpgScene setState(State state) {
		RpgScene scene = state.getScene(this);
		stage.setScene(scene);
		stage.centerOnScreen();
		return scene;
	}
}
