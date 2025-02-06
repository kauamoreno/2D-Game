package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	
	int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[36];
		mapTileNum = new int [gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImage() {
		
		try {
			int x = 0;
			
			while(x < tile.length) {
				String path = String.format("/tiles/%03d.png", x); //Format x to have 3 decimals of extension
				tile[x] = new Tile();
				tile[x].image = ImageIO.read(getClass().getResourceAsStream(path));
				x++;
			}

			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void loadMap(String mapFilePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(mapFilePath); //Calls the file
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); //Read the file
			
			int col = 0;
			int row = 0;
			
			while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				String line = br.readLine();
				
				while (col < gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					
					//Charging the numbers into
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y +=gp.tileSize;
			}
		}	
	}
	
}
