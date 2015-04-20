package com.nashdevsoft.ld32jam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;

public class MenuScreen implements Screen, InputProcessor {

	private MainGame game;
	
	private SpriteBatch batch;
	private Sprite titleBackground;
	private Sprite heroShip;
	private Texture spacebgTex;
	private Sprite spacebgSprite;
	private Texture spacedustTex;
	private Sprite spacedustSprite;
	
	private Vector2 scrollTimer = new Vector2(0,0);
	
	public MenuScreen(MainGame game){
		this.game = game;
		
		batch = new SpriteBatch();
		titleBackground = new Sprite(Assets.manager.get(Assets.menuscreen));
		titleBackground.setPosition(450-(titleBackground.getWidth()/2), 350-(titleBackground.getHeight()/2));
		
		heroShip = new Sprite(Assets.manager.get(Assets.heroshipLarge));
		heroShip.setPosition(604, 700-450);
		
		spacebgTex = Assets.manager.get(Assets.spacebg);
		spacebgTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		spacebgSprite = new Sprite(spacebgTex);
		
		spacedustTex = Assets.manager.get(Assets.spacedust);
		spacedustTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		spacedustSprite = new Sprite(spacedustTex);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		scrollTimer.add(0, -0.8f * 0.1f * delta);
		if(scrollTimer.y < -1.0f)
			scrollTimer.y -= -1.0f;
		spacebgSprite.setV(scrollTimer.y);
		spacebgSprite.setV2(scrollTimer.y + 1);
		spacedustSprite.setV(scrollTimer.y*3);
		spacedustSprite.setV2(scrollTimer.y*3 + 1);
		
		batch.enableBlending();
		batch.begin();
		spacebgSprite.draw(batch);
		heroShip.draw(batch);
		spacedustSprite.draw(batch);
		titleBackground.draw(batch);
		batch.end();
		batch.disableBlending();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Buttons.LEFT){
			if(700-screenY > titleBackground.getY() &&
					700-screenY < titleBackground.getY()+119){
				if(screenX > titleBackground.getX()+535 && screenX < titleBackground.getX()+837){
					Assets.manager.get(Assets.select_wav).play();
					MainGame.inputMultiplexer.removeProcessor(this);
					game.retry();
				}
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
