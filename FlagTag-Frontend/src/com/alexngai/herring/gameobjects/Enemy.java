package com.alexngai.herring.gameobjects;

import android.util.Log;

import com.alexngai.framework.Graphics;
import com.alexngai.framework.Image;
import com.alexngai.herring.charactertypes.Bluefish;
import com.alexngai.herring.charactertypes.Sardine;

public class Enemy extends GameObject{

	public int id;
	protected Animation animation;
	protected long animTime = 0;
	protected long totalDuration = 50;
	private float scaleMultiplier = 1;
	
    public static final int RIGHT = 0;
    public static final int RIGHT2 = 1;
    public static final int LEFT = 2;
	public static final int LEFT2 = 3;
	private Image[] sprites;
	
	private int enemyID;
	
	private final int bluefishID = 0;
	private final int sardineID = 1;
	
	public Enemy(){
		super();
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		checkLifetime();
		animate(deltaTime);
	}
	
	public void paint(Graphics g, float deltaTime){
		super.paint(g, deltaTime);
	}

	public void unuse(){
		setInUse(false);
	}
	
	private void checkLifetime(){
		int x = getPosX();
		if (x < (0-getIm_width()) || x > 1024) unuse();
	}
	
	private void animate(float deltaTime){
		if (sprites == null) return;
		
		animTime += deltaTime;
		animTime = animTime % totalDuration;		
		if (getVelX() > 0){
			if (animTime < totalDuration/2) curImage = sprites[RIGHT];
			else curImage = sprites[RIGHT2];
		}
		else{
			if (animTime < totalDuration/2) curImage = sprites[LEFT];
			else curImage = sprites[LEFT2];
		}	
	}
	
	public void reconstruct(int enemyID, float scaleMultiplier, int posX, int posY, float velX, float velY){
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
		this.scaleMultiplier = scaleMultiplier;
	
		setEnemyType(enemyID);
		
		curImage = (velX > 0) ? sprites[RIGHT]:sprites[LEFT]; 
		updateSize();
		
		this.posX = (posX == 0) ? (posX - getIm_width()):posX;
	}
	
	private void setEnemyType(int enemyID){
		this.enemyID = enemyID; 
		switch (enemyID) {
		case bluefishID:
			sprites = Bluefish.sprites;
			setScale(scaleMultiplier * Bluefish.scale);
			break;

		case sardineID:
			sprites = Sardine.sprites;
			setScale(scaleMultiplier * Sardine.scale);
			break;
			
		default:
			break;
		}
	}
	
}
