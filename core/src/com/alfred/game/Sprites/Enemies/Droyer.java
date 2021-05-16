package com.alfred.game.Sprites.Enemies;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.alfred.game.Sprites.Items.BlackRose;
import com.alfred.game.Sprites.Items.DroyerBullet;
import com.alfred.game.Sprites.Items.ItemDef;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;

import static com.badlogic.gdx.math.MathUtils.random;

public class Droyer extends Enemy{

    private float stateTime;

    private TextureRegion droyerStay;

    private boolean setToDestroy;
    private boolean destroyed;

    public Droyer(PlayScreen screen, float x, float y) {
        super(screen, x, y);

        droyerStay = new TextureRegion(screen.getAtlas().findRegion("droyer"), 0, 0, 34, 32);

        stateTime = 0;
        setBounds(getX(), getY(), 32 / AlfredMain.PPM, 32 / AlfredMain.PPM);

        setToDestroy = false;
        destroyed = false;
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
    }

    @Override
    public void hitOnHead() {
        setToDestroy = true;
    }

    @Override
    public void killAlfred(Alfred alfred) {

    }

    @Override
    public void update(float dt) {
        stateTime += dt;

        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            //setRegion(new TextureRegion(screen.getAtlas().findRegion("droyer"), 32, 0, 32, 32));
            stateTime = 0;
            Hud.addScore(10);
        } else if(!destroyed) {
            int rand = random.nextInt(3) + 1;
            if (rand == 1) {
                screen.spawnItem(new ItemDef(new Vector2(b2body.getPosition().x, b2body.getPosition().y -16 / AlfredMain.PPM), DroyerBullet.class));
            }
            //bulletsUpdate(dt);
        }
    }

    public void draw(Batch batch) {
        if ((!destroyed || stateTime < 5)) super.draw(batch);

    }
}
















