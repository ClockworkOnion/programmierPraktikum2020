package Platformer;
public class Vec2 {
		public float x;
		public float y;

		public Vec2(float x, float y) {
			this.x = x;
			this.y = y;
		}

		public Vec2 add(Vec2 b) {
			return new Vec2(x + b.x, y + b.y);
		}

		public Vec2 sub(Vec2 b) {
			return new Vec2(x - b.x, y - b.y);
		}

		public Vec2 mult(Vec2 b) {
		return new Vec2(x * b.x, y * b.y);
	}

		public Vec2 mult(float m) {
		return new Vec2(x * m, y * m);
	}

		public Vec2 div(Vec2 b) {
			return new Vec2(x / b.x, y / b.y);
		}

		public Vec2 inverse() {
			return new Vec2(-x, -y);
		}

		public String direction() {
			if (Math.abs(x) > Math.abs(y)) {
				return (x > 0 ? "right" : "left");
			} else {
				return (y > 0 ? "down" : "up");
			}


		}
	};