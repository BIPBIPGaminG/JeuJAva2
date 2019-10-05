package fr.lejeu.maths;

public class Vector2f {

	public float x, y;
	
	public Vector2f(float x, float y){
		
		this.x =x;
		this.y = y;
		
	}
	
	public Vector2f(){
		this(0, 0);
	}
	
	public Vector2f(Vector2f vector2f){
		
		this(vector2f.x, vector2f.y);
		
	}
	
	public Vector2f set(float x, float y){
		this.x = x;
		this.y = y;
		
		return this;
	}
	
	public Vector2f normalize(){
		
		float mag = (float) Math.sqrt(x * x + y * y);
		
		y /= mag;
		x /= mag;
		
		return this;
	}
	
	public Vector2f multiplier(float v){
		
		x *= v;
		y *= v;
		
		return this;
	}
	
	
}
