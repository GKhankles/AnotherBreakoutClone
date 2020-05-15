package main;

import java.awt.Color;
import java.awt.Graphics;

public class Entity_Block extends Entity_GameObject {

	public Entity_Block(int x, int y, Handler_ID id) {
		super(x, y, id);
	}

	@Override
	public void update() {

	}

	@Override
	public void render(Graphics g) {
		if (Main_Game.gameState == 1) {
			g.setColor(Color.white);
			g.fillRect(x, y, 80, 15);
		}
	}

}
