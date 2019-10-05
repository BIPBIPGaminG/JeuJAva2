package fr.lejeu.graphics.gui;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import fr.lejeu.game.TitleGame;
import fr.lejeu.main.Component;
import fr.lejeu.main.entities.particles.Particle;

public class Title {
	
	TitleGame level;
	Particle particle;
	
	public static float xScroll;

	public static float yScroll;
	
	public Title(){
		level = new TitleGame();
	}
	
	public void init(){
		level.init();
	}
	
	public void translateView(float xa, float ya){
	}
	
	float xa = 0, ya = 0;
	
	public void update(){
		level.update();
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
