package com.alfred.game.Screens;

import com.alfred.game.Joystick;
import com.alfred.game.Sprites.Alfred;
import com.alfred.game.Sprites.Enemies.Enemy;
import com.alfred.game.Sprites.Items.Arrow;
import com.alfred.game.Sprites.Items.BlackRaven;
import com.alfred.game.Sprites.Items.BlackRose;
import com.alfred.game.Sprites.Items.DroyerBullet;
import com.alfred.game.Sprites.Items.Item;
import com.alfred.game.Sprites.Items.ItemDef;
import com.alfred.game.Tools.B2WorldCreator;
import com.alfred.game.Tools.WorldContactListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;

import java.util.concurrent.LinkedBlockingQueue;

public class PlayScreen implements Screen {

    private AlfredMain game;
    private TextureAtlas atlas;

    private Alfred player;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    //private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;

    private Array<Item> items;
    private LinkedBlockingQueue<ItemDef> itemsToSpawn;

    public Joystick joystick;
    public static SpriteBatch batch;

    private Music music;

    private Sound bowsound;

    private int level;

    //Preferences prefs = Gdx.app.getPreferences("My Preferences");

    public PlayScreen(AlfredMain game, int level) {
        atlas = new TextureAtlas("global.pack");

        this.game = game;
        this.level = level;

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(game.vir_width / AlfredMain.PPM, game.vir_height / AlfredMain.PPM, gamecam);
        hud = new Hud(game.batch);
        batch = game.batch;

        mapLoader = new TmxMapLoader();
        if (level == 1) {
            music = AlfredMain.manager.get("audio/music/classic_music.ogg", Music.class);
            music.setLooping(true);
            music.setVolume(0.03f);
            music.play();
            map = mapLoader.load(/*"level_1.tmx"*/"Level1.tmx");
        } else if (level == 2) {
            music = AlfredMain.manager.get("audio/music/level2_music.ogg", Music.class);
            music.setLooping(true);
            music.setVolume(0.03f);
            music.play();
            map = mapLoader.load("Level2.tmx");
        } else if (level == 3) {
            music = AlfredMain.manager.get("audio/music/level3_music.ogg", Music.class);
            music.setLooping(true);
            music.setVolume(0.03f);
            music.play();
            map = mapLoader.load("Level3.tmx");
        }

        renderer = new OrthogonalTiledMapRenderer(map, 1f / AlfredMain.PPM);

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);
        //b2dr = new Box2DDebugRenderer();

        creator = new B2WorldCreator(this);

        player = new Alfred(this);

        world.setContactListener(new WorldContactListener());

        //music = AlfredMain.manager.get("audio/music/classic_music.ogg", Music.class);
        //music.setLooping(true);
        //music.setVolume(0.3f);

        items = new Array<Item>();
        itemsToSpawn = new LinkedBlockingQueue<ItemDef>();

        joystick = new Joystick();

        bowsound = AlfredMain.manager.get("audio/sounds/bowandarrow.wav", Sound.class);
    }

    public void spawnItem(ItemDef idef) {
        itemsToSpawn.add(idef);
    }

    public void handleSpawningItems() {
        if (!itemsToSpawn.isEmpty()) {
            ItemDef idef = itemsToSpawn.poll();
            if (idef.type == BlackRose.class) {
                items.add(new BlackRose(this, idef.position.x, idef.position.y));
            } else if (idef.type == DroyerBullet.class) {
                items.add(new DroyerBullet(this, idef.position.x, idef.position.y));
            } else if (idef.type == BlackRaven.class) {
                items.add(new BlackRaven(this, idef.position.x, idef.position.y));
            } else if(idef.type == Arrow.class) {
                items.add(new Arrow(this, idef.position.x, idef.position.y));
            }
        }
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        if (joystick.isRightTouched()) {
            if (!(player.b2body.getLinearVelocity().x > 2f)) {
                player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
            }
            //player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if (joystick.isLeftTouched()) {
            if (!(player.b2body.getLinearVelocity().x < -2f)) {
                player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
            }
            //player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
        if (joystick.isUpTouched() && !player.jumped && !player.isDead()) {
            player.b2body.applyLinearImpulse(new Vector2(0, 5f), player.b2body.getWorldCenter(), true);
        }

        if (joystick.isBowRightTouched()) {
            Gdx.app.log("RIGHT", "SHOT");
            joystick.bowrightTouched = false;
            player.jumped = true;
            if (player.isRunBowRightShotAnimation() == false) {
                player.bowShotRight();
                bowsound.setVolume(bowsound.play(), 0.1f);
            }
        }
        if (joystick.isBowDownTouched()) {
            Gdx.app.log("DOWN", "SHOT");
            joystick.bowdownTouched = false;
            player.jumped = true;
            if (player.isRunBowDownShotAnimation() == false) {
                player.bowShotDown();
                bowsound.setVolume(bowsound.play(), 0.1f);
            }
        }
        if (joystick.isBowUpTouched()) {
            Gdx.app.log("UP", "SHOT");
            joystick.bowupTouched = false;
            player.jumped = true;
            if (player.isRunBowUpShotAnimation() == false) {
                player.bowUpShot();
                bowsound.setVolume(bowsound.play(), 0.1f);
            }
        }


        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)/* && !player.jumped*/ && !player.isDead())
            player.b2body.applyLinearImpulse(new Vector2(0, 5f), player.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);

    }

    public void update(float dt) {
        handleInput(dt);
        handleSpawningItems();

        world.step(1/60f, 6, 2);

        player.update(dt);
        for (Enemy enemy: creator.getEnemies()) {
            enemy.update(dt);
            if (enemy.getX() < player.getX() + 352 / AlfredMain.PPM) {
                enemy.b2body.setActive(true);
            }
        }
        for (Item item: items) {
            item.update(dt);
        }

        hud.update(dt);

        gamecam.position.x = player.b2body.getPosition().x;

        gamecam.update();
        renderer.setView(gamecam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        //b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        player.draw(game.batch);

        for (Enemy enemy: creator.getEnemies()) {
            enemy.draw(game.batch);
        }
        for (Item item: items) {
            item.draw(game.batch);
        }

        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        joystick.draw();

        if(gameOver()){
            game.setScreen(new GameOverScreen(game, Alfred.judgment, level));
            dispose();
        }
        if(alfredFinish()){
            music.stop();
            game.setScreen(new FinishScreen(game, level));
        }
    }

    public boolean gameOver(){
        if(player.currentState == Alfred.State.DEAD && player.getStateTimer() > 1.5){
            return true;
        }
        return false;
    }

    public boolean alfredFinish() {
        if (Alfred.alfredwon == true) {
            return true;
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        joystick.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public TiledMap getMap() {
        return map;
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        //b2dr.dispose();
        hud.dispose();

    }
}