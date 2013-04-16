package com.worldoflearning;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.worldoflearning.screens.Splash;
import com.worldoflearning.services.LevelManager;
import com.worldoflearning.services.MusicManager;
import com.worldoflearning.services.PreferencesManager;
import com.worldoflearning.services.ProfileManager;
import com.worldoflearning.services.SoundManager;

public class WorldOfLearning extends Game {
    // constant useful for logging
    public static final String LOG = WorldOfLearning.class.getSimpleName();

    // whether we are in development mode
    public static final boolean DEV_MODE = false;
    
    // a libgdx helper class that logs the current FPS each second
    private FPSLogger fpsLogger;
    
    // services
    private PreferencesManager preferencesManager;
    private ProfileManager profileManager;
    private LevelManager levelManager;
    private MusicManager musicManager;
    private SoundManager soundManager;
    
    // Services' getters
    public PreferencesManager getPreferencesManager()
    {
        return preferencesManager;
    }

    public ProfileManager getProfileManager()
    {
        return profileManager;
    }

    public LevelManager getLevelManager()
    {
        return levelManager;
    }

    public MusicManager getMusicManager()
    {
        return musicManager;
    }

    public SoundManager getSoundManager()
    {
        return soundManager;
    }
	@Override
	public void create() {
		Gdx.app.log( WorldOfLearning.LOG, "Creating game on " + Gdx.app.getType() );

        // create the preferences manager
        preferencesManager = new PreferencesManager();

        // create the music manager
        musicManager = new MusicManager();
        musicManager.setVolume( preferencesManager.getVolume() );
        musicManager.setEnabled( preferencesManager.isMusicEnabled() );

        // create the sound manager
        soundManager = new SoundManager();
        soundManager.setVolume( preferencesManager.getVolume() );
        soundManager.setEnabled( preferencesManager.isSoundEnabled() );

        // create the profile manager
        profileManager = new ProfileManager();
        profileManager.retrieveProfile();

        // create the level manager
        levelManager = new LevelManager();

        // create the helper objects
        fpsLogger = new FPSLogger();
	}
	
	@Override
    public void resize(
        int width,
        int height )
    {
        super.resize( width, height );
        Gdx.app.log( WorldOfLearning.LOG, "Resizing game to: " + width + " x " + height );

        // show the splash screen when the game is resized for the first time;
        // this approach avoids calling the screen's resize method repeatedly
        if( getScreen() == null ) {
            if( DEV_MODE ) {
                //setScreen( new LevelScreen( this, 0 ) );
            } else {
                setScreen( new Splash( this ) );
            }
        }
    }

    @Override
    public void render()
    {
        super.render();

        // output the current FPS
        if( DEV_MODE ) fpsLogger.log();
    }

    @Override
    public void pause()
    {
        super.pause();
        Gdx.app.log( WorldOfLearning.LOG, "Pausing game" );

        // persist the profile, because we don't know if the player will come
        // back to the game
        profileManager.persist();
    }

    @Override
    public void resume()
    {
        super.resume();
        Gdx.app.log( WorldOfLearning.LOG, "Resuming game" );
    }

    @Override
    public void setScreen(
        Screen screen )
    {
        super.setScreen( screen );
        Gdx.app.log( WorldOfLearning.LOG, "Setting screen: " + screen.getClass().getSimpleName() );
    }

    @Override
    public void dispose()
    {
        super.dispose();
        Gdx.app.log( WorldOfLearning.LOG, "Disposing game" );
        
        // dipose some services
        musicManager.dispose();
        soundManager.dispose();
    }
	/*private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}*/
}
