package com.org;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyPressed implements KeyListener{

		
	
	
	public void keyTyped(KeyEvent e)
	{
		int KeyCode=e.getKeyCode(); 
		
		if(KeyCode==KeyEvent.VK_F5)
		{
			System.out.println("You pressed F5");
		
		}
	


}
	
	@Override
	public void keyPressed(KeyEvent paramKeyEvent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent paramKeyEvent) {
		// TODO Auto-generated method stub
		
	}
	
}
