package fr.lejeu.main;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import fr.lejeu.game.Game;
import fr.lejeu.graphics.gui.Title;

public class Component{
	  static boolean tick = false;
	public static boolean render = false;
	public static boolean running = false;
	public static String title = "LeJeu";
	public static int scale = 3;
	public static int width = 720 / scale;
	public static int height = 480 / scale;
	public static int time = 0;
	public screenTypes screenType;
	
	DisplayMode mode = new DisplayMode(width * scale, height * scale);
	
	Game game;
	
	Title titleScreen;
	
	public Component(){
		display();
		
		titleScreen = new Title();
		
		game = new Game();
	}
	
	public void start(){
		running = true;
		
		setScreenType(screenTypes.TITLE);
		loop();
	}

	public void stop(){
		running = false;
	}
	
	public void exit(){
		Display.destroy();
		System.exit(0);
	}
	
	public void loop(){
		
		if(getScreenType() == screenTypes.TITLE){
			titleScreen.init();
		}
		
		if(getScreenType() == screenTypes.GAME){
			game.init();
		}
		long timer = System.currentTimeMillis();
		
		long before = System.nanoTime();
		double elapsed = 0.0;
		double nanosecond = 1000000000.0 / 60.0;
		
		int ticks = 0;
		int frames = 0;
		
		while(running){
			if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				setScreenType(screenTypes.GAME);
			}
		if(Display.isCloseRequested()) stop();
			Display.update();
			width = Display.getWidth() / scale;
			height = Display.getHeight() / scale;
			
			tick = false;
			render = false;
			
			long now = System.nanoTime();
			elapsed = now - before; 
			
			if(elapsed > nanosecond){
				before  += nanosecond;
				tick = true;
				ticks++;
			}else{
				render = true;
				frames++;
			}
			
			if(tick) tick(); //UPDATE
			if(render) render();
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				Display.setTitle(title + " || ticks: " + ticks + ", fps: " + frames);
				ticks = 0;
				frames = 0;
			}
			
		}
		exit();
	}
	
	public void tick(){ //UPDATE
		
		time++;
		
		if(getScreenType() == screenTypes.TITLE){
			titleScreen.update();
		}
		
		if(getScreenType() == screenTypes.GAME){
			game.update();
		}
	}
	
	public void render(){
		view2D(width, height);	
		glClear(GL_COLOR_BUFFER_BIT);
		
		if(getScreenType() == screenTypes.TITLE){
			titleScreen.render();
		}
		
		if(getScreenType() == screenTypes.GAME){
			game.render();
		}
			
	}
	
	public static void main(String[] args){
		Component main = new Component();
		System.getenv("java.library.path");
		main.start();
	}
	
	
	public void display(){
		
		try {
			
			Display.setDisplayMode(mode);
			Display.setResizable(true);
			Display.setFullscreen(false);
			Display.setTitle(title  + " || ticks: 0, fps: 0");
			
			Display.create();
			
			view2D(width, height);
			
		} catch (LWJGLException e) {
			
			e.printStackTrace();
			
		}
	}
	
	private void view2D(int width, int height){
		glViewport(0, 0, width * scale, height * scale);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluOrtho2D(0, width, height, 0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
		
		glEnable(GL_TEXTURE_2D);
		
	}
	
	private void setScreenType(screenTypes type) {
		this.screenType = type;
	}
	
	private screenTypes getScreenType() {
		return screenType;
	}
}
