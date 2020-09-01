package Screens;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import app.Game;
import rafgfxlib.Util;

public class Screen3 {
	int width, height;
	public BufferedImage background;
	public BufferedImage ground;
	public BufferedImage pulse;
	public BufferedImage mask;

	public Lightning light[];
	Random r = new Random();
	
	int pulseStart, pulseEnd;
	float colorScale;
	float blue, red, green;
	boolean pulseFlag = false;
	int pulseSpeed = 40;

	public ArrayList<HoverBlock> blocks;

	double hScale;
	double wScale;
	
	public BufferedImage fog;
	public int Wfog;
	public int Hfog;
	public int fogX1, fogX2, fogX3;

	public Screen3() {
		width = Game.get().width;
		height = Game.get().height;
		
		background = Util.loadImage("/assets/background3.png");
		ground = Util.loadImage("/assets/ground3.png");
		mask = Util.loadImage("/assets/background3MASK.png");
		hScale = Game.get().height / (float) background.getHeight();
		wScale = Game.get().width / (float) background.getWidth();
		pulse = new BufferedImage(background.getWidth(), background.getHeight(), BufferedImage.TYPE_INT_ARGB);

		blocks = new ArrayList<>();
		blocks.add(new HoverBlock(1, 150, 30, 200, 0.05f, hScale, wScale, 1, 895, 1060));
		blocks.add(new HoverBlock(1, 140, 30, 240, 0.05f, hScale, wScale, 1, 895, 1060));
		blocks.add(new HoverBlock(2, 100, 30, 300, 0.03f, hScale, wScale, -1, 895, 1050));
		blocks.add(new HoverBlock(3, 120, 30, 250, 0.02f, hScale, wScale, 1, 900, 1050));
		blocks.add(new HoverBlock(4, 140, 30, 150, 0.04f, hScale, wScale, 1, 900, 1050));
		blocks.add(new HoverBlock(5, 160, 30, 100, 0.05f, hScale, wScale, -1, 900, 1050));
		blocks.add(new HoverBlock(6, 100, 30, 170, 0.03f, hScale, wScale, -1, 900, 1050));
		blocks.add(new HoverBlock(2, 100, 30, 80, 0.03f, hScale, wScale, 1, 890, 1050));
		blocks.add(new HoverBlock(5, 160, 30, 450, 0.02f, hScale, wScale, 1, 900, 1050));
		blocks.add(new HoverBlock(2, 1250, 30, 320, 0.04f, hScale, wScale, -1, 895, 1050));
		blocks.add(new HoverBlock(4, 140, 30, 70, 0.05f, hScale, wScale, 1, 900, 1050));
		blocks.add(new HoverBlock(1, 100, 30, 120, 0.03f, hScale, wScale, 1, 885, 1060));
		blocks.add(new HoverBlock(4, 120, 30, 70, 0.02f, hScale, wScale, 1, 900, 1050));
		blocks.add(new HoverBlock(4, 130, 30, 190, 0.03f, hScale, wScale, 1, 900, 1050));
		blocks.add(new HoverBlock(4, 150, 30, 360, 0.04f, hScale, wScale, 1, 900, 1050));

		pulseStart = 800;
		pulseEnd = pulseStart - pulseSpeed;
		colorScale = 1f;
		
		fog = Util.loadImage("/assets/fog.png");
		Wfog = (int) (fog.getWidth() * wScale);
		Hfog = (int) (fog.getHeight() * hScale);
		fogX1 = 0;
		fogX2 = Wfog;
		fogX3 = Wfog * 2;
		
		light = new Lightning[3];
		light[0] = new Lightning();
		light[1] = new Lightning();
		light[2] = new Lightning();
		
	}

	public void render(Graphics2D g, int sw, int sh) {
		g.drawImage(background, 0, 0, width, height, null);
		g.drawImage(pulse, 0, 0, width, height, null);
		for (HoverBlock b : blocks) {
			b.render(g, sw, sh);
		}
		for (Lightning l:light){
			if (l.life!=0)
			g.drawImage(l.image, l.x, l.y, (int)((l.width*wScale)*l.scale), (int)((l.height*hScale)*l.scale), null);
		}
		g.drawImage(fog, fogX1, height - Hfog, Wfog, Hfog, null);
		g.drawImage(fog, fogX2, height - Hfog, Wfog, Hfog, null);
		g.drawImage(fog, fogX3, height - Hfog, Wfog, Hfog, null);
		g.drawImage(ground, 0, 0, width, height, null);
	}

	public void update(int mouseX, int mouseY) {
		Color sColor, gColor;
		for (int y = pulseStart; y > pulseEnd; y--) {
			for (int x = 1260; x < 1850; x++) {
				if (mask.getRGB(x, y) == -1) {
					if (!pulseFlag) {
						gColor = new Color(background.getRGB(x, y));
						blue = gColor.getBlue();
						blue = (blue + (blue * colorScale)) / (float) 255;
						blue = blue > 1 ? 1 : blue;
						red = gColor.getRed();
						red = (red + (red * colorScale)) / (float) 255;
						red = red > 1 ? 1 : red;
						green = gColor.getGreen();
						green = (green + (green * colorScale)) / (float) 255;
						green = green > 1 ? 1 : green;
						sColor = new Color(red, green, blue, 1);
						pulse.setRGB(x, y, sColor.getRGB());
					} else {
						sColor = new Color(0f, 0f, 0f, 0f);
						pulse.setRGB(x, y, sColor.getRGB());
					}
				}
			}
		}

		for (HoverBlock b : blocks) {
			b.update();
		}
		pulseStart -= pulseSpeed;
		pulseEnd -= pulseSpeed;
		if (pulseEnd < 0) {
			pulseStart = 800;
			pulseEnd = pulseStart - pulseSpeed;
			if (!pulseFlag)
				pulseFlag = true;
			else
				pulseFlag = false;
		}
		if (Math.random()<=0.02){
			genLightning();
		}
		for (Lightning l:light){
			if (l.life == 0)
				continue;
			l.life--;
			if (l.life%2==0)
			l.image = Lightning.images[l.life%4];
		}
		if (fogX1 == -Wfog)
			fogX1 = (2 * Wfog) - 1;
		else
			fogX1 -= 1;

		if (fogX2 == -Wfog)
			fogX2 = (2 * Wfog) - 1;
		else
			fogX2 -= 1;

		if (fogX3 == -Wfog)
			fogX3 = (2 * Wfog) - 1;
		else
			fogX3 -= 1;
	}
	
	private void genLightning(){
		for (Lightning l:light){
			if (l.life == 0){
				l.x = 300 + r.nextInt(200);
				l.life = r.nextInt(5)+8;
				l.scale = (r.nextInt(5)+6)/(float)10;
				return;
			}
		}
	}

}
