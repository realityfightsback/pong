package impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;

import controlListeners.Keyboard;

@SuppressWarnings("serial")
public class GameBoard extends JFrame {
	private Image dbImage;
	private Graphics dbg;

	private int screenHeight;
	private int screenWidth;

	private Ball ball;
	private Paddle playerOnePaddle;
	private Paddle playerTwoPaddle;

	private ScoreBoard scoreBoard = new ScoreBoard();

	public GameBoard(int screenHeight, int screenWidth) {
		super();

		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;

		playerOnePaddle = new Paddle(screenWidth, screenHeight);
		playerTwoPaddle = new Paddle(screenWidth, screenHeight);

		ball = new Ball();

		addKeyListener(new Keyboard(playerOnePaddle));

		setSize(screenWidth, screenHeight);
		setBackground(Color.WHITE);
		setTitle("PongGame");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		initializeGame();
	}

	// Uses double buffering
	@Override
	public void paint(Graphics g) {

		// super.paint(g);

		dbImage = createImage(getWidth(), getHeight());
		dbg = dbImage.getGraphics();

		updateGameState();

		drawPaddle(playerOnePaddle);
		drawPaddle(playerTwoPaddle);

		dbg.setColor(Color.RED);
		drawCollisionBox(playerOnePaddle);
		drawCollisionBox(playerTwoPaddle);
		dbg.setColor(Color.BLACK);

		drawScore();

		drawBall();

		g.drawImage(dbImage, 0, 0, null);

	}

	private void drawCollisionBox(Paddle paddle) {
		dbg.fillRect(paddle.getCollisionBox().x, paddle.getCollisionBox().y,
				paddle.getCollisionBox().width, paddle.getCollisionBox().height);

	}

	public void updateGameState() {
		playerOnePaddle.updateCollisionBox();
		playerTwoPaddle.updateCollisionBox();
		ball.moveAlongPath();

		detectBallCollision();

		if (scoreBoard.startNewGame) {
			initializeGame();
		}

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
		dbg.fillRect(paddle.getCollisionBox().x, paddle.getCollisionBox().y,
				paddle.getCollisionBox().width, paddle.getCollisionBox().height);
	}

	private void drawBall() {
		dbg.fillOval(ball.x, ball.y, ball.size, ball.size);
	}

	private void initializeGame() {

		playerOnePaddle.resetP1PaddleLocation();
		playerTwoPaddle.resetP2PaddleLocation();
		
		ball.setStartLocation(playerOnePaddle);
		ball.setStartSpeed();

		scoreBoard.startNewGame = false;
	}

}
