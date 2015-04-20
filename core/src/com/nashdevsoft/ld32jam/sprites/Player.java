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
	
	public float shieldHealth = 1.0f;
	public float shieldRegenRate = 0.05f;
	public int crystalScore = 0;
	
	private Sprite shield;
	private float shieldAlpha = 0f;
	
	//Game variables
	public boolean dead = false;
	public boolean boost = false;
	
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
		if(dead) return;
		
		float oldX = getX();
		float oldY = getY();
		
		if(boost){
			setX(getX() + (velocity.x * 1.75f) * delta);
			setY(getY() + (velocity.y * 1.75f) * delta);
		} else {
			setX(getX() + (velocity.x) * delta);
			setY(getY() + (velocity.y) * delta);
		}
		
		if(getX() < 0 || getX() > (900 - getWidth()))
			setX(oldX);
		if(getY() < 0 || getY() > (700 - getHeight()))
			setY(oldY);
		
		shield.setX((getX()+(getWidth()/2))-(shield.getWidth()/2));
		shield.setY((getY()+(getHeight()/2))-(shield.getHeight()/2));
		
		
		
		//Handle collision
		shieldAlpha -= 1.5f * delta;
		if(shieldAlpha < 0) shieldAlpha = 0f;
		
		for(Asteroid a : MainGame.gameScreen.asteroids){
			Vector2 aV = new Vector2(a.getX()+(a.getWidth()/2), a.getY()+(a.getHeight()/2));
			if(checkCollision(aV, a.getRadius())){
				shieldAlpha = 1f;
				Assets.manager.get(Assets.rocksmash_wav).play();
				
				a.dead = true;
				shieldHealth -= 0.1f;
				if(shieldHealth < 0) shieldHealth = 0;
			}
		}
		
		for(RedShip rs : MainGame.gameScreen.redShips){
			Vector2 aV = new Vector2(rs.getX()+(rs.getWidth()/2), rs.getY()+(rs.getHeight()/2));
			if(checkCollision(aV, rs.getRadius())){
				shieldAlpha = 1f;
				Assets.manager.get(Assets.shipdestroy_wav).play();
				
				rs.dead = true;
				shieldHealth -= 0.1f;
				if(shieldHealth < 0) shieldHealth = 0;
			}
		}
		
		for(Missile m : MainGame.gameScreen.missiles){
			Vector2 aV = new Vector2(m.getX()+(m.getWidth()/2), m.getY()+(m.getHeight()/2));
			if(checkCollision(aV, m.getRadius())){
				shieldAlpha = 1f;
				Assets.manager.get(Assets.shipdestroy_wav).play();
				
				m.dead = true;
				shieldHealth -= 0.1f;
				if(shieldHealth < 0) shieldHealth = 0;
			}
		}
		
		for(Crystal c : MainGame.gameScreen.crystals){
			Vector2 cV = new Vector2(c.getX()+(c.getWidth()/2), c.getY()+(c.getHeight()/2));
			if(checkCollision(cV, c.getRadius())){
				crystalScore++;
				Assets.manager.get(Assets.gempickup_wav).play();
				c.dead = true;
				Gdx.app.debug("PLAYER", "Crystal collected");
			}
		}
		
		shield.setAlpha(shieldAlpha);
		
		if(shieldHealth <= 0) {
			dead = true;
			destroy();
		}
		
		if(!dead && !boost) shieldHealth += shieldRegenRate*delta;
		if(shieldHealth > 1.0f) shieldHealth = 1.0f;
	}
	
	public boolean checkCollision(Vector2 pos, float radius){
		Vector2 pV = new Vector2(getX()+(getWidth()/2), getY()+(getHeight()/2));
		return(pV.dst(pos) < (getRadius() + radius));
	}
	
	public void destroy(){
		MainGame.inputMultiplexer.removeProcessor(this);
		this.setAlpha(0);
		shield.setAlpha(0);
		Assets.manager.get(Assets.shipdestroy_wav).play();
		
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
		
		dead = true;
		MainGame.gameScreen.uiGameOver.active = true;
		MainGame.inputMultiplexer.addProcessor(MainGame.gameScreen.uiGameOver);
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
		case Keys.SHIFT_LEFT:
		case Keys.SHIFT_RIGHT:
			boost = true;
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
		case Keys.SHIFT_LEFT:
		case Keys.SHIFT_RIGHT:
			boost = false;
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
