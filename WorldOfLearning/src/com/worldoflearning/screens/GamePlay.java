package com.worldoflearning.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.domain.GameTimer;
import com.worldoflearning.domain.Level;
import com.worldoflearning.domain.Profile;
import com.worldoflearning.services.MusicManager.WorldOfLearningMusic;
import com.worldoflearning.utils.GameInputProcessor;

public class GamePlay extends AbstractScreen {
	private int targetWorldId;
	private int targetLevelId;
	private int scores;
	
	private boolean paused;
	
	private Profile profile;
	private Level level;
	private Texture background;
	private GameTimer timer;
	private SpriteBatch batch;
	
	private BitmapFont timeFont;
	private BitmapFont scoreFont;
	
	private OrthographicCamera cam;

	public GamePlay(WorldOfLearning game, int targetWorldId, int targetLevelId) {
		super(game);
		//back key shouldn't exit app
        Gdx.input.setCatchBackKey(true);
        
		background = new Texture(Gdx.files.internal(game.GAME_SCREEN));
		
		profile = game.getProfileManager().retrieveProfile();
		level = game.getLevelManager().findLevelById(targetWorldId, targetLevelId);
		
		timeFont = this.getFont();
		scoreFont = this.getFont();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();

		timer = new GameTimer(60000);
		
		scores = 0;
		
		// set up game input processor to catch back button
		Gdx.input.setCatchBackKey(true);
		GameInputProcessor  inputProcessor = new GameInputProcessor(game, targetWorldId);
		Gdx.input.setInputProcessor(inputProcessor);
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
	}
	
	@Override
	public void render(float delta){
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
        // if out of time
        if(timer.getTimeRemaining() <=0){
        	// save score
        	// update high score
        	// return to previous screen/ game over screen
        	game.setScreen(new Levels(game, targetWorldId));
        }
        
        cam.update();
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        
        //draw background
        batch.draw(background, 0, 0);
        
        // display timer
        timeFont.scale(3);
        timeFont.draw(batch, String.valueOf(timer.getTimeRemainingInSeconds()), 10, 480 - 10);
        timeFont.scale(-3);
        
        // dispplay score
        scoreFont.scale(2);
        scoreFont.draw(batch, String.valueOf(scores), 10, 480-60);
        scoreFont.scale(-2);
        
        batch.end();
	}
	
	@Override
	public void dispose(){
		background.dispose();
		timeFont.dispose();
		scoreFont.dispose();
		batch.dispose();
		stage.clear();
		stage.dispose();
	}
}
