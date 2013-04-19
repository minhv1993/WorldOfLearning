package com.worldoflearning.services;

import java.util.ArrayList;
import java.util.List;

import com.worldoflearning.domain.Level;

/**
 * Manages the levels.
 */
public class LevelManager
{
    private final List<Level> levels;
    private final String[] w1l1= {"circle", "hexagon", "oval", "pentagon", "semi-circle", "square", "trapezoid", "triangle"};
    private final String[] w1l2 = {"nine", "eight", "seven", "six", "five", "four", "three", "two", "one", "zero"};
    //private final String[] w1l3 =
    //private final String[] w1l4 =
    //private final String[] w2l1 =
    private final String[] w2l2 = {"turle", "seal", "owl", "mouse", "hippo", "frog","fish", "elephant", "dog", "cat"};
    //private final String[] w2l3 =
    //private final String[] w2l4 =
    /**
     * Creates the level manager.
     */
    public LevelManager()
    {
    	
        /*// create the level 2
        Level level2 = new Level( 2 );
        level2.setName( "Episode 3" );

        // create the level 1
        Level level1 = new Level( 1 );
        level1.setName( "Episode 2" );
        level1.setNextLevel( level2 );

        // create the level 0
        Level level0 = new Level( 0 );
        level0.setName( "Episode 1" );
        level0.setNextLevel( level0 );*/
    	
    	levels = new ArrayList<Level>();
    	
    	for(int world = 0; world < 2; world++){
    		for(int level = 0; level < 4; level++){
    			int id = world*4 + level;
    			Level l = new Level(id, world, level, "World "+ (world+1)+" - Level "+ (level+1), w1l1);
    			levels.add(l);
    		}
    	}
    }

    /**
     * Retrieve all the available levels.
     */
    public List<Level> getLevels()
    {
        return levels;
    }

    /**
     * Retrieve the level with the given id, or <code>null</code> if no such
     * level exist.
     */
    public Level findLevelById(int worldId, int levelId )
    {
    	if(worldId < 0)
    		return null;
    	if((worldId*4 + levelId) < 0 || (worldId*4 + levelId) >= levels.size())
    		return null;
        return levels.get(worldId*4 + levelId);
    }
}
