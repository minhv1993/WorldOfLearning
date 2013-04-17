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
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;

public class Worlds extends AbstractScreen {
	private Profile profile;
	private Image background;
	
	private TextButton world1Button;
	private TextButton world2Button;
	
	private WorldSelectListener worldSelectListener;

	public Worlds(WorldOfLearning game) {
		super(game);
		worldSelectListener = new WorldSelectListener();
	}
	
	@Override
	public void show(){
		super.show();
		// retrieve the splash image's region from the atlas
        AtlasRegion splashRegion = getAtlas().findRegion(game.SPLASH_SCREEN);
        Drawable splashDrawable = new TextureRegionDrawable( splashRegion );

        // here we create the splash image actor; its size is set when the
        // resize() method gets called
        background = new Image( splashDrawable, Scaling.stretch );
        background.setFillParent( true );

        // and finally we add the actor to the stage
        stage.addActor( background );
        
        // retrieve the table's layout
        profile = game.getProfileManager().retrieveProfile();
		
        // retrieve the default table actor
        Table table = super.getTable();
        table.add( "Worlds Select" ).spaceBottom( 50 );
        table.row();

        // register the button "World 1"
        world1Button = new TextButton( "World 1", getSkin() );
        world1Button.addListener( worldSelectListener);
        table.add( world1Button ).size( 300, 60 ).uniform().spaceBottom( 10 );
        table.row();

        // register the button "World 2"
        world2Button = new TextButton( "World 2", getSkin() );
        world2Button.addListener(worldSelectListener);
        table.add( world2Button ).uniform().fill().spaceBottom( 10 );
        table.row();

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
        table.add( mainMenuButton ).uniform().fill();
	}
	
	private class WorldSelectListener extends DefaultActorListener
	{
		 @Override
		 public void touchUp(InputEvent event, float x, float y, int pointer, int button ){
            super.touchUp( event, x, y, pointer, button );
            game.getSoundManager().play( WorldOfLearningSound.CLICK );

            // find the target level ID
            int targetWorldId = - 1;
            Actor actor = event.getListenerActor();
            if( actor == world1Button ) {
            	targetWorldId = 0;
            } else if( actor == world2Button ) {
            	targetWorldId = 1;
            } else {
                return;
            }

            // check the current level ID
            if( profile.getCurrentWorldId() >= targetWorldId ) {
                Gdx.app.log( WorldOfLearning.LOG, "Starting world: " + targetWorldId );
                game.setScreen( new Levels( game, targetWorldId ) );
            } else {
                Gdx.app.log( WorldOfLearning.LOG, "Unable to start world: " + targetWorldId );
            }
        }
	}
}
