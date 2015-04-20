package com.nashdevsoft.ld32jam.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;
import com.nashdevsoft.ld32jam.sprites.Asteroid;
import com.nashdevsoft.ld32jam.sprites.Crystal;
import com.nashdevsoft.ld32jam.sprites.Missile;
import com.nashdevsoft.ld32jam.sprites.Player;
import com.nashdevsoft.ld32jam.sprites.RedShip;
import com.nashdevsoft.ld32jam.sprites.Smoke;
import com.nashdevsoft.ld32jam.sprites.UIGameOver;
import com.nashdevsoft.ld32jam.sprites.UILeft;
import com.nashdevsoft.ld32jam.sprites.UIRight;

public class GameScreen implements Screen {
	
	public OrthographicCamera camera;
	public MainGame mainGame;
	public Player player;
	private ShapeRenderer shapes;
	public float distance = 0;
	public float difficulty = 0;
	
	//Sprites
	private SpriteBatch batch;
	private Texture spacebgTex;
	private Sprite spacebgSprite;
	private Texture spacedustTex;
	private Sprite spacedustSprite;
	private UILeft uiLeft;
	private UIRight uiRight;
	public UIGameOver uiGameOver;
	
	//Collections
	public ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	public ArrayList<Smoke> smoke = new ArrayList<Smoke>();
	public ArrayList<Crystal> crystals = new ArrayList<Crystal>();
	public ArrayList<RedShip> redShips = new ArrayList<RedShip>();
	public ArrayList<Missile> missiles = new ArrayList<Missile>();
	
	//AsteroidSpawner
	public float asteroidSpawnTimer = 4;
	public int maxAsteroids = 2;
	public int maxRedShips = -1;
	public float asteroidSpawnTime = 0f;
	public float asteroidThresholdSpawnTime = 10f;
	public float shipSpawnTime = 0f;
	public float shipThresholdSpawnTime = 15f;
	
	private Vector2 scrollTimer = new Vector2(0,0);
	private Vector2 scrollSpeed = new Vector2(0,-0.8f);
	
	public GameScreen(MainGame game){
		mainGame = game;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		spacebgTex = Assets.manager.get(Assets.spacebg);
		spacebgTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		spacebgSprite = new Sprite(spacebgTex);
		spacedustTex = Assets.manager.get(Assets.spacedust);
		spacedustTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		spacedustSprite = new Sprite(spacedustTex);
		uiLeft = new UILeft();
		uiRight = new UIRight();
		uiGameOver = new UIGameOver();
		shapes = new ShapeRenderer();
		player = new Player();
		
		
	}

	@Override
	public void render(float delta) {
		
		//Preprocessing
		if(!player.dead) setDifficulty();
		
		scrollTimer.add(0, scrollSpeed.y * 0.1f * delta);
		if(scrollTimer.y < -1.0f)
			scrollTimer.y -= -1.0f;
		spacebgSprite.setV(scrollTimer.y);
		spacebgSprite.setV2(scrollTimer.y + 1);
		spacedustSprite.setV(scrollTimer.y*3);
		spacedustSprite.setV2(scrollTimer.y*3 + 1);
		
		if(!player.dead){
			asteroidSpawnTime += delta;
			if(asteroidSpawnTime > asteroidThresholdSpawnTime){
				maxAsteroids++;
				asteroidSpawnTime = 0f;
			}
			
			shipSpawnTime += delta;
			if(shipSpawnTime > shipThresholdSpawnTime){
				maxRedShips++;
				shipSpawnTime = 0f;
			}
		}
		
		
		if(asteroids.size() < maxAsteroids){
			Asteroid a = new Asteroid(true);
			a.spawn();
			asteroids.add(a);
		}
		if(redShips.size() < maxRedShips){
			RedShip rs = new RedShip();
			rs.spawn();
			redShips.add(rs);
		}
		
		//Draw
		batch.enableBlending();
		batch.begin();
		spacebgSprite.draw(batch);
		for(Asteroid a : asteroids){
			a.draw(batch);
		}
		for(RedShip rs : redShips){
			rs.draw(batch);
		}
		for(Missile m : missiles){
			m.draw(batch);
		}
		player.draw(batch);
		for(Smoke s : smoke){
			s.draw(batch);
		}
		for(Crystal c : crystals){
			c.draw(batch);
		}
		spacedustSprite.draw(batch);
		uiLeft.draw(batch);
		uiRight.draw(batch);
		uiGameOver.draw(batch);
		batch.end();
		batch.disableBlending();
		
		uiLeft.drawShapes();
		uiRight.drawShapes();
		
		//Postprocessing
		for(int i = 0; i < asteroids.size(); i++){
			if(asteroids.get(i).dead) asteroids.get(i).dispose();
		}
		for(int i = 0; i < smoke.size(); i++){
			if(smoke.get(i).dead) smoke.get(i).destroy();
		}
		for(int i = 0; i < crystals.size(); i++){
			if(crystals.get(i).dead) crystals.get(i).dispose();
		}
		for(int i = 0; i < redShips.size(); i++){
			if(redShips.get(i).dead) redShips.get(i).destroy();
		}
		for(int i = 0; i < missiles.size(); i++){
			if(missiles.get(i).dead) missiles.get(i).destroy();
		}
		
		if(!player.dead)distance -= 2*(scrollSpeed.y * delta);
	}
	
	public void setDifficulty(){
		if(distance > (difficulty * 100)){
			difficulty++;
			scrollSpeed.y -= 0.2f * difficulty;
		}
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
	}

	@Override
	public void show() {
		Asteroid a = new Asteroid(true);
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
