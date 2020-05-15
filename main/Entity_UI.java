package main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Entity_UI extends Entity_GameObject {

	BufferedImage scorePlane;
	BufferedImage[] scoreNums;
	int scoreW[];
	int scoreH[];
	int num1;
	int num2;
	int timeToReplay;
	boolean winGame; // checks if the player has obtained 65 points

	public Entity_UI(int x, int y, Handler_ID id, Handler_Handler handler) {
		super(x, y, id, handler);

		buildScoreText();
		winGame = false;
	}

	/**
	 * Obtains the textures for the score element on the screen
	 */
	private void buildScoreText() {
		num1 = 1;
		num2 = 1;

		scoreNums = new BufferedImage[11];
		scoreW = new int[11];
		scoreH = new int[11];

		try {
			scorePlane = ImageIO.read(this.getClass().getResource("score.png"));

			scoreNums[0] = scorePlane.getSubimage(0, 0, 36, 9);
			scoreW[0] = scoreNums[0].getWidth(null) * 2;
			scoreH[0] = scoreNums[0].getHeight(null) * 2;

			for (int i = 1; i < 11; i++) {
				scoreNums[i] = scorePlane.getSubimage(30 + 6 * i, 0, 6, 9);
				scoreW[i] = scoreNums[i].getWidth(null) * 2;
				scoreH[i] = scoreNums[i].getHeight(null) * 2;

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		if (Main_Game.gameState == 1) {
			num1 = (int) (Math.floor(Main_Game.points / 10) + 1);
			num2 = (Main_Game.points % 10) + 1;

			if (Main_Game.points == 65) {
				winGame = true;
				timeToReplay++;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (Main_Game.gameState == 1) {
			g.drawImage(scoreNums[0], x, y, scoreW[0], scoreH[0], null);
			g.drawImage(scoreNums[num1], x + 72, y, scoreW[num1], scoreH[num1], null);
			g.drawImage(scoreNums[num2], x + 86, y, scoreW[num2], scoreH[num2], null);

			if (winGame) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
				g.drawString("You Win!", 640, 360);
				
				if(timeToReplay / 60 >= 5) {
					g.drawString("Play Again?", 640, 400);
				}
			}
		}
	}

}
