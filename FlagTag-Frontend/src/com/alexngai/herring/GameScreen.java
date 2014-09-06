package com.alexngai.herring;

import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

import com.alexngai.framework.Game;
import com.alexngai.framework.Graphics;
import com.alexngai.framework.Image;
import com.alexngai.framework.Screen;
import com.alexngai.framework.Input.TouchEvent;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    // Variable Setup
    GameInstance gameInstance;
    
    Paint paint;
   
    
    public GameScreen(Game game) {
        super(game);
        
        // Initialize game objects here
        gameInstance = new GameInstance(game);
        
        Typeface tf = Typeface.create("Georgia", Typeface.BOLD_ITALIC);
        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(40);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setTypeface(tf);

    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if (state == GameState.Paused)
            updatePaused(touchEvents);
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        
    	//updates ready screen
        
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    //update game while it is running
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        
        gameInstance.update(touchEvents, deltaTime);
        
        
        
        // 2. Check miscellaneous events like death:
        
        if (gameInstance.getlivesLeft() == 0) {
            state = GameState.GameOver;
        }
        
        
        // 3. Call individual update() methods here.
        // This is where all the game updates happen.
        // For example, robot.update();
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
            	if (event.x > 0 && event.x < 1024 && event.y > 0
                        && event.y < 300) {
            		state = GameState.Running;
            	
            	}
            	else if (event.x > 0 && event.x < 1024 && event.y > 350
                        && event.y < 500) {
            		state = GameState.GameOver;
            	}
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 0 && event.x < 1024 && event.y > 0
                        && event.y < 512) {
                    nullify();
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        // First draw the game elements.
        gameInstance.paint(g, deltaTime);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

    }

    private void nullify() {

        // Set all variables to null. You will be recreating them in the
        // constructor.
        paint = null;

        // Call garbage collector to clean up memory.
        System.gc();
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap to begin",
                512, 200, paint);
        g.drawString("Eat smaller fish", 512, 300, paint);
        g.drawString("Use joystick on the left", 512, 400, paint);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();

    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Paused - Tap top to continue", 512, 200, paint);
        g.drawString("Tap bottom to return to main menu", 512, 400, paint);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 510, 250, paint);

    }

    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        pause();
    }
}