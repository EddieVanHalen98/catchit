package com.evh98.hydrogen.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.evh98.hydrogen.screen.DiffScreen;
import com.evh98.hydrogen.util.Assets;

public class Wall {

	public TextureRegion tex;
	public Rectangle rect;
	
	public Wall(){
		tex = Assets.health10;
		
		rect = new Rectangle();
		rect.x = 0;
		rect.y = 0;
		rect.width = 1080;
		
		rect.height = DiffScreen.wall_height;
	}
}