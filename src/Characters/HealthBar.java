package Characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class HealthBar {
	
	public static int pn = 0;

	public int x, y;
	public int width, height;
	public int tempWidth;
	BufferedImage image;
	int tempHP = 100;
	int HP = 100;
	float r;
	float g;
	Color c1;
	Color c2;
	Color black;
	Player player;
	
	public HealthBar(int width, int height, Player player) {
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		r = 0;
		g = 0;
		/*if (pn == 0){
			c1 = new Color(0f,1f,0f,1f);
			c2 = new Color(207/255f,1f, 170/255f);
		} else{
			c1 = new Color(1f,0f,0f,1f);
			c2 = new Color(1f,168/255f, 168/255f);
		}*/
		if (pn == 0){
			c1 = new Color(0f,1f,0f,1f);
			c2 = new Color(1f,168/255f, 168/255f);
		} else{
			c1 = new Color(1f,0f,0f,1f);
			c2 = new Color(207/255f,1f, 170/255f);
		}
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				Color sC = new Color(lerp(c1.getRed(), c2.getRed(), j/(float)width)/255f, 
						lerp(c1.getGreen(), c2.getGreen(), j/(float)width)/255f,
						lerp(c1.getBlue(), c2.getBlue(), j/(float)width)/255f);
				image.setRGB(j, i, sC.getRGB());
			}
		}
		pn++;
		black = new Color(0f,0f,0f,1f);
		this.player = player;
	}
	
	public void render(Graphics2D g, int sw, int sh) {
		g.drawImage(image, x, y, width, height, null);
	}
	
	public void update(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void dealDmg(int dmg){
		tempHP -= dmg;
		if (tempHP<=0){
			player.idle = false;
			player.dead = true;
		}
		//100:tempHP = width:x?
		tempWidth = (tempHP*width)/100;
		if (tempWidth<0) tempWidth = 0;
		for (int i = 0; i < height; i++){
			for (int j = tempWidth; j < width; j++){
				image.setRGB(j, i, black.getRGB());
			}
		}
	}
	
	public static int lerp(int a, int b, double x){
		return (int)(a + (b - a) * x);
	}
	
}
