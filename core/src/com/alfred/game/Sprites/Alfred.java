package com.alfred.game.Sprites;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Alfred extends Sprite {

    public World world;
    public Body b2body;
    private TextureRegion alfredStay;

    public Alfred(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("alfred"));
        this.world = world;
        defineAlfred();

        alfredStay = new TextureRegion(getTexture(), 0, 0, 32, 32);
        setBounds(0, 0, 32 / AlfredMain.PPM, 32 / AlfredMain.PPM);
        setRegion(alfredStay);
    }

    public void defineAlfred() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / AlfredMain.PPM, 32 / AlfredMain.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / AlfredMain.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

}
