package com.worldoflearning.utils;

import com.badlogic.gdx.graphics.FPSLogger;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.screens.GamePlay;

public class GameLogger extends FPSLogger {
	int oneSecond = 0;
	
	public GameLogger(){
		super();
	}
	
	public void log(WorldOfLearning game){
		super.log();
        if(oneSecond > 60){
	        if(game.getScreen() instanceof GamePlay){
	        	((GamePlay) game.getScreen()).show();
	        }
	        oneSecond = 0;
        } else
        	oneSecond++;
	}
}
