package com.alfred.game.Sprites.TileObjects;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;

public class BrokenGround extends InteractiveTileObject {

    public BrokenGround(PlayScreen screen, MapObject object) {
        super(screen, object);
        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.BROKENGROUND_BIT);
    }

    @Override
    public void onHeadHit(Alfred alfred) {
        Gdx.app.log("BrokenGround", "Collision");
        //setCategoryFilter(AlfredMain.DESTROYED_BIT);
        //getCell().setTile(null);
    }

    @Override
    public void onLegsHit(Alfred alfred){
        Gdx.app.log("BrokenGround", "Collision");
        //setCategoryFilter(AlfredMain.DESTROYED_BIT);
        //getCell().setTile(null);
    }

    @Override
    public void onBodyHit(Alfred alfred) {
        Gdx.app.log("BrokenGround", "Collision");
    }
}