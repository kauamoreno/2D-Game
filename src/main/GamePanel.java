package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	// SCREEN SETTINGS
	public final int originalTileSize = 16; //16x16
	public final int scale = 3;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	
	final int FPS = 60; 
	
	public final int tileSize = originalTileSize * scale; //48x48
	public final int screenwidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	Player player = new Player(this, keyH);
	TileManager tileManager = new TileManager(this);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenwidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //Improve game's rendering - Search about
		this.addKeyListener(keyH);
		this.setFocusable(true); //Allows receiving key input
	}
	
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	long timer = 0;
	int drawCount = 0;
	
	@Override
	public void run() {
		while(gameThread != null) {
			
			//Set FPS
			double drawInterval = 1000000000/FPS; //1second in nanoseconds divide per FPS=60
			double lastTime = System.nanoTime();
			double nextDrawTime = lastTime + drawInterval;
			
			//Update information 
			update();
			
			//Ask to redraw the screen with the new positions
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000; //nanosecond for millisecond
				
				if (remainingTime < 0) {remainingTime = 0;}
				
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
				
				drawCount++;
				timer += System.nanoTime() - lastTime;
				if(timer >= 1000000000) {
					System.out.println("FPS: " + drawCount);
					drawCount = 0;
		            timer = 0;
				}	
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void update() {
		
		player.update();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileManager.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
	
}
