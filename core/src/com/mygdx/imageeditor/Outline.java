package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Outline {
	public Texture OutlineTex;
	public Outline(Vector2 recSize, Color color, int thickness) {
		Pixmap map = new Pixmap((int) recSize.x, (int) recSize.y, Format.RGBA8888);
		map.setColor(color);
		for(int x = 0; x < map.getWidth(); x++) {
			for(int y = 0; y < thickness; y++) {
				map.drawPixel(x, y);
			}
		}
		for(int x = 0; x < map.getWidth(); x++) {
			for(int y = map.getHeight()-1; y > map.getHeight()-1-thickness; y--) {
				map.drawPixel(x, y);
			}
		}
		for(int x = 0; x < thickness; x++) {
			for(int y = 0; y < map.getHeight(); y++) {
				map.drawPixel(x, y);
			}
		}
		for(int x = map.getWidth()-1; x > map.getWidth()-1-thickness; x--) {
			for(int y = 0; y < map.getHeight(); y++) {
				map.drawPixel(x, y);
			}
		}
		OutlineTex = new Texture(map);
	}
}
