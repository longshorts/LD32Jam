package com.nashdevsoft.ld32jam.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;

public class Player extends Sprite implements InputProcessor{
	
	private Sprite shield;
	private float shieldAlpha = 0f;
	
	//Game variables
	public boolean dead = false;
	
	//Movement
	private Vector2 velocity = new Vector2();
	public final float speed = 160f;
	
	private static TextureRegion tex = new TextureRegion(Assets.manager.get(Assets.heroship));
	private static TextureRegion shieldTex = new TextureRegion(Assets.manager.get(Assets.shield));
	
	public Player(){
		super(new Sprite(tex));
		MainGame.inputMultiplexer.addProcessor(this);
		shield = new Sprite(shieldTex);
		this.setOriginCenter();
		this.setPosition(450, 100);
		shield.setBounds(getX(), getY(), getWidth()+10, getWidth()+10);
		shield.setOriginCenter();
	}
	
	@Override
	public void draw(Batch batch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
		shield.draw(batch);
	}
	
	public void update(float delta){
		setX(getX() + velocity.x * delta);
		setY(getY() + velocity.y * delta);
		shield.setX((getX()+(getWidth()/2))-(shield.getWidth()/2));
		shield.setY((getY()+(getHeight()/2))-(shield.getHeight()/2));
		
		//Handle collision
		shieldAlpha -= 1.5f * delta;
		if(shieldAlpha < 0) shieldAlpha = 0f;
		
		for(Asteroid a : MainGame.gameScreen.asteroids){
			Vector2 pV = new Vector2(getX()+(getWidth()/2), getY()+(getHeight()/2));
			Vector2 aV = new Vector2(a.getX()+(a.getWidth()/2), a.getY()+(a.getHeight()/2));
			if(pV.dst(aV) < (getRadius() + a.getRadius())){
				Gdx.app.debug("COLLISION", "" + a.getRadius());
				shieldAlpha = 1f;
				
				//workout shield angle
				shield.setRotation(pV.angle(aV));
				
				a.dead = true;
			}
		}
		
		shield.setAlpha(shieldAlpha);
	}
	
	/**Input Handling**/
	private int moveLR = 0;
	private int moveUD = 0;

	@Override
	public boolean keyDown(int keycode) {
		if(dead) return false;
		switch(keycode){
		case Keys.LEFT:
			velocity.x = -speed;
			moveLR++;
			break;
		case Keys.RIGHT:
			velocity.x = speed;
			moveLR++;
			break;
		case Keys.UP:
			velocity.y = speed;
			moveUD++;
			break;
		case Keys.DOWN:
			velocity.y = -speed;
			moveUD++;
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(dead) return false;
		switch(keycode){
		case Keys.LEFT:
			moveLR--;
			if(moveLR <= 0)
				velocity.x = 0;
			else
				velocity.x = speed;
			break;
		case Keys.RIGHT:
			moveLR--;
			if(moveLR <= 0)
				velocity.x = 0;
			else
				velocity.x = -speed;
			break;
		case Keys.UP:
			moveUD--;
			if(moveUD <= 0)
				velocity.y = 0;
			else
				velocity.y = -speed;
			break;
		case Keys.DOWN:
			moveUD--;
			if(moveUD <= 0)
				velocity.y = 0;
			else
				velocity.y = speed;
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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
	
	public float getRadius() {
		return shield.getHeight()/2;
	}
}
