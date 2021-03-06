package com.worldoflearning.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.services.MusicManager.WorldOfLearningMusic;
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;


public class Menu extends AbstractScreen {
	private Image background;
	
	public Menu(final WorldOfLearning game) {
		super(game);
		// set up game input processor to catch back button
		Gdx.input.setCatchBackKey(false);
	}
	
	@Override
	public void show(){
		super.show();
        // retrieve the splash image's region from the atlas
        AtlasRegion splashRegion = getAtlas().findRegion(game.MENU_SCREEN);
        Drawable splashDrawable = new TextureRegionDrawable( splashRegion );

        // here we create the splash image actor; its size is set when the
        // resize() method gets called
        background = new Image( splashDrawable, Scaling.stretch );
        background.setFillParent( true );

        // and finally we add the actor to the stage
        stage.addActor( background );
        
        // play level music
		game.getMusicManager().play(WorldOfLearningMusic.MENU);

        // retrieve the default table actor
        Table table = super.getTable();
        table.columnDefaults(0).padRight(10);
        //table.columnDefaults(2).padLeft(10);
        table.add( " " ).spaceBottom( 300 ).colspan(3);
        table.row();

        // register the button "start game"
        TextButton startGameButton = new TextButton( "Start", getSkin() );
        startGameButton.addListener( new DefaultActorListener() {
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
                super.touchUp( event, x, y, pointer, button );
                game.getSoundManager().play( WorldOfLearningSound.CLICK );
                game.setScreen( new Worlds( game ) );
            }
        } );
        table.add( startGameButton ).fillX().size( 180, 90 ).spaceBottom( 10 ).padRight(10);

        // register the button "options"
        TextButton optionsButton = new TextButton( "Options", getSkin() );
        optionsButton.addListener( new DefaultActorListener() {
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
                super.touchUp( event, x, y, pointer, button );
                game.getSoundManager().play( WorldOfLearningSound.CLICK );
                game.setScreen( new Options( game ) );
            }
        } );
        table.add( optionsButton ).fillX().size( 180, 90 ).spaceBottom( 10 ).padRight(10);

        // register the button "high scores"
        TextButton highScoresButton = new TextButton( "High Scores", getSkin() );
        highScoresButton.addListener( new DefaultActorListener() {
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
                super.touchUp( event, x, y, pointer, button );
                game.getSoundManager().play( WorldOfLearningSound.CLICK );
                game.setScreen( new HighScores( game ) );
            }
        } );
        table.add( highScoresButton ).fillX().size( 180, 90 ).spaceBottom( 10 ).padRight(10);
        
        
        TextButton credits = new TextButton( "Credits", getSkin() );
        credits.addListener( new DefaultActorListener() {
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
                super.touchUp( event, x, y, pointer, button );
                game.getSoundManager().play( WorldOfLearningSound.CLICK );
                game.setScreen( new Credits ( game ) );
            }
        } );
        table.add( credits ).fillX().size( 180, 90 ).spaceBottom( 10 ).padRight(10);
	}
}
