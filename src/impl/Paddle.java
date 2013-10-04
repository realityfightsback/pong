package impl;

import java.awt.Rectangle;
import java.awt.Toolkit;

public class Paddle {
	Rectangle rectangle;
	int x, y, height, width = 5;

	int ySpeed;

	int screenHeight = 0;
	int screenWidth = 0;

	public Paddle(int screenWidth, int screenHeight) {
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}

	public void resetP1PaddleLocation() {
		x = (screenWidth / 100) * 5;
		y = screenHeight / 3;
		height = screenHeight / 4;
		updateRectangle();
	}

	public void resetP2PaddleLocation() {
		x = (screenWidth / 100) * 95;
		y = 0;
		height = screenHeight;
		updateRectangle();
	}

	public void updateRectangle() {
		rectangle = new Rectangle(x, y, width, height);
	}

	public void updatePaddle() {

		y += ySpeed;
	}

	public void updateY(int incrementAmt) {
		y += incrementAmt;
	}

}
