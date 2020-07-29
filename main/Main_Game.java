package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Main_Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -5352046454165616372L;

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	private Thread thread;
	private boolean running = false;
	private Handler_Handler handler;

	public static int points;
	public static int ballSpawner = 10;
	public boolean newBall;

	protected static int gameState; // 0 = start menu, 1 = game, 2 = restart screen
	public static boolean buildGame;

	public Main_Game() {
		handler = new Handler_Handler();
		this.addKeyListener(new Input_KeyInput(handler));
		this.addMouseListener(new Input_MouseInput(handler));

		new Main_Window(WIDTH, HEIGHT, "Sample Game", this);
		
		buildGame = false;

		gameState = 0;

		handler.addObject(new Entity_Menu(590, 360, Handler_ID.Menu, handler));

		handler.addObject(new Entity_Player(640, 600, Handler_ID.Player));
		handler.addObject(new Entity_UI(50, 10, Handler_ID.UI, handler));

		// block builder
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 5; j++) {
				handler.addObject(new Entity_Block(50 + 90 * i, 40 + 25 * j, Handler_ID.Block));
			}
		}

		newBall = true;
	}

	public void newGame() {
		handler.object.clear();

		handler.addObject(new Entity_Menu(590, 360, Handler_ID.Menu, handler));

		handler.addObject(new Entity_Player(640, 600, Handler_ID.Player));
		handler.addObject(new Entity_UI(50, 10, Handler_ID.UI, handler));

		// block builder
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 5; j++) {
				handler.addObject(new Entity_Block(50 + 90 * i, 40 + 25 * j, Handler_ID.Block));
			}
		}

		newBall = true;
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				delta--;
			}

			if (running)
				render();

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		if (gameState == 0 && buildGame) {
			newGame();
			buildGame = false;
		} else if (gameState == 1) {
			if (Main_Game.points % Main_Game.ballSpawner == 0 && newBall == true) {
				handler.addObject(new Entity_Ball(640, 200, Handler_ID.Ball, handler));
				newBall = false;
			}

			if (Main_Game.points % Main_Game.ballSpawner != 0) {
				newBall = true;
			}
		}

		handler.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Main_Game();
	}

	public Handler_Handler getHandler() {
		return handler;
	}

}
