package com.worldoflearning.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.worldoflearning.WorldOfLearning;
import com.worldoflearning.services.SoundManager.WorldOfLearningSound;
import com.worldoflearning.utils.DefaultActorListener;

public class Credits extends AbstractScreen {
	private static final String reallyLongString = "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n"
			+ "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n"
			+ "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n";

	public Credits(WorldOfLearning game) {
		super(game);

	}

	@Override
	public void show() {
		super.show();
        // retrieve the splash image's region from the atlas
        AtlasRegion splashRegion = getAtlas().findRegion(game.MENU_BACKGROUND);
        Drawable splashDrawable = new TextureRegionDrawable( splashRegion );
        Image background = new Image( splashDrawable, Scaling.stretch );
        background.setFillParent( true );

        // and finally we add the actor to the stage
        stage.addActor( background );
        
		Label text = new Label(reallyLongString, getSkin());
		text.setAlignment(Align.center);
		text.setWrap(true);
		Label text2 = new Label("This is a short string!", getSkin());
		text2.setAlignment(Align.center);
		text2.setWrap(true);
		Label text3 = new Label(reallyLongString, getSkin());
		text3.setAlignment(Align.center);
		text3.setWrap(true);

		Table scrollTable = new Table();
		scrollTable.add(text);
		scrollTable.row();
		scrollTable.add(text2);
		scrollTable.row();
		scrollTable.add(text3);

		ScrollPane scroller = new ScrollPane(scrollTable);

		Table table = getTable();
		table.setFillParent(true);
		table.add(scroller).fill().expand().padTop(30);

        TextButton backButton = new TextButton( "Main Menu", getSkin() );
        backButton.addListener( new DefaultActorListener() {
            @Override
            public void touchUp(
                InputEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
                super.touchUp( event, x, y, pointer, button );
                game.getSoundManager().play( WorldOfLearningSound.CLICK );
                game.setScreen( new Menu( game ) );
            }
        } );
        table.row();
        table.add( backButton ).size( 300, 50 ).pad(20);
		stage.addActor(table);
	}
}
