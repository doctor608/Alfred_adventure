package com.alfred.game.Sprites;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BadGround extends InteractiveTileObject{

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
        Gdx.app.log("BadGround", "Collision");
        getCell().setTile(tileSet.getTile(BLANK_BROKENGROUND));
    }
}
