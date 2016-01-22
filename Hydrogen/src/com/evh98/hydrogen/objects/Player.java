package com.evh98.hydrogen.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.evh98.hydrogen.util.Assets;

public class Player {

	public TextureRegion tex;
	public Rectangle rect;
	
	public Player(){
		tex = Assets.main;
		
		rect = new Rectangle();
		rect.x = 1080/2 - 128/2;
		rect.y = 1920 - 128 - 96;
		rect.width = 128;
		rect.height = 128;
	}
	
	public void update(Vector3 touch, OrthographicCamera camera){
		if(Gdx.input.isTouched()){
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			rect.x = touch.x - 128/2;
		}
	}
}