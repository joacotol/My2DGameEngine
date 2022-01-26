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
        // PLayer's default values
        x = 100;
        y = 100;
        speed = 4;
        // Any direction
        direction = "down";
    }

    public void getPlayerImage() {
        try {
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
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.white);
        // // Draws the player in the panel
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

}
