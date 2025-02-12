package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	GamePanel gp; 
	KeyHandler keyH;
	
	//Player position in the screen
	public final int screenX;
	public final int screenY;
	
	Map<String, BufferedImage> spriteMap = new HashMap<>();
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenwidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		setDefaultValues();
		getPlayerImages();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 24;
		
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImages() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
			spriteMap.put("up1", up1);
		    spriteMap.put("up2", up2);
		    spriteMap.put("down1", down1);
		    spriteMap.put("down2", down2);
		    spriteMap.put("left1", left1);
		    spriteMap.put("left2", left2);
		    spriteMap.put("right1", right1);
		    spriteMap.put("right2", right2);
						
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true) {
			if (keyH.upPressed) {
				direction = "up";
				worldY -= speed;
			} else if (keyH.downPressed) {
				direction = "down";
				worldY += speed;
			} else if (keyH.leftPressed) {
				direction = "left";
				worldX -= speed;
			} else if (keyH.rightPressed) {
				direction = "right";
				worldX += speed;
			}
			
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNumber == 1) {
					spriteNumber = 2;
				} else if (spriteNumber == 2) {
					spriteNumber = 1;
				}
				
				spriteCounter = 0;
			}
		}
	
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		String spriteKey = direction + spriteNumber;
		image = spriteMap.get(spriteKey);
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}
}
