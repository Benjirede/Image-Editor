package com.mygdx.imageeditor;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Array;

public class ImageEditor extends ApplicationAdapter {
	public static ImageEditor Instance;
	public Vector2 ScreenSize;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	SpriteBatch batch;
	EditWindow editWindow;

	@Override
	public void create () {
		Instance = this;
		new ImageInputOutput();
		Pixmap editMap = ImageInputOutput.Instance.loadImage("blackbuck.bmp");
		InputManager.Instance = new InputManager();
		Gdx.input.setInputProcessor(InputManager.Instance);
		Rectangles = new Array<Rec2D>();
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 40);
		editWindow = new EditWindow(editWindowSize, new Vector2(ScreenSize.x - editWindowSize.x, 0), Color.GRAY);
		Button yellow = new Button(new Vector2(50,50), new Vector2(0,0), Color.YELLOW);
		editWindow.DoodleTexture = new Texture(editMap);
		CollisionManager collisionManager = new CollisionManager();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		Rec2D rec;
		for(int i = 0; i < Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
		batch.draw(editWindow.DoodleTexture, editWindow.Position.x, editWindow.Position.y, editWindow.Scale.x,
				editWindow.Scale.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}