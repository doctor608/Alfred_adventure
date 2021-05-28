package com.alfred.game.Sprites.Enemies;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.alfred.game.Sprites.Items.BlackRaven;
import com.alfred.game.Sprites.Items.BlackRose;
import com.alfred.game.Sprites.Items.DroyerBullet;
import com.alfred.game.Sprites.Items.ItemDef;
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

public class SmallDeath extends Enemy{

    private float stateTime;

    private TextureRegion deathStay;
    private boolean setToDestroy;
    private boolean destroyed;
    private boolean setToKill;

    private boolean runningRight;

    public int enemyHp;

    private boolean timeToRedefineEnemy;
    private boolean timetoReRedefineEnemy;

    private Sound deathkillsound;
    private Sound blackravensound;

    public SmallDeath(PlayScreen screen, float x, float y) {
        super(screen, x, y);

        deathStay = new TextureRegion(screen.getAtlas().findRegion("smalldeath"), 0, 0, 32, 32);
        stateTime = 0;
        setBounds(getX(), getY(), 32 / AlfredMain.PPM, 32 / AlfredMain.PPM);

        setToDestroy = false;
        destroyed = false;
        setToKill = false;
        runningRight = false;

        enemyHp = 75;

        deathkillsound = AlfredMain.manager.get("audio/sounds/deathkill.wav", Sound.class);
        blackravensound = AlfredMain.manager.get("audio/sounds/blackraven.wav", Sound.class);
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
        fdef.filter.maskBits = /*AlfredMain.GROUND_BIT |*/ AlfredMain.ALFRED_BIT
                | AlfredMain.BADGROUND_BIT | AlfredMain.BROKENGROUND_BIT
                | AlfredMain.COIN_BIT | AlfredMain.OBJECT_BIT |AlfredMain.ENEMY_BIT | AlfredMain.ARROW_BIT | AlfredMain.DEMONICGROUND_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);


        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-10, 17).scl(1 / AlfredMain.PPM);
        vertice[1] = new Vector2(10, 17).scl(1 / AlfredMain.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / AlfredMain.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / AlfredMain.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = AlfredMain.ENEMYHEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);


        PolygonShape zad = new PolygonShape();
        Vector2[] zadvertice = new Vector2[4];
        zadvertice[0] = new Vector2(15, 15).scl(1 / AlfredMain.PPM);
        zadvertice[1] = new Vector2(17, 15).scl(1 / AlfredMain.PPM);
        zadvertice[2] = new Vector2(15, -15).scl(1 / AlfredMain.PPM);
        zadvertice[3] = new Vector2(17, -15).scl(1 / AlfredMain.PPM);
        zad.set(zadvertice);

        fdef.shape = zad;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = AlfredMain.ENEMYZAD_BIT;
        b2body.createFixture(fdef).setUserData(this);

        timeToRedefineEnemy = false;
        timetoReRedefineEnemy = false;
    }

    @Override
    protected void redefineEnemy() {
        Vector2 currentPosition = b2body.getPosition();
        world.destroyBody(b2body);

        BodyDef bdef = new BodyDef();
        bdef.position.set(currentPosition);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / AlfredMain.PPM);

        fdef.filter.categoryBits = AlfredMain.ENEMY_BIT;
        fdef.filter.maskBits = /*AlfredMain.GROUND_BIT |*/ AlfredMain.ALFRED_BIT
                | AlfredMain.BADGROUND_BIT | AlfredMain.BROKENGROUND_BIT
                | AlfredMain.COIN_BIT | AlfredMain.OBJECT_BIT |AlfredMain.ENEMY_BIT | AlfredMain.ARROW_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-10, 17).scl(1 / AlfredMain.PPM);
        vertice[1] = new Vector2(10, 17).scl(1 / AlfredMain.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / AlfredMain.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / AlfredMain.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = AlfredMain.ENEMYHEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);


        PolygonShape zad = new PolygonShape();
        Vector2[] zadvertice = new Vector2[4];
        zadvertice[0] = new Vector2(-17, 15).scl(1 / AlfredMain.PPM);
        zadvertice[1] = new Vector2(-15, 15).scl(1 / AlfredMain.PPM);
        zadvertice[2] = new Vector2(-17, -15).scl(1 / AlfredMain.PPM);
        zadvertice[3] = new Vector2(-15, -15).scl(1 / AlfredMain.PPM);
        zad.set(zadvertice);

        fdef.shape = zad;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = AlfredMain.ENEMYZAD_BIT;
        b2body.createFixture(fdef).setUserData(this);

        timeToRedefineEnemy = false;
        timetoReRedefineEnemy = false;
    }

    @Override
    protected void reredefineEnemy() {
        Vector2 currentPosition = b2body.getPosition();
        world.destroyBody(b2body);

        BodyDef bdef = new BodyDef();
        bdef.position.set(currentPosition);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / AlfredMain.PPM);

        fdef.filter.categoryBits = AlfredMain.ENEMY_BIT;
        fdef.filter.maskBits = /*AlfredMain.GROUND_BIT | */AlfredMain.ALFRED_BIT
                | AlfredMain.BADGROUND_BIT | AlfredMain.BROKENGROUND_BIT
                | AlfredMain.COIN_BIT | AlfredMain.OBJECT_BIT |AlfredMain.ENEMY_BIT | AlfredMain.ARROW_BIT | AlfredMain.REDGROUND_BIT | AlfredMain.DEMONICGROUND_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);


        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-10, 17).scl(1 / AlfredMain.PPM);
        vertice[1] = new Vector2(10, 17).scl(1 / AlfredMain.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / AlfredMain.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / AlfredMain.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = AlfredMain.ENEMYHEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);


        PolygonShape zad = new PolygonShape();
        Vector2[] zadvertice = new Vector2[4];
        zadvertice[0] = new Vector2(15, 15).scl(1 / AlfredMain.PPM);
        zadvertice[1] = new Vector2(17, 15).scl(1 / AlfredMain.PPM);
        zadvertice[2] = new Vector2(15, -15).scl(1 / AlfredMain.PPM);
        zadvertice[3] = new Vector2(17, -15).scl(1 / AlfredMain.PPM);
        zad.set(zadvertice);

        fdef.shape = zad;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = AlfredMain.ENEMYZAD_BIT;
        b2body.createFixture(fdef).setUserData(this);

        timeToRedefineEnemy = false;
        timetoReRedefineEnemy = false;
    }

    @Override
    public void hitOnBack(int damage) {
        enemyHp = enemyHp - damage;
        String hp = Integer.toString(enemyHp);
        Gdx.app.log("HP", hp);
        if (enemyHp <= 0) {
          setToDestroy = true;
        }
    }


    public void redef() {
        redefineEnemy();
    }

    public void update(float dt) {
        stateTime += dt;


        TextureRegion region = deathStay;

        if (timeToRedefineEnemy) {
            redefineEnemy();
        }
        if (timetoReRedefineEnemy) {
            reredefineEnemy();
        }

        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
            reredefineEnemy();
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
            redefineEnemy();
        }

        if (setToKill) {
            deathkillsound.setVolume(deathkillsound.play(), 0.1f);
            setToKill = false;
        }

        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            blackravensound.setVolume(blackravensound.play(), 0.1f);
            screen.spawnItem(new ItemDef(new Vector2(b2body.getPosition().x, b2body.getPosition().y), BlackRaven.class));
            stateTime = 0;
            Hud.addScore(3);
        } else if(!destroyed) {
            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion(region);
        }

    }

    @Override
    public void getHit(int damage) {
        //enemyHp = enemyHp - damage;
        //if (enemyHp <= 0) {
          //  setToDestroy = true;
        //}
        //setToDestroy = true;
    }

    public void killAlfred(Alfred alfred) {
        setToKill = true;
        alfred.hit(50, "Alfred was gibbet by death");
        //setToDestroy = true;
    }

    public void draw(Batch batch) {
        if ((!destroyed)) super.draw(batch);

    }

    @Override
    public void hitOnHead() {
        //setToDestroy = true;
    }
}
