package com.worldoflearning.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.domain.GameTimer;
import com.worldoflearning.domain.Level;
import com.worldoflearning.domain.Profile;
import com.worldoflearning.services.MusicManager.WorldOfLearningMusic;
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;

public class GamePlay extends AbstractScreen {
	private int targetWorldId;
	private int targetLevelId;
	private int scores;
	
	private boolean paused;
	
	private Profile profile;
	private Level level;
	private Texture background;
	private GameTimer timer;
	private GameTimer beginTimer;
	private SpriteBatch batch;
	private Random rand;
	
	private BitmapFont timeFont;
	private BitmapFont scoreFont;
	private BitmapFont countdownFont;
	
	private OrthographicCamera cam;
	
	private ArrayList<String> tempList;

	
	
	//use this boolean value to determine if the button pushed is correct. 
	//If it is, play the the correct sound and increment the counter.
	private boolean isCorrect;
	
	
	
	public GamePlay(WorldOfLearning game, int targetWorldId, int targetLevelId) {
		super(game);
		//back key shouldn't exit app
        //Gdx.input.setCatchBackKey(true);
        
		
		//temporary setting - DO NOT LEAVE THIS HERE!
		isCorrect = false;
		
		background = new Texture(Gdx.files.internal(game.GAME_SCREEN));
		
		profile = game.getProfileManager().retrieveProfile();
		level = game.getLevelManager().findLevelById(targetWorldId, targetLevelId);
		
		timeFont = this.getFont();
		scoreFont = this.getFont();
		countdownFont = this.getFont();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		timer = new GameTimer(10000);
		beginTimer = new GameTimer(4000);
		
		scores = 0;
		
		tempList = new ArrayList<String>();
		tempList.add("A");
		tempList.add("B");
		tempList.add("C");
		
		rand = new Random();
		
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
        // play level music
		game.getMusicManager().play(WorldOfLearningMusic.LEVEL);
		
		Table table = super.getTable();
		table.columnDefaults(0).padLeft(5);
		table.columnDefaults(6).padRight(5);
		
		for(int y = 0; y < 4; y++){
			table.row();
			for(int x = 0; x < 6; x++){
				if(x == 0){
					if(y == 0){
						table.add( "Time" ).fillX();
						table.add(Integer.toString(timer.getTimeRemainingInSeconds())).fillX();
					} else if (y == 1){
						table.add( "Shape" ).fillX().colspan(2);
					} else if (y == 2){
						table.add( "Score" ).fillX();
						table.add( Integer.toString(scores)).fillX();
					} else {
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
					}
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
