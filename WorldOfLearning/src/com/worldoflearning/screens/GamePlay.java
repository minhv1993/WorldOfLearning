package com.worldoflearning.screens;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

public class GamePlay extends AbstractScreen {
	public static final int NUM_OF_TILES = 4;
	public static final int TIME_IN_MILLIS = 31000;
	public static final int SCORE_QUOTA = 1500;
	
	private int targetWorldId;
	private int targetLevelId;
	private int profileId;
	public int scores;
	private Label volumeValue;
	
	private Profile profile;
	private Level level;
	public final ArrayList<Item> levelItems;
	public final Item[] itemTiles;
	
	public ImageButton toMatch;
	public ImageButton tile1;
	public ImageButton tile2;
	public ImageButton tile3;
	public ImageButton tile4;
	
	BitmapFont countDown;
	
	private GameTimer timer;
	public Random rand;
	
	public boolean isPaused;
	
	
	public GamePlay(WorldOfLearning game, int targetWorldId, int targetLevelId) {
		super(game);
		//back key shouldn't exit app
        //Gdx.input.setCatchBackKey(true);
		this.targetWorldId = targetWorldId;
		this.targetLevelId = targetLevelId;
		this.profileId = targetWorldId*4+targetLevelId;
		
		rand = new Random();
		
		profile = game.getProfileManager().retrieveProfile();
		level = game.getLevelManager().findLevelById(targetWorldId, targetLevelId);
		
		levelItems = level.getItems();
		itemTiles = new Item[NUM_OF_TILES];
		
		
		isPaused = false;
		// initialize the toMatch tile
		setUpTiles();
		Item itemToMatch = itemTiles[rand.nextInt(itemTiles.length)];
		toMatch = new ImageButton(new TextureRegionDrawable( getAtlas().findRegion(itemToMatch.getDirectory())));
		toMatch.setName(itemToMatch.getName());
		
		Item itemTile = itemTiles[0];
		tile1 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
		tile1.setName(itemTile.getName());
		addTile1Listener();
		itemTile = itemTiles[1];
		tile2 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
		tile2.setName(itemTile.getName());
		addTile2Listener();
		itemTile = itemTiles[2];
		tile3 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
		tile3.setName(itemTile.getName());
		addTile3Listener();
		itemTile = itemTiles[3];
		tile4 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
		tile4.setName(itemTile.getName());
		addTile4Listener();

		scores = 0;
		timer = new GameTimer(TIME_IN_MILLIS);
		
		countDown = getFont();
		
		// set up game input processor to catch back button
		//Gdx.input.setCatchBackKey(true);
		//GameInputProcessor  inputProcessor = new GameInputProcessor(game, targetWorldId);
		//Gdx.input.setInputProcessor(inputProcessor);
	}
	
	public void addTile1Listener(){
		tile1.addListener(new DefaultActorListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				boolean isCorrect = tile1.getName().equals(toMatch.getName());
				if (!isPaused) {
					if (isCorrect) {
						game.getSoundManager().play(
								WorldOfLearningSound.CORRECT);
						updateTiles(100);
					} else {
						game.getSoundManager().play(WorldOfLearningSound.WRONG);
						updateTiles(0);
					}
				}
			}
		});
	}
	
	public void addTile2Listener(){
		tile2.addListener(new DefaultActorListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				boolean isCorrect = tile2.getName().equals(toMatch.getName());
				if (!isPaused) {
					if (isCorrect) {
						game.getSoundManager().play(
								WorldOfLearningSound.CORRECT);
						updateTiles(100);
					} else {
						game.getSoundManager().play(WorldOfLearningSound.WRONG);
						updateTiles(0);
					}
				}
			}
		});
	}
	
	public void addTile3Listener(){
		tile3.addListener(new DefaultActorListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				boolean isCorrect = tile3.getName().equals(toMatch.getName());
				if (!isPaused) {
					if (isCorrect) {
						game.getSoundManager().play(
								WorldOfLearningSound.CORRECT);
						updateTiles(100);
					} else {
						game.getSoundManager().play(WorldOfLearningSound.WRONG);
						updateTiles(0);
					}
				}
			}
		});
	}
	
	public void addTile4Listener(){
		tile4.addListener(new DefaultActorListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				boolean isCorrect = tile4.getName().equals(toMatch.getName());
				if (!isPaused) {
					if (isCorrect) {
						game.getSoundManager().play(
								WorldOfLearningSound.CORRECT);
						updateTiles(100);
					} else {
						game.getSoundManager().play(WorldOfLearningSound.WRONG);
						updateTiles(0);
					}
				}
			}
		});
	}
	
	public void updateTiles(int score){
		scores += score;
		if(score > 0){
			for(int i = 0; i < NUM_OF_TILES; i++){
				boolean dupe = true;
				while(dupe){
					dupe = false;
					Item tempItem = levelItems.get(rand.nextInt(levelItems.size()));
					for(int j = 0; j < NUM_OF_TILES; j++){
						if(itemTiles[j] != null){
							if(itemTiles[j].getName().equals(tempItem.getName())){
								dupe = true;
							}
						}
					}
					itemTiles[i] = tempItem;
				}
			}
			Item itemTile = itemTiles[0];
			tile1 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
			tile1.setName(itemTile.getName());
			addTile1Listener();
			itemTile = itemTiles[1];
			tile2 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
			tile2.setName(itemTile.getName());
			addTile2Listener();
			itemTile = itemTiles[2];
			tile3 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
			tile3.setName(itemTile.getName());
			addTile3Listener();
			itemTile = itemTiles[3];
			tile4 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
			tile4.setName(itemTile.getName());
			addTile4Listener();
			itemTile = itemTiles[rand.nextInt(GamePlay.NUM_OF_TILES)];
			toMatch = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
			toMatch.setName(itemTile.getName());
		}
		show();
	}
	
	public void setUpTiles(){
		for(int i = 0; i < NUM_OF_TILES; i++){
			boolean dupe = true;
			while(dupe){
				dupe = false;
				Item tempItem = levelItems.get(rand.nextInt(levelItems.size()));
				for(int j = 0; j < NUM_OF_TILES; j++){
					if(itemTiles[j] != null){
						if(itemTiles[j].getName().equals(tempItem.getName())){
							dupe = true;
						}
					}
				}
				itemTiles[i] = tempItem;
			}
		}
	}
	
	@Override
	protected boolean isGameScreen(){
		return true;
	}
	
	@Override
	public void show(){
		super.show();
		stage.clear();
        // retrieve the splash image's region from the atlas
        AtlasRegion splashRegion = getAtlas().findRegion(game.GAME_SCREEN);
        Drawable splashDrawable = new TextureRegionDrawable( splashRegion );
        Image background = new Image( splashDrawable, Scaling.stretch );
        background.setFillParent( true );

        // and finally we add the actor to the stage
        stage.addActor( background );
        // play level music
		game.getMusicManager().play(WorldOfLearningMusic.LEVEL);

		Table table = super.getTable();
		table.clear();
		if(!isPaused) {
			if(timer.getTimeRemainingInSeconds() > 0){	
				table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("skin/game-table-bg.png")))));
				
				Table leftTable = new Table(getSkin());
				leftTable.add("W "+(targetWorldId+1)+" - Lvl "+(targetLevelId+1)).spaceBottom(5);
				leftTable.row();
				leftTable.add("Time: " + timer.getTimeRemainingInSeconds()).spaceBottom(5);
				leftTable.row();
				leftTable.add("Score: " + scores).spaceBottom(7);
				leftTable.row();
				leftTable.add(toMatch).size(80,80);
				leftTable.row();
				leftTable.add(toMatch.getName()).spaceBottom(10);
				leftTable.row();
				final TextButton pauseButton = new TextButton( "Pause" , getSkin() );
				pauseButton.addListener( new DefaultActorListener() {
					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button ){
						super.touchUp( event, x, y, pointer, button );
						game.getSoundManager().play( WorldOfLearningSound.CLICK );
						if (!isPaused) {
							isPaused = true;
							timer.pause();
						} 
					}
				});
				leftTable.add(pauseButton).size(80,20);
				table.add(leftTable).fillX().pad(6).padLeft(20);
				
				Table rightTable = new Table(getSkin());
				rightTable.add(tile1).fillX().size(110, 110).space(7);
				rightTable.add(tile2).fillX().size(110, 110).space(7);
				rightTable.row();
				rightTable.add(tile3).fillX().size(110, 110).space(7);
				rightTable.add(tile4).fillX().size(110, 110).space(7);
				table.add(rightTable).fillX().pad(6).padLeft(40);
				table.row();
			}else if(scores < SCORE_QUOTA){
				table.columnDefaults(0).padRight(5);
				table.columnDefaults(1).padLeft(5);
				table.setBackground(splashDrawable);
				table.row();

				table.add("Level Incomplete").colspan(2).padBottom(10);
				table.row();

				table.add("Required Score:").fillX().left();
				table.add(Integer.toString(SCORE_QUOTA)).fillX().right();
				table.row();

				table.add("Your Score:").fillX().left();
				table.add(Integer.toString(scores)).fillX().right();
				table.row();
				
				TextButton levelsButton = new TextButton( "Levels Select", getSkin() );
				levelsButton.addListener(new DefaultActorListener(){
					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button ){
						super.touchUp( event, x, y, pointer, button );
						game.getSoundManager().play( WorldOfLearningSound.CLICK );
						game.setScreen(new Levels(game, targetWorldId));
						
					}
				});
				TextButton replayButton = new TextButton("Replay", getSkin());
				replayButton.addListener(new DefaultActorListener(){
					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button ){
						super.touchUp( event, x, y, pointer, button );
						game.getSoundManager().play( WorldOfLearningSound.CLICK );
						game.setScreen(new GamePlay(game, targetWorldId, targetLevelId));
						
					}
				});
				table.add(levelsButton).fillX().size(150, 40).padTop(5);
				table.add(replayButton).fillX().size(150, 40).padTop(5);
				table.row();
			}else{
				final int nextLevel = (targetLevelId==3)?((targetWorldId==1)?3:0):(targetLevelId+1);
				final int nextWorld = (targetWorldId==1)?1:((targetLevelId==3)?1:0);
				
				table.columnDefaults(0).padRight(5);
				table.columnDefaults(1).padLeft(5);
				table.setBackground(splashDrawable);
				table.row();

				table.add("Level Completed").colspan(2).padBottom(10);
				table.row();

				table.add("High Score:").fillX().left();
				table.add(profile.notifyScore(profileId, scores)?Integer.toString(scores):Integer.toString(profile.getHighScore(profileId))).fillX().right();
				table.row();

				table.add("Your Score:").fillX().left();
				table.add(Integer.toString(scores)).fillX().right();
				table.row();
				
				if(profile.getCurrentLevelId()<(nextWorld*4+nextLevel))
					profile.setCurrentLevelId(nextWorld*4+nextLevel);
				game.getProfileManager().setProfile(profile);
				game.getProfileManager().persist();
				
				TextButton levelsButton = new TextButton( "Levels Select", getSkin() );
				levelsButton.addListener(new DefaultActorListener(){
					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button ){
						super.touchUp( event, x, y, pointer, button );
						game.getSoundManager().play( WorldOfLearningSound.CLICK );
						game.setScreen(new Levels(game, targetWorldId));
						
					}
				});
				TextButton nextButton = new TextButton("Next Level", getSkin());
				nextButton.addListener(new DefaultActorListener(){
					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button ){
						super.touchUp( event, x, y, pointer, button );
						game.getSoundManager().play( WorldOfLearningSound.CLICK );
						game.setScreen(new Tutorial(game, nextWorld, nextLevel));
						
					}
				});
				table.add(levelsButton).fillX().size(150, 40).padTop(5);
				table.add(nextButton).fillX().size(150, 40).padTop(5);
				table.row();
			}
		}else{
			table.columnDefaults(0).padRight(20);
			table.add("Paused").colspan(3);
			table.setBackground(splashDrawable);
			
			// create the labels widgets
	        final CheckBox soundEffectsCheckbox = new CheckBox( "", getSkin() );
	        soundEffectsCheckbox.setChecked( game.getPreferencesManager().isSoundEnabled() );
	        soundEffectsCheckbox.addListener( new ChangeListener() {
	            @Override
	            public void changed(
	                ChangeEvent event,
	                Actor actor )
	            {
	                boolean enabled = soundEffectsCheckbox.isChecked();
	                game.getPreferencesManager().setSoundEnabled( enabled );
	                game.getSoundManager().setEnabled( enabled );
	                game.getSoundManager().play( WorldOfLearningSound.CLICK );
	            }
	        } );
	        table.row();
	        table.add( "Sound Effects" );
	        table.add( soundEffectsCheckbox ).colspan( 2 ).left();

	        final CheckBox musicCheckbox = new CheckBox( "", getSkin() );
	        musicCheckbox.setChecked( game.getPreferencesManager().isMusicEnabled() );
	        musicCheckbox.addListener( new ChangeListener() {
	            @Override
	            public void changed(
	                ChangeEvent event,
	                Actor actor )
	            {
	                boolean enabled = musicCheckbox.isChecked();
	                game.getPreferencesManager().setMusicEnabled( enabled );
	                game.getMusicManager().setEnabled( enabled );
	                game.getSoundManager().play( WorldOfLearningSound.CLICK );

	                // if the music is now enabled, start playing the menu music
	                if( enabled ) game.getMusicManager().play( WorldOfLearningMusic.MENU );
	            }
	        } );
	        table.row();
	        table.add( "Music" );
	        table.add( musicCheckbox ).colspan( 2 ).left();

	        // range is [0.0,1.0]; step is 0.1f
	        Slider volumeSlider = new Slider( 0f, 1f, 0.1f, false, getSkin());
	        volumeSlider.setValue( game.getPreferencesManager().getVolume() );
	        volumeSlider.addListener( new ChangeListener() {
	            @Override
	            public void changed(ChangeEvent event, Actor actor )
	            {
	                float value = ( (Slider) actor ).getValue();
	                game.getPreferencesManager().setVolume( value );
	                game.getMusicManager().setVolume( value );
	                game.getSoundManager().setVolume( value );
	                updateVolumeLabel();
	            }
	        } );

	        // create the volume label
	        volumeValue = new Label( "", getSkin() );
	        updateVolumeLabel();

	        // add the volume row
	        table.row();
	        table.add( "Volume" );
	        table.add( volumeSlider );
	        table.add( volumeValue ).width( 40 );

	        // register the back button
	        TextButton backButton = new TextButton( "Back to Game", getSkin() );
	        backButton.addListener( new DefaultActorListener() {
	            @Override
	            public void touchUp(InputEvent event, float x, float y, int pointer, int button ) {
	                super.touchUp( event, x, y, pointer, button );
	                game.getSoundManager().play( WorldOfLearningSound.CLICK );
	                isPaused = false;
	                timer.resume();
	                show();
	            }
	        } );
	        table.row();
	        table.add( backButton ).size( 150, 40 ).fillX().padTop(30);
	        
	        TextButton menuButton = new TextButton( "Level Select", getSkin() );
	        menuButton.addListener( new DefaultActorListener() {
	            @Override
	            public void touchUp(InputEvent event, float x, float y, int pointer, int button ) {
	                super.touchUp( event, x, y, pointer, button );
	                game.getSoundManager().play( WorldOfLearningSound.CLICK );
	                game.setScreen( new Levels( game , targetWorldId ) );
	            }
	        } );
	        table.add( menuButton ).size( 150, 40 ).fillX().padTop(30);
	        table.row();
		}
		stage.addActor(table);
	}

    private void updateVolumeLabel()
    {
        float volume = ( game.getPreferencesManager().getVolume() * 100 );
        volumeValue.setText( String.format( Locale.US, "%1.0f%%", volume ) );
    }
}