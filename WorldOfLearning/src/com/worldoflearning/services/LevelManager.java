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
    private final String[] w1l3 = {"blue", "green", "orange", "purple", "red", "yellow"};
    private final String[] w1l4 = {"blue-five", "blue-four", "blue-hexagon", "blue-one", "blue-pentagon", "blue-triangle", "green-circle", "green-five",	
    								"green-four", "green-one", "green-square", "orange-circle", "orange-four", "orange-pentagon", "orange-two", "purple-hexagon", 
    								"purple-square", "purple-three", "purple-two", "red-five", "red-hexagon", "red-one", "red-square", "red-three", 
    								"red-triangle", "yellow-circle", "yellow-pentagon", "yellow-three", "yellow-triangle", "yellow-two"};
    private final String[] w2l1 = {"watermelon", "strawberry", "pineapple", "pear", "orange", "cherry", "banana", "apple"};
    private final String[] w2l2 = {"turle", "seal", "owl", "mouse", "hippo", "frog","fish", "elephant", "dog", "cat"};
    private final String[] w2l3 = {"sleepy", "sick", "scared", "sad", "happy", "embarassed", "confused", "angry"};
    private final String[] w2l4 = {"watermelon", "turtle", "strawberry", "sleepy", "sick", "seal", "scared", "sad", "pineapple", "pear", "owl", "orange", "mouse",
    								"hippo", "happy", "frog", "fish", "embarassed", "elephant", "dog", "confused", "cherry", "cat", "banana", "apple", "angry"};
    /**
     * Creates the level manager.
     */
    public LevelManager()
    {
    	levels = new ArrayList<Level>();
    	
    	for(int world = 0; world < 2; world++){
    		for(int level = 0; level < 4; level++){
    			int id = world*4 + level;
    			Level l = null;
    			switch(id){
    			case 0:
        			l = new Level(id, world, level, "World "+ (world+1)+" - Level "+ (level+1), w1l1);
        			break;
    			case 1:
        			l = new Level(id, world, level, "World "+ (world+1)+" - Level "+ (level+1), w1l2);
        			break;
    			case 2:
        			l = new Level(id, world, level, "World "+ (world+1)+" - Level "+ (level+1), w1l3);
        			break;
    			case 3:
        			l = new Level(id, world, level, "World "+ (world+1)+" - Level "+ (level+1), w1l4);
        			break;
    			case 4:
        			l = new Level(id, world, level, "World "+ (world+1)+" - Level "+ (level+1), w2l1);
        			break;
    			case 5:
        			l = new Level(id, world, level, "World "+ (world+1)+" - Level "+ (level+1), w2l2);
        			break;
    			case 6:
        			l = new Level(id, world, level, "World "+ (world+1)+" - Level "+ (level+1), w2l3);
        			break;
    			case 7:
        			l = new Level(id, world, level, "World "+ (world+1)+" - Level "+ (level+1), w2l4);
        			break;
    			default:
    			}
    			if(l != null)
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
