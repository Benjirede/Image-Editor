package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
	public static CollisionManager Instance;
	public CollisionManager() {
		Instance = this;
	}
	
	public Button getCollision(Vector2 coordinates) {
		Button iteratingButton;
		for(int i = 0; i < InputManager.Instance.Buttons.size; i++) {
			iteratingButton = InputManager.Instance.Buttons.get(i);
			if((coordinates.x > iteratingButton.Position.x) && 
					(coordinates.x < iteratingButton.Position.x+iteratingButton.Scale.x) &&
					(coordinates.y > iteratingButton.Position.y) && (coordinates.y < iteratingButton.Position.y+iteratingButton.Scale.y)) { return iteratingButton;}
		}
		return null;
	}
	
	public IHoverable getHovered(Vector2 coordinates) {
		IHoverable hoveredButton;
		for(int i = 0; i < InputManager.Instance.IHoverables.size; i++) {
			hoveredButton = InputManager.Instance.IHoverables.get(i);
		}
		return null;
	}
	
public IClickable getClicked(Vector2 coordinates) {
		IClickable clickedButton;
		for(int i = 0; i < InputManager.Instance.IClickables.size; i++) {
			clickedButton = InputManager.Instance.IClickables.get(i);
		}
		return null;
	}
}