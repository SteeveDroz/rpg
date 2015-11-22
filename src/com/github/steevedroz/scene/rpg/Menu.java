package com.github.steevedroz.scene.rpg;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.steevedroz.rpg.Main;
import com.github.steevedroz.rpg.voronoi.biome.Biome;
import com.github.steevedroz.rpg.voronoi.biome.Environment;
import com.github.steevedroz.utils.Point;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Menu extends RpgScene {
	public static final Point SIZE = new Point(800, 600);

	public Menu(Main parent) {
		super(parent, SIZE);
		Environment.define(Environment.NATURAL);
		createComponents();
	}

	private void createComponents() {
		GridPane grid = new GridPane();
		super.setRoot(grid);

		int col = 0;
		int row = 0;

		Label title = new Label("RPG");
		grid.add(title, col = 0, row++, 2, 1);

		Label nameLabel = new Label("Nom");
		grid.add(nameLabel, col++, row);

		TextField name = new TextField();
		grid.add(name, col, row++);
		col = 0;

		HashMap<Biome, Slider> biomes = new HashMap<Biome, Slider>();

		for (Biome biome : Biome.values()) {
			if (!biome.isChangable()) {
				continue;
			}

			Slider biomeSlider = new Slider(0, 100, biome.getChances());
			grid.add(new Label(biome.getName()), col++, row);

			biomes.put(biome, biomeSlider);
			grid.add(biomeSlider, col, row++);
			col = 0;
		}
		HBox presets = new HBox();
		for (Environment environment : Environment.values()) {
			Button environmentButton = new Button(environment.getName());
			environmentButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					for (Map.Entry<Biome, Integer> biome : environment
							.getBiomes().entrySet()) {
						biomes.get(biome.getKey()).setValue(biome.getValue());
					}
				}
			});
			presets.getChildren().add(environmentButton);
		}
		grid.add(presets, col, row++, 99, 1);
		col = 0;

		Button start = new Button();
		start.setText("Start");
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				for (Entry<Biome, Slider> biome : biomes.entrySet()) {
					biome.getKey()
							.setChances((int) biome.getValue().getValue());
				}
				int seed = 0;
				try {
					seed = Integer.parseInt(name.getText());
				} catch (NumberFormatException e) {
					seed = name.getText().hashCode();
					if (seed == 0) {
						seed = new Random().nextInt();
					}
				} finally {
					((Game) parent.setState(State.GAME)).setSeed(seed);
				}
			}
		});
		grid.add(start, col, row++);
		col = 0;
	}
}
