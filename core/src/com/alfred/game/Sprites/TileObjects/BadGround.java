package com.alfred.game.Sprites.TileObjects;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;

public class BadGround extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_BADGROUND = 7;
    private final int BLANK_BROKENGROUND = 8;
    public BadGround(PlayScreen screen, MapObject object) {
        super(screen, object);

        tileSet = map.getTileSets().getTileSet("set");
        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.BADGROUND_BIT);
    }

    @Override
    public void onHeadHit(Alfred alfred) {
        Gdx.app.log("BadGround", "Collision");
        //setCategoryFilter(AlfredMain.DESTROYED_BIT);
        //getCell().setTile(null);
    }

    @Override
    public void onLegsHit(Alfred alfred){
        if (getCell().getTile().getId() == BLANK_BROKENGROUND) {
            setCategoryFilter(AlfredMain.DESTROYED_BIT);
            getCell().setTile(null);
        } else if (getCell().getTile().getId() == BLANK_BADGROUND){
            getCell().setTile(tileSet.getTile(BLANK_BROKENGROUND));
        }
    }

    @Override
    public void onBodyHit(Alfred alfred) {
        Gdx.app.log("BadGround", "Collision");
    }


}
