package com.nashdevsoft.ld32jam.sprites;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;

public class Crystal extends Sprite {
	
	private Vector2 driftVelocity = new Vector2(0,0);
	private float rotationSpeed;
	public boolean dead = false;

	private static TextureRegion tex = new TextureRegion(Assets.manager.get(Assets.crystal));
	
	public Crystal(){
		super(new Sprite(tex));
		
		Random rand = new Random();
		float rX = 1.0f * (rand.nextFloat()-0.5f);
		float rY = -0.5f + (rand.nextFloat()-0.5f);
		float rScale = rand.nextFloat()*0.7f + 0.3f;
		rotationSpeed = 2 * (rand.nextFloat()-0.5f);
		
		this.setSize(getWidth()*rScale, getHeight()*rScale);
		this.setOriginCenter();
		driftVelocity = new Vector2(rX, rY + MainGame.gameScreen.getScrollSpeed().y);
	}
	
	public float getRadius() {
		return this.getHeight()/2;
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
	
	public void dispose(){
		if(MainGame.gameScreen.crystals.contains(this))
			MainGame.gameScreen.crystals.remove(this);
	}

}
