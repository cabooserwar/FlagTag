package com.alexngai.herring;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.alexngai.framework.Game;
import com.alexngai.framework.Graphics;
import com.alexngai.framework.Screen;
import com.alexngai.framework.Input.TouchEvent;


public class MainMenuScreen extends Screen {
    
	Paint paint, paint2;

	
	public MainMenuScreen(Game game) {
        super(game);
        
        Typeface tf = Typeface.create("Georgia", Typeface.BOLD_ITALIC);
        paint = new Paint();
        paint.setTextSize(100);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setTypeface(tf);
        
        paint2 = new Paint();
        paint2.setTextSize(40);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);
        paint2.setTypeface(tf);
    }


    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();


        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {


                if (inBounds(event, 0, 0, 1024, 512)) {
                    //START GAME
                                game.setScreen(new GameScreen(game));               
      }


            }
        }
    }


    private boolean inBounds(TouchEvent event, int x, int y, int width,
            int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }


    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.menu, 0, 0);
        g.drawString("Herring", 510, 250, paint);
        g.drawString("Tap to begin", 510, 300, paint2);
    }


    @Override
    public void pause() {
    }


    @Override
    public void resume() {


    }


    @Override
    public void dispose() {


    }


    @Override
    public void backButton() {
        //Display "Exit Game?" Box


    }
}