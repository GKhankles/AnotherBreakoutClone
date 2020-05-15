package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Entity_Ball extends Entity_GameObject {

	Random randVelocity; // random variable used to determine the velX of the ball after hitting the
							// player (paddle)
	boolean edgeBounceX; // determines whether the ball can "bounce" when it hits a hall
	boolean edgeBounceY; // determines whether the ball can "bounce" when it hits the ceiling
	boolean playerBounce; // determines whether the ball can "bounce" when it hits the player (paddle)
	double bounceOffset; // stores where the ball bounces off the player (paddle)
	int previousX; // stores the x position of the ball during the last update()
	int previousY; // stores the y position of the ball during the last update()
	int cornerCheckX; // x position of the block corner to check for collisions
	int cornerCheckY; // y position of the block corner to check for collisions
	double previousToCorner; // stores x / y for the distance between the previous point of the ball and the
								// corner of the block
	double previousToCollision; // stores x / y for the distance between the previous point of the ball and the
								// point of collision

	public Entity_Ball(int x, int y, Handler_ID id, Handler_Handler handler) {
		super(x, y, id, handler);

		edgeBounceX = true;
		edgeBounceY = true;
		playerBounce = true;
		randVelocity = new Random();
		
		velY = -5;
		velX = randVelocity.nextInt(15) - 7;
	}

	@Override
	public void update() {
		if (Main_Game.gameState == 1) {
			x += velX;
			y += velY;
			
			Collision();

			previousX = this.x;
			previousY = this.y;

			if (this.x < 1270 && this.x > 0 && this.y > 0) {
				edgeBounceX = true;
				edgeBounceY = true;
			}
		}
	}

	/**
	 * Handles collisions for the ball
	 */
	private void Collision() {

		// collisions with blocks and the player
		for (int i = 0; i < handler.object.size(); i++) {
			Entity_GameObject tempObject = handler.object.get(i);
			// @FIX THIS
			if (tempObject.id == Handler_ID.Block || tempObject.id == Handler_ID.Player) {
				if (tempObject.x >= this.x - 80 && tempObject.x <= this.x + 10) {
					if (tempObject.y >= this.y - 15 && tempObject.y <= this.y + 10) {
						if (tempObject.id == Handler_ID.Block) {
							Main_Game.points++;
							handler.removeObject(tempObject);
							playerBounce = true;

							if (tempObject.x >= previousX - 80 && tempObject.x <= previousX + 10) {
								velY *= -1;
							} else if (tempObject.y >= previousY - 15 && tempObject.y <= previousY + 10) {
								velX *= -1;
							} else {
								try {
									if (velX < 0 && velY < 0) {
										cornerCheckX = tempObject.getX() + 80;
										cornerCheckY = tempObject.getY();

										previousToCorner = (cornerCheckX - previousX) / (cornerCheckY - previousY);
										previousToCollision = velX / velY;
									}
									if (velX < 0 && velY > 0) {
										cornerCheckX = tempObject.getX() + 15;
										cornerCheckY = tempObject.getY() + 80;

										previousToCorner = (cornerCheckX - previousX) / (cornerCheckY - previousY - 10);
										previousToCollision = velX / velY;
									}
									if (velX > 0 && velY < 0) {
										cornerCheckX = tempObject.getX();
										cornerCheckY = tempObject.getY() + 15;

										previousToCorner = (cornerCheckX - previousX - 10) / (cornerCheckY - previousY);
										previousToCollision = velX / velY;
									}
									if (velX > 0 && velY > 0) {
										cornerCheckX = tempObject.getX();
										cornerCheckY = tempObject.getY();

										previousToCorner = (cornerCheckX - previousX - 10)
												/ (cornerCheckY - previousY - 10);
										previousToCollision = velX / velY;
									}

									if (previousToCorner < 0) {
										previousToCorner *= -1;
									}

									if (previousToCollision < 0) {
										previousToCollision *= -1;
									}

									if (previousToCollision > previousToCorner) {
										velX *= -1;
									} else if (previousToCollision < previousToCorner) {
										velY *= -1;
									} else {
										velX *= -1;
										velY *= -1;
									}

								} catch (Exception e) {
									velX *= -1;
									velY *= -1;
								}
							}
						} else {
							if (playerBounce) {
								do {
									velX = randVelocity.nextInt(15) - 7;
								} while (velX == 0);

								velY *= -1;
								playerBounce = false;
							}
						}
					}
				}
			}
		}

		// collisions with walls
		if (this.y <= 0 && edgeBounceY) {
			this.velY *= -1;
			this.y = 1;
			edgeBounceY = false;
			edgeBounceX = true;
			playerBounce = true;
		}

		if (this.x >= 1270 && edgeBounceX) {
			this.velX *= -1;
			this.cornerCheckX = 1269;
			edgeBounceX = false;
			edgeBounceY = true;
			playerBounce = true;
		}

		if (this.x <= 0 && edgeBounceX) {
			this.velX *= -1;
			this.x = 1;
			edgeBounceX = false;
			edgeBounceY = true;
			playerBounce = true;
		}

		if (y > 720) {
			handler.removeObject(this);
		}

		if (x > 1280) {
			x = 1260;
			velX = -5;
			velY = -5;
		}

		if (x < -10) {
			x = 10;
			velX = 5;
			velY = -5;
		}

		if (y < -10) {
			y = 10;
			velY = -5;
		}
	}

	@Override
	public void render(Graphics g) {
		if (Main_Game.gameState == 1) {
			g.setColor(Color.white);
			g.fillRect(x, y, 10, 10);
		}
	}
}
