package entity;
import java.awt.image.BufferedImage;

// This stores variables that will be used in player, monster, and NPC classes
public class Entity {

    public int x, y;
    public int speed;

    // Describes an image with an accessible buffer of image data
    // Use this to store image files
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, idle;
    public String direction;
    
    // Sprite movement
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    
    
}
