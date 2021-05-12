package com.alfred.game.Sprites;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;

public class Knight extends Enemy {

    private float stateTime;
    private Animation<TextureRegion> walkAnimation;
    private Array<TextureRegion> frames;

    public Knight(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        frames = new Array<>();
        for (int i = 0; i < 2; ++i) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("knight"), i * 32, 0, 32, 32));
        }

        walkAnimation = new Animation(0.4f, frames);
        stateTime = 0;
        setBounds(getX(), getY(), 32 / AlfredMain.PPM, 32 / AlfredMain.PPM);
    }

    public void update(float dt) {
        stateTime = stateTime + dt;
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(walkAnimation.getKeyFrame(stateTime, true));
    }
    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / AlfredMain.PPM, 32 / AlfredMain.PPM);
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
        b2body.createFixture(fdef);

    }
}















