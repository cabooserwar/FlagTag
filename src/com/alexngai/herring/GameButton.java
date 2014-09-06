package com.alexngai.herring;

import com.alexngai.framework.Graphics;
import com.alexngai.framework.Image;

public class GameButton {

	private int posX;
	private int posY;
	private int sizeX;
	private int sizeY;
	
	private Image icon;
	
	public GameButton(int posX, int posY, int sizeX, int sizeY){
		this.posX = posX;
		this.posY = posY;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}
	
	public void paint(Graphics g){
		g.drawRect(posX, posY, sizeX, sizeY, 0x32FFFFFF);
	}
	
	public boolean selected(int x, int y){
		
		return (x > posX && x < posX+sizeX && y > posY && y < posY+sizeY) ? true:false;

	}
	
}
