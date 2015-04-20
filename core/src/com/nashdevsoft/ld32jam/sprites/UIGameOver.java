package com.nashdevsoft.ld32jam.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;
import com.nashdevsoft.ld32jam.screens.GameScreen;

public class UIGameOver extends Sprite implements InputProcessor {

	public boolean active = false;
	
	private static TextureRegion tex = new TextureRegion(Assets.manager.get(Assets.gameover));
	
	private Skin labelSkin;
	private Label scoreLabel;
	private Label difficultyLabel;
	private Label crystalsLabel;
	private Label finalScoreLabel;
	private BitmapFont font = Assets.manager.get(Assets.fontLarge);
	
	public UIGameOver(){
		super(new Sprite(tex));
		setPosition(450-(tex.getRegionWidth()/2), 350-(tex.getRegionHeight()/2));
		
		labelSkin = new Skin();
		labelSkin.add("default", new LabelStyle(font, Color.WHITE));
		
		scoreLabel = new Label("0", labelSkin);
		scoreLabel.setBounds(getX()+145, getY()+tex.getRegionHeight()-132, 250, 40);
		scoreLabel.setAlignment(Align.left);
		
		difficultyLabel = new Label("0", labelSkin);
		difficultyLabel.setBounds(getX()+145, getY()+tex.getRegionHeight()-186, 250, 40);
		difficultyLabel.setAlignment(Align.left);
		
		crystalsLabel = new Label("0", labelSkin);
		crystalsLabel.setBounds(getX()+145, getY()+tex.getRegionHeight()-240, 250, 40);
		crystalsLabel.setAlignment(Align.left);
		
		finalScoreLabel = new Label("0", labelSkin);
		finalScoreLabel.setBounds(getX(), getY()+tex.getRegionHeight()-300, 495, 40);
		finalScoreLabel.setAlignment(Align.center);
	}
	
	@Override
	public void draw(Batch batch){
		if(!active) return;
		
		super.draw(batch);
		
		scoreLabel.setText("" + ((int)MainGame.gameScreen.distance));
		scoreLabel.draw(batch, this.getColor().a);
		
		difficultyLabel.setText("" + ((int)MainGame.gameScreen.difficulty));
		difficultyLabel.draw(batch, this.getColor().a);
		
		crystalsLabel.setText("" + ((int)MainGame.gameScreen.player.crystalScore) + " X " + ((int)MainGame.gameScreen.difficulty));
		crystalsLabel.draw(batch, this.getColor().a);
		
		finalScoreLabel.setText("FINAL SCORE: " +
		(((int)MainGame.gameScreen.distance) + 
		((int)MainGame.gameScreen.difficulty) +
		(((int)MainGame.gameScreen.player.crystalScore)*((int)MainGame.gameScreen.difficulty))));
		finalScoreLabel.draw(batch, this.getColor().a);
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if(button == Buttons.LEFT){
			if(700-screenY > getY()+16 &&
					700-screenY < getY()+76){
				if(screenX > getX()+16 && screenX < getX()+165)
					Gdx.app.exit();
				else if(screenX > getX()+173 && screenX < getX()+321){
					Assets.manager.get(Assets.select_wav).play();
					MainGame.inputMultiplexer.removeProcessor(this);
					MainGame.gameScreen.dispose();
					MainGame.gameScreen.mainGame.retry();
				}
				else if(screenX > getX()+330 && screenX < getX()+478){
					Assets.manager.get(Assets.select_wav).play();
					MainGame.inputMultiplexer.removeProcessor(this);
					MainGame.gameScreen.dispose();
					MainGame.gameScreen.mainGame.menu();
				}
			}
		}
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
}
