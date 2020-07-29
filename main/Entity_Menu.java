package main;

import java.awt.Color;
import java.awt.Graphics;

public class Entity_Menu extends Entity_GameObject {

	public Entity_Menu(int x, int y, Handler_ID id, Handler_Handler handler) {
		super(x, y, id, handler);

	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		if (Main_Game.gameState == 0) {
			g.setColor(Color.WHITE);
			g.drawRect(x, y, 100, 50);
			g.drawString("Play Game", x + 20, y + 30);
		}
		
		if (Main_Game.gameState == 2) {
			g.setColor(Color.WHITE);
			g.drawRect(x, y + 100, 100, 50);
			g.drawString("Restart", x + 20, y + 130);
			//
		}
	}
}