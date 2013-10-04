package impl;

public class ScoreBoard {

	int playerOneScore, playerTwoScore;

	boolean startNewGame = true;

	public void playerScored(int xBallCoord) {
		if (xBallCoord <= 15)
			playerTwoScore++;
		else
			playerOneScore++;
		startNewGame = true;
	}

}
