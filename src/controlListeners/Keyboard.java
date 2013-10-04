package controlListeners;

import impl.Paddle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Could also implement KeyListener

public class Keyboard extends KeyAdapter {

	Paddle p1;
	
	public Keyboard(Paddle p1){
		this.p1 =p1;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			p1.updateY(5);
			break;
		case KeyEvent.VK_UP:
			p1.updateY(-5);
					break;
		default:
			break;
		}
	}
}