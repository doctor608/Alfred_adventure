package com.alfred.game.Sprites.TileObjects;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;

public class Finish extends InteractiveTileObject {

    private Sound finishsound;
    public Finish(PlayScreen screen, MapObject object) {
        super(screen, object);

        fixture.setUserData(this);

        setCategoryFilter(AlfredMain.FINISH_BIT);

        finishsound = AlfredMain.manager.get("audio/sounds/finish.wav", Sound.class);
    }

    @Override
    public void onHeadHit(Alfred alfred) {
        finishsound.setVolume(finishsound.play(), 0.3f);
        Hud.addScore(5);
    }

    @Override
    public void onLegsHit(Alfred alfred) {
        finishsound.setVolume(finishsound.play(), 0.3f);
        Hud.addScore(5);
    }

    @Override
    public void onBodyHit(Alfred alfred) {
        finishsound.setVolume(finishsound.play(), 0.3f);
        Hud.addScore(5);
    }
}