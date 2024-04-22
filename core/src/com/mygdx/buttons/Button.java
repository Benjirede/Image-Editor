package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.Rec2D;
import com.mygdx.utility.IClickable;
import com.mygdx.utility.IHoverable;
import com.mygdx.utility.InputManager;

public class Button extends Rec2D implements IClickable, IHoverable {
	protected Color _startColor;
	public String ButtonText;
	public enum ButtonState {Clicked, Hovered, None};
	private ButtonState _state;

	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_startColor = recColor;
		InputManager.Instance.Clickables.add(this);
		InputManager.Instance.Hoverables.add(this);
		_state = ButtonState.None;
	}
	
	public void onClickDown() {
		if(_state == ButtonState.Clicked) return;
		_state = ButtonState.Clicked;
		_recColor = new Color(_startColor.r/4f, _startColor.g/4f, _startColor.b/4f, 1);
		generateTexture();
	}
	public void onClickUp() {
		_state = ButtonState.Hovered;
		_recColor = new Color(_startColor.r/2f, _startColor.g/2f, _startColor.b/2f, 1);
		generateTexture();
	}
	public void onHovered() {
		if(_state == ButtonState.Clicked) return;
		if(_state == ButtonState.Hovered) return;
		_recColor = new Color(_startColor.r/2f, _startColor.g/2f, _startColor.b/2f, 1);
		_state = ButtonState.Hovered;
		generateTexture();
	}
	public void onHoverExit() {
		_state = ButtonState.None;
		_recColor = _startColor;
		generateTexture();
	}

	public void onClickDown(Vector2 position) {
		if(_state == ButtonState.Clicked) return;
		_state = ButtonState.Clicked;
		_recColor = new Color(_startColor.r/4f, _startColor.g/4f, _startColor.b/4f, 1);
		generateTexture();		
	}

	public void onClickUp(Vector2 mousePosition) {
		if(_state == ButtonState.None) return;
		_state = ButtonState.Hovered;
		_recColor = new Color(_startColor.r/2f, _startColor.g/2f, _startColor.b/2f, 1);
		generateTexture();		
	}

	@Override
	public void onClickDragged(Vector2 mousePosition) {
		// TODO Auto-generated method stub
		
	}
}