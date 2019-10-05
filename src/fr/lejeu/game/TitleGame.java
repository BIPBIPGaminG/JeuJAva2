package fr.lejeu.game;

import fr.lejeu.game.level.tiles.TitleTile;
import fr.lejeu.main.entities.particles.Particle;

public class TitleGame {
	TitleTile level;
	Particle particle;
	
	public static float xScroll;

	public static float yScroll;
	
	public TitleGame(){
		level = new TitleTile(20, 20);
	}
	
	public void init(){
		level.init();
	}
	
	public void update(){
		level.update();		
	}
	
	public void render(){
		level.render();
	}
	
}
