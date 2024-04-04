package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D {
	private static int _buttonCount;
	private int _buttonNumber;
	private Color _startColor;
	public enum ButtonState {Clicked, Hovered, None};
	private ButtonState _currentState;

	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_buttonCount+=1;
		_buttonNumber = _buttonCount;
		_startColor = recColor;
		InputManager.Instance.Buttons.add(this);
		_currentState = ButtonState.None;
	}
	
	public void onClickDown() {
		_currentState = ButtonState.Clicked;
		_recColor = new Color(_startColor.r/4f, _startColor.rg/4f, _startColor.b/4f, 1);
		generateTexture();
	}
	public void onClickUp() {
		_currentState = ButtonState.Hovered;
		_recColor = _startColor;
		generateTexture();
	}
	public void onHovered() {
		if(_currentState == ButtonState.Clicked) return;
		_recColor = new Color(_startColor.r/2f, _startColor.rg/2f, _startColor.b/2f, 1);
		_currentState = ButtonState.Hovered;
		generateTexture();
	}
	public void onHoverExit() {
		_currentState = ButtonState.None;
		_recColor = _startColor;
		generateTexture();
	}
}
