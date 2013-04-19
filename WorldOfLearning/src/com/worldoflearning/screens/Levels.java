package com.worldoflearning.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.domain.Profile;
import com.worldoflearning.services.MusicManager.WorldOfLearningMusic;
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;

public class Levels extends AbstractScreen {
	private Image background;
	private int targetWorldId;
	private Profile profile;
	
	private TextButton level1Button;
	private TextButton level2Button;
	private TextButton level3Button;
	private TextButton level4Button;
	
	private LevelSelectListener levelSelectListener;
	
	public Levels(WorldOfLearning game, int targetWorldId) {
		super(game);
		this.targetWorldId = targetWorldId;
		levelSelectListener = new LevelSelectListener();
	}
	
	@Override
	public void show(){
		super.show();
        // Retrieve the splash image's region from the atlas
        AtlasRegion splashRegion = getAtlas().findRegion(game.LEVEL_SCREEN);
        Drawable splashDrawable = new TextureRegionDrawable( splashRegion );

        // here we create the splash image actor; its size is set when the
        // resize() method gets called
        background = new Image( splashDrawable, Scaling.stretch );
        background.setFillParent( true );
        
        // and finally we add the actor to the stage
        stage.addActor( background );

        // play level music
		game.getMusicManager().play(WorldOfLearningMusic.MENU);

        // retrieve the profile
        profile = game.getProfileManager().retrieveProfile();
        
        // Retrieve default table
        Table table = super.getTable();
        table.defaults().spaceBottom(20);
        table.columnDefaults(0).padRight(20);
        table.columnDefaults(1).padLeft(20);
        table.add("World "+(targetWorldId+1)).colspan(2).spaceBottom(100);
        
        table.row();
        
        level1Button = new TextButton("Level 1", getSkin());
        level1Button.addListener(levelSelectListener);
        table.add(level1Button).fillX().size( 300, 60 ).padRight(10);
        
        level2Button = new TextButton("Level 2", getSkin());
        level2Button.addListener(levelSelectListener);
        table.add(level2Button).fillX().size( 300, 60 ).padRight(10);
        
        table.row();
        
        level3Button = new TextButton("Level 3", getSkin());
        level3Button.addListener(levelSelectListener);
        table.add(level3Button).fillX().size( 300, 60 ).padRight(10);
        
        level4Button = new TextButton("Level 4", getSkin());
        level4Button.addListener(levelSelectListener);
        table.add(level4Button).fillX().size( 300, 60 ).padRight(10);
        
        table.row();
        // register the button "Worlds Select"
        TextButton worldSelectButton = new TextButton( "Worlds Select", getSkin() );
        worldSelectButton.addListener( new DefaultActorListener() {
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
        table.add( worldSelectButton ).fillX().size( 300, 60 ).padRight(10);
        
        // register the button "Main Menu"
        TextButton mainMenuButton = new TextButton( "Main Menu", getSkin() );
        mainMenuButton.addListener( new DefaultActorListener() {
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
        table.add( mainMenuButton ).fillX().size( 300, 60 ).padRight(10);
	}
	
	private class LevelSelectListener extends DefaultActorListener{
		@Override
		public void touchUp(InputEvent event, float x,float y,int pointer,int button ){
		    super.touchUp( event, x, y, pointer, button );
		    game.getSoundManager().play( WorldOfLearningSound.CLICK );
		
		    // find the target level ID
		    int targetLevelId = - 1;
		    Actor actor = event.getListenerActor();
		    if( actor == level1Button) {
		        targetLevelId = 0;
		    } else if( actor == level2Button ) {
		        targetLevelId = 1;
		    } else if( actor == level3Button ) {
		        targetLevelId = 2;
		    } else if( actor == level4Button ) {
		        targetLevelId = 3;
		    }else {
		        return;
		    }
		
		    // check the current level ID
	    	if(profile.getCurrentLevelId() >= (targetWorldId*4+targetLevelId)){
		        Gdx.app.log( WorldOfLearning.LOG, "Starting level: " + (targetLevelId+1));
		        //game.setScreen( new GamePlay( game, targetWorldId, targetLevelId ) );
		        game.setScreen( new Tutorial( game, targetWorldId, targetLevelId ) );
		    } else {
		        Gdx.app.log( WorldOfLearning.LOG, "Unable to start level: " + (targetLevelId+1));
		    }
		}
	}
}
