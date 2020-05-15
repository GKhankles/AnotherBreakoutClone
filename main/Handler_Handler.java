package main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler_Handler {

	LinkedList<Entity_GameObject> object = new LinkedList<Entity_GameObject>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			Entity_GameObject tempObject = object.get(i);
			
			tempObject.update();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			Entity_GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(Entity_GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(Entity_GameObject object) {
		this.object.remove(object);
	}
	
}
