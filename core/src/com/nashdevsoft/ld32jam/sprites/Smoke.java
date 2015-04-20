package com.nashdevsoft.ld32jam.sprites;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;

public class Smoke extends Sprite {
	
	public enum SmokeType {DUST, SMOKE, EXPLOSION};
	
	private float timeAlive = 0f;
	private float lifeSpan = 1f;
	private float endScale = 1f;
	
	public boolean dead = false;
	public boolean drift = true;
	
	private Vector2 driftVelocity = new Vector2(0,0);

	private static TextureRegion smoketex = new TextureRegion(Assets.manager.get(Assets.smoke));
	private static TextureRegion dusttex = new TextureRegion(Assets.manager.get(Assets.dust));
	private static TextureRegion explosiontex = new TextureRegion(Assets.manager.get(Assets.explosion));
	
	public Smoke(SmokeType type, float startScale, float endScale, float lifeSpan){
		super(new Sprite(smoketex));
		this.setBounds(0, 0, getWidth()*startScale, getHeight()*startScale);
		setPosition(450,350);
		this.lifeSpan = lifeSpan;
		
		switch(type){
		case SMOKE:
			setRegion(smoketex);
			break;
		case DUST:
			setRegion(dusttex);
			break;
		case EXPLOSION:
			setRegion(explosiontex);
			break;
		}
		
		//Setup drift velocity
		Random rand = new Random();
		float rX = 2.0f * (rand.nextFloat()-0.5f);
		float rY = -0.5f + (rand.nextFloat()-0.5f);
		//float rScale = rand.nextFloat()*0.7f + 0.3f;
		
		//this.setSize(getWidth()*rScale, getHeight()*rScale);
		//this.setOriginCenter();
		driftVelocity = new Vector2(rX, rY + MainGame.gameScreen.getScrollSpeed().y);
	}
	
	@Override
	public void draw(Batch batch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
		
	}
	
	private void update(float delta){
		timeAlive += delta;
		if(timeAlive >= lifeSpan) dead = true;
		else{
			this.setAlpha((lifeSpan-timeAlive)/lifeSpan);
		}
		
		setPosition(getX()+driftVelocity.x,getY()+driftVelocity.y);
		
		if(getY()<0-getHeight()){
			dead = true;
		}
	}
	
	public void destroy(){
		if(MainGame.gameScreen.smoke.contains(this))
			MainGame.gameScreen.smoke.remove(this);
	}
}
