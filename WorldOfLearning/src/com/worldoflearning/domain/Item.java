package com.worldoflearning.domain;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * An item that can be added to the ship.
 */
public class Item
{
	private String name;
	private String directory;
	private Drawable skin;
	
	public Item(String name, String directory){
		this.name = name;
		this.directory = directory;
	}
	
    public String getName(){
    	return this.name;
    }

    public String getSimpleName(){
    	return this.name.toLowerCase();
    }
    
    public String getDirectory(){
    	return this.directory;
    }

    public void setSkin(Drawable skin){
    	this.skin = skin;
    }
    
    public Drawable getSkin(){
    	return this.skin;
    }
}
