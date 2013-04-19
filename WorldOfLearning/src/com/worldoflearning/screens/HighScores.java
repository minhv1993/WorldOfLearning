package com.worldoflearning.screens;

import java.util.Map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
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
        // retrieve the splash image's region from the atlas
        AtlasRegion splashRegion = getAtlas().findRegion(game.MENU_BACKGROUND);
        Drawable splashDrawable = new TextureRegionDrawable( splashRegion );
        Image background = new Image( splashDrawable, Scaling.stretch );
        background.setFillParent( true );

        // and finally we add the actor to the stage
        stage.addActor( background );
		
		Table table = super.getTable();
		table.setFillParent(true);
		table.columnDefaults(1).padRight(20);
		table.columnDefaults(2).padLeft(20);

		Label highscoreslabel = new Label("High Scores", getSkin());
		highscoreslabel.setAlignment(Align.center);
		highscoreslabel.setWrap(true);
		highscoreslabel.setScale(3);
		table.add(highscoreslabel).fillX().colspan(4);
		table.row();
		
		Label world1label = new Label("World 2", getSkin());
		world1label.setAlignment(Align.center);
		world1label.setWrap(true);
		world1label.setScale(2);
		Label world2label = new Label("World 1", getSkin());
		world2label.setAlignment(Align.center);
		world2label.setWrap(true);
		world2label.setScale(2);
		table.add(world1label ).fillX().colspan(2);
		table.add(world2label).fillX().colspan(2);
		table.row();
		
		
		Map<Integer,Integer> highscores = profile.getHighScores();
		
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
		
        TextButton backButton = new TextButton( "Main Menu", getSkin() );
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
        table.add( backButton ).size( 200, 50 ).colspan( 4 ).padTop(20);
	}
}
