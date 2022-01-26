package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// The listener interface for receiving keyboard events(keystrokes)
public class KeyHandler implements KeyListener{

    @Override
    public void keyTyped(KeyEvent e) {}

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        // Returns the integer keyCode associated with the key in this event
        int code = e.getKeyCode();

        // If user presses the 'x' key ? Turns to true
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        // If user releases the 'x' key ? Turns to false
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        
    }
    
}
