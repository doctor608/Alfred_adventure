package com.alfred.game.Sprites.TileObjects;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;

public class Coin extends InteractiveTileObject {

    private Sound coinsound;
    public Coin(PlayScreen screen, MapObject object) {
        super(screen, object);

        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.COIN_BIT);

        coinsound = AlfredMain.manager.get("audio/sounds/coin.wav", Sound.class);
    }

    @Override
    public void onHeadHit(Alfred alfred) {
        Gdx.app.log("Coin", "Collision");
        coinsound.setVolume(coinsound.play(), 0.1f);
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(10);
    }

    @Override
    public void onLegsHit(Alfred alfred) {
        Gdx.app.log("Coin", "Collision");
        coinsound.setVolume(coinsound.play(), 0.1f);
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(10);
    }

    @Override
    public void onBodyHit(Alfred alfred) {
        Gdx.app.log("Coin", "Collision");
        coinsound.setVolume(coinsound.play(), 0.1f);
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(10);
    }
}
