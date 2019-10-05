package fr.lejeu.main.entities;


import org.lwjgl.input.Keyboard;

import fr.lejeu.game.Game;
import fr.lejeu.game.level.Level;
import fr.lejeu.graphics.Color;
import fr.lejeu.graphics.ParticleSystem;
import fr.lejeu.graphics.Renderer;
import fr.lejeu.graphics.Textures;
import fr.lejeu.graphics.utils.Animations;
import fr.lejeu.main.entities.particles.Particle;
import fr.lejeu.maths.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class Player extends Entity{

	Animations animation;
	int direction = 0;
	int speed = 1;
	
	public Player(int x, int y, EntityType entityType) {
		super(x, y, entityType);
		texture = Textures.PlayerSprite;
		animation = new Animations(3, 10, true);
		
		mousePointerPos = new Vector2f(8, 8);
		mouseDirection = new Vector2f();
	}
	
	float xa, ya;
	
	public void update() {
		animation.update();
		animation.pause();
		if (Keyboard.isKeyDown(Keyboard.KEY_Z) || Keyboard.isKeyDown(Keyboard.KEY_W)) {
			ya = -speed;
			Particle particle = new Particle();
			particle.setColor(Color.WHITE);
			particle.setTexure(Textures.GrassParticle);
			particle.setDirection(new Vector2f(0, 2));
			particle.setSize(1);
			particle.setSpeed(0.01f);
			particle.setLifeTime(25);
			level.addEntity(new ParticleSystem((int) x + 8, (int) y + 16, 1, particle));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			ya = speed;
			Particle particle = new Particle();
			particle.setColor(Color.WHITE);
			particle.setTexure(Textures.GrassParticle);
			particle.setDirection(new Vector2f(0, 2));
			particle.setSize(1);
			particle.setSpeed(0.01f);
			particle.setLifeTime(25);
			level.addEntity(new ParticleSystem((int) x + 8, (int) y + 16, 1, particle));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q) || Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xa = -speed;
			direction = 0;
			animation.play();
			Particle particle = new Particle();
			particle.setColor(Color.WHITE);
			particle.setTexure(Textures.GrassParticle);
			particle.setDirection(new Vector2f(0, 2));
			particle.setSize(1);
			particle.setSpeed(0.01f);
			particle.setLifeTime(25);
			level.addEntity(new ParticleSystem((int) x + 10, (int) y + 15, 1, particle));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xa = speed;
			direction = 1;
			animation.play();
			Particle particle = new Particle();
			particle.setColor(Color.WHITE);
			particle.setTexure(Textures.GrassParticle);
			particle.setDirection(new Vector2f(0, 2));
			particle.setSize(1);
			particle.setSpeed(0.01f);
			particle.setLifeTime(25);
			level.addEntity(new ParticleSystem((int) x + 10, (int) y + 15, 1, particle));
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D) == false && Keyboard.isKeyDown(Keyboard.KEY_Z) == false && Keyboard.isKeyDown(Keyboard.KEY_Q) == false && Keyboard.isKeyDown(Keyboard.KEY_S) == false) {
			animation.stop();
			direction = -1;
		}
		
		mouseDirection.set(Game.getMouseX() - (x + mousePointerPos.x), Game.getMouseY() - (y + mousePointerPos.y)).normalize();
		
		int xStep = (int) Math.abs(xa * 100);
		for (int i = 0; i < xStep; i++) {
			if (!isSolidTile(xa / xStep, 0)) {
				x += xa / xStep;
			} else {
				xa = 0;
			}
		}
		
		int yStep = (int) Math.abs(ya * 100);
		for (int i = 0; i < yStep; i++) {
			if (!isSolidTile(0, ya / yStep)) {
				y += ya / yStep;
			} else {
				ya = 0;
			}
		}
		
		xa = 0;
		ya = 0;
	}

	@Override
	public void render() {
		texture.bind();
		Renderer.renderEntity(x, y, 16, 16, Color.TRANSPARENT, 8.0F, 1 + direction, animation.getCurrentframe());
		texture.unBind();
		
		glBegin(GL_LINES);
			glVertex2f(x + mousePointerPos.x, ya + mousePointerPos.y);
			glVertex2f(x + mousePointerPos.x + mouseDirection.x * 16, ya + mouseDirection.y * 16);
		glEnd();
	}

	@Override
	public void init(Level level) {
		this.level = level;
	}
	
}
