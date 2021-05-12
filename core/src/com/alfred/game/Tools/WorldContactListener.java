package com.alfred.game.Tools;

import com.alfred.game.AlfredMain;
import com.alfred.game.Sprites.Alfred;
import com.alfred.game.Sprites.Enemy;
import com.alfred.game.Sprites.InteractiveTileObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        if (fixA.getUserData() == "head") {
            Fixture head = fixA;
            Fixture object = fixB;
            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        } else if (fixB.getUserData() == "head") {
            Fixture head = fixB;
            Fixture object = fixA;
            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onHeadHit();
            }
        }


        if (fixA.getUserData() == "legs") {
            Fixture legs = fixA;
            Fixture object = fixB;
            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onLegsHit();;
            }
        } else if (fixB.getUserData() == "legs") {
            Fixture legs = fixB;
            Fixture object = fixA;
            if (object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
                ((InteractiveTileObject) object.getUserData()).onLegsHit();
            }
        }

        switch (cDef) {
            case AlfredMain.ENEMYHEAD_BIT | AlfredMain.ALFRED_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.ENEMYHEAD_BIT) {
                    ((Enemy)fixA.getUserData()).hitOnHead();
                } else {
                    ((Enemy)fixB.getUserData()).hitOnHead();
                }
                break;
            case AlfredMain.ENEMY_BIT | AlfredMain.OBJECT_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.ENEMY_BIT) {
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                } else {
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                }
                break;
            case AlfredMain.ALFRED_BIT | AlfredMain.ENEMY_BIT:
                Gdx.app.log("ALFRED", "DIED");
                break;
            case AlfredMain.ENEMY_BIT | AlfredMain.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
