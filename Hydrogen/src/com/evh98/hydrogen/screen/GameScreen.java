package com.evh98.hydrogen.screen;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.evh98.hydrogen.Hydrogen;
import com.evh98.hydrogen.objects.Block;
import com.evh98.hydrogen.objects.Health;
import com.evh98.hydrogen.objects.Player;
import com.evh98.hydrogen.objects.Point;
import com.evh98.hydrogen.objects.Poison;
import com.evh98.hydrogen.objects.Regen;
import com.evh98.hydrogen.objects.Wall;
import com.evh98.hydrogen.util.Assets;
import com.evh98.hydrogen.util.Util;

public class GameScreen implements Screen{

	Hydrogen game;
	OrthographicCamera camera;
	SpriteBatch batch;
	Vector3 touch;
	
	Random rand;
	
	public static boolean paused;
	boolean playing;
	int life;
	
	Player player;
	Wall wall;
	Health health;
	Array<Block> blocks;
	long lastBlockDropTime;
	Array<Point> points;
	long lastPointDropTime;
	Array<Poison> poisons;
	long lastPoisonDropTime;
	Array<Regen> regens;
	long lastRegenDropTime;
	
	public GameScreen(Hydrogen game){
		this.game = game;
		camera = new OrthographicCamera();
		camera.setToOrtho(true,1080,1920);
		batch = new SpriteBatch();
		touch = new Vector3();

		rand = new Random();
		
		paused = false;
		playing = true;
		life = 10;
		
		player = new Player();
		wall = new Wall();
		health = new Health();
		blocks = new Array<Block>();
		spawnBlock();
		points = new Array<Point>();
		spawnPoint();
		poisons = new Array<Poison>();
		spawnPoison();
		regens = new Array<Regen>();
		spawnRegen();
		
		Hydrogen.score = 0;
	}

	@Override
	public void show() {
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.95F, 0.95F, 0.95F, 1F);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		if(paused){
			if(Util.isTouched(130, 192, 820, 192, touch, camera)){
				game.setScreen(game.main_screen);
				paused = false;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				game.game_screen = new GameScreen(game);
			}
			if(Util.isTouched(130, 768, 820, 384, touch, camera)){
				if(Hydrogen.sound){
					Assets.pause_sound = Assets.pause_sound_off;
					Hydrogen.sound = false;
				}else{
					Assets.pause_sound = Assets.pause_sound_on;
					Hydrogen.sound = true;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if(Util.isTouched(130, 1536, 820, 192, touch, camera)){
				paused = false;
			}
		}else{
			generalUpdate();
			player.update(touch, camera);
			health.update(life);
			updateBlock(delta);
			updatePoint(delta);
			updatePoison(delta);
			updateRegen(delta);
		}
		
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
			for(Block block: blocks){
				batch.draw(block.tex, block.rect.x, block.rect.y);
			}
			
			for(Point point: points){
				batch.draw(point.tex, point.rect.x, point.rect.y);
			}
			
			for(Poison poison: poisons){
				batch.draw(poison.tex, poison.rect.x, poison.rect.y);
			}
			
			for(Regen regen: regens){
				batch.draw(regen.tex, regen.rect.x, regen.rect.y);
			}
			
			switch(Hydrogen.difficulty){
			case 1:
				batch.draw(wall.tex, wall.rect.x, wall.rect.y);
				break;
			case 2:
				batch.draw(wall.tex, wall.rect.x, wall.rect.y);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+64);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+128);
				break;
			case 3:
				batch.draw(wall.tex, wall.rect.x, wall.rect.y);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+64);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+128);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+192);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+256);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+256+64);
				break;
			case 4:
				batch.draw(wall.tex, wall.rect.x, wall.rect.y);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+64);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+128);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+192);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+256);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+256+64);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+256+128);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+256+192);
				batch.draw(wall.tex, wall.rect.x, wall.rect.y+512);
			}
		
			batch.draw(player.tex, player.rect.x, player.rect.y);
			batch.draw(health.tex, health.rect.x, health.rect.y);
			
			if(paused){
				batch.draw(Assets.pause_back, 0, 0);
				batch.draw(Assets.pause_menu, 130, 192);
				batch.draw(Assets.pause_sound, 130, 768);
				batch.draw(Assets.pause_continue, 130, 1536);
			}
		batch.end();
	}

	@Override
	public void dispose() {
		Assets.Tblock.dispose();
		Assets.Thealth1.dispose();
		Assets.Thealth10.dispose();
		Assets.Thealth2.dispose();
		Assets.Thealth3.dispose();
		Assets.Thealth4.dispose();
		Assets.Thealth5.dispose();
		Assets.Thealth6.dispose();
		Assets.Thealth7.dispose();
		Assets.Thealth8.dispose();
		Assets.Thealth9.dispose();
		Assets.Tmain.dispose();
		Assets.Tregen.dispose();
		Assets.Tpoison.dispose();
		Assets.Tpoint.dispose();
		
		Assets.Tpause_back.dispose();
		Assets.Tpause_continue.dispose();
		Assets.Tpause_menu.dispose();
		Assets.Tpause_sound_off.dispose();
		Assets.Tpause_sound_on.dispose();
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		
	}
	
	private void spawnBlock(){
		Block block = new Block();
		blocks.add(block);
		lastBlockDropTime = TimeUtils.nanoTime();
	}
	
	private void spawnPoint(){
		Point point = new Point();
		points.add(point);
		lastPointDropTime = TimeUtils.nanoTime();
	}
	
	private void spawnPoison(){
		Poison poison = new Poison();
		poisons.add(poison);
		lastPoisonDropTime = TimeUtils.nanoTime();
	}
	
	private void spawnRegen(){
		Regen regen = new Regen();
		regens.add(regen);
		lastRegenDropTime = TimeUtils.nanoTime();
	}
	
	private void generalUpdate(){
		if(Gdx.input.isTouched(1) || Gdx.input.isKeyPressed(Keys.SPACE)){
			paused = true;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(life==0){
			playing=false;
			life=10;
		}
		
		if(playing==false){
			game.setScreen(game.finished_screen);
		}
	}
	
	private void updateBlock(float d){
		if(TimeUtils.nanoTime() - lastBlockDropTime > DiffScreen.block_time){
			spawnBlock();
		}
		
		Iterator<Block> iter = blocks.iterator();
		while(iter.hasNext()){
			Block block = iter.next();
			block.rect.y += DiffScreen.speed*d;
			
			if(block.rect.overlaps(player.rect)){
				iter.remove();
				Util.playNegative();
				life--;
			}
		}
	}
	
	private void updatePoint(float d){
		if(TimeUtils.nanoTime() - lastPointDropTime > DiffScreen.point_time){
			spawnPoint();
		}
		
		Iterator<Point> iter = points.iterator();
		while(iter.hasNext()){
			Point point = iter.next();
			point.rect.y += DiffScreen.speed*d;
			
			if(point.rect.overlaps(player.rect)){
				iter.remove();
				Util.playPositive();
				Hydrogen.score += 10;
			}
		}
	}
	
	private void updatePoison(float d){
		if(TimeUtils.nanoTime() - lastPoisonDropTime > DiffScreen.poison_time){
			spawnPoison();
		}
		
		Iterator<Poison> iter = poisons.iterator();
		while(iter.hasNext()){
			Poison poison = iter.next();
			poison.rect.y += DiffScreen.speed*d;
			
			if(poison.rect.overlaps(player.rect)){
				iter.remove();
				Util.playNegative();
				life=0;
			}
		}
	}
	
	private void updateRegen(float d){
		if(TimeUtils.nanoTime() - lastRegenDropTime > DiffScreen.regen_time){
			int x = rand.nextInt(3) + 1;
			if(x==3){
				spawnRegen();
			}
		}
		
		Iterator<Regen> iter = regens.iterator();
		while(iter.hasNext()){
			Regen regen = iter.next();
			regen.rect.y += DiffScreen.speed*d;
			
			if(regen.rect.overlaps(player.rect)){
				iter.remove();
				Util.playPositive();
				if(life<10){
					life++;
				}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void hide() {
	}
}