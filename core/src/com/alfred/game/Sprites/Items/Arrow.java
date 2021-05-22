package com.alfred.game.Sprites.Items;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.alfred.game.Sprites.Enemies.Enemy;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Arrow extends Item {

    public TextureRegion region;
    private float stateTime;

    public Arrow(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        if (Alfred.shotRight) {
            region = new TextureRegion(screen.getAtlas().findRegion("arrow"), 0, 0, 32, 32);
        } else if (Alfred.shotLeft) {
            region = new TextureRegion(screen.getAtlas().findRegion("arrow"), 0, 0, 32, 32);
            region.flip(true, false);
        } else if (Alfred.shotDownRight) {
            region = new TextureRegion(screen.getAtlas().findRegion("arrow"), 34, 0, 32, 32);
        } else if (Alfred.shotDownLeft) {
            region = new TextureRegion(screen.getAtlas().findRegion("arrow"), 34, 0, 32, 32);
            region.flip(true,false);
        } else if (Alfred.shotUpRight) {
            region = new TextureRegion(screen.getAtlas().findRegion("arrow"), 68, 0, 32, 32);
        } else if (Alfred.shotUpLeft) {
            region = new TextureRegion(screen.getAtlas().findRegion("arrow"), 68, 0, 32, 32);
            region.flip(true,false);
        }

        setRegion(region);

        velocity = new Vector2(0/*5f*/, 0);
    }

    @Override
    public void defineItem() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(3 / AlfredMain.PPM);
        fdef.filter.categoryBits = AlfredMain.ARROW_BIT;
        fdef.filter.maskBits = AlfredMain.OBJECT_BIT | AlfredMain.GROUND_BIT | AlfredMain.DEMONICGROUND_BIT
                | AlfredMain.ALFRED_LEGS_BIT | AlfredMain.REDGROUND_BIT | AlfredMain.ENEMY_BIT
                | AlfredMain.DROYERBULLET_BIT | AlfredMain.REDROSE_BIT;

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void use(Alfred alfred) {
        //Gdx.app.log("WHY", "ARE YOU");
        //destroy();
    }

    @Override
    public void hitEnemy(Enemy enemy) {
        enemy.getHit(25);
    }


    @Override
    public void update(float dt) {
        super.update(dt);

        stateTime += dt;

        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        if (Alfred.shotRight) {
            velocity.x = /*body.getLinearVelocity().x*/ 3f;
            velocity.y = /*body.getLinearVelocity().y*/ -0.2f;
        } else if (Alfred.shotLeft){
            velocity.x = /*body.getLinearVelocity().x*/ -3f;
            velocity.y = /*body.getLinearVelocity().y*/ -0.2f;
        } else if (Alfred.shotDownRight) {
            velocity.x = 3f;
            velocity.y = -3f;
        } else if (Alfred.shotDownLeft) {
            velocity.x = -3f;
            velocity.y = -3f;
        } else if (Alfred.shotUpRight) {
            velocity.x = 3f;
            velocity.y = 3f;
        } else if (Alfred.shotUpLeft) {
            velocity.x = -3f;
            velocity.y = 3f;
        }

        if (stateTime > 40*dt) {
            destroy();
        }

        body.setLinearVelocity(velocity);
    }

}