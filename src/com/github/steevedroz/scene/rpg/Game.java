package com.github.steevedroz.scene.rpg;

import java.util.ArrayList;
import java.util.List;

import com.github.steevedroz.rpg.Main;
import com.github.steevedroz.rpg.dummy.SpriteAnimation;
import com.github.steevedroz.rpg.element.character.Foe;
import com.github.steevedroz.rpg.element.character.Hero;
import com.github.steevedroz.rpg.eventhandler.KeyEventHandler;
import com.github.steevedroz.rpg.voronoi.Voronoi;
import com.github.steevedroz.rpg.voronoi.biome.Biome;
import com.github.steevedroz.rpg.voronoi.biome.Structure;
import com.github.steevedroz.utils.Point;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Game extends RpgScene {
	private static final int SEED = 0;
	private static final Point SIZE = new Point(1280, 600);
	private static final double NOISE = 0.1;
	private static final int WIDTH = 64;

	private int seed = SEED;
	private Point size = SIZE;
	private double noise = NOISE;
	private int width = WIDTH;

	private Voronoi voronoi = new Voronoi();
	private AnimationTimer timer;

	private List<Hero> heroes;
	private List<Foe> foes;

	private Point center;

	public Game(Main parent) {
		super(parent, SIZE);
		createComponents();
		setOnKeyPressed(new KeyEventHandler());
		this.heroes = new ArrayList<Hero>();
		this.foes = new ArrayList<Foe>();
		this.center = Point.ORIGIN;

		// TODO Delete this dummy code.
		{
			Image img = new Image("resources/walk-right.png");
			ImageView imageView = new ImageView(img);
			imageView.setViewport(new Rectangle2D(0, 0, 102, 148));
			final Animation animation = new SpriteAnimation(imageView,
					Duration.millis(1000), 6, 6, 0, 0, 102, 148);
			animation.setCycleCount(Animation.INDEFINITE);
			animation.play();
			// addChild(imageView);
		}

		timer = new AnimationTimer() {
			private Point oldCenter;
			private Point oldSize;

			@Override
			public void handle(long now) {
				if (hasMoved() || isResized()) {
					oldCenter = center;
					size = new Point(getWidth(), getHeight());
					oldSize = size;
					draw();
				}
			}

			private boolean hasMoved() {
				return !center.equals(oldCenter);
			}

			private boolean isResized() {
				return getWidth() != oldSize.x || getHeight() != oldSize.y;
			}
		};
		timer.start();
	}

	public void draw() {
		getChildren().clear();
		Point tiles = size.div(width);
		for (int x = (int) (center.x - tiles.x / 2 - 1); x < center.x + tiles.x
				/ 2 + 1; x++) {
			for (int y = (int) (center.y - tiles.y / 2 - 1); y < center.y
					+ tiles.y / 2 + 1; y++) {
				Point point = new Point(x, y);
				Biome biome = voronoi.getBiome(point, seed, noise);
				biome.setStructure(Structure.createStructure(point));
				Node node = biome.getNode(width);
				node.relocate((x + tiles.x / 2) * width, (y + tiles.y / 2)
						* width);
				addChild(node);
			}
		}
	}

	public String getTitle() {
		return "seed: " + seed;
	}

	public int getSeed() {
		return seed;
	}

	public double getNoise() {
		return noise;
	}

	public List<Hero> getHeroes() {
		return heroes;
	}

	public List<Foe> getFoes() {
		return foes;
	}

	public Point getSize() {
		return size;
	}

	public Point getCenter() {
		return center;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public void setNoise(double noise) {
		this.noise = noise;
	}

	public void setSeed(String seed) {
		this.seed = seed.hashCode();
	}

	public void setSize(Point size) {
		this.size = size;
	}

	private void createComponents() {
		super.setRoot(new Pane());
	}
}
