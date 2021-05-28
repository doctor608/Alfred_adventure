package com.alfred.game.Sprites.TileObjects;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.alfred.game.Sprites.Items.BlackRose;
import com.alfred.game.Sprites.Items.ItemDef;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.math.MathUtils.random;

public class DemonicGround extends InteractiveTileObject{

    boolean notUsed = true;

    public DemonicGround(PlayScreen screen, MapObject object) {
        super(screen, object);

        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.DEMONICGROUND_BIT);
    }
    @Override
    public void onHeadHit(Alfred alfred) {
        int rand = random.nextInt(4) + 1;
        if (rand == 1) {
            screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 32 / AlfredMain.PPM), BlackRose.class));
            setCategoryFilter(AlfredMain.DESTROYED_BIT);
            getCell().setTile(null);
        }

    }

    @Override
    public void onLegsHit(Alfred alfred) {
        int rand = random.nextInt(4) + 1;
        if (rand == 1) {
            screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 32 / AlfredMain.PPM), BlackRose.class));
            setCategoryFilter(AlfredMain.DESTROYED_BIT);
            getCell().setTile(null);
        }

    }

    @Override
    public void onBodyHit(Alfred alfred) {
        /*
        if (object.getProperties().containsKey("blackrose")) {
            screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 32 / AlfredMain.PPM), BlackRose.class));
        }
        setCategoryFilter(AlfredMain.DESTROYED_BIT);
        getCell().setTile(null);*/
    }
}
