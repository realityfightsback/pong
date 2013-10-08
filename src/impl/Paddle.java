package impl;

import java.awt.Rectangle;

public class Paddle {
	private Rectangle collisionBox;
	private int width = 5;
	private int y;

	private int screenHeight = 0;
	private int screenWidth = 0;

	public Paddle(int screenWidth, int screenHeight) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}

	/**
	 * Located on the left.
	 */

	public void resetP1PaddleLocation() {
		int x = (screenWidth / 100) * 5;
		y = screenHeight / 3;
		int height = screenHeight / 4;
		collisionBox = new Rectangle(x, y, width, height);
	}

	/**
	 * Located on the right side. Currently just a solid wall running the length
	 * of the screen.
	 */
	public void resetP2PaddleLocation() {
		int x = (screenWidth / 100) * 95;
		y = 0;
		int height = screenHeight;
		collisionBox = new Rectangle(x, y, width, height);
	}

	public void updateY(int incrementAmt) {
		y += incrementAmt;
	}

	/**
	 * Only the y location ever changes
	 * 
	 */
	public void updateCollisionBox() {
		getCollisionBox().setBounds(getCollisionBox().x, y, getCollisionBox().width,
				getCollisionBox().height);
	}

	public int getX() {
		return getCollisionBox().x;
	}

	public int getY() {
		return getCollisionBox().y;
	}

	public int getHeight() {
		return getCollisionBox().height;
	}

	public int getWidth() {
		return getCollisionBox().width;
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

}
