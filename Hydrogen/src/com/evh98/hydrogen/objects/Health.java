package com.evh98.hydrogen.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.evh98.hydrogen.util.Assets;

public class Health {
	
	public TextureRegion tex;
	public Rectangle rect;
	
	public Health(){
		tex = Assets.health10;
		
		rect = new Rectangle();
		rect.x = 0;
		rect.y = 1920-64;
		rect.width = 1080;
		rect.height = 64;
	}
	
	public void update(int health){
		switch(health){
		case 1: tex = Assets.health1;
			break;
		case 2: tex = Assets.health2;
			break;
		case 3: tex = Assets.health3;
			break;
		case 4: tex = Assets.health4;
			break;
		case 5: tex = Assets.health5;
			break;
		case 6: tex = Assets.health6;
			break;
		case 7: tex = Assets.health7;
			break;
		case 8: tex = Assets.health8;
			break;
		case 9: tex = Assets.health9;
			break;
		case 10: tex = Assets.health10;
			break;
		}
	}
}