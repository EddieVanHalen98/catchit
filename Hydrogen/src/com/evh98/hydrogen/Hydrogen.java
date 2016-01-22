package com.evh98.hydrogen;

import com.badlogic.gdx.Game;
import com.evh98.hydrogen.screen.*;
import com.evh98.hydrogen.util.Assets;

public class Hydrogen extends Game{

	public MainScreen main_screen;
	public DiffScreen diff_screen;
	public GameScreen game_screen;
	public HelpScreen help_screen;
	public MoreScreen more_screen;
	public FinishedScreen finished_screen;
	
	public static int difficulty;
	public static int score;
	public static boolean sound;
	
	@Override
	public void create() {
		Assets.load();
		
		main_screen = new MainScreen(this);
		diff_screen = new DiffScreen(this);
		game_screen = new GameScreen(this);
		help_screen = new HelpScreen(this);
		more_screen = new MoreScreen(this);
		finished_screen = new FinishedScreen(this);
		
		setScreen(main_screen);
	}
	
}