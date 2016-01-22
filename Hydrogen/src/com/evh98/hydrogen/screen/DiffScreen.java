package com.evh98.hydrogen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.evh98.hydrogen.Hydrogen;
import com.evh98.hydrogen.util.Assets;
import com.evh98.hydrogen.util.Util;

public class DiffScreen implements Screen{

	Hydrogen game;
	OrthographicCamera camera;
	SpriteBatch batch;
	Vector3 touch;
	
	public static int wall_height;
	public static int speed;
	public static long block_time;
	public static long point_time;
	public static long poison_time;
	public static long regen_time;
	
	public DiffScreen(Hydrogen game){
		this.game = game;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true,1080,1920);
		
		batch = new SpriteBatch();
		
		touch = new Vector3();
	}

	@Override
	public void show() {
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.95F, 0.95F, 0.95F, 1F);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		generalUpdate(touch, camera);
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
			batch.draw(Assets.health10, 0, 0);
			batch.draw(Assets.diff_easy, 130, 416);
			batch.draw(Assets.diff_medium, 130, 608);
			batch.draw(Assets.diff_hard, 130, 800);
			batch.draw(Assets.diff_harder, 130, 992);
			batch.draw(Assets.back, 130, 1312);
			batch.draw(Assets.health10, 0, 1920 - 64);
		batch.end();
	}

	@Override
	public void dispose() {
		Assets.Tmain_exit.dispose();
		Assets.Tmain_facebook.dispose();
		Assets.Tmain_help.dispose();
		Assets.Tmain_more.dispose();
		Assets.Tmain_play.dispose();
		Assets.Tmain_twitter.dispose();
	}
	
	private void generalUpdate(Vector3 touch, OrthographicCamera camera){
		// easy
		if(Util.isTouched(130, 416, 820, 192, touch, camera)){
			Hydrogen.difficulty = 1;
			gameInit();
			game.setScreen(game.game_screen);
		}
		// medium
		else if(Util.isTouched(130, 608, 820, 192, touch, camera)){
			Hydrogen.difficulty = 2;
			gameInit();
			game.setScreen(game.game_screen);
		}
		// hard
		else if(Util.isTouched(130, 800, 820, 192, touch, camera)){
			Hydrogen.difficulty = 3;
			gameInit();
			game.setScreen(game.game_screen);
		}
		// harder
		else if(Util.isTouched(130, 992, 820, 192, touch, camera)){
			Hydrogen.difficulty = 4;
			gameInit();
			game.setScreen(game.game_screen);
		}
		// back
		else if(Util.isTouched(130, 1312, 820, 192, touch, camera)){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			game.setScreen(game.main_screen);
		}
		
		if(Gdx.input.isTouched()){
			try{
				System.out.println("Difficulty is " + Hydrogen.difficulty);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void gameInit(){
		switch(Hydrogen.difficulty){
		case 1:
			wall_height = 64;
			speed = 400;
			block_time = 600000000;
			point_time = 500000000;
			poison_time = 1200000000;
			regen_time = 1800000000;
			break;
		case 2:
			wall_height = 192;
			speed = 500;
			block_time = 500000000;
			point_time = 600000000;
			poison_time = 1000000000;
			regen_time = 1900000000;
			break;
		case 3:
			wall_height = 384;
			speed = 600;
			block_time = 400000000;
			point_time = 700000000;
			poison_time = 800000000;
			regen_time = 2000000000;
			break;
		case 4:
			wall_height = 576;
			speed = 800;
			block_time = 250000000;
			point_time = 800000000;
			poison_time = 600000000;
			regen_time = 2100000000;
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void hide() {
	}
}