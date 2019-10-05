package fr.lejeu.main.entities.particles;

import java.util.Random;

import fr.lejeu.graphics.Renderer;
import fr.lejeu.graphics.Textures;
import fr.lejeu.maths.Vector2f;

public class Particle {

	public boolean remove = false;
	
	float x, y;
	double rx, ry;
	Random random = new Random();
	
	public Textures texture;
	
	private Vector2f direction;
	private int size, lifeTime;
	private float[] color;
	private float speed;
	
	public Particle() {}
	
	public Particle setColor(float[] color){
		this.color = color;
		return this;
	}
	
	public Particle setDirection(Vector2f direction){
		this.direction = direction;
		return this;
	}
	
	public Particle setSpeed(float speed){
		this.speed = speed;
		return this;
	}
	
	public Particle setSize(int size){
		this.size = size;
		return this;
	}
	
	public Particle setLifeTime(int lifeTime){
		this.lifeTime = lifeTime;
		return this;
	}
	
	public Particle setTexure(Textures texture){
		this.texture = texture;
		return this;
	}
	
	public Particle(Particle particle, int x, int y){
		
		this.x = x;
		this.y = y;
		
		this.direction = particle.direction;
		this.color = particle.color;
		this.size = particle.size;
		this.speed = particle.speed;
		this.lifeTime = particle.lifeTime;
		
		rx = random.nextGaussian();
		ry = random.nextGaussian();
		
	}
	
	//public Particle(Color color, int size, float speed, int lifeTime, int[] randomness) {}
	public Particle(Textures texture, int size, float speed, int lifeTime, int[] randomness) {}
	
	int time = 0;
	
	public void update(){
		time++;
		if(time > lifeTime){
			remove = true;
			return;
		}
		x += (rx + direction.x) * speed;
		y += (ry + direction.y) * speed;
	}
	
	public void render(){
		Renderer.particleData(x, y, size, color);
	}
	
}
