package com.nashdevsoft.ld32jam.sprites;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;

public class Asteroid extends Sprite {
	
	public boolean dead = false;
	private Vector2 driftVelocity = new Vector2(0,0);
	private float rotationSpeed;

	private static TextureRegion tex = new TextureRegion(Assets.manager.get(Assets.asteroid));
	
	public Asteroid(){
		super(new Sprite(tex));
		
		//Setup drift velocity
		Random rand = new Random();
		float rX = 1.0f * (rand.nextFloat()-0.5f);
		float rY = -0.5f + (rand.nextFloat()-0.5f);
		float rScale = rand.nextFloat()*0.7f + 0.3f;
		rotationSpeed = 1 * (rand.nextFloat()-0.5f);
		
		this.setSize(getWidth()*rScale, getHeight()*rScale);
		this.setOriginCenter();
		driftVelocity = new Vector2(rX, rY + MainGame.gameScreen.getScrollSpeed().y);
	}
	
	@Override
	public void draw(Batch batch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	public void update(float delta){
		setPosition(getX()+driftVelocity.x,getY()+driftVelocity.y);
		setRotation(this.getRotation()+rotationSpeed);
		
		if(getY()<0-getHeight()){
			dead = true;
		}
	}
	
	public void spawn(){
		Random rand = new Random();
		
		float rX = 900 * rand.nextFloat();
		setPosition(rX, 700);
		
	}
	
	public void dispose(){
		if(MainGame.gameScreen.asteroids.contains(this))
			MainGame.gameScreen.asteroids.remove(this);
		
		Gdx.app.debug("Radius", "" + getRadius());
		if(getRadius() > 80) {
			Asteroid a1 = new Asteroid();
			a1.setSize(getWidth()/2, getHeight()/2);
			a1.setOriginCenter();
			a1.setPosition(getX()+(getWidth()/2), getY()+(getHeight()/2));
			MainGame.gameScreen.asteroids.add(a1);
			
			Asteroid a2 = new Asteroid();
			a2.setSize(getWidth()/2, getHeight()/2);
			a2.setOriginCenter();
			a2.setPosition(getX()+(getWidth()/2), getY()+(getHeight()/2));
			MainGame.gameScreen.asteroids.add(a2);
		}
	}

	public float getRadius() {
		return this.getHeight()/2;
	}
}
