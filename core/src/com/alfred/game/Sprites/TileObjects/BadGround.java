package com.alfred.game.Sprites.TileObjects;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;

public class BadGround extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_BROKENGROUND = 8;
    public BadGround(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);

        tileSet = map.getTileSets().getTileSet("set");
        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.BADGROUND_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("BadGround", "Collision");
        //setCategoryFilter(AlfredMain.DESTROYED_BIT);
        //getCell().setTile(null);
    }

    @Override
    public void onLegsHit(){
        if (getCell().getTile().getId() == BLANK_BROKENGROUND) {
            getCell().setTile(null);
        } else {
            getCell().setTile(tileSet.getTile(BLANK_BROKENGROUND));
        }
    }
}
