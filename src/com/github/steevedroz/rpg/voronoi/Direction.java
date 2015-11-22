package com.github.steevedroz.rpg.voronoi;

import com.github.steevedroz.utils.Point;

public enum Direction {
	TOP_LEFT {
		@Override
		public Point getPoint() {
			return new Point(-1, -1);
		}
	},
	TOP {
		@Override
		public Point getPoint() {
			return new Point(0, -1);
		}
	},
	TOP_RIGHT {
		@Override
		public Point getPoint() {
			return new Point(1, -1);
		}
	},
	LEFT {
		@Override
		public Point getPoint() {
			return new Point(-1, 0);
		}
	},
	RIGHT {
		@Override
		public Point getPoint() {
			return new Point(1, 0);
		}
	},
	BOTTOM_LEFT {
		@Override
		public Point getPoint() {
			return new Point(-1, 1);
		}
	},
	BOTTOM {
		@Override
		public Point getPoint() {
			return new Point(0, 1);
		}
	},
	BOTTOM_RIGHT {
		@Override
		public Point getPoint() {
			return new Point(1, 1);
		}
	};

	public abstract Point getPoint();
}
