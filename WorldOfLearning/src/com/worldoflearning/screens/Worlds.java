package com.worldoflearning.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.worldoflearning.WorldOfLearning;

public class Worlds extends AbstractScreen {

	public Worlds(WorldOfLearning game) {
		super(game);
	}
	
	@Override
	public void show(){
		super.show();
		
		// Retrieve the default table actor
		Table table = super.getTable();
		table.add("Worlds Selection").spaceBottom(50);
		table.row();
		
		// Register 
	}
}
