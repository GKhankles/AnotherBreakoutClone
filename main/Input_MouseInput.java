package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Input_MouseInput extends MouseAdapter {

	private Handler_Handler handler;

	public Input_MouseInput(Handler_Handler handler) {
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		int key = e.getButton();
	}

	public void mouseReleased(MouseEvent e) {

		for (int i = 0; i < handler.object.size(); i++) {
			Entity_GameObject tempObject = handler.object.get(i);

			if (Main_Game.gameState == 0) {

				System.out.println(e.getX());

				if (e.getX() >= 590 && e.getX() <= 690 && e.getY() >= 360 && e.getY() <= 410) {
					Main_Game.gameState = 1;
				}
			}

			if (Main_Game.gameState == 2) {

				System.out.println(e.getX());

				if (e.getX() >= 590 && e.getX() <= 690 && e.getY() >= 460 && e.getY() <= 510) {
					Main_Game.points = 0;
					Main_Game.buildGame = true;
					Main_Game.gameState = 0;
				}
			}
		}
	}
}
