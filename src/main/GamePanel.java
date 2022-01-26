package main;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

// extends basically makes GamePanel a subclass to JPanel
public class GamePanel extends JPanel implements Runnable{
    // The game screen

    // SCREEN SETTINGS

    // Default size for player characters, npcs, mob tiles for the game
    // 16 pixels is retro style
    // 16 is too small therefore it will need to be scaled
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    // Actual tile size that will be displayed on screen
    final int tileSize = originalTileSize * scale; // 48x48 tile 
    // The amount of total tiles show on the screen 16x12 WidthxHeight
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    KeyHandler keyH = new KeyHandler();
    // Can be started and stopped
    // When started it keeps the program keeps running until you stop it
    Thread gameThread;

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        
        // Sets the size of this class(JPanel) 
        // using the screenWidth and screenHeight
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        // Sets the background color to black
        this.setBackground(Color.black);
        // If set to true, all the drawing from this component will be done in an offscreen
        // painting buffer
        // Enabling this can improve the game's rendering performance
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        // This focuses the GamePanel to receive key input
        this.setFocusable(true);

    }

    public void startGameThread() {

        // this passes the GamePanel class to the Thread() constructor
        gameThread = new Thread(this);
        // This will automaticall call the run() method
        gameThread.start();
    }

    // When an object implementing interface Runnable is used to create a thread
    // starting the thread causes the object's [run] method to be called in that 
    // seperately executing thread
    @Override
    public void run() {

        // GAME LOOP
        
        // As long as the gameThread exists it will continue to run the loop
        while(gameThread != null) {

            //System.out.println("The game loop is running."); 

            // 1 UPDATE: update information such as character position
            update();

            // 2  DRAW:  draw the screen with the updated information
            repaint();

        }
        
        
    }

    public void update() {

        // This makes the player character move
        if(keyH.upPressed == true) {

        }

    }

    // This method is called by using repaint()
    // Graphics is a class that has many functions to draw objects on the screen
    public void paintComponent(Graphics g) {

        // Format
        // super - the parent class of this class (JPanel)
        super.paintComponent(g);

        // Graphics2D class extends the Graphics class to provide  more sophisticated
        // control over geometry, coordinate transformations, color management, and 
        // text layout
        // This changes the Graphics [g] and convert it to Graphics2D in variable [g2]
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        // Draws the player in the panel
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        // Dispose of this graphics context and release any system resources it is using
        g2.dispose();
    }
    
}
