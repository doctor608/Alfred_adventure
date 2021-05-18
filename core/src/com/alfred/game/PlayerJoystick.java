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


public class PlayerJoystick {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera camera;

    public boolean isTouched;
    public boolean upTouched, leftTouched, rightTouched;

    public PlayerJoystick() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(AlfredMain.vir_width,AlfredMain.vir_height, camera);
        stage = new Stage(viewport, PlayScreen.batch);

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();


        Image leftImg = new Image(new Texture("leftjoystick.png"));
        leftImg.setSize(80, 60);
        leftImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftTouched = false;
            }
        });
        Image upImg = new Image(new Texture("upjoystick.png"));
        upImg.setSize(48, 64);
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
        Image rightImg = new Image(new Texture("rightjoystick.png"));
        rightImg.setSize(80, 60);
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

        table.left().bottom();
        table.row().padLeft(16);

        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add();
        table.add(upImg).size(upImg.getWidth(), upImg.getHeight());

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

    public boolean isLeftTouched() {
        return leftTouched;
    }

    public boolean isRightTouched() {
        return rightTouched;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}






