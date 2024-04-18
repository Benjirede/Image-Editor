package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {
	public static int bytesToInt(byte[] bytes) {
		int[] ints = unsignBytes(bytes);
		int result = 0;
		for(int i = 0; i < ints.length; i++) {
			result += (int) ints[i] << (8 * i);
		}
		return result;
	}
	public static int[] unsignBytes(byte[] bytes) {
		int[] ints = new int[bytes.length];
		for(int i = 0; i < bytes.length; i++) {
			if(bytes[i]>=0) {
				ints[i]=bytes[i];
			} else if(bytes[i]<0) {
				int d=bytes[i]+129;
				d+=127;
				ints[i]=d;
			}
		}
		return ints;
	}
	public static byte[] intToSignedBytes(int value) {
		byte[] result = new byte[4];
		result[0] = (byte) (value >> 24);
		result[1] = (byte) ((value << 8) >> 24);
		result[2] = (byte) ((value << 16) >> 24);
		result[3] = (byte) ((value << 24) >> 24);
		return result;
	}
	public static Pixmap scalePixmap(Pixmap source, Vector2 desiredSize) {
		int tempColor;
		Pixmap target = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
		float targetWidth = target.getWidth();
		float targetHeight = target.getHeight();
		float sourceWidth = source.getWidth();
		float sourceHeight = source.getHeight();
		for(int x = 0; x < target.getWidth(); x++) {
			for(int y = 0; y < target.getHeight(); y++) {
				int newX = (Math.round(x/targetWidth*sourceWidth));
				int newY = (Math.round(y/targetHeight*sourceHeight));
				tempColor = source.getPixel(newX, newY);
				target.drawPixel(x, y, tempColor);
			}
		}
		return target;
	}

}
