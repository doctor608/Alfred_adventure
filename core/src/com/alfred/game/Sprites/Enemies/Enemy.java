package com.alfred.game.Sprites.Enemies;

import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Enemy extends Sprite {

    protected World world;
    protected PlayScreen screen;

    public Body b2body;

    public Vector2 velocity;

    public Enemy(PlayScreen screen, float x, float y) {
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
        velocity = new Vector2(1, 0);
        b2body.setActive(false);
    }

    protected abstract void defineEnemy();
    public abstract void redef();
    protected abstract void redefineEnemy();
    protected abstract void reredefineEnemy();
    public abstract void hitOnBack(int damage);
    public abstract void hitOnHead();
    public abstract void killAlfred(Alfred alfred);
    public abstract void update(float dt);
    public abstract void getHit(int damage);

    public void reverseVelocity(boolean x, boolean y) {
        if (x) {
            velocity.x = -velocity.x;
        }
        if (y) {
            velocity.y = -velocity.y;
        }
    }

}





















