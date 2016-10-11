package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.SampleGame;
import com.mygdx.util.Declerations;
//import com.seg3125.project.Declerations;

//import com.seg3125.project.GameApp;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MainMenu implements Screen
{
    private SampleGame app;
    public Stage stage;

    private Image images[];
    private TextButton textButtons[];
    private Declerations declerations;

    public MainMenu(SampleGame gameApp)
    {
        app = gameApp;
        Viewport viewport = new FillViewport(SampleGame.V_WIDTH, SampleGame.V_HEIGHT, gameApp.camera);
        stage = new Stage(viewport, app.batch);
        Gdx.input.setInputProcessor(stage);

        declerations = new Declerations();
        declerations.initMainMenu();

        textButtons = new TextButton[4];
        images = new Image[4];

        for(int i=0; i<4; i++)
        {
            images[i] = new Image();
        }

        //draws the background, how to play instructions and title
        images[0] = new Image(declerations.mainMenuSkins[1],"background");
        images[0].setSize(SampleGame.V_WIDTH,SampleGame.V_HEIGHT);
        images[0].setBounds(0,0, SampleGame.V_WIDTH, SampleGame.V_HEIGHT);
        stage.addActor(images[0]);

        images[1] = new Image(declerations.mainMenuSkins[3],"howToPlay");
        images[1].setSize(SampleGame.V_WIDTH,SampleGame.V_HEIGHT);
        images[1].setBounds(0,0, SampleGame.V_WIDTH, SampleGame.V_HEIGHT);

        images[2] = new Image(declerations.mainMenuSkins[2],"title");
        images[2].setSize(SampleGame.V_WIDTH,SampleGame.V_HEIGHT);
        images[2].setBounds(50, (SampleGame.V_HEIGHT/2)-95, 150 ,200);
        stage.addActor( images[2]);


        //for the buttons
        declerations.mainMenutextButtonStyles[0].up = declerations.mainMenuSkins[0].getDrawable("playButtonUnpressed");
        declerations.mainMenutextButtonStyles[0].down = declerations.mainMenuSkins[0].getDrawable("playButtonPresed");
        declerations.mainMenutextButtonStyles[0].checked = declerations.mainMenuSkins[0].getDrawable("playButtonPresed");
        declerations.mainMenutextButtonStyles[1].up = declerations.mainMenuSkins[0].getDrawable("instructionsButtonUnpressed");
        declerations.mainMenutextButtonStyles[1].down = declerations.mainMenuSkins[0].getDrawable("instructionsButtonPressed");
        declerations.mainMenutextButtonStyles[1].checked = declerations.mainMenuSkins[0].getDrawable("instructionsButtonPressed");

        declerations.mainMenutextButtonStyles[2].up = declerations.mainMenuSkins[0].getDrawable("backButtonUnpressed");
        declerations.mainMenutextButtonStyles[2].down = declerations.mainMenuSkins[0].getDrawable("backButtonPresed");
        declerations.mainMenutextButtonStyles[2].checked = declerations.mainMenuSkins[0].getDrawable("backButtonPresed");
        textButtons[2] = new TextButton("", declerations.mainMenutextButtonStyles[2]);
        textButtons[2].setBounds((400 ),35,50,205);

        for(int i=0; i<2; i++)
        {
            int shift = 75 * i;
            textButtons[i] = new TextButton("", declerations.mainMenutextButtonStyles[i]);
            textButtons[i].setBounds((275 + shift),40,50,200);
            stage.addActor(textButtons[i]);
        }


        //button listeners
        textButtons[0].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {

                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(app));

            }
        });

        textButtons[1].addListener(new ChangeListener()
        {
            @Override
            public void changed (ChangeEvent event, Actor actor)
            {

                //remove the play and instructions buttons and only show the
                //how to play instruction image
                images[0].remove();
                textButtons[0].remove();
                textButtons[1].remove();

                stage.addActor(images[1]);
                images[2].remove();

                stage.addActor(textButtons[2]);

            }
        });

        textButtons[2].addListener(new ChangeListener()
        {

            @Override
            public void changed(ChangeEvent event, Actor actor)
            {

                //reload everything but the how to play image
                images[1].remove();
                textButtons[2].remove();

                stage.addActor(images[0]);
                stage.addActor(images[2]);
                stage.addActor(textButtons[0]);
                stage.addActor(textButtons[1]);

                //set the button states back to normal
                textButtons[1].setChecked(false);
                textButtons[2].setChecked(false);
            }
        });

    }

    @Override
    public void show()
    {
    }

    public void update(float delta)
    {
        app.camera.update();
        stage.act(delta);
    }

    @Override
    public void render(float delta)
    {
        update(delta);
        Gdx.gl.glClearColor(0.9f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

    }



    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {    }

    @Override
    public void resume() {  }

    @Override
    public void hide() {
        stage.clear();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
        //TODO: add dispose methods for Grid and GamePiece
    }
}
