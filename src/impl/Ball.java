package impl;

import java.awt.geom.Dimension2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ball {

	public int size = 15;

	int x, y, xSpeed, ySpeed;

	Ellipse2D circle = new Ellipse2D.Double(x, y, size, size);

	Point2D loc = new Point2D() {
		double x;
		double y;

		@Override
		public void setLocation(double x, double y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public double getY() {
			return y;
		}

		@Override
		public double getX() {
			return x;
		}
	};

	Dimension2D dim = new Dimension2D() {
		double width, height;

		@Override
		public void setSize(double width, double height) {
			this.width = width;
			this.height = height;
		}

		@Override
		public double getWidth() {
			return width;
		}

		@Override
		public double getHeight() {
			return height;
		}
	};

	/**
	 * Sets ball on playerOnePaddle
	 */
	public Ball() {
		super();
	}

	public void setStartLocation(Paddle p1) {
		this.setX(p1.getX() + p1.getWidth());
		this.setY(p1.getY() + p1.getHeight() - (this.size / 2));

		updateCircle();
	}

	public void setStartSpeed() {
		xSpeed = ySpeed = 1;
	}

	public void moveAlongPath() {
		x += xSpeed;
		y += ySpeed;

		updateCircle();
	}

	private void updateCircle() {
		loc.setLocation(x, y);
		dim.setSize(size, size);
		circle.setFrame(loc, dim);
	}

	public void reverseYSpeed() {
		ySpeed = -ySpeed;
	}

	public void reverseXSpeed() {
		xSpeed = -xSpeed;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean hitTopOrBottom(int screenHeight) {
		return ((this.y + this.size) >= screenHeight)
				|| ((this.y - this.size) <= 0);
	}

	public boolean hitLeftOrRightEdge(int screenWidth) {
		return ((x + size) >= screenWidth) || ((x - size) <= 0);
	}

	/**
	 * 
	 * @param playerOnePaddle
	 * @param playerTwoPaddle
	 * @return
	 * 
	 * 
	 * 
	 *         Paddle needs to be at the point 0123456 0 * 1 *** 2 | * 3 | 4 | 5
	 * 
	 */

	public boolean hitPaddle(Paddle playerOnePaddle, Paddle playerTwoPaddle) {

		return (circle.intersects(playerOnePaddle.getCollisionBox()) || circle
				.intersects(playerTwoPaddle.getCollisionBox()));
	}
}
