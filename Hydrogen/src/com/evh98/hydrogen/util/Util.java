package com.evh98.hydrogen.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.evh98.hydrogen.Hydrogen;

public class Util {

	public static boolean isTouched(int x, int y, int width, int height, Vector3 touch, OrthographicCamera camera){
		if(Gdx.input.isTouched()){
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			if(touch.x >= x && touch.x <= x + width &&
					touch.y >= y && touch.y <= y + height){
				if(Hydrogen.sound){
					Assets.click.play();
				}
				return true;
			}
		}
		return false;
	}
	
	public static void playPositive(){
		if(Hydrogen.sound){
			Assets.positive.play();
		}
	}
	
	public static void playNegative(){
		if(Hydrogen.sound){
			Assets.negative.play();
		}
	}
}