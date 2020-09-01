package Screens;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import app.Game;

public class TransitionScreen {

	int width;
	int height;

	BufferedImage screen1;
	BufferedImage screen2;
	float position = 0.0f;
	float speed = 0.08f;
	int screen;

	public TransitionScreen() {
		Game.get();
		width = Game.width;
		height = Game.height;
	}

	public void render(Graphics2D g, int sw, int sh) {
		g.drawImage(screen1, 0, 0, null);

		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, position));
		g.drawImage(screen2, 0, 0, null);
	}

	public void update() {

		position += speed;

		if (position >= 1.0f) {
			position = 1.0f;

			Game.get().screenNumber = screen;
		}
	}

	public void setScreens(BufferedImage screen1, BufferedImage screen2, int screen) {
		this.screen1 = screen1;
		this.screen2 = screen2;
		
		position = 0.0f;		
		this.screen = screen;
	}
}
