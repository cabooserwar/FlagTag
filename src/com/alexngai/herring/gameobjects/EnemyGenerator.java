package com.alexngai.herring.gameobjects;

import java.math.RoundingMode;

import android.util.Log;

import com.alexngai.framework.Image;
import com.alexngai.herring.Assets;

public class EnemyGenerator {

	EnemyPool enemypool;
	private final double spawnChance = 0.03;
	private final int numTypes = 2;
	
	public EnemyGenerator(EnemyPool enemypool){
		this.enemypool=enemypool;
	}
	
	public void update(){
		float x = (float) Math.random();
		if (x < spawnChance){
			float direction = (float) Math.random();
			float speed = (float) (3*Math.random()+1);
			float spawnPosition = (float) (412*Math.random());
			int typeID = (int) Math.floor(numTypes*Math.random());
			float scaleMultiplier = (float) (.5+Math.random()); //range .5 to 1.5
			
			int posX; int posY; float velX;
			if (direction < 0.5){
				//right
				posX = 0;
				velX = speed;
			} else{
				//left
				posX = 1024;
				velX = -speed;
			}
			
			posY = (int) spawnPosition;
			
			int created = enemypool.addEnemy(typeID, scaleMultiplier, posX, posY, velX, 0);
			//Log.d("Enemy", "created enemy " + created + ", pos:" + posX);
		}
	}
}
