package com.alfred.game.Scenes;

import com.alfred.game.AlfredMain;

import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import javax.swing.text.View;

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    public static int worldTimer;
    private float timeCount;
    public static int score;
    private static int hp;

    private Label countTimeLabel;
    private static Label scoreLabel;
    private static Label hpLabel;
    private Label emptyLabel1;
    private Label emptyLabel2;

    public Hud(SpriteBatch batch) {
        worldTimer = 0;
        timeCount = 0;
        score = 0;
        hp = 50;

        viewport = new FitViewport(AlfredMain.vir_width, AlfredMain.vir_height, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Image heartImg = new Image(new Texture("heart.png"));
        heartImg.setSize(32, 32);

        Image coinImg = new Image(new Texture("coin_z.png"));
        coinImg.setSize(32, 32);

        hpLabel = new Label(String.format(": %d", hp), new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        countTimeLabel = new Label(String.format("%d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        scoreLabel = new Label(String.format(":%02d", score), new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        emptyLabel1 = new Label(" ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        emptyLabel2 = new Label(" ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //worldLabel = new Label(" ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //alfredLabel = new Label("ALFRED", new Label.LabelStyle(new BitmapFont(), Color.GREEN));

        table.add(heartImg).size(heartImg.getWidth(), heartImg.getHeight());
        table.add(hpLabel).padTop(10);
        table.add(countTimeLabel).padTop(10).expandX();
        //table.add(emptyLabel2).expandX().padTop(10);
        table.add(coinImg).size(coinImg.getWidth(), coinImg.getHeight()).padTop(10);
        table.add(scoreLabel).padTop(10).padLeft(2).padRight(4);

        stage.addActor(table);
    }

    public void update(float dt) {
        timeCount += dt;
        if (timeCount >= 1) {
            worldTimer++;
            countTimeLabel.setText(String.format("%d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value) {
        score = score + value;
        scoreLabel.setText(String.format(":%02d", score));
    }

    public static void addHp(int value) {
        if (hp + value > 50) {
            hp = 50;
        } else {
            hp = hp + value;
        }
        hpLabel.setText(String.format(": %d", hp));
    }

    public static void downHp(int value) {
        if (hp - value < 0) {
            hp = 0;
        } else {
            hp = hp - value;
        }
        hpLabel.setText(String.format(": %d", hp));
    }

    public static void setHp(int health) {
        hp = health;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}