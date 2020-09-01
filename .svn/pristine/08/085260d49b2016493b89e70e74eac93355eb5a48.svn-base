package Screens;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import Characters.Player;
import Characters.Tiles;
import rafgfxlib.GameFrame.GFMouseButton;
import rafgfxlib.Util;

public class PlayScreen {

	int counter = 0;
	int negativeFrequency = 2;
	boolean neg = false;

	BufferedImage background;
	BufferedImage midground;
	BufferedImage midgroundPulse;
	BufferedImage midgroundRiver;

	BufferedImage backgroundNormal;
	BufferedImage midgroundNormal;
	BufferedImage midgroundPulseNormal;
	BufferedImage midgroundRiverNormal;

	BufferedImage backgroundNegative;
	BufferedImage midgroundNegative;
	BufferedImage midgroundPulseNegative;
	BufferedImage midgroundRiverNegative;

	public Tiles[][] tiles = new Tiles[9][5];

	/*public Player player1 = new Player(264, 334, "/assets/f1_tier2general.png", "/assets/f1_tier2general_desc.txt",
			"/assets/testImpact.png", "Arthas");
	public Player player2 = new Player(816, 334, "/assets/f6_seismicelemental.png",
			"/assets/f6_seismicelemental_desc.txt", "/assets/testImpact.png", "Illidan");
	
	*/
	
	public Player player1, player2;
	
	private String[] chars = new String[7];
	Random r = new Random();

	float position = 0.0f;
	float speed = 0.02f;
	boolean pulse = false;

	public PlayScreen() {
		chars[0] = "boss_kron";
		chars[1] = "boss_solfist";
		chars[2] = "f1_tier2general";
		chars[3] = "f1_warblade";
		chars[4] = "f2_eternitypainter";
		chars[5] = "f3_incinera";
		chars[6] = "f6_seismicelemental";
		int t1 = r.nextInt(7);
		String p1 = chars[t1];
		int t2 = r.nextInt(7);
		if (t2 == t1) {
			if (t2==0) t2++;
			else if (t2==7) t2--;
			else t2++;
		}
		String p2 = chars[t2];
		if (p1 == "f2_eternitypainter")
			player1 = new Player(264, 334, "/assets/"+p1+".png", "/assets/"+p1+"_desc.txt",
					"/assets/eternityImpact.png", "Arthas");
		else
			player1 = new Player(264, 334, "/assets/"+p1+".png", "/assets/"+p1+"_desc.txt",
					"/assets/testImpact.png", "Arthas");
		if (p2 == "f2_eternitypainter")
			player2 = new Player(816, 334, "/assets/"+p2+".png", "/assets/"+p2+"_desc.txt",
					"/assets/eternityImpact.png", "Illidan");
		else
			player2 = new Player(816, 334, "/assets/"+p2+".png", "/assets/"+p2+"_desc.txt",
					"/assets/testImpact.png", "Illidan");
		
		
		background = Util.loadImage("/assets/Pbackground.jpg");
		backgroundNormal = background;
		backgroundNegative = negative(background);

		midground = Util.loadImage("/assets/Pmidground.png");
		midgroundNormal = midground;
		midgroundNegative = negative(midground);

		midgroundRiver = Util.loadImage("/assets/Pmidground_river.png");
		midgroundRiverNormal = midgroundRiver;
		midgroundRiverNegative = negative(midgroundRiver);

		midgroundPulse = Util.loadImage("/assets/Pmidground glow.png");
		midgroundPulseNormal = midgroundPulse;
		midgroundPulseNegative = negative(midgroundPulse);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 5; j++) {
				tiles[i][j] = new Tiles(i, j);
			}
		}
		player1.setTile(tiles[0][2]);
		player2.setTile(tiles[8][2]);
		tiles[8][2].setPaintedTile(true);
	}

	public void render(Graphics2D g, int sw, int sh) {

		g.drawImage(background, 0, 0, sw, sh, null);
		g.drawImage(midground, 0, 0, sw, sh, null);
		g.drawImage(midgroundRiver, 0, 0, sw, sh, null);

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, position));
		g.drawImage(midgroundPulse, 0, 0, sw, sh, null);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 5; j++) {
				tiles[i][j].render(g, sw, sh);
			}
		}

		player1.render(g, sw, sh);
		player2.render(g, sw, sh);
		if (player1.attacking)
			player1.renderImpact(g, sw, sh, player2.tile);
		if (player2.attacking)
			player2.renderImpact(g, sw, sh, player1.tile);
	}

	public void update(int mouseX, int mouseY) {

		if (player1.dead || player2.dead) {
			negativePuls();
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 5; j++) {
				tiles[i][j].update(mouseX, mouseY);
			}
		}

		if (pulse == false)
			position += speed;
		else
			position -= speed;
		if (position > 1) {
			position = 1;
			pulse = !pulse;
		}

		if (position < 0) {
			position = 0;
			pulse = !pulse;
		}
		player1.update();
		player2.update();
	}

	public void handleMouseUp(int mouseX, int mouseY, GFMouseButton button) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 5; j++) {
				tiles[i][j].handleMouseUp(mouseX, mouseY, button);
			}
		}
	}

	public void negativePuls() {
		if(counter > negativeFrequency*10)
			return;
		counter++;
		if (counter % negativeFrequency == 0) {
			neg = !neg;
		}

		if (neg) {
			background = backgroundNegative;
			midground = midgroundNegative;
			midgroundRiver = midgroundRiverNegative;
			midgroundPulse = midgroundPulseNegative;
		} else {
			background= backgroundNormal;
			midground = midgroundNormal;
			midgroundRiver = midgroundRiverNormal;
			midgroundPulse = midgroundPulseNormal;
		}
	}

	public BufferedImage negative(BufferedImage image) {
		float[] rgb = new float[3];
		float alpha;
		BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				Color col = new Color(image.getRGB(x, y), true);

				rgb[0] = 255 - col.getRed();
				rgb[1] = 255 - col.getGreen();
				rgb[2] = 255 - col.getBlue();
				alpha = col.getAlpha();

				Color newCol = new Color(rgb[0] / 255, rgb[1] / 255, rgb[2] / 255, alpha / 255);
				newImg.setRGB(x, y, newCol.getRGB());
			}
		}

		return newImg;
	}
}
