package com.mygdx.utility;

import java.io.IOException;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.imageeditor.ImageEditor;

public class InputManager implements InputProcessor {
	public static InputManager Instance;
	private IHoverable _currentlyHovered;
	private IClickable _currentlyClicked;
	private boolean _controlPressed;
	public Array<IClickable> Clickables = new Array<IClickable>();
	public Array<IHoverable> Hoverables = new Array<IHoverable>();
	public InputManager() {
		Instance = this;
	}

	public boolean keyDown(int keycode) {
		if(ImageInputOutput.Instance.ImageFolderLocation == null) {
			return false;
		}
		if(_controlPressed && keycode == Keys.S) {
			try {ImageInputOutput.Instance.saveImage(ImageInputOutput.Instance.ImageFolderLocation+"\\output.bmp");}
			catch (IOException e) {e.printStackTrace();}
		}
		if(keycode == Keys.CONTROL_LEFT) _controlPressed = true;
		return false;
	}
	public boolean keyUp(int keycode) {
		if(keycode == Keys.CONTROL_LEFT) _controlPressed = false;
		return false;
	}
	public boolean keyTyped(char character) {return false;}
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 clickPosition = new Vector2(screenX, ImageEditor.Instance.ScreenSize.y-screenY);
		IClickable collision = CollisionManager.Instance.getClicked(clickPosition);
		_currentlyClicked = collision;
		if(_currentlyClicked!=null) {
			_currentlyClicked.onClickDown(clickPosition);
		}
		return true;
	}
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(_currentlyClicked!=null) {
			_currentlyClicked.onClickUp(new Vector2(screenX-ImageEditor.Instance.editWindow.Position.x, ImageEditor.Instance.ScreenSize.y-screenY));
		}
		return true;
	}
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseMoved(screenX, screenY);
		if(_currentlyClicked != null)
			_currentlyClicked.onClickDragged(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		return true;
	}
	public boolean mouseMoved(int screenX, int screenY) {
		IHoverable collision = CollisionManager.Instance.getHovered(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		if(collision != _currentlyHovered && _currentlyHovered != null) { _currentlyHovered.onHoverExit(); }
		if(collision!=null) { collision.onHovered(); }
		if(collision != _currentlyHovered) _currentlyClicked = null;
		_currentlyHovered = collision;
		return true;
	}
	public boolean scrolled(float amountX, float amountY) {return false;}

}