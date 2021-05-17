package com.alfred.game.Sprites.Enemies;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.alfred.game.Sprites.Items.BlackRaven;
import com.alfred.game.Sprites.Items.DroyerBullet;
import com.alfred.game.Sprites.Items.ItemDef;
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

    public SmallDeath(PlayScreen screen, float x, float y) {
        super(screen, x, y);

        deathStay = new TextureRegion(screen.getAtlas().findRegion("smalldeath"), 0, 0, 32, 32);
        stateTime = 0;
        setBounds(getX(), getY(), 32 / AlfredMain.PPM, 32 / AlfredMain.PPM);

        setToDestroy = false;
        destroyed = false;
        setToKill = false;
        runningRight = false;
    }

    public void update(float dt) {
        setRegion(deathStay);
        stateTime += dt;

        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            //screen.spawnItem(new ItemDef(new Vector2(b2body.getPosition().x, b2body.getPosition().y), BlackRaven.class));
            stateTime = 0;
            Hud.addScore(10);
        } else if(!destroyed) {
            b2body.setLinearVelocity(velocity);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        }

    }

    public void killAlfred(Alfred alfred) {
        setToKill = true;
        alfred.hit(50, "Alfred was gibbet by death");
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
        fdef.filter.maskBits = AlfredMain.GROUND_BIT | AlfredMain.ALFRED_BIT
                | AlfredMain.BADGROUND_BIT | AlfredMain.BROKENGROUND_BIT
                | AlfredMain.COIN_BIT | AlfredMain.OBJECT_BIT |AlfredMain.ENEMY_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(-10, 16).scl(1 / AlfredMain.PPM);
        vertice[1] = new Vector2(10, 16).scl(1 / AlfredMain.PPM);
        vertice[2] = new Vector2(-3, 3).scl(1 / AlfredMain.PPM);
        vertice[3] = new Vector2(3, 3).scl(1 / AlfredMain.PPM);
        head.set(vertice);

        fdef.shape = head;
        fdef.restitution = 0.5f;
        fdef.filter.categoryBits = AlfredMain.ENEMYHEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void draw(Batch batch) {
        if ((!destroyed || stateTime < 5)) super.draw(batch);

    }

    @Override
    public void hitOnHead() {
        setToDestroy = true;
    }
}
