package com.alfred.game.Screens;

import com.alfred.game.AlfredMain;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

public class LevelsScreen implements Screen {
    private Viewport viewport;
    private Stage stage;

    private Game game;
    public boolean firstButtonTouched, secondButtonTouched, thirdButtonTouched;

    public LevelsScreen(Game game) {
        this.game = game;
        viewport = new FitViewport(AlfredMain.vir_width, AlfredMain.vir_height, new OrthographicCamera());
        stage = new Stage(viewport, ((AlfredMain) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.CORAL);

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();


        Image firstImg = new Image(new Texture("firstbutton.png"));
        firstImg.setSize(58, 64);
        firstImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                firstButtonTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                firstButtonTouched = false;
            }
        });
        Image secondImg = new Image(new Texture("secondbutton.png"));
        secondImg.setSize(58, 64);
        secondImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                secondButtonTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                secondButtonTouched = false;
            }
        });
        Image thirdImg = new Image(new Texture("thirdbutton.png"));
        thirdImg.setSize(58, 64);
        thirdImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                thirdButtonTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                thirdButtonTouched = false;
            }
        });

        //table.top();

        table.padLeft(420);
        table.row().padLeft(64).padBottom(256);
        table.add(firstImg).size(firstImg.getWidth(), firstImg.getHeight());
        table.add(secondImg).size(secondImg.getWidth(), secondImg.getHeight());
        table.add(thirdImg).size(thirdImg.getWidth(), thirdImg.getHeight());
        /*
        table.left().bottom();
        //table.row().padLeft(16);
        table.add(levelImg).size(levelImg.getWidth(), levelImg.getHeight());
        table.add(shopImg).size(shopImg.getWidth(), shopImg.getHeight());
        */

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    public boolean isFirstButtonTouched() {
        return firstButtonTouched;
    }

    public boolean isSecondButtonTouched() {
        return secondButtonTouched;
    }

    public boolean isThirdButtonTouched() {
        return thirdButtonTouched;
    }


    @Override
    public void render(float delta) {
        if (isFirstButtonTouched()) {
            game.setScreen(new PlayScreen((AlfredMain) game, 1));
            dispose();
        } else if (isSecondButtonTouched()) {
            game.setScreen(new PlayScreen((AlfredMain) game, 2));
            dispose();
        } else if (isThirdButtonTouched()) {
            game.setScreen(new PlayScreen((AlfredMain) game, 3));
            dispose();
        }

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
