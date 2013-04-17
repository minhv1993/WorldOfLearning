package com.worldoflearning.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.worldoflearning.WorldOfLearning;

public class LevelsSelect extends AbstractScreen {
	private Image background;
	
	public LevelsSelect(WorldOfLearning game) {
		super(game);
	}
	
	@Override
	public void show(){
		super.show();
        
        // retrieve the splash image's region from the atlas
        AtlasRegion splashRegion = getAtlas().findRegion( "splash-screen/splash-image" );
        Drawable splashDrawable = new TextureRegionDrawable( splashRegion );

        // here we create the splash image actor; its size is set when the
        // resize() method gets called
        background = new Image( splashDrawable, Scaling.stretch );
        background.setFillParent( true );

	}

}
