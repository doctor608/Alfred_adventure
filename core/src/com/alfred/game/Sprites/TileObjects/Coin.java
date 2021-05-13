package com.alfred.game.Sprites.TileObjects;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class Coin extends InteractiveTileObject {

    public Coin(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);

        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin", "Collision");
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(10);
    }

    @Override
    public void onLegsHit() {
        Gdx.app.log("Coin", "Collision");
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(10);
    }
}