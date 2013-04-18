package com.worldoflearning.domain;

import java.util.ArrayList;

/**
 * A playable level.
 */
public class Level
{
    private final int id;
    private final int worldId;
    private final int levelId;
    
    private boolean completed;
    private boolean isLocked;
    
    private String name;
    private Level nextLevel;
    private ArrayList<Item> items;

    public Level( int id, int worldId, int levelId, String name, String[] itemsName){
        this.id = id;
        this.worldId = worldId;
        this.levelId = levelId;
        this.name = name;
        this.isLocked = true;
        this.items = new ArrayList<Item>();
        for(String itemName: itemsName){
        	Item item = new Item(itemName, "level-screen/w-"+(worldId+1)+"-l-"+(levelId+1)+"/"+itemName);
        	this.items.add(item);
        }
    }

    /**
     * Retrieves the ID of this object.
     */
    public int getId()
    {
        return id;
    }
    
    public int getWorldId(){
    	return worldId;
    }
    
    public int getLevelId(){
    	return levelId;
    }

    /**
     * Retrieves the level's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the level's name.
     */
    public void setName(
        String name )
    {
        this.name = name;
    }
    
    public ArrayList<Item> getItems(){
    	return items;
    }
    
    public void setItems(ArrayList<Item> items){
    	this.items = items;
    }

    /**
     * Retrieves whether this level is completed.
     */
    public boolean isCompleted()
    {
        return completed;
    }

    /**
     * Sets the level status.
     */
    public void setCompleted(
        boolean completed )
    {
        this.completed = completed;
    }
    
    public void unLockLevel(){
    	this.isLocked = false;
    }
    
    public boolean isLocked(){
    	return this.isLocked;
    }

    /**
     * Retrieves the next level.
     */
    public Level getNextLevel()
    {
        return nextLevel;
    }

    /**
     * Sets the next level.
     */
    public void setNextLevel(
        Level nextLevel )
    {
        this.nextLevel = nextLevel;
    }

    /**
     * Retrieves whether there is a next level.
     */
    public boolean hasNextLevel()
    {
        return ( nextLevel != null );
    }
}
