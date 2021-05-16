package com.alfred.game.Tools;

import com.alfred.game.AlfredMain;
import com.alfred.game.Sprites.Alfred;
import com.alfred.game.Sprites.Enemies.Enemy;
import com.alfred.game.Sprites.Items.DroyerBullet;
import com.alfred.game.Sprites.Items.Item;
import com.alfred.game.Sprites.TileObjects.InteractiveTileObject;
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

        switch (cDef) {
            case AlfredMain.ALFRED_HEAD_BIT | AlfredMain.BADGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.BADGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onHeadHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onHeadHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_HEAD_BIT | AlfredMain.BROKENGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.BROKENGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onHeadHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onHeadHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_HEAD_BIT | AlfredMain.COIN_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.COIN_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onHeadHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onHeadHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_HEAD_BIT | AlfredMain.DEMONICGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.DEMONICGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onHeadHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onHeadHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_HEAD_BIT | AlfredMain.REDGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.REDGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onHeadHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onHeadHit((Alfred)fixA.getUserData());
                }
                break;

            case AlfredMain.ALFRED_LEGS_BIT | AlfredMain.BADGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.BADGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onLegsHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onLegsHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_LEGS_BIT | AlfredMain.BROKENGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.BROKENGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onLegsHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onLegsHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_LEGS_BIT| AlfredMain.COIN_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.COIN_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onLegsHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onLegsHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_LEGS_BIT | AlfredMain.DEMONICGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.DEMONICGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onLegsHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onLegsHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_LEGS_BIT | AlfredMain.REDGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.REDGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onLegsHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onLegsHit((Alfred)fixA.getUserData());
                }
                break;

            case AlfredMain.ALFRED_BODY_BIT | AlfredMain.BADGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.BADGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onBodyHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onBodyHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_BODY_BIT| AlfredMain.BROKENGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.BROKENGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onBodyHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onBodyHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_BODY_BIT | AlfredMain.COIN_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.COIN_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onBodyHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onBodyHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_BODY_BIT | AlfredMain.DEMONICGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.DEMONICGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onBodyHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onBodyHit((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.ALFRED_BODY_BIT | AlfredMain.REDGROUND_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.REDGROUND_BIT) {
                    ((InteractiveTileObject)fixA.getUserData()).onBodyHit((Alfred)fixB.getUserData());
                } else {
                    ((InteractiveTileObject)fixB.getUserData()).onBodyHit((Alfred)fixA.getUserData());
                }
                break;

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
                if (fixA.getFilterData().categoryBits == AlfredMain.ALFRED_BIT) {
                    ((Enemy)fixB.getUserData()).killAlfred((Alfred) fixA.getUserData());
                } else {
                    ((Enemy)fixA.getUserData()).killAlfred((Alfred) fixB.getUserData());
                }
                break;
                /*
                if (fixA.getFilterData().categoryBits == AlfredMain.ENEMY_BIT) {
                    ((Enemy)fixA.getUserData()).killAlfred();
                } else {
                    ((Enemy)fixB.getUserData()).killAlfred();
                }
                break;
                 */
            case AlfredMain.ENEMY_BIT | AlfredMain.ENEMY_BIT:
                ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;
            case AlfredMain.ITEM_BIT | AlfredMain.OBJECT_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.ITEM_BIT) {
                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
                } else {
                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
                }
                break;
            case AlfredMain.ITEM_BIT | AlfredMain.ALFRED_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.ITEM_BIT) {
                    ((Item)fixA.getUserData()).use((Alfred)fixB.getUserData());
                } else {
                    ((Item)fixB.getUserData()).use((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.DROYERBULLET_BIT | AlfredMain.ALFRED_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.DROYERBULLET_BIT) {
                    ((DroyerBullet)fixA.getUserData()).use((Alfred)fixB.getUserData());
                } else {
                    ((DroyerBullet)fixB.getUserData()).use((Alfred)fixA.getUserData());
                }
                break;
            case AlfredMain.DROYERBULLET_BIT | AlfredMain.OBJECT_BIT | AlfredMain.DROYERBULLET_BIT | AlfredMain.ENEMYHEAD_BIT
                    | AlfredMain.ENEMY_BIT | AlfredMain.ITEM_BIT:
                if (fixA.getFilterData().categoryBits == AlfredMain.DROYERBULLET_BIT) {
                    ((DroyerBullet)fixA.getUserData()).destroy();
                } else {
                    ((DroyerBullet)fixB.getUserData()).destroy();
                }
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
