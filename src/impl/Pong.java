package impl;

public class Pong implements Runnable {

	private GameBoard gameBoard;

	public Pong(){
		super();
		gameBoard = new GameBoard();
	}

	@Override
	public void run() {
		while (true) {
			try {
				this.gameBoard.repaint();
				Thread.sleep(3);
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
