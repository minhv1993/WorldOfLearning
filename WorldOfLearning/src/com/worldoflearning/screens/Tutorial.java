package com.worldoflearning.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.domain.Item;
import com.worldoflearning.domain.Level;
import com.worldoflearning.services.MusicManager.WorldOfLearningMusic;
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;


public class Tutorial extends AbstractScreen {
	private int targetWorldId;
	private int targetLevelId;
	private Image background;
	private Level level;
	private ArrayList<Item> levelItems;
	private Table table;
	private Table temp;
	private int counter;

	public Tutorial(final WorldOfLearning game, final int targetWorldId, int targetLevelId) {
		super(game);

		this.targetWorldId = targetWorldId;
		this.targetLevelId = targetLevelId ;
		level = game.getLevelManager().findLevelById(targetWorldId, targetLevelId);
		levelItems = level.getItems();
		counter = 0;

		// set up game input processor to catch back button
		Gdx.input.setCatchBackKey(true);
		stage.addListener(new InputListener(){
			@Override
			public boolean keyDown(InputEvent event, int keyCode){
				if (keyCode == (Keys.BACK) || keyCode == (Keys.ESCAPE)){
					game.getSoundManager().play( WorldOfLearningSound.CLICK );
					game.setScreen(new Levels(game, targetWorldId));
				}   
				return true;
			}
		});
	}

	@Override
	public void show(){
		super.show();

		game.getMusicManager().play(WorldOfLearningMusic.TUTORIAL);
		AtlasRegion splashRegion = getAtlas().findRegion(game.GAME_SCREEN);
		Drawable splashDrawable = new TextureRegionDrawable( splashRegion );
		background = new Image( splashDrawable, Scaling.stretch );
		background.setFillParent( true );
		stage.addActor( background );

		table = super.getTable();
		table.columnDefaults(0).padRight(10);
		table.columnDefaults(1).padLeft(10);
		table.add("World " + (targetWorldId+1) + " - Level " + (targetLevelId+1) + " Tutorial").colspan(2).padBottom(20);
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
				table.clear();
				table = setTutorial(table, counter);
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


	private Table setTutorial(Table table, int i) {
		temp = table;


		temp.add(new ImageButton( new TextureRegionDrawable (getAtlas().findRegion(levelItems.get(i).getDirectory())))).fillX();
		temp.add(levelItems.get(i).getName()).fillX().size(100);
		temp.row();
		
		final TextButton yesButton = new TextButton( "NEXT!", getSkin() );
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
				//temp.add(new ImageButton( new TextureRegionDrawable (getAtlas().findRegion(levelItems.get(0).getDirectory())))).center();
				counter++;
				temp.clear();
				//if try to access something no in the array, then end tutorial
				if (counter >= levelItems.size()) {
					//end tutorial
					endTutorial(temp);  
				}else{
					setTutorial(temp, counter);
				}
			}
		} );
		table.add( yesButton ).fillX().size( 200, 60 ).spaceBottom( 10 ).pad(20);


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
		table.add( noButton ).fillX().size( 200, 60 ).spaceBottom( 10 ).pad(20);


		return temp;
	}
	private Table endTutorial (Table table) {
		table = this.table;
		table.defaults().spaceBottom( 30 );
		TextButton backButton = new TextButton( "Continue" , getSkin() );
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
				game.setScreen( new GamePlay ( game, targetWorldId, targetLevelId ) );
			}
		} );
		table.row();
		table.add( backButton ).size( 250, 60 );

		return table;

	}


}