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


public class Joystick {

    private Viewport viewport;
    private Stage stage;
    private OrthographicCamera camera;

    public boolean isTouched;
    public boolean upTouched, leftTouched, rightTouched;
    public boolean bowupTouched, bowdownTouched, bowrightTouched;

    public Joystick() {
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
        table.row().padLeft(2);

        table.add(leftImg).size(leftImg.getWidth(), leftImg.getHeight());
        table.add(rightImg).size(rightImg.getWidth(), rightImg.getHeight());
        table.add(upImg).size(upImg.getWidth(), upImg.getHeight()).padLeft(270);

        stage.addActor(table);


        Table table1 = new Table();

        Image bowdownImg = new Image(new Texture("bowdownjoystick.png"));
        bowdownImg.setSize(38, 38);
        bowdownImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bowdownTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                bowdownTouched = false;
            }
        });
        Image bowupImg = new Image(new Texture("bowupjoystick.png"));
        bowupImg.setSize(38, 38);
        bowupImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bowupTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                bowupTouched = false;
            }
        });
        Image bowrightImg = new Image(new Texture("bowrightjoystick.png"));
        bowrightImg.setSize(38, 38);
        bowrightImg.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                bowrightTouched = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                bowrightTouched = false;
            }
        });

        table1.bottom();
        table1.add(bowupImg).size(bowupImg.getWidth(), bowupImg.getHeight()).padLeft(38).padBottom(2);
        table1.row();
        table1.add(bowrightImg).size(bowrightImg.getWidth(), bowrightImg.getHeight()).padLeft(38).padBottom(2);
        table1.row();
        table1.add(bowdownImg).size(bowdownImg.getWidth(), bowdownImg.getHeight()).padLeft(38).padBottom(64);

        stage.addActor(table1);
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

    public boolean isBowUpTouched() {
        return bowupTouched;
    }

    public boolean isBowDownTouched() {
        return bowdownTouched;
    }

    public boolean isBowRightTouched() {
        return bowrightTouched;
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}






