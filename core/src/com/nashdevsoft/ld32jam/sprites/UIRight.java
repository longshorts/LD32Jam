package com.nashdevsoft.ld32jam.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;

public class UIRight extends Sprite {
	
	private ShapeRenderer shapes;
	private static TextureRegion tex = new TextureRegion(Assets.manager.get(Assets.uiRight));

	private Skin labelSkin;
	private Label scoreLabel;
	private BitmapFont font = Assets.manager.get(Assets.fontLarge);
	
	public UIRight(){
		super(new Sprite(tex));
		setPosition(890-tex.getRegionWidth(), 10);
		
		shapes = new ShapeRenderer();
		
		labelSkin = new Skin();
		labelSkin.add("default", new LabelStyle(font, Color.WHITE));
		scoreLabel = new Label("0", labelSkin);
		scoreLabel.setBounds(708, 18, 139, 35);
		scoreLabel.setAlignment(Align.left);
	}
	
	@Override
	public void draw(Batch batch){
		super.draw(batch);
		
		scoreLabel.setText("" + ((int)MainGame.gameScreen.distance));
		scoreLabel.draw(batch, this.getColor().a);
	}
	
	public void drawShapes(){
		shapes.begin(ShapeType.Filled);
		shapes.setColor(Color.CYAN);
		
		float difficultyPer = MainGame.gameScreen.difficulty / 20;
		if(difficultyPer > 1) difficultyPer = 1f;
		
		shapes.rect(862, 6+getY(), 22, 243*difficultyPer);
		shapes.end();
	}
	
	
}
