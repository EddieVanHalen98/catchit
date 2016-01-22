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

public class MainScreen implements Screen{

	Hydrogen game;
	OrthographicCamera camera;
	SpriteBatch batch;
	Vector3 touch;
	
	public MainScreen(Hydrogen game){
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
			batch.draw(Assets.main_play, 130, 224);
			batch.draw(Assets.main_help, 130, 544);
			batch.draw(Assets.main_more, 130, 864);
			batch.draw(Assets.main_exit, 130, 1184);
			batch.draw(Assets.main_facebook, 130, 1504);
			batch.draw(Assets.main_twitter, 758, 1504);
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
		// play
		if(Util.isTouched(130, 224, 820, 192, touch, camera)){
			game.setScreen(game.diff_screen);
		}
		// help
		else if(Util.isTouched(130, 544, 820, 192, touch, camera)){
			game.setScreen(game.help_screen);
		}
		// more
		else if(Util.isTouched(130, 864, 820, 192, touch, camera)){
			game.setScreen(game.more_screen);
		}
		// exit
		else if(Util.isTouched(130, 1184, 820, 192, touch, camera)){
			Gdx.app.exit();
		}
		// facebook
		else if(Util.isTouched(130, 1504, 256, 256, touch, camera)){
			Gdx.net.openURI("http://www.facebook.com/The45Industries");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// twitter
		else if(Util.isTouched(758, 1504, 256, 256, touch, camera)){
			Gdx.net.openURI("http://www.twitter.com/The45Industries");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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