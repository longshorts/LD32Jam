package com.nashdevsoft.ld32jam.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.nashdevsoft.ld32jam.assets.Assets;
import com.nashdevsoft.ld32jam.sprites.Asteroid;
import com.nashdevsoft.ld32jam.sprites.Player;

public class GameScreen implements Screen {
	
	private OrthographicCamera camera;
	private Player player;
	
	//Sprites
	private SpriteBatch batch;
	private Texture spacebgTex;
	private Sprite spacebgSprite;
	
	//Collections
	public ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	
	//AsteroidSpawner
	public float asteroidSpawnTimer = 4;
	public int maxAsteroids = 2;
	public float currentSpawnTime = 0f;
	public float thresholdSpawnTime = 10f;
	public float thresholdItemSpawnTime = 30f;
	
	private Vector2 scrollTimer = new Vector2(0,0);
	private Vector2 scrollSpeed = new Vector2(0,-0.9f);
	
	public GameScreen(){
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		spacebgTex = Assets.manager.get(Assets.spacebg);
		spacebgTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		spacebgSprite = new Sprite(spacebgTex);
		
		player = new Player();
		
	}

	@Override
	public void render(float delta) {
		
		//Preprocessing
		scrollTimer.add(0, scrollSpeed.y * 0.1f * delta);
		if(scrollTimer.y < -1.0f)
			scrollTimer.y -= -1.0f;
		spacebgSprite.setV(scrollTimer.y);
		spacebgSprite.setV2(scrollTimer.y + 1);
		
		currentSpawnTime += delta;
		if(currentSpawnTime > thresholdSpawnTime){
			maxAsteroids++;
			currentSpawnTime = 0f;
		}
		
		if(asteroids.size() < maxAsteroids){
			Asteroid a = new Asteroid();
			a.spawn();
			asteroids.add(a);
		}
		
		//Draw
		batch.enableBlending();
		batch.begin();
		spacebgSprite.draw(batch);
		for(Asteroid a : asteroids){
			a.draw(batch);
		}
		player.draw(batch);
		batch.end();
		batch.disableBlending();
		
		//Postprocessing
		for(int i = 0; i < asteroids.size(); i++){
			if(asteroids.get(i).dead) asteroids.get(i).dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
	}

	@Override
	public void show() {
		Asteroid a = new Asteroid();
		a.spawn();
		asteroids.add(a);
		
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
	
	private void backgroundRender(){
		batch.disableBlending();
		scrollTimer.add(0.0001f, 0.0001f);
		
		if(scrollTimer.x>1.0f)
			scrollTimer.x -= 1.0f;
		if(scrollTimer.y>1.0f)
			scrollTimer.y -= 1.0f;
	}
	
	public Vector2 getScrollSpeed() {
		return scrollSpeed;
	}

	public void setScrollSpeed(Vector2 scrollSpeed) {
		this.scrollSpeed = scrollSpeed;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	

}
