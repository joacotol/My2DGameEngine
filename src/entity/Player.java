package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.KeyHandler;
import main.GamePanel;

public class Player extends Entity {
	// Able to use GamePanel and KeyHandler class in the Player class
	GamePanel gp;
	KeyHandler keyH;

	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {
		// Player's default values
		x = 100;
		y = 100;
		speed = 4;
		// Any direction
		direction = "idle";
	}

	public void getPlayerImage() {
		try {
			idle = ImageIO.read(getClass().getResourceAsStream("/player/idle.png"));
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boyUp1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boyUp2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boyDown1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boyDown2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boyLeft1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boyLeft2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boyRight1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boyRight2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

			// This makes the player character move
			if (keyH.upPressed == true) {
				direction = "up";
				y -= speed;
			} else if (keyH.downPressed == true) {
				direction = "down";
				y += speed;
			} else if (keyH.leftPressed == true) {
				direction = "left";
				x -= speed;
			} else if (keyH.rightPressed == true) {
				direction = "right";
				x += speed;
			}

			// Sprite image changes every 12 frames
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNumber == 1) {
					spriteNumber = 2;
				} else if(spriteNumber == 2) {
					spriteNumber = 1;
				} else if(spriteNumber == 3) {
					spriteNumber = 3;
				}
				spriteCounter = 0;
			}

		}
		
		


	}

	public void draw(Graphics2D g2) {
		// g2.setColor(Color.white);
		// // Draws the player in the panel
		// g2.fillRect(x, y, gp.tileSize, gp.tileSize);

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if(spriteNumber == 1) {
				image = up1;
			}
			if(spriteNumber == 2) {
				image = up2;
			}

			break;
		case "down":
			if(spriteNumber == 1) {
				image = down1;
			}
			if(spriteNumber == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNumber == 1) {
				image = left1;
			}
			if(spriteNumber == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNumber == 1) {
				image = right1;
			}
			if(spriteNumber == 2) {
				image = right2;
			}
			break;
		case "idle":
			image = idle;
			break;


		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}

}
