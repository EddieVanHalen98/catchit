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

public class HelpScreen implements Screen{

	Hydrogen game;
	OrthographicCamera camera;
	SpriteBatch batch;
	Vector3 touch;
	
	public HelpScreen(Hydrogen game){
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
			batch.draw(Assets.help, 0, 0);
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
		if(Util.isTouched(130, 1312, 820, 192, touch, camera)){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			game.setScreen(game.main_screen);
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