package com.alfred.game.Sprites.Enemies;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Droyer extends Enemy{

    private float stateTime;

    private TextureRegion droyerStay;
    //private Array<TextureRegion> frames;

    private boolean setToDestroy;
    private boolean destroyed;
    private boolean setToKill;
    private boolean alfredKilled;

    public Droyer(PlayScreen screen, float x, float y) {
        super(screen, x, y);

        droyerStay = new TextureRegion(screen.getAtlas().findRegion("droyer"), 0, 0, 32, 32);

        stateTime = 0;
        setBounds(getX(), getY(), 32 / AlfredMain.PPM, 32 / AlfredMain.PPM);

        setToDestroy = false;
        destroyed = false;
        // setToKill = false;
        // alfredKilled = false;
    }

    @Override
    protected void defineEnemy() {

    }

    @Override
    public void hitOnHead() {

    }

    @Override
    public void killAlfred() {

    }

    @Override
    public void update(float dt) {
        stateTime += dt;

        //TextureRegion region = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);

        if (setToKill && !alfredKilled) {
            Gdx.app.log("ALFRED", "KILLED");
            world.destroyBody(b2body);
            alfredKilled = true;
            //region = new TextureRegion(screen.getAtlas().findRegion("knight"), 142, 0, 34, 32);
            stateTime = 0;
        }

        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            setRegion(new TextureRegion(screen.getAtlas().findRegion("droyer"), 32, 0, 32, 32));
            stateTime = 0;
            Hud.addScore(10);
        }
        else if(!destroyed && !alfredKilled) {
            bulletsUpdate(dt);
        }

    }
}
















