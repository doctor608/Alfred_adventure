package com.alfred.game.Screens;

import com.alfred.game.AlfredMain;
import com.alfred.game.Scenes.Hud;
import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameFinishedScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;
    private Preferences prefs;

    public GameFinishedScreen(Game game, int time, int score) {
        this.game = game;
        prefs = Gdx.app.getPreferences("alfred.alfredadventure");
        viewport = new FitViewport(AlfredMain.vir_width, AlfredMain.vir_height, new OrthographicCamera());
        stage = new Stage(viewport, ((AlfredMain) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.CORAL);

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();

        int record = prefs.getInteger("timerecord");
        Label theLabel = new Label("THE TIME RECORD", font);
        Label recordLabel = new Label(String.format("%d", record), font);
        Label yourTimeLabel = new Label("YOUR TIME", font);
        Label timeLabel = new Label(String.format("%d", time), font);
        Label yourScoreLabel = new Label("YOUR SCORE", font);
        Label scoreLabel = new Label(String.format("%d", score), font);

        table.center();
        table.setFillParent(true);

        table.add(theLabel).expandX();
        table.row();
        table.add(recordLabel).expandX().padTop(10f);
        table.row();
        table.add(yourTimeLabel).expandX().padTop(10f);
        table.row();
        table.add(timeLabel).expandX().padTop(10f);
        table.row();
        table.add(yourScoreLabel).expandX().padTop(10f);
        table.row();
        table.add(scoreLabel).expandX().padTop(10f);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        /*if (Gdx.input.justTouched()) {
            Alfred.alfredwon = false;
            //game.setScreen(new MenuScreen((AlfredMain) game));
            dispose();
        }*/
        Gdx.gl.glClearColor(.25f, .68f, .18f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}