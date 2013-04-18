package com.worldoflearning.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	public static final int TIME_IN_MILLIS = 31000;
	
	private int targetWorldId;
	private int targetLevelId;
	public int scores;
	
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
	
	public GamePlay(WorldOfLearning game, int targetWorldId, int targetLevelId) {
		super(game);
		//back key shouldn't exit app
        //Gdx.input.setCatchBackKey(true);
		rand = new Random();
		
		profile = game.getProfileManager().retrieveProfile();
		level = game.getLevelManager().findLevelById(targetWorldId, targetLevelId);
		
		levelItems = level.getItems();
		itemTiles = new Item[NUM_OF_TILES];
		
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
				boolean isCorrect = ((GamePlay) game.getScreen()).tile1.getName().equals(((GamePlay) game.getScreen()).toMatch.getName());
				if(isCorrect){
					game.getSoundManager().play(WorldOfLearningSound.CORRECT);
					((GamePlay) game.getScreen()).updateTiles(10);
				} else{
					game.getSoundManager().play(WorldOfLearningSound.WRONG);
					((GamePlay) game.getScreen()).updateTiles(-5);
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
					((GamePlay) game.getScreen()).updateTiles(10);
				} else{
					((GamePlay) game.getScreen()).game.getSoundManager().play(WorldOfLearningSound.WRONG);
					((GamePlay) game.getScreen()).updateTiles(-5);
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
					((GamePlay) game.getScreen()).updateTiles(10);
				} else{
					game.getSoundManager().play(WorldOfLearningSound.WRONG);
					((GamePlay) game.getScreen()).updateTiles(-5);
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
					((GamePlay) game.getScreen()).updateTiles(10);
				} else{
					game.getSoundManager().play(WorldOfLearningSound.WRONG);
					((GamePlay) game.getScreen()).updateTiles(-5);
				}
			}
		});
	}
	
	public void updateTiles(int score){
		((GamePlay) game.getScreen()).scores += score;
		if(score > 0){
			for(int i = 0; i < GamePlay.NUM_OF_TILES; i++){
				Item temp = ((GamePlay) game.getScreen()).levelItems.get(((GamePlay) game.getScreen()).rand.nextInt(((GamePlay) game.getScreen()).levelItems.size()));
				((GamePlay) game.getScreen()).itemTiles[i] = temp;
			}
			Item itemTile = ((GamePlay) game.getScreen()).itemTiles[0];
			((GamePlay) game.getScreen()).tile1 = new ImageButton(new TextureRegionDrawable(((GamePlay) game.getScreen()).getAtlas().findRegion(itemTile.getDirectory())));
			((GamePlay) game.getScreen()).tile1.setName(itemTile.getName());
			addTile1Listener();
			itemTile = ((GamePlay) game.getScreen()).itemTiles[1];
			((GamePlay) game.getScreen()).tile2 = new ImageButton(new TextureRegionDrawable(((GamePlay) game.getScreen()).getAtlas().findRegion(itemTile.getDirectory())));
			((GamePlay) game.getScreen()).tile2.setName(itemTile.getName());
			addTile2Listener();
			itemTile = ((GamePlay) game.getScreen()).itemTiles[2];
			((GamePlay) game.getScreen()).tile3 = new ImageButton(new TextureRegionDrawable(((GamePlay) game.getScreen()).getAtlas().findRegion(itemTile.getDirectory())));
			((GamePlay) game.getScreen()).tile3.setName(itemTile.getName());
			addTile3Listener();
			itemTile = ((GamePlay) game.getScreen()).itemTiles[3];
			((GamePlay) game.getScreen()).tile4 = new ImageButton(new TextureRegionDrawable(((GamePlay) game.getScreen()).getAtlas().findRegion(itemTile.getDirectory())));
			((GamePlay) game.getScreen()).tile4.setName(itemTile.getName());
			addTile4Listener();
			itemTile = ((GamePlay) game.getScreen()).itemTiles[((GamePlay) game.getScreen()).rand.nextInt(GamePlay.NUM_OF_TILES)];
			((GamePlay) game.getScreen()).toMatch = new ImageButton(new TextureRegionDrawable(((GamePlay) game.getScreen()).getAtlas().findRegion(itemTile.getDirectory())));
			((GamePlay) game.getScreen()).toMatch.setName(itemTile.getName());
		}
		((GamePlay) game.getScreen()).show();
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

		Table table = super.getTable();
		table.clear();
		if(timer.getTimeRemainingInSeconds() > 0){
			table.columnDefaults(0).padLeft(5);
			table.columnDefaults(6).padRight(5);
			
			for(int y = 0; y < 3; y++){
				table.row();
				for(int x = 0; x < 4; x++){
					if(x == 0){
						if(y == 0){
							table.add( Integer.toString(timer.getTimeRemainingInSeconds())).fillX().colspan(2);
							table.add( Integer.toString(scores)).fillX().colspan(2).right();
						} else if (y == 1){
							table.add(toMatch).fillX().size(100,100).colspan(2).padRight(20);
						} else if (y == 2){
							Table smallTable = new Table(getSkin());
							smallTable.columnDefaults(0).padRight(5);
							smallTable.columnDefaults(1).padRight(5);
							smallTable.row();
							
							smallTable.add(toMatch.getName()).fillX().colspan(2).center().padBottom(30).padLeft(35);
							smallTable.row();
							TextButton pauseButton = new TextButton( "Pause", getSkin() );
							pauseButton.addListener( new DefaultActorListener() {
					            @Override
					            public void touchUp(InputEvent event, float x, float y, int pointer, int button ){
					                super.touchUp( event, x, y, pointer, button );
					                game.getSoundManager().play( WorldOfLearningSound.CLICK );
					                game.setScreen( new Menu( game ) );
					            }
					        } );
							
							smallTable.add(pauseButton).fillX();
							TextButton optionButton = new TextButton( "Option", getSkin());
							optionButton.addListener( new DefaultActorListener() {
					            @Override
					            public void touchUp(InputEvent event, float x, float y, int pointer, int button ){
					                super.touchUp( event, x, y, pointer, button );
					                game.getSoundManager().play( WorldOfLearningSound.CLICK );
					            }
					        } );
							smallTable.add(optionButton).fillX();
							table.add(smallTable).fillX().colspan(2).padRight(20);
						}
					}else if (x >1){
						if(y > 0){
							if(y == 1){
								if(x == 2){
									table.add(tile1).fillX().size(100, 100).space(2);
								}else if (x == 3){
									table.add(tile2).fillX().size(100, 100).space(2);
								}
							} else if (y == 2){
								if(x == 2){
									table.add(tile3).fillX().size(100, 100).space(2);
								}else if (x == 3){
									table.add(tile4).fillX().size(100, 100).space(2);
								}
							}
						}
					}
				}
			}
		}else{
			table.columnDefaults(0).padRight(5);
			table.columnDefaults(1).padLeft(5);
			table.row();
			
			table.add("Level Completed").colspan(2).padBottom(10);
			table.row();
			
			table.add("High Score:").fillX().left();
			table.add("189299").fillX().right();
			table.row();
			
			table.add("Your Score:").fillX().left();
			table.add("12312").fillX().right();
			table.row();

			TextButton levelsButton = new TextButton( "Levels Select", getSkin() );
			levelsButton.addListener(new DefaultActorListener(){
				@Override
	            public void touchUp(InputEvent event, float x, float y, int pointer, int button ){
	                super.touchUp( event, x, y, pointer, button );
	                game.getSoundManager().play( WorldOfLearningSound.CLICK );
	                game.setScreen(new Levels(game, targetWorldId));
	        		int tempWorldId = (targetLevelId==3)?1:0;
	        		int tempLevelId = (targetLevelId==3)?(targetWorldId==1)?3:0:targetLevelId+1;
	        		Profile tempProfile = game.getProfileManager().retrieveProfile();
	        		if(tempProfile.getCurrentWorldId() <= tempWorldId){
	        			if(tempProfile.getCurrentLevelId() <= tempLevelId){
	        				tempProfile.setCurrentWorldId(tempWorldId);
	        				tempProfile.setCurrentLevelId(tempLevelId);
	        				game.getProfileManager().setProfile(tempProfile);
	        		        game.getProfileManager().persist();
	        			}
	        		}
	            }
			});
			TextButton nextButton = new TextButton("Next Level", getSkin());
			nextButton.addListener(new DefaultActorListener(){
				@Override
	            public void touchUp(InputEvent event, float x, float y, int pointer, int button ){
	                super.touchUp( event, x, y, pointer, button );
	                game.getSoundManager().play( WorldOfLearningSound.CLICK );
	                game.setScreen(new Tutorial(game, (targetLevelId==3)?1:targetWorldId, (targetLevelId==3)?(targetWorldId==1)?3:0:targetLevelId+1));
	        		int tempWorldId = (targetLevelId==3 && targetWorldId==0)?1:0;
	        		int tempLevelId = (targetLevelId==3)?(targetWorldId==1)?3:0:targetLevelId+1;
	        		Profile tempProfile = game.getProfileManager().retrieveProfile();
	        		if(tempProfile.getCurrentWorldId() <= tempWorldId){
	        			if(tempProfile.getCurrentLevelId() <= tempLevelId){
	        				tempProfile.setCurrentWorldId(tempWorldId);
	        				tempProfile.setCurrentLevelId(tempLevelId);
	        				game.getProfileManager().setProfile(tempProfile);
	        		        game.getProfileManager().persist();
	        			}
	        		}
				}
			});
			table.add(levelsButton).fillX().size(100, 20).padTop(5);
			table.add(nextButton).fillX().size(100, 20).padTop(5);
			table.row();
		}
		stage.addActor(table);
	}
}
