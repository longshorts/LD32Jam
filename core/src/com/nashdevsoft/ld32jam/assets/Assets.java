package com.nashdevsoft.ld32jam.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
public static final AssetManager manager = new AssetManager();
	
	//Images
	public static final AssetDescriptor<Texture> spacebg = 
			new AssetDescriptor<Texture>("backgrounds/space.png", Texture.class);
	public static final AssetDescriptor<Texture> spacedust = 
			new AssetDescriptor<Texture>("backgrounds/spacedust.png", Texture.class);
	public static final AssetDescriptor<Texture> heroship = 
			new AssetDescriptor<Texture>("img/heroshipsmall.png", Texture.class);
	public static final AssetDescriptor<Texture> heroshipLarge = 
			new AssetDescriptor<Texture>("img/heroship.png", Texture.class);
	public static final AssetDescriptor<Texture> asteroid = 
			new AssetDescriptor<Texture>("img/asteroid.png", Texture.class);
	public static final AssetDescriptor<Texture> crystalasteroid = 
			new AssetDescriptor<Texture>("img/crystalasteroid.png", Texture.class);
	public static final AssetDescriptor<Texture> redship = 
			new AssetDescriptor<Texture>("img/redship.png", Texture.class);
	public static final AssetDescriptor<Texture> missile = 
			new AssetDescriptor<Texture>("img/missile.png", Texture.class);
	public static final AssetDescriptor<Texture> smoke = 
			new AssetDescriptor<Texture>("img/smoke2.png", Texture.class);
	public static final AssetDescriptor<Texture> shield = 
			new AssetDescriptor<Texture>("img/shield.png", Texture.class);
	public static final AssetDescriptor<Texture> crystal = 
			new AssetDescriptor<Texture>("img/crystal.png", Texture.class);
	public static final AssetDescriptor<Texture> dust = 
			new AssetDescriptor<Texture>("img/dust.png", Texture.class);
	public static final AssetDescriptor<Texture> explosion = 
			new AssetDescriptor<Texture>("img/explosion.png", Texture.class);
	public static final AssetDescriptor<Texture> menuscreen = 
			new AssetDescriptor<Texture>("img/menuscreen.png", Texture.class);
	public static final AssetDescriptor<Texture> gameover = 
			new AssetDescriptor<Texture>("img/gameoverscreen.png", Texture.class);
	
	//Packs
	public static final AssetDescriptor<BitmapFont> fontLarge =
			new AssetDescriptor<BitmapFont>("fonts/irislarge.fnt", BitmapFont.class);
	
	//Skins
	public static final AssetDescriptor<Texture> uiLeft = 
			new AssetDescriptor<Texture>("img/uileft.png", Texture.class);
	public static final AssetDescriptor<Texture> uiRight = 
			new AssetDescriptor<Texture>("img/uiright.png", Texture.class);
	
	//Sounds
	public static final AssetDescriptor<Sound> gempickup_wav =
			new AssetDescriptor<Sound>("sounds/gempickup.wav", Sound.class);
	public static final AssetDescriptor<Sound> rocksmash_wav =
			new AssetDescriptor<Sound>("sounds/rocksmash.wav", Sound.class);
	public static final AssetDescriptor<Sound> select_wav =
			new AssetDescriptor<Sound>("sounds/select.wav", Sound.class);
	public static final AssetDescriptor<Sound> shipdestroy_wav =
			new AssetDescriptor<Sound>("sounds/shipdestroy.wav", Sound.class);
	
	public static void load(){
		manager.load(spacebg);
		manager.load(spacedust);
		manager.load(heroship);
		manager.load(heroshipLarge);
		manager.load(asteroid);
		manager.load(crystalasteroid);
		manager.load(crystal);
		manager.load(dust);
		manager.load(explosion);
		manager.load(redship);
		manager.load(missile);
		manager.load(smoke);
		manager.load(shield);
		manager.load(uiLeft);
		manager.load(uiRight);
		manager.load(fontLarge);
		manager.load(gameover);
		manager.load(menuscreen);
		
		manager.load(gempickup_wav);
		manager.load(rocksmash_wav);
		manager.load(select_wav);
		manager.load(shipdestroy_wav);
	}
	
	public static void dispose(){
		manager.dispose();
	}
}
