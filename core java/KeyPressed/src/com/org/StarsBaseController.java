package com.org;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StarsBaseController {

	public static void main(String args[]) {
		 KeyPressed key=new KeyPressed();
		 key.keyPressed(null);

	}

	public static void keyPressed(KeyEvent evt) {
		System.out.println("in side ");

		int KeyCode = evt.getKeyCode();
		if (KeyCode == KeyEvent.VK_F5) {
			System.out.println("You clicked on F5");
		}
		if (evt.getKeyChar() == 'a') {
			System.out.println("Check for key characters: " + evt.getKeyChar());
		}
		if (evt.getKeyCode() == KeyEvent.VK_HOME) {
			System.out.println("Check for key codes: " + evt.getKeyCode());
		}
	}

}
