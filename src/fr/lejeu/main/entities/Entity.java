package fr.lejeu.main.entities;

import fr.lejeu.game.level.Level;
import fr.lejeu.graphics.Textures;
import fr.lejeu.maths.Vector2f;

public abstract class Entity {

	protected float x, y;
	protected EntityType entityType;
	protected boolean removed = false;
	protected Textures texture;
	protected Level level;
	
	protected Vector2f mousePointerPos;
	protected Vector2f mouseDirection;
	
	public Entity(int x, int y, EntityType entityType){
		this.x = x * 16;
		this.y = y * 16;
		this.entityType = entityType;
	}
	
	public abstract void init(Level level);
	public abstract void update();
	public abstract void render();
	
	public boolean isSolidTile(float xa, float ya){
		
		int x0 = (int) (x + xa + 2) / 16;
		int x1 = (int) (x + xa + 13) / 16;
		int y0 = (int) (y + ya + 0.5) / 16;
		int y1 = (int) (y + ya + 16) / 16;
		
		
		
		if(level.getSolidTile(x0, y0) != null) return true;
		if(level.getSolidTile(x1, y0) != null) return true;
		if(level.getSolidTile(x1, y1) != null) return true;
		if(level.getSolidTile(x0, y1) != null) return true;
		return false;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public boolean getRemoved(){
		return removed;
	}
	
	public EntityType getEntityType(){
		return entityType;
	}
	
	public Textures getTexture(){
		return texture;
	}
	
}
