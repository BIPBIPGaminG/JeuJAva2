package fr.lejeu.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
	public static void quadData(float x, float y, int width, int height, float[] color, int xo, int yo){
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(xo / 32.0F, yo / 32.0F); glVertex2f(x, y);
		
		glTexCoord2f((1 + xo) / 32.0F, yo / 32.0F); glVertex2f(x + width, y);
		
		glTexCoord2f((1 + xo) / 32.0F, (1 + yo) / 32.0F); glVertex2f(x + width, y + height);
		
		glTexCoord2f(xo / 32.0F, (1 + yo) / 32.0F); glVertex2f(x, y + height);
		
	}
	
	public static void quadDataGUI(float x, float y, int width, int height, float[] color, int xo, int yo){
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(xo / 32.0F, yo / 32.0F); glVertex2f(x, y);
		
		glTexCoord2f((1 + xo) / 32.0F, yo / 32.0F); glVertex2f(x + width, y);
		
		glTexCoord2f((1 + xo) / 32.0F, (1 + yo) / 32.0F); glVertex2f(x + width, y + height);
		
		glTexCoord2f(xo / 32.0F, (1 + yo) / 32.0F); glVertex2f(x, y + height);
		
	}
	
	public static void renderQuad(float x, float y, int width, int height, float[] color, int xo, int yo){
		
		glBegin(GL_QUADS);
			quadData(x, y, width, height, color, xo, yo);
		glEnd();
	}
	
	public static void renderEntity(float x, float y, int width, int height, float[] color, float size, int xo, int yo){
		glBegin(GL_QUADS);
			glColor4f(color[0], color[1], color[2], color[3]);
			glTexCoord2f(xo / size, yo / size); glVertex2f(x, y);
			glTexCoord2f((1 + xo) / size, yo / size); glVertex2f(x + width, y);
			glTexCoord2f((1 + xo) / size, (1 + yo) / size); glVertex2f(x + width, y + height);
			glTexCoord2f(xo / size, (1 + yo) / size); glVertex2f(x, y + height);
		glEnd();
	}
	
	public static void particleData(float x, float y, int size, float[] color){
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(0, 0); glVertex2f(x, y);
		
		glTexCoord2f(1, 0); glVertex2f(x + size, y);
		
		glTexCoord2f(1, 1); glVertex2f(x + size, y + size);
		
		glTexCoord2f(0, 1); glVertex2f(x, y + size);
		
	}
	
	public static void renderGui(float x, float y, int width, int height, float[] color, int xo, int yo){
		
		glBegin(GL_QUADS);
			quadDataGUI(x, y, width, height, color, xo, yo);
		glEnd();
	}
	
}
