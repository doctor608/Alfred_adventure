package com.alfred.game.Screens;

import com.alfred.game.AlfredMain;
import com.alfred.game.Sprites.Alfred;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class MenuScreen implements Screen {

    private Viewport viewport;
    private Stage stage;

    private Game game;
    public boolean levelButtonTouched, shopButtonTouched;

    public MenuScreen(Game game) {
        this.game = game;
        viewport = new FitViewport(AlfredMain.vir_width, AlfredMain.vir_height, new OrthographicCamera());
        stage = new Stage(viewport, ((AlfredMain) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.CORAL);

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();

        Image levelImg = new Image(new Texture("levelsbutton.png"));
        levelImg.setSize(480, 256);
        levelImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                levelButtonTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                levelButtonTouched = false;
            }
        });
        Image shopImg = new Image(new Texture("shopbutton.png"));
        shopImg.setSize(240,256);
        shopImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                shopButtonTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                shopButtonTouched = false;
            }
        });

        table.left().bottom();
        //table.row().padLeft(16);
        table.add(levelImg).size(levelImg.getWidth(), levelImg.getHeight());
        //table.add(shopImg).size(shopImg.getWidth(), shopImg.getHeight());

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    public boolean isLevelButtonTouched() {
        return levelButtonTouched;
    }

    public boolean isShopButtonTouched() {
        return shopButtonTouched;
    }


    @Override
    public void render(float delta) {
        if (isLevelButtonTouched()) {
            game.setScreen(new LevelsScreen((AlfredMain) game));
            //game.setScreen(new PlayScreen((AlfredMain) game));
            dispose();
        } /*else if (isShopButtonTouched()) {
            game.setScreen(new ShopScreen((AlfredMain) game));
            dispose();
        }*/
        Gdx.gl.glClearColor(66, 177, 18, 1);
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
