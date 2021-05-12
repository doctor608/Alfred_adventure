package com.alfred.game.Sprites;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class BrokenGround extends InteractiveTileObject{

    public BrokenGround(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.BROKENGROUND_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("BrokenGround", "Collision");
        //setCategoryFilter(AlfredMain.DESTROYED_BIT);
        //getCell().setTile(null);
    }

    @Override
    public void onLegsHit(){
        Gdx.app.log("BrokenGround", "Collision");
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);
    }
}