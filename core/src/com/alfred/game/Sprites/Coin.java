package com.alfred.game.Sprites;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Coin extends InteractiveTileObject {

    public Coin(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);

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
