package impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

import controlListeners.Keyboard;

public class GameBoard extends JFrame {
	private Image dbImage;
	private Graphics dbg;

	int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

	Ball ball = new Ball();
	Paddle playerOnePaddle;
	Paddle playerTwoPaddle;

	ScoreBoard scoreBoard = new ScoreBoard();

	public GameBoard() throws HeadlessException {
		super();

		playerOnePaddle = new Paddle(screenWidth, screenHeight);
		playerTwoPaddle = new Paddle(screenWidth, screenHeight);

		addKeyListener(new Keyboard(playerOnePaddle));

		setSize(screenWidth, screenHeight);
		setBackground(Color.WHITE);
		setTitle("PongGame");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	// Uses double buffering
	@Override
	public void paint(Graphics g) {

		// super.paint(g);

		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();

		if (scoreBoard.startNewGame) {
			ball.setStartLocation(playerOnePaddle);
			ball.setStartSpeed();

			playerOnePaddle.resetP1PaddleLocation();
			playerTwoPaddle.resetP2PaddleLocation();
			
			scoreBoard.startNewGame=false;
		}

		playerOnePaddle.updatePaddle();
		playerTwoPaddle.updatePaddle();
		
		drawPaddle(playerOnePaddle);
		drawPaddle(playerTwoPaddle);

		drawScore();

		drawBall();

		g.drawImage(dbImage, 0, 0, null);

		ball.moveAlongPath();

		detectBallCollision();

	}

	private void drawScore() {
		dbg.drawString(scoreBoard.playerOneScore + "    "
				+ scoreBoard.playerTwoScore, screenWidth / 2, screenHeight / 10);
	}

	private void detectBallCollision() {
		if (ball.hitTopOrBottom(screenHeight))
			ball.reverseYSpeed();

		if (ball.hitPaddle(playerOnePaddle, playerTwoPaddle))
			ball.reverseXSpeed();

		if (ball.hitLeftOrRightEdge(screenWidth))
			scoreBoard.playerScored(ball.getX());
	}

	private void drawPaddle(Paddle paddle) {
		dbg.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
	}

	private void drawBall() {
		dbg.fillOval(ball.x, ball.y, ball.size, ball.size);
	}

}
