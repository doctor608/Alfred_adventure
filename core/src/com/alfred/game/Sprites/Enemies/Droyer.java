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

    public int enemyHp;

    public Droyer(PlayScreen screen, float x, float y) {
        super(screen, x + 16 / AlfredMain.PPM, y + 16 / AlfredMain.PPM);

        droyerStay = new TextureRegion(screen.getAtlas().findRegion("droyer"), 0, 0, 34, 32);

        setBounds(getX(), getY(), 32 / AlfredMain.PPM, 32 / AlfredMain.PPM);
        stateTime = 0;

        setToDestroy = false;
        destroyed = false;
        enemyHp = 50;
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.StaticBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / AlfredMain.PPM);

        fdef.filter.categoryBits = AlfredMain.ENEMY_BIT;
        fdef.filter.maskBits = AlfredMain.GROUND_BIT | AlfredMain.ALFRED_BIT
                | AlfredMain.BADGROUND_BIT | AlfredMain.BROKENGROUND_BIT
                | AlfredMain.COIN_BIT | AlfredMain.OBJECT_BIT | AlfredMain.ENEMY_BIT | AlfredMain.ITEM_BIT | AlfredMain.ARROW_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        /*
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
        b2body.createFixture(fdef).setUserData(this);*/
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
    public void hitOnHead() {
        setToDestroy = true;
    }

    @Override
    public void killAlfred(Alfred alfred) {

    }

    @Override
    public void update(float dt) {
        //setRegion(droyerStay);
        stateTime += dt;

        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed = true;
            setRegion(new TextureRegion(screen.getAtlas().findRegion("deaddroyer"), 0, 0, 32, 32));
            stateTime = 0;
            Hud.addScore(10);
        } else if(!destroyed) {
            if (stateTime > 33 * dt) {
                screen.spawnItem(new ItemDef(new Vector2(b2body.getPosition().x, b2body.getPosition().y -16 / AlfredMain.PPM), DroyerBullet.class));
                stateTime = 0;
            }
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() /2);
            setRegion(droyerStay);
        }
    }

    @Override
    public void getHit(int damage) {
        enemyHp = enemyHp - damage;
        if (enemyHp <= 0) {
            setToDestroy = true;
        }
    }

    public void draw(Batch batch) {
        if ((!destroyed || stateTime < 1)) super.draw(batch);

    }
}
















