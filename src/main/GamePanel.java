package main;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import entity.Player;
import tile.TileManager;

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
    public final int tileSize = originalTileSize * scale; // 48x48 tile 
    // The amount of total tiles show on the screen 16x12 WidthxHeight
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    // Can be started and stopped
    // When started it keeps the program keeps running until you stop it
    Thread gameThread;

    // From Player class
    Player player = new Player(this, keyH);

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
        // This will automatically call the run() method
        gameThread.start();
    }

    // When an object implementing interface Runnable is used to create a thread
    // starting the thread causes the object's [run] method to be called in that 
    // Separately executing thread
    @Override
    public void run() {
         // Draws the screen every x amount of seconds based on FPS
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        // These two variables checks the FPS
        long timer = 0;
        int drawCount = 0;

        // GAME LOOP
        
        // As long as the gameThread exists it will continue to run the loop
        while (gameThread != null) {

            // Get the current time
            currentTime = System.nanoTime();
            
            // First expression signifies how much time has passed
            delta += (currentTime - lastTime) / drawInterval;
            
            // FPS Counter
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // 1 UPDATE: update information such as character position
                update();
                // 2  DRAW:  draw the screen with the updated information
                repaint();
                // Delta resets count
                delta--;
                drawCount++;
            }

            // FPS Counter
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
        }
    }

    public void update() {
        player.update();
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
        
        // Will draw the tiles using GamePanel
        // Must be before player.draw()
         tileM.draw(g2);

        player.draw(g2);

        // Dispose of this graphics context and release any system resources it is using
        g2.dispose();
    }
    
}
