package fr.lejeu.graphics;

import java.util.ArrayList;
import java.util.List;

import fr.lejeu.game.level.Level;
import fr.lejeu.main.entities.Entity;
import fr.lejeu.main.entities.EntityType;
import fr.lejeu.main.entities.particles.Particle;

import static org.lwjgl.opengl.GL11.*;

public class ParticleSystem extends Entity{

	private List<Particle> particles = new ArrayList<Particle>();
	
	public ParticleSystem(int x, int y, int number, Particle particle){
		super(x, y, EntityType.PARTICLE);
		
		for (int i = 0; i < number; i++) {
			particles.add(new Particle(particle, x, y));
		}
		
		if(particle.texture != null) this.texture = particle.texture;
		
	}

	@Override
	public void init(Level level) {
		this.level = level;
	}

	@Override
	public void update() {
		
		for (int i = 0; i < particles.size(); i++) {
			Particle particle = particles.get(i);
			particle.update();
			if(particle.remove) particles.remove(particle);
		}
		
	}

	@Override
	public void render() {
		if(texture != null) texture.bind();
		glBegin(GL_QUADS);
		for (Particle particle : particles) {
			particle.render();
		}
		glEnd();
	}
	
}
