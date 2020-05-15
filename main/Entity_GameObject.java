package main;

import java.awt.Graphics;

public abstract class Entity_GameObject {

	protected int x;
	protected int y;
	protected Handler_ID id;
	protected int velX;
	protected int velY;
	protected Handler_Handler handler;
	
	public Entity_GameObject(int x, int y, Handler_ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public Entity_GameObject(int x, int y, Handler_ID id, Handler_Handler handler) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
	}

	public abstract void update();
	public abstract void render(Graphics g);
	
	//Getter and setter methods
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setID(Handler_ID id) {
		this.id = id;
	}
	
	public Handler_ID getID() {
		return id;
	}
	
	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
}

