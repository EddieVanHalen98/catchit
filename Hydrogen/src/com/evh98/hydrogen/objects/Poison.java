package com.evh98.hydrogen.objects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.evh98.hydrogen.util.Assets;

public class Poison {

	public TextureRegion tex;
	public Rectangle rect;
	
	public Poison(){
		tex = Assets.poison;
		
		rect = new Rectangle();
		rect.x = MathUtils.random(0, 1080-64);
		rect.y = 0;
		rect.width = 64;
		rect.height = 64;
	}
}