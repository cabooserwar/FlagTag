package com.alexngai.herring.gameobjects;

import java.util.ResourceBundle.Control;

import android.util.Log;

import com.alexngai.framework.Graphics;
import com.alexngai.framework.Image;
import com.alexngai.herring.Assets;
import com.alexngai.herring.ControlState;

public class GameCharacter extends GameObject {
	
	private final float decay = (float) 0.95;
	private final float accel = (float) 0.15;
	private final float minSpeed = (float) 0.2;
	private final float velX_max = 4;
	private final float velY_max = 4;
	
	//private Animation animation;
	
	private static final Image[] sprites = {Assets.avatar, Assets.avatar2, Assets.avatarflip, Assets.avatar2flip};
    public static final int RIGHT = 0;
    public static final int RIGHT2 = 1;
    public static final int LEFT = 2;
	public static final int LEFT2 = 3;
	private boolean left = false;
	
	private long animTime = 0;
	private long totalDuration = 20;
	
	
	
	public GameCharacter(float size){
		super();
		setScale(size);
		setCurImage(sprites[RIGHT]);
	}
	
	/*
	public GameCharacter(Animation animation, float size){
		this.animation = animation;
		Image img = Assets.avatar;
		super.setSprite(img);
		super.setScale(size);
	}*/
	
	/*
	public GameCharacter(Image sprite, Image sprite2, float size){
		//super(sprite, sprite2, size);
		super.setScale(size);
	}*/ 
	
	public void update(float deltaTime, ControlState control){
		
		/*if (control.getState() >= 0){
			if (animation.update((long) deltaTime)){
				super.setSprite(animation.getImage());
				Log.d("Animation", "animation changed");
			}
		} else super.setSprite(Assets.avatar);*/
		
		if (control.getState() >= 0){
			animTime += deltaTime;
			animTime = animTime % totalDuration;
			
			if (control.getCtrl_x() > 0){
				if (animTime < totalDuration/2) curImage = sprites[RIGHT];
				else curImage = sprites[RIGHT2];
				left = false;
			}
			else{
				if (animTime < totalDuration/2) curImage = sprites[LEFT];
				else curImage = sprites[LEFT2];
				left = true;
			}
		} 
		
		float x;
		float y;
		if (control.getState() < 0){
			y = (float) (super.getVelY()*decay);
			if (Math.abs(y) < minSpeed) super.setVelY(0);
			else super.setVelY(y);
			
			x = (float) (super.getVelX()*decay);
			if (Math.abs(x) < minSpeed) super.setVelX(0);
			else super.setVelX(x);
		}
		else{
			y = (float) (super.getVelY()*decay+accel*control.getCtrl_y());
			if (Math.abs(y) < velY_max) super.setVelY(y);
			
			x = (float) (super.getVelX()*decay+accel*control.getCtrl_x());
			if (Math.abs(x) < velX_max) super.setVelX(x);
		}
		
		/*
		float x;
		float y;

		switch (control.getState()) {
		//none selected, slow down
		case ControlState.NONE:
			y = (float) (super.getVelY()*decay);
			if (Math.abs(y) < minSpeed) super.setVelY(0);
			else super.setVelY(y);
			
			x = (float) (super.getVelX()*decay);
			if (Math.abs(x) < minSpeed) super.setVelX(0);
			else super.setVelX(x);
			
			break;
		//increase up speed, slow lateral movement	
		case ControlState.UP:
			y = (float) (super.getVelY()-accel);
			if (Math.abs(y) < velY_max) super.setVelY(y);
			
			x = (float) (super.getVelX()*decay);
			if (Math.abs(x) < minSpeed) super.setVelX(0);
			else super.setVelX(x);
			
			break;
		//increase down speed, slow lateral movement
		case ControlState.DOWN:
			y = (float) (super.getVelY()+accel);
			if (Math.abs(y) < velY_max) super.setVelY(y);
			
			x = (float) (super.getVelX()*decay);
			if (Math.abs(x) < minSpeed) super.setVelX(0);
			else super.setVelX(x);
			
			break;
		//increase left speed, slow vertical movement
		case ControlState.LEFT:
			x = (float) (super.getVelX()-accel);
			if (Math.abs(x) < velX_max) super.setVelX(x);
			
			y = (float) (super.getVelY()*decay);
			if (Math.abs(y) < minSpeed) super.setVelY(0);
			else super.setVelY(y);
			
			break;
		//increase right speed, slow vertical movement
		case ControlState.RIGHT:
			x = (float) (super.getVelX()+accel);
			if (Math.abs(x) < velX_max) super.setVelX(x);
			
			y = (float) (super.getVelY()*decay);
			if (Math.abs(y) < minSpeed) super.setVelY(0);
			else super.setVelY(y);
			
			break;

		default:
			break;
		}
		*/
		super.update(deltaTime);
		
		if (super.getPosX() > boundX){
			super.setPosX(boundX);
			super.setVelX(0);
		}
		else if (super.getPosX() < 0){
			super.setPosX(0);
			super.setVelX(0);
		}

		if (super.getPosY() > boundY){ 
			super.setPosY(boundY);
			super.setVelY(0);
		}
		else if (super.getPosY() < 0){
			super.setPosY(0);
			super.setVelY(0);
		}
	}
	
	public void paint(Graphics g, float deltaTime){
		super.paint(g, deltaTime);
	}

	public void grow(){
		scale = scale + .05f;
	}
	
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}


	
}
