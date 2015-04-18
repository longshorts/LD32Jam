package com.nashdevsoft.ld32jam.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
public static final AssetManager manager = new AssetManager();
	
	//Images
	public static final AssetDescriptor<Texture> spacebg = 
			new AssetDescriptor<Texture>("backgrounds/space.png", Texture.class);
	public static final AssetDescriptor<Texture> heroship = 
			new AssetDescriptor<Texture>("img/heroshipsmall.png", Texture.class);
	public static final AssetDescriptor<Texture> asteroid = 
			new AssetDescriptor<Texture>("img/asteroid.png", Texture.class);
	public static final AssetDescriptor<Texture> redship = 
			new AssetDescriptor<Texture>("img/redship.png", Texture.class);
	public static final AssetDescriptor<Texture> missile = 
			new AssetDescriptor<Texture>("img/missile.png", Texture.class);
	public static final AssetDescriptor<Texture> smoke = 
			new AssetDescriptor<Texture>("img/smoke.png", Texture.class);
	public static final AssetDescriptor<Texture> shield = 
			new AssetDescriptor<Texture>("img/shield.png", Texture.class);
	//Packs
	
	//Skins
	
	//NinePatchDrawable
	
	public static void load(){
		manager.load(spacebg);
		manager.load(heroship);
		manager.load(asteroid);
		manager.load(redship);
		manager.load(missile);
		manager.load(smoke);
		manager.load(shield);
	}
	
	public static void dispose(){
		manager.dispose();
	}
}
