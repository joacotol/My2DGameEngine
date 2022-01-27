package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	int mapTileNumber[][];
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		// Will create 10 different kinds of tiles
		tile = new Tile[10];
		
		// Stores all numbers from map.text files
		mapTileNumber = new int[gp.maxScreenColumn][gp.maxScreenRow];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String filePath) {
		
		try {
			// Import text file
			InputStream is = getClass().getResourceAsStream(filePath);
			// Reads the contents of the text file
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int column = 0;
			int row = 0;
			
			while(column < gp.maxScreenColumn && row < gp.maxScreenRow) {
				// Reads line of text from the text file
				String line = br.readLine();
				
				while(column < gp.maxScreenColumn) {
					// Splits this string around matches of the given regular expression
					// Will get all numbers in the file one by one
					String numbers[] = line.split(" ");
					
					// Use column as an index for the number[] array
					// Converts string numbers to integers
					int number = Integer.parseInt(numbers[column]);
					
					// Continue this until everything in the numbers[] is stored in mapTileNumber[][]
					mapTileNumber[column][row] = number;
					column++;
				}
				if(column == gp.maxScreenColumn) {
					column = 0;
					row++;
				}
			}
			br.close();
			
		} catch(Exception e) {
			
		}
	}
 	
	public void draw(Graphics2D g2) {
		
		int column = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(column < gp.maxScreenColumn && row < gp.maxScreenRow) {
			// The map data  has been stored in the mapTileNumber[][] array
			int tileNumber = mapTileNumber[column][row];
			
			g2.drawImage(tile[tileNumber].image, x, y, gp.tileSize, gp.tileSize, null);
			column++;
			x += gp.tileSize;
			
			if(column == gp.maxScreenColumn) {
				column = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
			
		}
	}
}
