package com.alfred.game.Sprites;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Items.Arrow;
import com.alfred.game.Sprites.Items.BlackRaven;
import com.alfred.game.Sprites.Items.BlackRose;
import com.alfred.game.Sprites.Items.DroyerBullet;
import com.alfred.game.Sprites.Items.ItemDef;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Alfred extends Sprite {

    public enum State{ JUMPING, STAYING, RUNNING, TRANSFORMING, DEAD, RETRANSFORMING, BOWRIGHTSHOT, BOWDOWNSHOT, BOWUPSHOT };
    public State currentState;
    public State previousState;

    private float stateTimer;

    public World world;
    public Body b2body;
    protected PlayScreen screen;

    private TextureRegion alfredStay;
    private Animation alfredRun;
    private TextureRegion alfredJump;

    private TextureRegion blackAlfredStay;
    private TextureRegion blackAlfredJump;
    private Animation blackAlfredRun;
    private Animation transformingToBlack;
    private Animation retransforming;


    private Animation bowrightshot;
    private Animation bowdownshot;
    private Animation bowupshot;

    private boolean runBowRightShotAnimation;
    private boolean runBowDownShotAnimation;
    private boolean runBowUpShotAnimation;

    public static boolean shotRight;
    public static boolean shotLeft;
    public static boolean shotDownRight;
    public static boolean shotDownLeft;
    public static boolean shotUpRight;
    public static boolean shotUpLeft;


    public static boolean runningRight;
    private boolean alfredIsBlack;
    private boolean runTransformingAnimation;
    private boolean alfredIsDead;

    public static String judgment;
    //private boolean timeToDefineBlackAlfred;
    //private boolean timeToRedefineAlfred;

    public static int hp;

    public boolean jumped;

    public Alfred(PlayScreen screen) {
        //super(screen.getAtlas().findRegion("alfred"));
        this.world = screen.getWorld();
        this.screen = screen;

        currentState = State.STAYING;
        previousState = State.STAYING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i < 3; ++i) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("alfred"), i * 32, 0, 34, 32));
        }
        alfredRun = new Animation(0.1f, frames);
        frames.clear();

        for (int i = 1; i < 3; ++i) {
            frames.add(new TextureRegion(screen.getAtlas().findRegion("blackalfred"), i * 32, 0, 34, 32));
        }
        blackAlfredRun = new Animation(0.1f, frames);
        frames.clear();

        frames.add(new TextureRegion(screen.getAtlas().findRegion("blackalfred"), 130, 0, 34, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("blackalfred"), 166, 0, 34, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("blackalfred"), 204, 0, 34, 32));
        transformingToBlack = new Animation(0.2f, frames);
        frames.clear();

        alfredJump = new TextureRegion(screen.getAtlas().findRegion("alfred"), 102, 0, 34, 32);
        blackAlfredJump = new TextureRegion(screen.getAtlas().findRegion("blackalfred"), 102, 0, 34, 32);

        alfredStay = new TextureRegion(screen.getAtlas().findRegion("alfred"), 0, 0, 32, 32);
        blackAlfredStay = new TextureRegion(screen.getAtlas().findRegion("blackalfred"), 0, 0, 32, 32);

        frames.add(new TextureRegion(screen.getAtlas().findRegion("bow"), 0, 0, 32, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("bow"), 0, 0, 32, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("bow"), 0, 0, 32, 32));
        bowrightshot = new Animation(0.2f, frames);
        frames.clear();

        frames.add(new TextureRegion(screen.getAtlas().findRegion("bow"), 32, 0, 32, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("bow"), 32, 0, 32, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("bow"), 32, 0, 32, 32));
        bowdownshot = new Animation(0.2f, frames);
        frames.clear();

        frames.add(new TextureRegion(screen.getAtlas().findRegion("bow"), 64, 0, 32, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("bow"), 64, 0, 32, 32));
        frames.add(new TextureRegion(screen.getAtlas().findRegion("bow"), 64, 0, 32, 32));
        bowupshot = new Animation(0.2f, frames);
        frames.clear();

        defineAlfred();

        setBounds(0, 0, 32 / AlfredMain.PPM, 32 / AlfredMain.PPM);
        setRegion(alfredStay);

        hp = 50;
        Hud.setHp(hp);

        runBowRightShotAnimation = false;
        runBowDownShotAnimation = false;
        runBowUpShotAnimation = false;
        //isBowRightShot = false;
    }

    public void defineAlfred() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / AlfredMain.PPM, 32 / AlfredMain.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / AlfredMain.PPM);

        fdef.filter.categoryBits = AlfredMain.ALFRED_BIT;
        fdef.filter.maskBits = /*AlfredMain.GROUND_BIT |*/ AlfredMain.BADGROUND_BIT
                        | AlfredMain.BROKENGROUND_BIT | AlfredMain.COIN_BIT
                        | AlfredMain.OBJECT_BIT | AlfredMain.ENEMY_BIT | AlfredMain.ENEMYHEAD_BIT | AlfredMain.DEMONICGROUND_BIT | AlfredMain.ITEM_BIT
                        | AlfredMain.REDGROUND_BIT | AlfredMain.DROYERBULLET_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-8 / AlfredMain.PPM, 16 / AlfredMain.PPM), new Vector2(8 / AlfredMain.PPM, 16 / AlfredMain.PPM));
        fdef.filter.categoryBits = AlfredMain.ALFRED_HEAD_BIT;
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape legs = new EdgeShape();
        legs.set(new Vector2(-8 / AlfredMain.PPM, -16 / AlfredMain.PPM), new Vector2(8 / AlfredMain.PPM, -16 / AlfredMain.PPM));
        fdef.filter.categoryBits = AlfredMain.ALFRED_LEGS_BIT;
        fdef.shape = legs;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);

        /*
        EdgeShape body = new EdgeShape();
        body.set(new Vector2(0 / AlfredMain.PPM, 0 / AlfredMain.PPM), new Vector2(0 / AlfredMain.PPM, 0 / AlfredMain.PPM));
        fdef.filter.categoryBits = AlfredMain.ALFRED_BODY_BIT;
        fdef.shape = body;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);

         */

    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();

        TextureRegion region;
        switch (currentState) {
            case TRANSFORMING:
                region = (TextureRegion) transformingToBlack.getKeyFrame(stateTimer);
                if (runningRight == true) {
                    region.flip(true, false);
                }
                if (transformingToBlack.isAnimationFinished(stateTimer)) {
                    runTransformingAnimation = false;
                }
                break;
            case BOWRIGHTSHOT:
                region = (TextureRegion) bowrightshot.getKeyFrame(stateTimer);
                if (runningRight == true) {
                    region.flip(true, false);
                }
                boolean variabl = bowrightshot.isAnimationFinished(stateTimer);
                if (previousState == State.STAYING) {
                    variabl = false;
                }
                if (variabl) {
                    if (runningRight == true) {
                        screen.spawnItem(new ItemDef(new Vector2(b2body.getPosition().x + 32 / AlfredMain.PPM, b2body.getPosition().y + 16 / AlfredMain.PPM), Arrow.class));
                    } else {
                        screen.spawnItem(new ItemDef(new Vector2(b2body.getPosition().x - 32 / AlfredMain.PPM, b2body.getPosition().y + 16 / AlfredMain.PPM), Arrow.class));
                    }
                    runBowRightShotAnimation = false;
                    //currentState = State.STAYING;
                }
                //currentState = State.STAYING;
                //jumped = false;
                break;
            case BOWUPSHOT:
                region = (TextureRegion) bowupshot.getKeyFrame(stateTimer);
                if (runningRight == true) {
                    region.flip(true, false);
                }
                boolean variable = bowupshot.isAnimationFinished(stateTimer);
                if (previousState == State.STAYING) {
                    variable = false;
                }
                if (variable) {
                    if (runningRight == true) {
                        screen.spawnItem(new ItemDef(new Vector2(b2body.getPosition().x + 32 / AlfredMain.PPM, b2body.getPosition().y + 8 / AlfredMain.PPM), Arrow.class));
                    } else {
                        screen.spawnItem(new ItemDef(new Vector2(b2body.getPosition().x - 32 / AlfredMain.PPM, b2body.getPosition().y + 8 / AlfredMain.PPM), Arrow.class));
                    }
                    runBowUpShotAnimation = false;
                    //currentState = State.STAYING;
                }
                //currentState = State.STAYING;
                //jumped = false;
                break;
            case BOWDOWNSHOT:
                region = (TextureRegion) bowdownshot.getKeyFrame(stateTimer);
                if (runningRight == true) {
                    region.flip(true, false);
                }
                boolean variabel = bowdownshot.isAnimationFinished(stateTimer);
                if (previousState == State.STAYING) {
                    variabel = false;
                }
                if (variabel) {
                    if (runningRight == true) {
                        screen.spawnItem(new ItemDef(new Vector2(b2body.getPosition().x + 32 / AlfredMain.PPM, b2body.getPosition().y - 8 / AlfredMain.PPM), Arrow.class));
                    } else {
                        screen.spawnItem(new ItemDef(new Vector2(b2body.getPosition().x - 32 / AlfredMain.PPM, b2body.getPosition().y - 8 / AlfredMain.PPM), Arrow.class));
                    }
                    runBowDownShotAnimation = false;
                    //currentState = State.STAYING;
                }
                //currentState = State.STAYING;
                //jumped = false;
                break;
            case JUMPING:
                region = alfredIsBlack ? blackAlfredJump : alfredJump;
                jumped = true;
                break;
            case RUNNING:
                region = alfredIsBlack ? (TextureRegion) blackAlfredRun.getKeyFrame(stateTimer, true) : (TextureRegion) alfredRun.getKeyFrame(stateTimer, true);
                jumped = false;
                break;
            case STAYING:
            default:
                region = alfredIsBlack ? blackAlfredStay : alfredStay;
                jumped = false;
                break;
        }

        if (b2body.getLinearVelocity().y < -10f && !alfredIsDead) {
            die("Alfred was consumed by void");
        }

        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        if (currentState == previousState) {
            stateTimer = stateTimer + dt;
        } else {
            stateTimer = 0;
        }

        previousState = currentState;

        return region;
    }

    public State getState(){
        if (alfredIsDead) {
            return State.DEAD;
        } else if (runBowRightShotAnimation) {
            return State.BOWRIGHTSHOT;
        } else if (runBowDownShotAnimation) {
            return State.BOWDOWNSHOT;
        } else if (runBowUpShotAnimation) {
            return State.BOWUPSHOT;
        } else if (runTransformingAnimation) {
            return State.TRANSFORMING;
        } else if (b2body.getLinearVelocity().y != 0) {
            return State.JUMPING;
        } else if (b2body.getLinearVelocity().x != 0) {
            return State.RUNNING;
        } else {
            return State.STAYING;
        }
    }

    public void transform() {
        runTransformingAnimation = true;
        alfredIsBlack = true;
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public void bowShotRight() {
        runBowRightShotAnimation = true;
        if (runningRight) {
            shotRight = true;
            shotLeft = false;
            shotDownRight = false;
            shotDownLeft = false;
            shotUpLeft = false;
            shotUpRight = false;
        } else {
            shotRight = false;
            shotLeft = true;
            shotDownRight = false;
            shotDownLeft = false;
            shotUpLeft = false;
            shotUpRight = false;
        }
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public void bowShotDown() {
        runBowDownShotAnimation = true;
        if (runningRight) {
            shotDownRight = true;
            shotDownLeft = false;
            shotRight = false;
            shotLeft = false;
            shotUpLeft = false;
            shotUpRight = false;
        } else {
            shotDownRight = false;
            shotDownLeft = true;
            shotRight = false;
            shotLeft = false;
            shotUpLeft = false;
            shotUpRight = false;
        }
    }

    public void bowUpShot() {
        runBowUpShotAnimation = true;
        if (runningRight) {
            shotUpRight = true;
            shotUpLeft = false;
            shotDownRight = false;
            shotDownLeft = false;
            shotRight = false;
            shotLeft = false;
        } else {
            shotUpLeft  = true;
            shotUpRight = false;
            shotDownRight = false;
            shotDownLeft = false;
            shotRight = false;
            shotLeft = false;
        }
    }

    public boolean isRunBowUpShotAnimation() {
        return runBowUpShotAnimation;
    }

    public boolean isRunBowDownShotAnimation() {
        return runBowDownShotAnimation;
    }

    public boolean isRunBowRightShotAnimation() {
        return runBowRightShotAnimation;
    }

    public boolean isDead() {
        return alfredIsDead;
    }

    public float getStateTimer() {
        return stateTimer;
    }

    public boolean isBlack() {
        return alfredIsBlack;
    }


    public void die(String string) {

        if (!isDead()) {

            alfredIsDead = true;
            Filter filter = new Filter();
            filter.maskBits = AlfredMain.NOTHING_BIT;

            for (Fixture fixture : b2body.getFixtureList()) {
                fixture.setFilterData(filter);
            }

            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
        }

        judgment = string;
    }

    public void hit(int damage, String string) {
        if (hp > damage) {
            hp = hp - damage;
            String howmanyhp = Integer.toString(hp);
            Gdx.app.log("ALFRED HP", howmanyhp);
        } else {
            die(string);
        }
        Hud.downHp(damage);
    }

    public void heal(int healing) {
        if (hp + healing > 50) {
            hp = 50;
        } else {
            hp = hp + healing;
        }
        Hud.addHp(hp);
        String howmanyhp = Integer.toString(hp);
        Gdx.app.log("ALFRED HP", howmanyhp);
    }

    /*
    public void defineBlackAlfred() {
        Vector2 currentPosition = b2body.getPosition();
        world.destroyBody(b2body);

        BodyDef bdef = new BodyDef();
        bdef.position.set(currentPosition.add(0, 0));
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / AlfredMain.PPM);
        fdef.filter.categoryBits = AlfredMain.ALFRED_BIT;
        fdef.filter.maskBits = AlfredMain.GROUND_BIT | AlfredMain.BADGROUND_BIT
                | AlfredMain.BROKENGROUND_BIT | AlfredMain.COIN_BIT
                | AlfredMain.OBJECT_BIT | AlfredMain.ENEMY_BIT
                | AlfredMain.ENEMYHEAD_BIT | AlfredMain.DEMONICGROUND_BIT
                | AlfredMain.ITEM_BIT | AlfredMain.RED_GROUND_BIT;

        fdef.shape = shape;
        shape.setPosition(new Vector2(0, 0));
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-8 / AlfredMain.PPM, 16 / AlfredMain.PPM), new Vector2(8 / AlfredMain.PPM, 16 / AlfredMain.PPM));
        fdef.filter.categoryBits = AlfredMain.ALFRED_HEAD_BIT;
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape legs = new EdgeShape();
        legs.set(new Vector2(-8 / AlfredMain.PPM, -16 / AlfredMain.PPM), new Vector2(8 / AlfredMain.PPM, -16 / AlfredMain.PPM));
        fdef.filter.categoryBits = AlfredMain.ALFRED_LEGS_BIT;
        fdef.shape = legs;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape body = new EdgeShape();
        body.set(new Vector2(0 / AlfredMain.PPM, 0 / AlfredMain.PPM), new Vector2(0 / AlfredMain.PPM, 0 / AlfredMain.PPM));
        fdef.filter.categoryBits = AlfredMain.ALFRED_BODY_BIT;
        fdef.shape = body;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);
        Gdx.app.log("BLACKALFRED", "DEFINED");
        timeToDefineBlackAlfred = false;
    }
    */

    /*
    public void redefineAlfred() {
        Vector2 position = b2body.getPosition();
        world.destroyBody(b2body);


        BodyDef bdef = new BodyDef();
        bdef.position.set(position);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(15 / AlfredMain.PPM);

        fdef.filter.categoryBits = AlfredMain.ALFRED_BIT;
        fdef.filter.maskBits = AlfredMain.GROUND_BIT | AlfredMain.BADGROUND_BIT
                | AlfredMain.BROKENGROUND_BIT | AlfredMain.COIN_BIT
                | AlfredMain.OBJECT_BIT | AlfredMain.ENEMY_BIT | AlfredMain.ENEMYHEAD_BIT | AlfredMain.DEMONICGROUND_BIT | AlfredMain.ITEM_BIT;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-8 / AlfredMain.PPM, 16 / AlfredMain.PPM), new Vector2(8 / AlfredMain.PPM, 16 / AlfredMain.PPM));
        fdef.filter.categoryBits = AlfredMain.ALFRED_HEAD_BIT;
        fdef.shape = head;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape legs = new EdgeShape();
        legs.set(new Vector2(-8 / AlfredMain.PPM, -16 / AlfredMain.PPM), new Vector2(8 / AlfredMain.PPM, -16 / AlfredMain.PPM));
        fdef.filter.categoryBits = AlfredMain.ALFRED_LEGS_BIT;
        fdef.shape = legs;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape body = new EdgeShape();
        body.set(new Vector2(0 / AlfredMain.PPM, 0 / AlfredMain.PPM), new Vector2(0 / AlfredMain.PPM, 0 / AlfredMain.PPM));
        fdef.filter.categoryBits = AlfredMain.ALFRED_BODY_BIT;
        fdef.shape = body;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);

        timeToRedefineAlfred = false;
    }
     */

}
