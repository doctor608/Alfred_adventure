package com.alfred.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.alfred.game.Screens.PlayScreen;

public class AlfredMain extends Game {
	public SpriteBatch batch;

	public static final int vir_width = 620;
	public static final int vir_height = 320;
	public static final float PPM = 100;

	public static final short GROUND_BIT = 1;
	public static final short ALFRED_BIT = 2;
	public static final short BADGROUND_BIT = 4;
	public static final short BROKENGROUND_BIT = 8;
	public static final short COIN_BIT = 16;
	public static final short DESTROYED_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short OBJECT_BIT = 128;

	@Override
	public void create () {
		batch = new SpriteBatch();

		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}
}