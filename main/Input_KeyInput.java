package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input_KeyInput extends KeyAdapter implements KeyListener{
	
	private Handler_Handler handler;
	
	private boolean pU;
	private boolean pL;
	private boolean pD;
	private boolean pR;
	
	public Input_KeyInput(Handler_Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			Entity_GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == Handler_ID.Player) {
				
				if(key == KeyEvent.VK_W) { 
					tempObject.setVelY(-10);
					pU = true;
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setVelX(-10);
					pL = true;
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setVelY(10);
					pD = true;
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setVelX(10);
					pR = true;
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			Entity_GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == Handler_ID.Player) {
				if(key == KeyEvent.VK_W) {
					pU = false;
					if(pD == false) {
						tempObject.setVelY(0);
					}
				}
				if(key == KeyEvent.VK_A) {
					pL = false;
					if(pR == false) {
						tempObject.setVelX(0);
					}
				}
				if(key == KeyEvent.VK_S) {
					pD = false;
					if(pU == false) {
						tempObject.setVelY(0);
					}
				}
				if(key == KeyEvent.VK_D) {
					pR = false;
					if(pL == false) {
						tempObject.setVelX(0);
					}
				}
				if( key == KeyEvent.VK_N) {
					handler.addObject(new Entity_Ball(640, 200, Handler_ID.Ball, handler));
				}
			}
		}
	}	

}
