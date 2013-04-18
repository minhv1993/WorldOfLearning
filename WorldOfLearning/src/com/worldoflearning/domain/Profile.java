package com.worldoflearning.domain;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.services.ProfileManager;
import com.worldoflearning.utils.TextUtils;

/**
 * The player's profile.
 * <p>
 * This class is used to store the game progress, and is persisted to the file
 * system when the game exists.
 * 
 * @see ProfileManager
 */
public class Profile
    implements
        Serializable
{
	private int currentWorldId;
    private int currentLevelId;
    private Map<Integer,Integer> highScores;

    public Profile()
    {
        highScores = new HashMap<Integer,Integer>();
    }

    /**
     * Retrieves the ID of the next playable level.
     */
    public int getCurrentLevelId()
    {
        return currentLevelId;
    }
    
    /**
     * Retrieves the ID of the next playable world
     */
    public int getCurrentWorldId(){
    	return currentWorldId;
    }

    /**
     * Retrieves the high scores for each level (Level-ID -> High score).
     */
    public Map<Integer,Integer> getHighScores()
    {
        return highScores;
    }

    /**
     * Gets the current high score for the given level.
     */
    public int getHighScore(
        int levelId )
    {
        if( highScores == null ) return 0;
        Integer highScore = highScores.get( levelId );
        return ( highScore == null ? 0 : highScore );
    }

    /**
     * Notifies the score on the given level. Returns <code>true</code> if its a
     * high score.
     */
    public boolean notifyScore(
        int levelId,
        int score )
    {
        if( score > getHighScore( levelId ) ) {
            highScores.put( levelId, score );
            return true;
        }
        return false;
    }
    
    // Serializable implementation

    @SuppressWarnings( "unchecked" )
    @Override
    public void read(
        Json json,
        OrderedMap<String,Object> jsonData )
    {
        // read the some basic properties
    	currentWorldId = json.readValue("currentWorldId", Integer.class, jsonData);
        currentLevelId = json.readValue( "currentLevelId", Integer.class, jsonData );

        // libgdx handles the keys of JSON formatted HashMaps as Strings, but we
        // want it to be an integer instead (levelId)
        Map<String,Integer> highScores = json.readValue( "highScores", HashMap.class,
            Integer.class, jsonData );
        for( String levelIdAsString : highScores.keySet() ) {
            int levelId = Integer.valueOf( levelIdAsString );
            Integer highScore = highScores.get( levelIdAsString );
            this.highScores.put( levelId, highScore );
        }
    }

    @Override
    public void write(
        Json json )
    {
    	json.writeValue( "currentWorldId", currentWorldId );
        json.writeValue( "currentLevelId", currentLevelId );
        json.writeValue( "highScores", highScores );
    }
}
