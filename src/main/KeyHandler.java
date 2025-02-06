package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	
	public void switchKey(int code, boolean pressed) {
		switch (code) {
			case KeyEvent.VK_W:
				upPressed = pressed;
				break;
			case KeyEvent.VK_S:
				downPressed = pressed;
				break;	
			case KeyEvent.VK_A:
				leftPressed = pressed;
				break;	
			case KeyEvent.VK_D:
				rightPressed = pressed;
				break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {		}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getExtendedKeyCode();
		boolean pressed = true;
		
		switchKey(code, pressed);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {		
		int code = e.getExtendedKeyCode();
		boolean pressed = false;
		
		switchKey(code, pressed);
	}

}
