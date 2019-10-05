package fr.lejeu.game.level.tiles;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import fr.lejeu.game.level.tiles.Tile;
import fr.lejeu.game.level.tiles.Tiles;

public class TitleTile {
	
	public int width, height;
	
	public static int x = 5;

	public static int y = 5;
	
	public static List<Tile> tiles = new ArrayList<Tile>();
	public static Tile[][] allTiles;

	private int[] bounds = new int[4];  
	
	public TitleTile(int width, int height){
		this.width = width;
		this.height = height;
		allTiles = new Tile[width][height];
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
					allTiles[x][y] = new Tile(x, y, Tiles.SOLID_ROCK);
			}
		}
		
		setTile();
		loadLevel();
	}
	
	public void loadLevel(){
		bounds[0] = -16;
		bounds[1] = -16;
		bounds[2] = -width * 16 + 18 + Display.getWidth();
		bounds[3] = -height * 16 + 18 + Display.getHeight();
	}
	
	public void setTile(){
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				
				addTiles(x, y);
			}
		}
	}
	
	public void init(){
		
	}
	
	public void addTiles(int x, int y){
			tiles.add(allTiles[x][y]);
	}
	
	public void update(){
		
	}
	
	public void render(){
		
		for(Tile tile : tiles){
			tile.render();
		}
		
	}
	
}
