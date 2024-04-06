package com.mygdx.imageeditor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
	public static InputManager Instance;
	private IHoverable _hoveredButton;
	private IClickable _currentlyClicked;
	public Array<Button> Buttons = new Array<Button>();
	public Array<IClickable> IClickables = new Array<IClickable>();
	public Array<IHoverable> IHoverables = new Array<IHoverable>();
	public InputManager() {
		Instance = this;
	}

	public boolean keyDown(int keycode) {return false;}
	public boolean keyUp(int keycode) {return false;}
	public boolean keyTyped(char character) {return false;}
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		_currentlyClicked = CollisionManager.Instance.getCollision(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		Vector2 clickPosition = new Vector2(screenX-ImageEditor.Instance.editWindow.Position.x, ImageEditor.Instance.ScreenSize.y-screenY);
		if(_currentlyClicked!=null) {
			_currentlyClicked.onClickDown(clickPosition);
		}
		return true;
	}
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		//Button collision = CollisionManager.Instance.getCollision(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		if(_currentlyClicked!=null) {
			_currentlyClicked.onClickUp(new Vector2(screenX-ImageEditor.Instance.editWindow.Position.x, ImageEditor.Instance.ScreenSize.y-screenY));
		}
		return true;
	}
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseMoved(screenX, screenY);
		return true;
	}
	public boolean mouseMoved(int screenX, int screenY) {
		Button collision = CollisionManager.Instance.getCollision(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		if(collision == null && _hoveredButton != null) { _hoveredButton.onHoverExit(); }
		if(collision!=null) { collision.onHovered(); }
		_hoveredButton = collision;
		return true;
	}
	public boolean scrolled(float amountX, float amountY) {return false;}

}