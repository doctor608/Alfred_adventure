package com.alfred.game.Tools;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.Enemies.Droyer;
import com.alfred.game.Sprites.Enemies.Enemy;
import com.alfred.game.Sprites.Enemies.SmallDeath;
import com.alfred.game.Sprites.TileObjects.BadGround;
import com.alfred.game.Sprites.TileObjects.BrokenGround;
import com.alfred.game.Sprites.TileObjects.Coin;
import com.alfred.game.Sprites.Enemies.Knight;
import com.alfred.game.Sprites.TileObjects.DemonicGround;
import com.alfred.game.Sprites.TileObjects.RedGround;
import com.alfred.game.Sprites.TileObjects.RedRose;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class B2WorldCreator {

    private Array<Knight> knights;
    private Array<Droyer> droyers;
    private Array<SmallDeath> smallDeaths;

    public B2WorldCreator(PlayScreen screen) {

        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / AlfredMain.PPM, (rect.getY() + rect.getHeight() / 2) / AlfredMain.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / AlfredMain.PPM, rect.getHeight() / 2 / AlfredMain.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = AlfredMain.OBJECT_BIT;
            body.createFixture(fdef);
        }

        for (MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            new Coin(screen, object);
        }

        for (MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            new BadGround(screen, object);
        }

        for (MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            new BrokenGround(screen, object);
        }

        for (MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
            new DemonicGround(screen, object);
        }

        for (MapObject object: map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
            new RedGround(screen, object);
        }

        for (MapObject object: map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)) {
            new RedRose(screen, object);
        }

        knights = new Array<Knight>();
        for (MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            knights.add(new Knight(screen, rect.getX() / AlfredMain.PPM, rect.getY() / AlfredMain.PPM));
        }

        droyers = new Array<Droyer>();
        for (MapObject object: map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            droyers.add(new Droyer(screen, rect.getX() / AlfredMain.PPM, rect.getY() / AlfredMain.PPM));
        }

        smallDeaths = new Array<SmallDeath>();
        for (MapObject object: map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            smallDeaths.add(new SmallDeath(screen, rect.getX() / AlfredMain.PPM, rect.getY() / AlfredMain.PPM));
        }

    }

    public Array<Knight> getKnights() {
        return knights;
    }
    public Array<Enemy> getEnemies() {
        Array<Enemy> enemies = new Array<Enemy>();
        enemies.addAll(knights);
        enemies.addAll(droyers);
        enemies.addAll(smallDeaths);

        return enemies;
    }
}
