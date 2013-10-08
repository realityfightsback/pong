package impl;

import java.awt.Toolkit;

public class Pong implements Runnable {

	private GameBoard gameBoard;

	// 1-100 recommended. Lower is faster
	public static final int gameSpeed = 3;
	
	public static final int paddleSpeed = 10;

	// Can be customized or left as is for fullscreen
	int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

	//% of the screen width the ball should be
//	int relativeBallSize = 1;
	public Pong() {
		super();
		gameBoard = new GameBoard(screenHeight, screenWidth);
	}

	@Override
	public void run() {
		
		while (true) {
			try {
				this.gameBoard.repaint();
				this.gameBoard.updateGameState();
				Thread.sleep(gameSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Thread t = new Thread(new Pong());
		t.start();
	}

}
