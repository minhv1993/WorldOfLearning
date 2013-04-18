package com.worldoflearning;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.worldoflearning.screens.GamePlay;
import com.worldoflearning.screens.Splash;
import com.worldoflearning.services.LevelManager;
import com.worldoflearning.services.MusicManager;
import com.worldoflearning.services.PreferencesManager;
import com.worldoflearning.services.ProfileManager;
import com.worldoflearning.services.SoundManager;
import com.worldoflearning.utils.GameLogger;

public class WorldOfLearning extends Game {
    // constant useful for logging
	
	// Nick test committee
	//romo test commit
	
    public static final String LOG = WorldOfLearning.class.getSimpleName();

    // whether we are in development mode
    public static final boolean DEV_MODE = false;
    
    // background options
    public final String SPLASH_SCREEN = "splash-screen/splash-screen";
    public final String MENU_SCREEN = "menu-screen/menu-screen";
    public final String LEVEL_SCREEN = "level-screen/level-screen";
    public final String WORLD_SCREEEN = "world-screen/world-screen";
	public final String GAME_SCREEN = "game-screen/game-screen";
    
    // a libgdx helper class that logs the current FPS each second
    private GameLogger gameLogger;
    
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
        gameLogger = new GameLogger();
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
                setScreen( new GamePlay( this, 0,0 ) );
            } else {
                setScreen( new Splash( this ) );
            }
        }
    }

    @Override
    public void render()
    {
        super.render();
        if( DEV_MODE ) gameLogger.log();
        else gameLogger.log(this);
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
}
