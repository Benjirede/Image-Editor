package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.utility.IClickable;
import com.mygdx.utility.InputManager;

public class EditWindow extends Rec2D implements IClickable {
	public static EditWindow Instance;
	public Color DrawColor;
	public Texture DoodleTexture;
	public Pixmap _doodleMap;
	private Vector2 _previousPaintPosition;
	public EditWindow(Vector2 scale, Vector2 position) {
		super(scale, position, Color.SLATE);
		_doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		DrawColor = Color.ORANGE;
		_doodleMap.setColor(DrawColor);
		DoodleTexture = new Texture(_doodleMap);
		InputManager.Instance.Clickables.add(this);
		Instance = this;
	}
	private void paintAtPosition(Vector2 worldPosition) {
		Vector2 paintPosition = new Vector2(worldPosition.x - Position.x, Scale.y - worldPosition.y);
		int startX = (int) _previousPaintPosition.x;
		int startY = (int)_previousPaintPosition.y;
		int endX = (int) paintPosition.x;
		int endY = (int) paintPosition.y;
		_doodleMap.drawLine(startX, startY, endX, endY);
		_doodleMap.drawLine(startX + 1, startY, endX + 1, endY);
		_doodleMap.drawLine(startX - 1, startY, endX - 1, endY);
		_doodleMap.drawLine(startX, startY+1, endX, endY+1);
		_doodleMap.drawLine(startX, startY-1, endX, endY-1);
		_previousPaintPosition = paintPosition;
		DoodleTexture = new Texture(_doodleMap);
	}
	@Override
	public void onClickDown(Vector2 position) { 
		if(_previousPaintPosition == null) {
			 _previousPaintPosition = new Vector2(position.x - Position.x, Scale.y - position.y);
		}
		paintAtPosition(position);
		}
	@Override
	public void onClickUp(Vector2 mousePosition) { _previousPaintPosition = null; }
	@Override
	public void onClickDragged(Vector2 clickPosition) { 
		if(_previousPaintPosition == null) {
			 _previousPaintPosition = new Vector2(clickPosition.x - Position.x, Scale.y - clickPosition.y);
		}
		paintAtPosition(clickPosition); 
	}
}
