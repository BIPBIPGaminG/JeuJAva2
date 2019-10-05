package fr.lejeu.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import fr.lejeu.game.level.Level;
import fr.lejeu.main.Component;
import fr.lejeu.main.entities.particles.Particle;

public class Game {
	Level level;
	Particle particle;
	
	public static float xScroll;

	public static float yScroll;
	
	public Game(){
		level = new Level(250, 250);
		xScroll = level.getBounds(0);
		yScroll = level.getBounds(1);
	}
	
	public void init(){
		level.init();
	}
	
	public void translateView(float xa, float ya){
		xScroll = xa;
		yScroll = ya;
		if(xScroll > level.getBounds(0)) xScroll = level.getBounds(0);
		if(xScroll < level.getBounds(2)) xScroll = level.getBounds(2);
		if(yScroll > level.getBounds(1)) yScroll = level.getBounds(1);
		if(yScroll < level.getBounds(3)) yScroll = level.getBounds(3);
		
	}
	
	float xa = 0, ya = 0;
	
	public void update(){
		level.update();
		
		xa = -Level.getPlayer().getX() + Component.width / 2 - 8;
		ya = -Level.getPlayer().getY() + Component.height / 2 - 8;
		
		translateView(xa, ya);
		
		
	}
	
	public void render(){
		glTranslatef(xScroll, yScroll, 0);
		level.render();
	}
	
	public static float getMouseX(){
		return Mouse.getX() / Component.scale - xScroll;
	}
	
	public static float getMouseY(){
		return Display.getHeight() - Mouse.getY() / Component.scale - yScroll;
	}
	
}
