package com.worldoflearning.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.worldoflearning.screens.Menu;
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;
import com.worldoflearning.WorldOfLearning;

public class HighScores extends AbstractScreen {

	public HighScores(WorldOfLearning game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	public void show() {
		super.show();
		Table table = super.getTable();
        table.defaults().spaceBottom( 30 );
        TextButton backButton = new TextButton( "Back to main menu", getSkin() );
        backButton.addListener( new DefaultActorListener() {
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
                game.setScreen( new Menu( game ) );
            }
        } );
        table.row();
        table.add( backButton ).size( 250, 60 ).colspan( 2 );
	}
}
