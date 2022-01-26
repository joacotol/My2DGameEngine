package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        // Creates a window using JFrame
        JFrame window = new JFrame();
        // This lets the window properly close when user clicks the close ('x') button
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // false = cannot resize the window
        window.setResizable(false);
        // This creates the title for the window
        window.setTitle("2D Adventure");

        // Add GamePanel class to Main class
        GamePanel gamePanel = new GamePanel();
        // Object gamePanel is added to the window
        window.add(gamePanel);

        // Causes this window to be sized to fit the preferred size and layouts
        // of its subcomponents (=GamePanel)
        window.pack();

        // Does not specify the location of the window
        // The window will be displayed at the center of the screen
        window.setLocationRelativeTo(null);
        // true = able to see the window 
        window.setVisible(true);

        gamePanel.startGameThread();


    }
}