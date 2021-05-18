package com.alfred.game.Sprites.TileObjects;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;

public class RedRose extends InteractiveTileObject{

    public RedRose(PlayScreen screen, MapObject object) {
        super(screen, object);

        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.REDROSE_BIT);
    }

    @Override
    public void onHeadHit(Alfred alfred) {
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);
        alfred.heal(25);
    }

    @Override
    public void onLegsHit(Alfred alfred) {
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);
        alfred.heal(25);
    }

    @Override
    public void onBodyHit(Alfred alfred) {
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);
        alfred.heal(25);
    }
}
