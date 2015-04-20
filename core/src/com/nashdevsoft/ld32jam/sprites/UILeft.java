package com.nashdevsoft.ld32jam.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.nashdevsoft.ld32jam.MainGame;
import com.nashdevsoft.ld32jam.assets.Assets;

public class UILeft extends Sprite {

	private ShapeRenderer shapes;
	private float scale = 0.7f;
	private static TextureRegion tex = new TextureRegion(Assets.manager.get(Assets.uiLeft));
	
	private Skin labelSkin;
	private Label crystalScoreLabel;
	private BitmapFont font = Assets.manager.get(Assets.fontLarge);
	
	public UILeft(){
		super(new Sprite(tex));
		setPosition(10,10);
		
		shapes = new ShapeRenderer();
		
		labelSkin = new Skin();
		labelSkin.add("default", new LabelStyle(font, Color.WHITE));
		crystalScoreLabel = new Label("0", labelSkin);
		crystalScoreLabel.setBounds(53, 18, 139, 35);
		crystalScoreLabel.setAlignment(Align.right);
	}
	
	@Override
	public void draw(Batch batch){
		super.draw(batch);
		
		crystalScoreLabel.setText("" + MainGame.gameScreen.player.crystalScore);
		crystalScoreLabel.draw(batch, this.getColor().a);
	}
	
	public void drawShapes(){
		shapes.begin(ShapeType.Filled);
		if(MainGame.gameScreen.player.shieldHealth > 0.2f)
			shapes.setColor(Color.CYAN);
		else
			shapes.setColor(Color.RED);
		shapes.rect(6+getX(), 6+getY(), 22, 243*MainGame.gameScreen.player.shieldHealth);
		shapes.end();
	}
}
