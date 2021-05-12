package com.alfred.game.Tools;

import com.alfred.game.AlfredMain;
import com.alfred.game.Screens.PlayScreen;
import com.alfred.game.Sprites.BadGround;
import com.alfred.game.Sprites.BrokenGround;
import com.alfred.game.Sprites.Coin;
import com.alfred.game.Sprites.Knight;
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

import org.graalvm.compiler.debug.CSVUtil;

public class B2WorldCreator {

    private Array<Knight> knights;

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
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(screen, rect);
        }

        for (MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new BadGround(screen, rect);
        }

        for (MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new BrokenGround(screen, rect);
        }

        knights = new Array<Knight>();
        for (MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            knights.add(new Knight(screen, rect.getX() / AlfredMain.PPM, rect.getY() / AlfredMain.PPM));
        }
    }

    public Array<Knight> getKnights() {
        return knights;
    }
}
