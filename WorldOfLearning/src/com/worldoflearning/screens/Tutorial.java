package com.worldoflearning.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.domain.GameTimer;
import com.worldoflearning.domain.Item;
import com.worldoflearning.domain.Level;
import com.worldoflearning.domain.Profile;
import com.worldoflearning.services.MusicManager.WorldOfLearningMusic;
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;


public class Tutorial extends AbstractScreen {

	
	private int targetWorldId;
	private int targetLevelId;
	private int scores;
	
	private boolean paused;
	
	private Profile profile;
	private Level level;
	private ArrayList<Item> levelItems;
	private ArrayList<Item> playFieldItems;
	
	private ImageButton toMatch;
	
	private GameTimer timer;
	private GameTimer beginTimer;
	private Random rand;
	private Image background;
	
	
	public Tutorial(WorldOfLearning game, int targetWorldId, int targetLevelId) {
		super(game);

		targetWorldId = this.targetWorldId;
		targetLevelId = this.targetLevelId;
		
	}
	
	
	
	@Override
	public void show(){
		super.show();
		
		game.getMusicManager().play(WorldOfLearningMusic.TUTORIAL);
		AtlasRegion splashRegion = getAtlas().findRegion(game.LEVEL_SCREEN);
        Drawable splashDrawable = new TextureRegionDrawable( splashRegion );
        background = new Image( splashDrawable, Scaling.stretch );
        background.setFillParent( true );
        stage.addActor( background );
		
        Table table = super.getTable();
        table.columnDefaults(0).padRight(10);
        table.columnDefaults(2).padLeft(10);
        table.add( " " ).spaceBottom( 300 ).colspan(2);
        table.row();
		
        TextButton yesButton = new TextButton( "Teach Me!", getSkin() );
        yesButton.addListener( new DefaultActorListener() {
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
                //CREATE TUTORIAL FROM HERE!
            }
        } );
        table.add( yesButton ).fillX().size( 200, 60 ).spaceBottom( 10 ).padRight(10);
		
		
        TextButton noButton = new TextButton( "Skip", getSkin() );
        noButton.addListener( new DefaultActorListener() {
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
                game.setScreen( new GamePlay ( game, targetWorldId, targetLevelId ) );
                //SKIP THE TUTORIAL
                
            }
        } );
        table.add( noButton ).fillX().size( 200, 60 ).spaceBottom( 10 ).padRight(10);
		
		
		
		
		
		
	}	
	
	
}