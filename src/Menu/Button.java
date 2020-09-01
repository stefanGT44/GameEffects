package Menu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import app.Game;
import rafgfxlib.GameFrame.GFMouseButton;

public class Button {

	int x, y;
	BufferedImage text, hover;
	BufferedImage image;
	public boolean rotFlag;
	String name;

	public Button(BufferedImage text, BufferedImage hover, int x, int y, String name) {
		this.text = text;
		this.hover = hover;
		image = text;
		this.x = x;
		this.y = y;
		this.name  = name;
	}

	public void render(Graphics2D g, int sw, int sh) {

		g.drawImage(image, x, y, null);
	}

	public void update(int mouseX, int mouseY) {

		if (mouseX > x && mouseX < (x + image.getWidth()) && mouseY > y && mouseY < (y + image.getHeight())) {
			image = hover;
			rotFlag = true;
		} else {
			image = text;
			rotFlag = false;
		}
	}
	
	
	public void handleMouseUp(int mouseX, int mouseY, GFMouseButton button) {
		if (mouseX > x && mouseX < (x + image.getWidth()) && mouseY > y && mouseY < (y + image.getHeight())) {
			if(name.equals("quit"))
				System.exit(0);
			if(name.equals("play"))
				Game.get().screenNumber = 4;
		}

	}

}
