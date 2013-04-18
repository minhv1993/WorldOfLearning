package com.worldoflearning.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
<<<<<<< HEAD
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
=======
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
>>>>>>> branch 'master' of https://github.com/minhv1993/WorldOfLearning.git
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.domain.GameTimer;
import com.worldoflearning.domain.Item;
import com.worldoflearning.domain.Level;
import com.worldoflearning.domain.Profile;
import com.worldoflearning.services.MusicManager.WorldOfLearningMusic;
<<<<<<< HEAD
import com.worldoflearning.utils.GameInputProcessor;
=======
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;
>>>>>>> branch 'master' of https://github.com/minhv1993/WorldOfLearning.git

public class GamePlay extends AbstractScreen {
	private int targetWorldId;
	private int targetLevelId;
	private int scores;
	
	private boolean paused;
	
	private Profile profile;
	private Level level;
	private ArrayList<Item> levelItems;
	private ArrayList<Item> playFieldItems;
	
	private ImageButton toMatch;
	
	private Texture background;
	private GameTimer timer;
	private GameTimer beginTimer;
	private Random rand;
	
	
	
<<<<<<< HEAD
=======
	private ArrayList<String> tempList;

	
	
>>>>>>> branch 'master' of https://github.com/minhv1993/WorldOfLearning.git
	//use this boolean value to determine if the button pushed is correct. 
	//If it is, play the the correct sound and increment the counter.
	private boolean isCorrect;
	
	
	
	public GamePlay(WorldOfLearning game, int targetWorldId, int targetLevelId) {
		super(game);
		//back key shouldn't exit app
        //Gdx.input.setCatchBackKey(true);
<<<<<<< HEAD

		rand = new Random();
		
=======
        
>>>>>>> branch 'master' of https://github.com/minhv1993/WorldOfLearning.git
		
		//temporary setting - DO NOT LEAVE THIS HERE!
		isCorrect = false;
		
		background = new Texture(Gdx.files.internal(game.GAME_SCREEN));
		
		profile = game.getProfileManager().retrieveProfile();
		level = game.getLevelManager().findLevelById(targetWorldId, targetLevelId);
		levelItems = level.getItems();
		playFieldItems = new ArrayList<Item>(4);
		for(int i = 0; i < 4; i++){
			playFieldItems.add(levelItems.get(rand.nextInt(levelItems.size())));
		}

		toMatch = new ImageButton(new TextureRegionDrawable( getAtlas().findRegion(playFieldItems.get(rand.nextInt(playFieldItems.size())).getDirectory())));

		timer = new GameTimer(10000);
		beginTimer = new GameTimer(4000);
		
		scores = 0;
		
		// set up game input processor to catch back button
		//Gdx.input.setCatchBackKey(true);
		//GameInputProcessor  inputProcessor = new GameInputProcessor(game, targetWorldId);
		//Gdx.input.setInputProcessor(inputProcessor);
	}
	
	@Override
	protected boolean isGameScreen(){
		return true;
	}
	
	@Override
	public void show(){
		super.show();

        // retrieve the splash image's region from the atlas

        // play level music
		game.getMusicManager().play(WorldOfLearningMusic.LEVEL);
		
		Table table = super.getTable();
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
					} else {
<<<<<<< HEAD
						TextButton temp = new TextButton( "Pause", getSkin() );
						table.add(temp).fillX();
						TextButton temp2 = new TextButton( "Option", getSkin() );
						table.add(temp2).fillX().padRight(20);
=======
						TextButton pauseButton = new TextButton( "Pause", getSkin() );
						table.add(pauseButton).fillX();
						
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
						
						TextButton optionButton = new TextButton( "Option", getSkin() );
						table.add(optionButton).fillX();
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
				                
				                //using option button as a switch for click sounds temporarily.
				                if (isCorrect) {
				                	isCorrect = false;
				                }
				                else isCorrect = true;
				                 
				            }
				        } );
>>>>>>> branch 'master' of https://github.com/minhv1993/WorldOfLearning.git
					}
<<<<<<< HEAD
				}else if (x >1){
					if(y > 0){
						if(y == 1){
							if(x == 2){
								ImageButton ib = new ImageButton(new TextureRegionDrawable(  getAtlas().findRegion(playFieldItems.get(0).getDirectory()) ));
								table.add(ib).fillY().size(100, 100).space(2);
							}else{
								ImageButton ib = new ImageButton(new TextureRegionDrawable(  getAtlas().findRegion(playFieldItems.get(1).getDirectory()) ));
								table.add(ib).fillY().size(100, 100).space(2);
							}
						} else {
							if(x == 2){
								ImageButton ib = new ImageButton(new TextureRegionDrawable(  getAtlas().findRegion(playFieldItems.get(2).getDirectory()) ));
								table.add(ib).fillY().size(100, 100).space(2);
							}else{
								ImageButton ib = new ImageButton(new TextureRegionDrawable(  getAtlas().findRegion(playFieldItems.get(3).getDirectory()) ));
								table.add(ib).fillY().size(100, 100).space(2);
							}
						}
					}
=======
				}else{
					TextButton temp = new TextButton( tempList.get(rand.nextInt(tempList.size())).toString(), getSkin() );
					table.add(temp).fillX().size(55, 55);
					temp.addListener( new DefaultActorListener() {
			            @Override
			            public void touchUp(
			                InputEvent event,
			                float x,
			                float y,
			                int pointer,
			                int button )
			            {
			                super.touchUp( event, x, y, pointer, button );
			               
			                //if correct button is push, play this sound:
			                if(isCorrect) {
			                	game.getSoundManager().play( WorldOfLearningSound.CORRECT );
			                }
			                else {
			                game.getSoundManager().play( WorldOfLearningSound.WRONG );
			                }
			                 
			            }
			        } );
>>>>>>> branch 'master' of https://github.com/minhv1993/WorldOfLearning.git
				}
			}
		}
	}
	
	/*@Override
	public void render(float delta){
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        if(beginTimer.getTimeRemaining() <= 0){
        	checkTimeLeft();
	        
	        cam.update();
	        batch.setProjectionMatrix(cam.combined);
	        batch.begin();
	        
	        addTextLayout();
	        
	        // Add game logic here
	        
	        batch.end();
        } else {
        	cam.update();
	        batch.setProjectionMatrix(cam.combined);
	        batch.begin();
	        
	        addTextLayoutBeforeStart();
	        
	        batch.end();
        }
	}
	
	public void addTextLayoutBeforeStart(){
        //draw background
        //batch.draw(background, 0, 0);
        
        // display timer
        timeFont.scale(3);
        timeFont.draw(batch, String.valueOf(60), 10, 480 - 10);
        timeFont.scale(-3);
        
        // dispplay score
        scoreFont.scale(2);
        scoreFont.draw(batch, String.valueOf(0), 10, 480-60);
        scoreFont.scale(-2);
        
        countdownFont.scale(4);
        countdownFont.draw(batch, String.valueOf(beginTimer.getTimeRemainingInSeconds()), 390, 480-200);
        countdownFont.scale(-4);
	}
	
	public void addTextLayout(){
		//draw background
        //batch.draw(background, 0, 0);
        
        // display timer
        timeFont.scale(3);
        timeFont.draw(batch, String.valueOf(timer.getTimeRemainingInSeconds()), 10, 480 - 10);
        timeFont.scale(-3);
        
        // dispplay score
        scoreFont.scale(2);
        scoreFont.draw(batch, String.valueOf(scores), 10, 480-60);
        scoreFont.scale(-2);
	}
	
	public void checkTimeLeft(){
		if(timer.getTimeRemaining() <=0){
        	// save score
        	// update high score
        	// return to previous screen/ game over screen
        	game.setScreen(new Levels(game, targetWorldId));
        }
	}*/
}
