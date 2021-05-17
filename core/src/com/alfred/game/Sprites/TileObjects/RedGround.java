package com.alfred.game.Sprites.TileObjects;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;

public class RedGround extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_REDGROUND = 10;
    private final int BLANK_DEMONICGROUND = 9;

    public RedGround(PlayScreen screen, MapObject object) {
        super(screen, object);

        tileSet = map.getTileSets().getTileSet("set");
        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.REDGROUND_BIT);
    }

    @Override
    public void onHeadHit(Alfred alfred) {
        //Gdx.app.log("BadGround", "Collision");
        //setCategoryFilter(AlfredMain.DESTROYED_BIT);
        //getCell().setTile(null);
    }

    @Override
    public void onLegsHit(Alfred alfred){
        if (alfred.isBlack()) {
            getCell().setTile(tileSet.getTile(9));
        }

    }

    @Override
    public void onBodyHit(Alfred alfred) {

    }


}
