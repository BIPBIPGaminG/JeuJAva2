package fr.lejeu.game.level;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import fr.lejeu.game.level.tiles.Tile;
import fr.lejeu.game.level.tiles.Tiles;
import fr.lejeu.main.entities.Entity;
import fr.lejeu.main.entities.EntityType;
import fr.lejeu.main.entities.Player;

public class Level {
	
	public int width, height;
	
	public static int x = 5;

	public static int y = 5;
	
	public static List<Tile> tiles = new ArrayList<Tile>();
	public static Tile[][] solidTiles;
	public static Tile[][] bgTiles;
	public static Tile[][] allTiles;

	public static Player player = new Player(x, y, EntityType.PLAYER);

	private int[] bounds = new int[4];  
	
	List<Entity> entities = new ArrayList<Entity>();
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		solidTiles = new Tile[width][height];
		bgTiles = new Tile[width][height];
		allTiles = new Tile[width][height];
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				if(Math.random() > 0.05F){
					allTiles[x][y] = new Tile(x, y, Tiles.BG_GRASS);
					bgTiles[x][y] = new Tile(x, y, Tiles.BG_GRASS);
				}else{
					allTiles[x][y] = new Tile(x, y, Tiles.SOLID_ROCK);
					solidTiles[x][y] = new Tile(x, y, Tiles.SOLID_ROCK);
				}
			}
		}
		
		setTile();
		loadLevel();
		spawner();
	}
	
	public void spawner(){

		if(getSolidTile(Level.x, Level.y) == null){
			addEntity(player);
			System.out.println("World check ...");
			System.out.println("World created !");
		}
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
				
				if(x - 1 < 0 || y - 1 < 0 || x + 1 >= width || y + 1 >= height) continue;
				boolean vu = false, vd = false, vl = false, vr = false;
				boolean vur = false, vdr = false, vdl = false, vul = false;
				if(solidTiles[x + 1][y] == null){
					vr = true;
				}
				if(solidTiles[x - 1][y] == null){
					vl = true;
				}
				if(solidTiles[x][y + 1] == null){
					vd = true;
				}
				if(solidTiles[x][y - 1] == null){
					vu = true;
				}
				//-------------------------------------
				if(solidTiles[x + 1][y + 1] == null){
					vdr = true;
				}
				if(solidTiles[x - 1][y - 1] == null){
					vul = true;
				}
				if(solidTiles[x - 1][y + 1] == null){
					vdl = true;
				}
				if(solidTiles[x + 1][y - 1] == null){
					vur = true;
				}
				if(solidTiles[x][y] != null){
					
					solidTiles[x][y].setTiles(vu, vd, vl, vr, vur, vdr, vdl, vul);
					
				}
				addTiles(x, y);
			}
		}
	}
	
	public Tile getSolidTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height) return null;
		return solidTiles[x][y];
	}
	
	public Tile getBackgroundTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height) return null;
		return bgTiles[x][y];
	}
	
	public void addTiles(int x, int y){
		if(solidTiles[x][y] != null){
			tiles.add(solidTiles[x][y]);
			
		}else if(bgTiles[x][y] != null){
			tiles.add(bgTiles[x][y]);
					
		}
	}
	
	public void init(){
		
	}
	
	public void addEntity(Entity entity){
		player.init(this);
		entities.add(entity);
	}
	
	public void removeEntity(Entity entity){
		entities.remove(entity);
	}
	
	public void update(){
		
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if(entity.getRemoved()) entities.remove(entity);
			entity.update();
		}
		
	}
	
	public void render(){
		
		for(Tile tile : tiles){
			tile.render();
		}
		
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if(!(entity instanceof Player)) entity.render();
		}
		if(entities.size() >= 0){
			entities.get(0).render();
		}
	}
	
	public int getBounds(int index){
		return bounds[index];
	}

	
	public static Player getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}
	
}
