package com.alfred.game.Sprites.Items;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Arrow extends Item {

    public Arrow(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getAtlas().findRegion("arrow"), 0, 0, 32, 32);
        velocity = new Vector2(0, 0);
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
        fdef.filter.categoryBits = AlfredMain.ITEM_BIT;
        fdef.filter.maskBits = AlfredMain.ALFRED_BIT | AlfredMain.OBJECT_BIT | AlfredMain.GROUND_BIT | AlfredMain.DEMONICGROUND_BIT | AlfredMain.ALFRED_LEGS_BIT;

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void use(Alfred alfred) {
        //Gdx.app.log("WHY", "ARE YOU");
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        velocity.y = body.getLinearVelocity().y + 0.3f;
        if (velocity.y > 15f) {
            destroy();
        }
        body.setLinearVelocity(velocity);
    }

}