package com.alfred.game.Sprites.Items;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.alfred.game.Sprites.Enemies.Enemy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class BlackRose extends Item{


    public BlackRose(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getAtlas().findRegion("blackrose"), 0, 0, 36, 32);
        velocity = new Vector2(1.7f, 0);
    }

    @Override
    public void defineItem() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / AlfredMain.PPM);
        fdef.filter.categoryBits = AlfredMain.ITEM_BIT;
        fdef.filter.maskBits = AlfredMain.ALFRED_BIT | AlfredMain.OBJECT_BIT | AlfredMain.GROUND_BIT | AlfredMain.DEMONICGROUND_BIT;

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);

    }

    @Override
    public void use(Alfred alfred) {
        destroy();
        alfred.transform();
        alfred.hit(25, "Alfred was poisoned by a black rose");
    }

    @Override
    public void hitEnemy(Enemy enemy) {

    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        velocity.y = body.getLinearVelocity().y;
        body.setLinearVelocity(velocity);
    }
}



