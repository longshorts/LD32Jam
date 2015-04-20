package com.nashdevsoft.ld32jam;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.nashdevsoft.ld32jam.assets.Assets;
import com.nashdevsoft.ld32jam.screens.GameScreen;
import com.nashdevsoft.ld32jam.screens.MenuScreen;

public class MainGame extends Game {
	
	SpriteBatch batch;
	Texture img;
	
	public static Engine engine;
	public static GameScreen gameScreen;
	public static MenuScreen menuScreen;
	public static InputMultiplexer inputMultiplexer = new InputMultiplexer();
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Logger.DEBUG);
		
		//Load assets
		Assets.load();
		Assets.manager.finishLoading();
		
		menuScreen = new MenuScreen(this);
		
		setScreen(menuScreen);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
		inputMultiplexer.addProcessor(menuScreen);
	}

	@Override
	public void render () {
		super.render();
	}
	
	public void retry(){
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
	
	public void menu(){
		setScreen(menuScreen);
		inputMultiplexer.addProcessor(menuScreen);
	}
	
	@Override
	public void dispose(){
		super.dispose();
		Assets.dispose();
		gameScreen.dispose();
	}
}
