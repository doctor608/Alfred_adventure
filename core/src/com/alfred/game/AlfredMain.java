package com.alfred.game;

import com.alfred.game.Screens.MenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.alfred.game.Screens.PlayScreen;

public class AlfredMain extends Game {
	public SpriteBatch batch;
	public static AssetManager manager;

	public static final int vir_width = 480;
	public static final int vir_height = 256;
	public static final float PPM = 100;

	public static final short NOTHING_BIT = 0;
	public static final short ARROW_BIT = 1;
	public static final short ALFRED_BIT = 2;
	public static final short BADGROUND_BIT = 4;
	public static final short BROKENGROUND_BIT = 8;
	public static final short COIN_BIT = 16;
	public static final short DESTROYED_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short OBJECT_BIT = 128;
	public static final short ENEMYHEAD_BIT = 256;
	public static final short DEMONICGROUND_BIT = 512;
	public static final short ITEM_BIT = 1024;
	public static final short ALFRED_HEAD_BIT = 2048;
	public static final short ALFRED_LEGS_BIT = 4096;
	public static final short REDGROUND_BIT = 8192;
	public static final short DROYERBULLET_BIT = 16384;
	public static final short REDROSE_BIT = -1;
	public static final short BLACKRAVEN_BIT = -2;
	//public static final short ENEMYPERED_BIT = -4;
	public static final short ENEMYZAD_BIT = -4;
	//public static final short ARROW_BIT = -8;

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/music/classic_music.ogg", Music.class);

		manager.load("audio/sounds/droyer.wav", Sound.class);
		manager.load("audio/sounds/deathkill.wav", Sound.class);
		manager.load("audio/sounds/knightkill.wav", Sound.class);
		manager.load("audio/sounds/bowandarrow.wav", Sound.class);
		manager.load("audio/sounds/splash.wav", Sound.class);
		manager.load("audio/sounds/coin.wav", Sound.class);
		manager.load("audio/sounds/blackraven.wav", Sound.class);
		manager.load("audio/sounds/knightdie.wav", Sound.class);
		//manager.load("audio/sounds/badground.wav", Sound.class);

		manager.finishLoading();

		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
}