package com.worldoflearning.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.domain.GameTimer;
import com.worldoflearning.domain.Level;
import com.worldoflearning.domain.Profile;
import com.worldoflearning.services.MusicManager.WorldOfLearningMusic;

public class GamePlay extends AbstractScreen {
	private int targetWorldId;
	private int targetLevelId;
	
	private Profile profile;
	private Level level;
	private Image background;
	private GameTimer timer;
	private SpriteBatch batch;
	
	private BitmapFont font;
	private BitmapFont timeFont;
	
	private OrthographicCamera cam;

	public GamePlay(WorldOfLearning game, int targetWorldId, int targetLevelId) {
		super(game);
		//back key shouldn't exit app
        //Gdx.input.setCatchBackKey(true);
        
		timer = new GameTimer(60000);
		profile = game.getProfileManager().retrieveProfile();
		level = game.getLevelManager().findLevelById(targetWorldId, targetLevelId);
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 800, 480);
		batch = new SpriteBatch();
		
		font = this.getFont();
		timeFont = this.getFont();
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
		
		// retrieve the splash image's region from the atlas
        AtlasRegion splashRegion = getAtlas().findRegion(game.LEVEL_SCREEN);
        Drawable splashDrawable = new TextureRegionDrawable( splashRegion );

        // here we create the splash image actor; its size is set when the
        // resize() method gets called
        background = new Image( splashDrawable, Scaling.stretch );
        background.setFillParent( true );

        // and finally we add the actor to the stage
        stage.addActor( background );
	}
	
	@Override
	public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
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
        
        //display timer
        timeFont.scale(3);
        timeFont.draw(batch, String.valueOf(timer.getTimeRemainingInSeconds()), 10, 480 - 10);
        timeFont.scale(-3);
        batch.end();
	}
	
	@Override
	public void dispose(){
		timeFont.dispose();
		batch.dispose();
		stage.clear();
		stage.dispose();
	}
}
