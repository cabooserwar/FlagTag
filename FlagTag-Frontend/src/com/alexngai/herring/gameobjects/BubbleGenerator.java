package com.alexngai.herring.gameobjects;

import com.alexngai.framework.Image;
import com.lab318.flagtag.Assets;

public class BubbleGenerator {
	
	BubblePool bubblepool;
	private final double spawnChance = 0.02;
	private final float bubbleSpeed = -1;
	
	public BubbleGenerator(BubblePool bubblepool){
		this.bubblepool=bubblepool;
	}
	
	public void update(GameCharacter gchar){
		int posY = gchar.getPosY();
		int posX = gchar.isLeft() ? gchar.getPosX():(gchar.getPosX()+gchar.getIm_width());
		
		float x = (float) Math.random();
		if (x < spawnChance){
			
			bubblepool.addBubble((float)(.05), posX, posY, 0f, bubbleSpeed);
			//Log.d("Enemy", "created enemy " + created + ", pos:" + posX);
		}
	}
}
