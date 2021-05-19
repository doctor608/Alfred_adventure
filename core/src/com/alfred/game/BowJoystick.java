package com.alfred.game;

import com.alfred.game.Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BowJoystick {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera camera;

    public boolean isTouched;
    public boolean upTouched, downTouched, rightTouched;

    public BowJoystick() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(AlfredMain.vir_width,AlfredMain.vir_height, camera);
        stage = new Stage(viewport, PlayScreen.batch);

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();


        Image downImg = new Image(new Texture("bowdownjoystick.png"));
        downImg.setSize(38, 38);
        downImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                downTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                downTouched = false;
            }
        });
        Image upImg = new Image(new Texture("bowupjoystick.png"));
        upImg.setSize(38, 38);
        upImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                upTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                upTouched = false;
            }
        });
        Image rightImg = new Image(new Texture("bowrightjoystick.png"));
        rightImg.setSize(38, 38);
        rightImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightTouched = false;
            }
        });

        table.bottom();
        table.add(upImg).size(upImg.getWidth(), upImg.getHeight()).padLeft(38).padBottom(2);
        table.row();
        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight()).padLeft(38).padBottom(2);
        table.row();
        table.add(downImg).size(downImg.getWidth(), downImg.getHeight()).padLeft(38).padBottom(64);

        stage.addActor(table);

        /*
        Table table1 = new Table();
        table1.center().bottom();
        table1.add();
        table1.add();
        table1.add(upImg).size(upImg.getWidth(), upImg.getHeight());
        table1.add();
        stage.addActor(table1);*/

        /* если кнопка прыжок над передвижением
        Table table1 = new Table();
        table1.left().padBottom(168);
        table1.padLeft(64);
        table1.add(upImg).size(upImg.getWidth(), upImg.getHeight());
        stage.addActor(table1);
        */

    }

    public void draw() {
        stage.draw();
    }

    public boolean isUpTouched() {
        return upTouched;
    }

    public boolean isDownTouched() {
        return downTouched;
    }

    public boolean isRightTouched() {
        return rightTouched;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}

