package com.worldoflearning.screens;

import java.util.Map;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.domain.Profile;
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;

public class HighScores extends AbstractScreen {
	Profile profile;

	public HighScores(WorldOfLearning game) {
		super(game);
		profile = game.getProfileManager().retrieveProfile();
	}

	public void show() {
		super.show();
		Table table = super.getTable();
		table.columnDefaults(2).padRight(20);
		table.columnDefaults(3).padLeft(20);
		
		table.add("High Scores").fillX().colspan(4);
		table.row();
		
		Map<Integer,Integer> highscores = profile.getHighScores();
		
		table.add("World 1").fillX().colspan(2);
		table.add("World 2").fillX().colspan(2);
		table.row();
		
		int size = highscores.size();
		switch(size){
		case 8:
			for(int i = 0; i < 4; i++){
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(highscores.get(i))).fillX();
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(highscores.get(i+4))).fillX();
				table.row();
			}
			break;
		case 7:
			for(int i = 0; i < 4; i++){
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(highscores.get(i))).fillX();
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(i==3?0:highscores.get(i+4))).fillX();
				table.row();
			}
			break;
		case 6:
			for(int i = 0; i < 4; i++){
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(highscores.get(i))).fillX();
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString((i==3 || i==2)?0:highscores.get(i+4))).fillX();
				table.row();
			}
			break;
		case 5:
			for(int i = 0; i < 4; i++){
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(highscores.get(i))).fillX();
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString((i==3 || i==2 || i==1)?0:highscores.get(i+4))).fillX();
				table.row();
			}
			break;
		case 4:
			for(int i = 0; i < 4; i++){
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(highscores.get(i))).fillX();
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(0)).fillX();
				table.row();
			}
			break;
		case 3:
			for(int i = 0; i < 4; i++){
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString((i==3)?0:highscores.get(i))).fillX();
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(0)).fillX();
				table.row();
			}
			break;
		case 2:
			for(int i = 0; i < 4; i++){
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString((i==3 || i==2)?0:highscores.get(i))).fillX();
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(0)).fillX();
				table.row();
			}
			break;
		case 1:
			for(int i = 0; i < 4; i++){
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString((i==3 || i==2 || i==1)?0:highscores.get(i))).fillX();
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(0)).fillX();
				table.row();
			}
			break;
		default:
			for(int i = 0; i < 4; i++){
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(0)).fillX();
				table.add("Level "+(1+i)+":").fillX();
				table.add(Integer.toString(0)).fillX();
				table.row();
			}
			break;
		}
		
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
        table.add( backButton ).size( 300, 30 ).colspan( 2 ).padTop(20);
	}
}
