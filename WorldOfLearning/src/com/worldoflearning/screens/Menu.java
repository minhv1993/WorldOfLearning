package com.worldoflearning.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;


public class Menu extends AbstractScreen {

	public Menu(WorldOfLearning game) {
		super(game);
	}
	
	@Override
	public void show(){
		super.show();

        // retrieve the default table actor
        Table table = super.getTable();
        table.add( "Welcome to World Of Learning" ).spaceBottom( 50 );
        table.row();

        // register the button "start game"
        TextButton startGameButton = new TextButton( "Level Select", getSkin() );
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
                game.setScreen( new LevelsSelect( game ) );
            }
        } );
        table.add( startGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
        table.row();

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
        table.add( optionsButton ).uniform().fill().spaceBottom( 10 );
        table.row();

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
        table.add( highScoresButton ).uniform().fill();
	}
}
