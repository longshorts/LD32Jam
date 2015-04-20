package com.nashdevsoft.ld32jam.sprites;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;

public class Missile extends Sprite {

	public boolean dead = false;

	private Vector2 driftVelocity = new Vector2(0,0);
	private static TextureRegion tex = new TextureRegion(Assets.manager.get(Assets.missile));
	
	public Missile(float parentVelocity){
		super(new Sprite(tex));
		
		driftVelocity.y = -1f + parentVelocity;
	}
	
	@Override
	public void draw(Batch batch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	public void update(float delta){
		setPosition(getX()+driftVelocity.x,getY()+driftVelocity.y+MainGame.gameScreen.getScrollSpeed().y);
		
		if(getY()<0-getHeight()-40){
			dead = true;
		}
	}
	
	public void destroy(){
		if(MainGame.gameScreen.missiles.contains(this))
			MainGame.gameScreen.missiles.remove(this);
		
		Smoke s2 = new Smoke(Smoke.SmokeType.SMOKE, 1f, 1f, 1f);
		s2.setPosition(getX()+(getWidth()/2)-(s2.getWidth()/2), getY()+(getHeight()/2)-(s2.getHeight()/2));
		MainGame.gameScreen.smoke.add(s2);
		
		Smoke s3 = new Smoke(Smoke.SmokeType.SMOKE, 1f, 1f, 1f);
		s3.setPosition(getX()+(getWidth()/2)-(s3.getWidth()/2), getY()+(getHeight()/2)-(s3.getHeight()/2));
		MainGame.gameScreen.smoke.add(s3);
		
		Smoke s4 = new Smoke(Smoke.SmokeType.SMOKE, 1f, 1f, 1f);
		s4.setPosition(getX()+(getWidth()/2)-(s4.getWidth()/2), getY()+(getHeight()/2)-(s4.getHeight()/2));
		MainGame.gameScreen.smoke.add(s4);
		
		Smoke s1 = new Smoke(Smoke.SmokeType.EXPLOSION, 1f, 1f, 0.2f);
		s1.setPosition(getX()+(getWidth()/2)-(s1.getWidth()/2), getY()+(getHeight()/2)-(s1.getHeight()/2));
		MainGame.gameScreen.smoke.add(s1);
		
		dead = true;
	}
	
	public float getRadius() {
		return ((this.getHeight()/2) + (this.getWidth()/2))/2;
	}
}
