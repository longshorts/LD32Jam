package com.nashdevsoft.ld32jam.sprites;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;

public class RedShip extends Sprite {
	
	public boolean dead = false;
	
	private int missiles = 2;

	private Vector2 driftVelocity = new Vector2(0,0);
	private static TextureRegion tex = new TextureRegion(Assets.manager.get(Assets.redship));

	public RedShip(){
		super(new Sprite(tex));
		
		Random rand = new Random();
		float rY = -0.5f + (rand.nextFloat()-0.8f);
		driftVelocity = new Vector2(0, rY);
	}
	
	@Override
	public void draw(Batch batch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	public void update(float delta){
		setPosition(getX()+driftVelocity.x,getY()+driftVelocity.y+MainGame.gameScreen.getScrollSpeed().y);
		
		Random rand = new Random();
		if(missiles > 0 && rand.nextFloat() > 0.995f){
			Missile m = new Missile(driftVelocity.y);
			m.setPosition(getX()+(getWidth()/2)-(m.getWidth()/2), getY()+(getHeight()/2)-(m.getHeight()/2));
			MainGame.gameScreen.missiles.add(m);
			missiles--;
		}
		
		if(getY()<0-getHeight()-40){
			dead = true;
		}
	}
	
	public void spawn(){
		Random rand = new Random();
		
		float rX = 900 * rand.nextFloat();
		setPosition(rX, 1000);
		
	}
	
	public void destroy(){
		if(MainGame.gameScreen.redShips.contains(this))
			MainGame.gameScreen.redShips.remove(this);
		
		Smoke s2 = new Smoke(Smoke.SmokeType.SMOKE, 1f, 1f, 3f);
		s2.setPosition(getX()+(getWidth()/2)-(s2.getWidth()/2), getY()+(getHeight()/2)-(s2.getHeight()/2));
		MainGame.gameScreen.smoke.add(s2);
		
		Smoke s3 = new Smoke(Smoke.SmokeType.SMOKE, 1f, 1f, 3f);
		s3.setPosition(getX()+(getWidth()/2)-(s3.getWidth()/2), getY()+(getHeight()/2)-(s3.getHeight()/2));
		MainGame.gameScreen.smoke.add(s3);
		
		Smoke s4 = new Smoke(Smoke.SmokeType.SMOKE, 1f, 1f, 3f);
		s4.setPosition(getX()+(getWidth()/2)-(s4.getWidth()/2), getY()+(getHeight()/2)-(s4.getHeight()/2));
		MainGame.gameScreen.smoke.add(s4);
		
		Smoke s1 = new Smoke(Smoke.SmokeType.EXPLOSION, 1f, 1f, 0.5f);
		s1.setPosition(getX()+(getWidth()/2)-(s1.getWidth()/2), getY()+(getHeight()/2)-(s1.getHeight()/2));
		MainGame.gameScreen.smoke.add(s1);
		
		Random rand = new Random();
		int ci = rand.nextInt(3 - 2 + 1) + 2;
		
		for(int i = 0; i < ci; i++){
			Crystal c = new Crystal();
			c.setPosition(getX()+(getWidth()/2)-(c.getWidth()/2), getY()+(getHeight()/2)-(c.getHeight()/2));
			MainGame.gameScreen.crystals.add(c);
		}
		
		dead = true;
	}
	
	public float getRadius() {
		return ((this.getHeight()/2) + (this.getWidth()/2))/2;
	}
}
