package com.worldoflearning.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
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

public class GamePlay extends AbstractScreen {
	public static final int NUM_OF_TILES = 4;
	
	private int targetWorldId;
	private int targetLevelId;
	private int scores;
	
	private boolean paused;
	
	private Profile profile;
	private Level level;
	public final ArrayList<Item> levelItems;
	public final Item[] itemTiles;
	
	public ImageButton toMatch;
	public ImageButton tile1;
	public ImageButton tile2;
	public ImageButton tile3;
	public ImageButton tile4;
	
	public TextButton optionButton;
	
	private GameTimer timer;
	private GameTimer beginTimer;
	public Random rand;
	
	public GamePlay(WorldOfLearning game, int targetWorldId, int targetLevelId) {
		super(game);
		//back key shouldn't exit app
        //Gdx.input.setCatchBackKey(true);
		rand = new Random();
		
		profile = game.getProfileManager().retrieveProfile();
		level = game.getLevelManager().findLevelById(targetWorldId, targetLevelId);
		
		levelItems = level.getItems();
		itemTiles = new Item[NUM_OF_TILES];
		timer = new GameTimer(10000);
		beginTimer = new GameTimer(4000);
		
		scores = 0;
		
		// set up game input processor to catch back button
		//Gdx.input.setCatchBackKey(true);
		//GameInputProcessor  inputProcessor = new GameInputProcessor(game, targetWorldId);
		//Gdx.input.setInputProcessor(inputProcessor);
	}
	
	public void addTile1Listener(){
		tile1.addListener(new DefaultActorListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				boolean isCorrect = ((GamePlay) game.getScreen()).tile1.getName().equals(((GamePlay) game.getScreen()).toMatch.getName());
				if(isCorrect){
					game.getSoundManager().play(WorldOfLearningSound.CORRECT);
					((GamePlay) game.getScreen()).show();
				} else{
					game.getSoundManager().play(WorldOfLearningSound.WRONG);
				}
			}
		});
	}
	
	public void addTile2Listener(){
		tile2.addListener(new DefaultActorListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				boolean isCorrect = ((GamePlay) game.getScreen()).tile2.getName().equals(((GamePlay) game.getScreen()).toMatch.getName());
				if(isCorrect){
					game.getSoundManager().play(WorldOfLearningSound.CORRECT);
					((GamePlay) game.getScreen()).show();
				} else{
					((GamePlay) game.getScreen()).game.getSoundManager().play(WorldOfLearningSound.WRONG);
				}
			}
		});
	}
	
	public void addTile3Listener(){
		tile3.addListener(new DefaultActorListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				boolean isCorrect = ((GamePlay) game.getScreen()).tile3.getName().equals(((GamePlay) game.getScreen()).toMatch.getName());
				if(isCorrect){
					game.getSoundManager().play(WorldOfLearningSound.CORRECT);
					((GamePlay) game.getScreen()).show();
				} else{
					game.getSoundManager().play(WorldOfLearningSound.WRONG);
				}
			}
		});
	}
	
	
	public void addTile4Listener(){
		tile4.addListener(new DefaultActorListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button){
				boolean isCorrect = ((GamePlay) game.getScreen()).tile4.getName().equals(((GamePlay) game.getScreen()).toMatch.getName());
				if(isCorrect){
					game.getSoundManager().play(WorldOfLearningSound.CORRECT);
					((GamePlay) game.getScreen()).show();
				} else{
					game.getSoundManager().play(WorldOfLearningSound.WRONG);
				}
			}
		});
	}
	
	public void setUpTiles(){
		for(int i = 0; i < 4; i++){
			itemTiles[i] = levelItems.get(rand.nextInt(levelItems.size()));
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
		
		// initialize the toMatch tile
		setUpTiles();
		Item itemToMatch = itemTiles[rand.nextInt(itemTiles.length)];
		toMatch = new ImageButton(new TextureRegionDrawable( getAtlas().findRegion(itemToMatch.getDirectory())));
		toMatch.setName(itemToMatch.getName());
		
		Table table = super.getTable();
		table.clear();
		table.columnDefaults(0).padLeft(5);
		table.columnDefaults(6).padRight(5);
		
		for(int y = 0; y < 3; y++){
			table.row();
			for(int x = 0; x < 4; x++){
				if(x == 0){
					if(y == 0){
						table.add( "Time" ).fillX();
						table.add(Integer.toString(timer.getTimeRemainingInSeconds())).fillX();
						table.add( "Score" ).fillX();
						table.add( Integer.toString(scores)).fillX();
					} else if (y == 1){
						table.add( toMatch ).fillX().size(100,100).colspan(2).padRight(20);
					} else if (y == 2){
						TextButton pauseButton = new TextButton( "Pause", getSkin() );
						
						//Temporary listener for debugging purposes.  Send back to levels.
						pauseButton.addListener( new DefaultActorListener() {
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
						
						table.add(pauseButton).fillX();
						optionButton = new TextButton( "Option", getSkin() );
						optionButton.addListener( new DefaultActorListener() {
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
				            }
				        } );
						table.add(optionButton).fillX().padRight(20);
					}
				}else if (x >1){
					if(y > 0){
						if(y == 1){
							if(x == 2){
								Item itemTile = itemTiles[0];
								tile1 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
								tile1.setName(itemTile.getName());
								addTile1Listener();
								table.add(tile1).fillX().size(100, 100).space(2);
							}else if (x == 3){
								Item itemTile = itemTiles[1];
								tile2 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
								tile2.setName(itemTile.getName());
								addTile2Listener();
								table.add(tile2).fillX().size(100, 100).space(2);
							}
						} else if (y == 2){
							if(x == 2){
								Item itemTile = itemTiles[2];
								tile3 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
								tile3.setName(itemTile.getName());
								addTile3Listener();
								table.add(tile3).fillX().size(100, 100).space(2);
							}else if (x == 3){
								Item itemTile = itemTiles[3];
								tile4 = new ImageButton(new TextureRegionDrawable(getAtlas().findRegion(itemTile.getDirectory())));
								tile4.setName(itemTile.getName());
								addTile4Listener();
								table.add(tile4).fillX().size(100, 100).space(2);
							}
						}
					}
				}
			}
		}
		stage.addActor(table);
	}
}
