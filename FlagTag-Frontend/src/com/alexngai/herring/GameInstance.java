package com.alexngai.herring;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.Paint;

import com.alexngai.framework.Game;
import com.alexngai.framework.Graphics;
import com.alexngai.framework.Input.TouchEvent;
import com.alexngai.herring.gameobjects.Animation;
import com.alexngai.herring.gameobjects.BubbleGenerator;
import com.alexngai.herring.gameobjects.BubblePool;
import com.alexngai.herring.gameobjects.CollisionDetector;
import com.alexngai.herring.gameobjects.Enemy;
import com.alexngai.herring.gameobjects.EnemyGenerator;
import com.alexngai.herring.gameobjects.EnemyPool;
import com.alexngai.herring.gameobjects.GameCharacter;

public class GameInstance {
	Paint p1;
	
	private Game game;
	private int livesLeft = 1;
	
	private GameCharacter gchar;
	private EnemyPool enemypool;
	private EnemyGenerator enemyGenerator;
	private BubblePool bubblepool;
	private BubbleGenerator bubbleGenerator;
	
	
	private ControlState control;
	
	private Joystick joystick;
	/*
	private GameButton upButton;
	private GameButton downButton;
	private GameButton leftButton;
	private GameButton rightButton;
	*/
	
	Animation charAnimation;
	Animation enemyAnimation;
	
	public GameInstance(Game game){
		this.game = game;
		
		//setupAnimations();
		
		gchar = new GameCharacter(0.07f);
		gchar.setPosition(525-gchar.getIm_width()/2, 20);
		gchar.setVelY(4);
		
		//object pool
		enemypool = new EnemyPool(20);
		enemyGenerator = new EnemyGenerator(enemypool);
		
		bubblepool = new BubblePool();
		bubbleGenerator = new BubbleGenerator(bubblepool);
		
		control = new ControlState();
		
		joystick = new Joystick(150, 370, 100);
		
		p1 = new Paint();
	    p1.setTextSize(12);
	    p1.setColor(Color.WHITE);
	}

	public void update(List<TouchEvent> touchEvents, float deltaTime){
		
		//handle touch events
        handleTouchEvents(touchEvents);
		
		//randomly generate new enemies
		enemyGenerator.update();
		
		//update game objects
		enemypool.update(deltaTime);
        
		gchar.update(deltaTime, control);
		
		bubbleGenerator.update(gchar);
        bubblepool.update(deltaTime);
		
		//check for collisions between objects
		Enemy e = CollisionDetector.checkCollision(gchar, enemypool);
		if (e != null){
			if ( e.getIm_width() < gchar.getIm_width()){
				e.unuse();
				gchar.grow();
			}
			else if ( e.getIm_width() >= gchar.getIm_width()){
				livesLeft--;
			}
		}
		//if (collided) livesLeft--;
	}
	
	
	public void paint(Graphics g, float deltaTime){
		g.drawImage(Assets.background, 0, 0);
        enemypool.paint(g, deltaTime);
        bubblepool.paint(g, deltaTime);
		gchar.paint(g, deltaTime);
        
        joystick.paint(g);
       
        int temp =  (int) (100f/deltaTime);
        g.drawString("" + temp + "fps", 20, 20, p1);
        g.drawString("Lives:" + livesLeft, 510, 20, p1);
	}
	
	private void handleTouchEvents(List<TouchEvent> touchEvents){
		
		//handle touch events
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_DOWN) {
            	/*
            	if (upButton.selected(event.x, event.y)) control.setState(ControlState.UP);
            	else if (downButton.selected(event.x, event.y)) control.setState(ControlState.DOWN);
            	else if (leftButton.selected(event.x, event.y)) control.setState(ControlState.LEFT);
            	else if (rightButton.selected(event.x, event.y)) control.setState(ControlState.RIGHT);
            	*/
            	if (joystick.selected(event.x, event.y)){
            		joystick.setStickPosition(event.x, event.y);
            		control.setCtrl(joystick.getCtrl_x(), joystick.getCtrl_y(), joystick.getMag());
            	}
            }

            if (event.type == TouchEvent.TOUCH_UP) {
            	control.setState(ControlState.NONE);
            	joystick.centerStick();
            }
            
            if (event.type == TouchEvent.TOUCH_DRAGGED){
            	/*
            	if (upButton.selected(event.x, event.y)) control.setState(ControlState.UP);
            	else if (downButton.selected(event.x, event.y)) control.setState(ControlState.DOWN);
            	else if (leftButton.selected(event.x, event.y)) control.setState(ControlState.LEFT);
            	else if (rightButton.selected(event.x, event.y)) control.setState(ControlState.RIGHT);
            	*/
            	if (joystick.selected(event.x, event.y)){
            		joystick.setStickPosition(event.x, event.y);
            		control.setCtrl(joystick.getCtrl_x(), joystick.getCtrl_y(), joystick.getMag());
            	}
            }            
        }
	}
	
	/*
	public void setupAnimations(){
		charAnimation = new Animation();
		charAnimation.addFrame(Assets.avatar, 10);
		charAnimation.addFrame(Assets.avatar2, 10);
		
		enemyAnimation = new Animation();
		enemyAnimation.addFrame(Assets.enemy, 10);
		enemyAnimation.addFrame(Assets.enemy2, 10);
	}*/
	
	public int getlivesLeft() {
		return livesLeft;
	}
	
	public void setlivesLeft(int lives) {
		livesLeft = lives;
	}


}
