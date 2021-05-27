package com.alfred.game.Sprites.Enemies;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.alfred.game.Sprites.Enemies.Enemy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;

public class Knight extends Enemy {

    private float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;

    private boolean setToDestroy;
    private boolean destroyed;
    private boolean setToKill;
    private boolean alfredKilled;

    private boolean runningRight;
    public int enemyHp = 25;

    private Sound knightkillsound;
    private Sound knightdiesound;

    public Knight(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<>();
        for (int i = 0; i < 2; ++i) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("knight"), i * 32, 0, 32, 32));
        }

        walkAnimation = new Animation(0.4f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 32 / AlfredMain.PPM, 32 / AlfredMain.PPM);

        setToDestroy = false;
        destroyed = false;
        setToKill = false;
        alfredKilled = false;
        runningRight = false;

        knightkillsound = AlfredMain.manager.get("audio/sounds/knightkill.wav", Sound.class);
        knightdiesound = AlfredMain.manager.get("audio/sounds/knightdie.wav", Sound.class);
    }

    public void update(float dt) {
        stateTime += dt;

        TextureRegion region = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);

        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        /*
        if (enemyHp <= 0) {
            setToDestroy = true;
        } */

        if (setToKill && !alfredKilled) {
            world.destroyBody(b2body);
            alfredKilled = true;
            destroyed = true;
            knightkillsound.setVolume(knightkillsound.play(), 0.1f);
            region = new TextureRegion(screen.getAtlas().findRegion("knight"), 142, 0, 34, 32);
            if (runningRight == true) {
                region.flip(true, false);
                setRegion(region);
            } else {
                setRegion(region);
            }
            stateTime = 0;
        } else if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            knightdiesound.setVolume(knightdiesound.play(), 0.3f);
            setRegion(new TextureRegion(screen.getAtlas().findRegion("knight"), 74, 0, 34, 32));
            stateTime = 0;
            Hud.addScore(5);
        } else if(!destroyed) {
            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion(walkAnimation.getKeyFrame(stateTime, true));
        }

    }

    public void getHit(int damage) {
        enemyHp = enemyHp - damage;
        setToDestroy = true;
    }

    public void killAlfred(Alfred alfred) {
        setToKill = true;
        alfred.hit(25, "Alfred was slain by a gray knight");
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / AlfredMain.PPM);

        fdef.filter.categoryBits = AlfredMain.ENEMY_BIT;
        fdef.filter.maskBits = /*AlfredMain.GROUND_BIT | */AlfredMain.ALFRED_BIT
                | AlfredMain.BADGROUND_BIT | AlfredMain.BROKENGROUND_BIT
                | AlfredMain.COIN_BIT | AlfredMain.OBJECT_BIT |AlfredMain.ENEMY_BIT | AlfredMain.ARROW_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        /*
        vertice[0] = new Vector2(-10, 16).scl(1 / AlfredMain.PPM);
        vertice[1] = new Vector2(10, 16).scl(1 / AlfredMain.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / AlfredMain.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / AlfredMain.PPM);
        */
        vertice[0] = new Vector2(3, 3).scl(1 / AlfredMain.PPM);
        vertice[1] = new Vector2(-3, 3).scl(1 / AlfredMain.PPM);
        vertice[2] = new Vector2(0, 0).scl(1 / AlfredMain.PPM);
        vertice[3] = new Vector2(0, -1).scl(1 / AlfredMain.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = AlfredMain.ENEMYHEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void redef() {

    }

    @Override
    protected void redefineEnemy() {

    }

    @Override
    protected void reredefineEnemy() {

    }

    @Override
    public void hitOnBack(int damage) {

    }

    public void draw(Batch batch) {
        if ((!destroyed || stateTime < 5) && (!alfredKilled || stateTime < 0.1)) super.draw(batch);

    }

    @Override
    public void hitOnHead() {
        setToDestroy = true;
    }
}















