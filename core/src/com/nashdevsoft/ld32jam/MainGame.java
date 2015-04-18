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

public class MainGame extends Game {
	
	SpriteBatch batch;
	Texture img;
	
	public static Engine engine;
	public static GameScreen gameScreen;
	public static InputMultiplexer inputMultiplexer = new InputMultiplexer();
	
	@Override
	public void create () {
		Gdx.app.setLogLevel(Logger.DEBUG);
		
		//Load assets
		Assets.load();
		Assets.manager.finishLoading();
		
		gameScreen = new GameScreen();
		setScreen(gameScreen);
		
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose(){
		super.dispose();
		Assets.dispose();
		gameScreen.dispose();
	}
}
