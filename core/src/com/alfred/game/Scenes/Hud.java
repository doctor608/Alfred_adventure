package com.alfred.game.Scenes;

import com.alfred.game.AlfredMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import javax.swing.text.View;

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    private int worldTimer;
    private float timeCount;
    private static int score;

    private Label countTimeLabel;
    private static Label scoreLabel;
    private Label emptyLabel1;
    private Label emptyLabel2;

    //Label worldLabel;
    //Label timeLabel;
    //Label levelLabel
    //Label alfredLabel;

    public Hud(SpriteBatch batch) {
        worldTimer = 0;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(AlfredMain.vir_width, AlfredMain.vir_height, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countTimeLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        scoreLabel = new Label(String.format("%d", score), new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        emptyLabel1 = new Label(" ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        emptyLabel2 = new Label(" ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //worldLabel = new Label(" ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //alfredLabel = new Label("ALFRED", new Label.LabelStyle(new BitmapFont(), Color.GREEN));

        table.add(emptyLabel1).expandX().padTop(10);
        table.add(countTimeLabel).expandX().padTop(10);
        //table.add(emptyLabel2).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer++;
            countTimeLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value) {
        score = score + value;
        scoreLabel.setText(String.format("%d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}