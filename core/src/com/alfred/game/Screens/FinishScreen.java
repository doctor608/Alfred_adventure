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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class FinishScreen implements Screen {

    private Viewport viewport;
    private Stage stage;

    private Game game;

    private int level;

    Preferences prefs;

    private int fulltime, fullscore;

    public FinishScreen(Game game, int level) {
        this.game = game;
        this.level = level;
        prefs = Gdx.app.getPreferences("alfred.alfredadventure");
        viewport = new FitViewport(AlfredMain.vir_width, AlfredMain.vir_height, new OrthographicCamera());
        stage = new Stage(viewport, ((AlfredMain) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.CORAL);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        /*
        Label gameOverLabel = new Label("YOU WON", font);
        Label playAgainLabel = new Label("Click to enter a new level", font);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);
         */
        if (level == 1) {
            Label gameOverLabel = new Label("YOU BEAT FIRST LEVEL", font);
            Label timeLabel = new Label(String.format("TIME - %d", Hud.worldTimer), font);
            Label scoreLabel = new Label(String.format("SCORE - %d", Hud.score), font);
            Label playAgainLabel = new Label("Click to enter a second level", font);

            prefs = prefs.putInteger("level1time", Hud.worldTimer);
            prefs = prefs.putInteger("level1score", Hud.score);
            prefs.flush();

            table.add(gameOverLabel).expandX();
            table.row();
            table.add(timeLabel).expandX().padTop(10f);
            table.row();
            table.add(scoreLabel).expandX().padTop(10f);
            table.row();
            table.add(playAgainLabel).expandX().padTop(10f);
        } else if (level == 2) {
            Label gameOverLabel = new Label("YOU BEAT SECOND LEVEL", font);
            Label timeLabel = new Label(String.format("TIME - %d", Hud.worldTimer), font);
            Label scoreLabel = new Label(String.format("SCORE - %d", Hud.score), font);
            Label playAgainLabel = new Label("Click to enter a third level", font);

            prefs = prefs.putInteger("level2time", Hud.worldTimer);
            prefs = prefs.putInteger("level2score", Hud.score);
            prefs.flush();

            table.add(gameOverLabel).expandX();
            table.row();
            table.add(timeLabel).expandX().padTop(10f);
            table.row();
            table.add(scoreLabel).expandX().padTop(10f);
            table.row();
            table.add(playAgainLabel).expandX().padTop(10f);
        } else {
            Label gameOverLabel = new Label("YOU WON", font);
            Label timeLabel = new Label(String.format("TIME - %d", Hud.worldTimer), font);
            Label scoreLabel = new Label(String.format("SCORE - %d", Hud.score), font);

            prefs = prefs.putInteger("level3time", Hud.worldTimer);
            prefs = prefs.putInteger("level3score", Hud.score);
            prefs.flush();

            fulltime = prefs.getInteger("level1time") + prefs.getInteger("level2time") + prefs.getInteger("level3time");
            fullscore = prefs.getInteger("level1score") + prefs.getInteger("level2score") + prefs.getInteger("level3score");

            Label fullTimeLabel =  new Label(String.format("FULL TIME - %d", fulltime), font);
            Label fullScoreLabel =  new Label(String.format("FULL SCORE - %d", fullscore), font);

            if (prefs.getInteger("level1time") != 0 && prefs.getInteger("level2time") != 0 && prefs.getInteger("level3time") != 0) {
                if (prefs.getInteger("timerecord") != 0) {
                    if (fulltime < prefs.getInteger("timerecord")) {
                        prefs.putInteger("timerecord", fulltime);
                        prefs.flush();
                        Label recordLabel =  new Label("NEW RECORD!", font);
                        table.add(recordLabel).expandX();
                        table.row();
                    }
                } else {
                    prefs.putInteger("timerecord", fulltime);
                    prefs.flush();
                    Label recordLabel =  new Label("NEW RECORD!", font);
                    table.add(recordLabel).expandX();
                    table.row();
                }
            }

            table.add(gameOverLabel).expandX();
            table.row();
            table.add(timeLabel).expandX().padTop(10f);
            table.row();
            table.add(scoreLabel).expandX().padTop(10f);
            table.row();
            table.add(fullTimeLabel).expandX().padTop(10f);
            table.row();
            table.add(fullScoreLabel).expandX().padTop(10f);
        }

        stage.addActor(table);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            Alfred.alfredwon = false;
            if (level == 3) {
                game.setScreen(new GameFinishedScreen((AlfredMain) game, fulltime, fullscore));
            } else {
                game.setScreen(new PlayScreen((AlfredMain) game, level + 1));
            }
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
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