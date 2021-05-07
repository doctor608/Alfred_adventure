package com.alfred.game.Sprites;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Scaffold extends InteractiveTileObject{

    public Scaffold(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.SCAFFOLD_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Scaffold", "Collision");
        //setCategoryFilter(AlfredMain.DESTROYED_BIT);
        //getCell().setTile(null);
    }

    @Override
    public void onLegsHit(){
        Gdx.app.log("Scaffold", "Collision");
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);
    }
}
