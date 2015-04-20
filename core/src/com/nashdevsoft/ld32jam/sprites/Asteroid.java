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
	public boolean crystal = false;
	private Vector2 driftVelocity = new Vector2(0,0);
	private float rotationSpeed;

	private static TextureRegion tex = new TextureRegion(Assets.manager.get(Assets.asteroid));
	private static TextureRegion texCrystal = new TextureRegion(Assets.manager.get(Assets.crystalasteroid));
	
	public Asteroid(boolean canCrystal){
		super(new Sprite(tex));
		//Setup drift velocity
		Random rand = new Random();
		
		if(rand.nextFloat() < 0.3f && canCrystal){
			this.setRegion(texCrystal);;
			crystal = true;
		}
			
		
		float rX = 1.0f * (rand.nextFloat()-0.5f);
		float rY = -0.5f + (rand.nextFloat()-0.5f);
		float rScale = rand.nextFloat()*0.7f + 0.3f;
		rotationSpeed = 1 * (rand.nextFloat()-0.5f);
		
		this.setSize(getWidth()*rScale, getHeight()*rScale);
		this.setOriginCenter();
		driftVelocity = new Vector2(rX, rY);
	}
	
	@Override
	public void draw(Batch batch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	public void update(float delta){
		setPosition(getX()+driftVelocity.x,getY()+driftVelocity.y+MainGame.gameScreen.getScrollSpeed().y);
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
		
		if(getRadius() > 80) {
			Asteroid a1 = new Asteroid(crystal);
			a1.setSize(getWidth()/2, getHeight()/2);
			a1.setOriginCenter();
			a1.setPosition(getX()+(getWidth()/2), getY()+(getHeight()/2));
			MainGame.gameScreen.asteroids.add(a1);
			
			Asteroid a2 = new Asteroid(crystal);
			a2.setSize(getWidth()/2, getHeight()/2);
			a2.setOriginCenter();
			a2.setPosition(getX()+(getWidth()/2), getY()+(getHeight()/2));
			MainGame.gameScreen.asteroids.add(a2);
		}
		
		Smoke s1 = new Smoke(Smoke.SmokeType.DUST, 1f, 1f, 3f);
		s1.setPosition(getX()+(getWidth()/2)-(s1.getWidth()/2), getY()+(getHeight()/2)-(s1.getHeight()/2));
		MainGame.gameScreen.smoke.add(s1);
		
		Smoke s2 = new Smoke(Smoke.SmokeType.DUST, 1f, 1f, 3f);
		s2.setPosition(getX()+(getWidth()/2)-(s2.getWidth()/2), getY()+(getHeight()/2)-(s2.getHeight()/2));
		MainGame.gameScreen.smoke.add(s2);
		
		Smoke s3 = new Smoke(Smoke.SmokeType.DUST, 1f, 1f, 3f);
		s3.setPosition(getX()+(getWidth()/2)-(s3.getWidth()/2), getY()+(getHeight()/2)-(s3.getHeight()/2));
		MainGame.gameScreen.smoke.add(s3);
		
		if(crystal){
			Random rand = new Random();
			int ci = rand.nextInt(4 - 1 + 1) + 1;
			
			for(int i = 0; i < ci; i++){
				Crystal c = new Crystal();
				c.setPosition(getX()+(getWidth()/2)-(c.getWidth()/2), getY()+(getHeight()/2)-(c.getHeight()/2));
				MainGame.gameScreen.crystals.add(c);
			}
			
		}
	}

	public float getRadius() {
		return this.getHeight()/2;
	}
}
