package com.lab318.flagtag;

public class ControlState {
	
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
	public static final int NONE = -1;
    
    private int state;
    
    private float ctrl_x;
    private float ctrl_y;
    private float mag;
	
	public ControlState(){
		state = -1;
		ctrl_x = 0;
		ctrl_y = 0;
		mag = 0;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
	
	public void setCtrl(float x, float y, float mag){
		ctrl_x = x;
		ctrl_y = y;
		this.mag = mag;
		state = 0;
	}

	public float getCtrl_x() {
		return ctrl_x;
	}

	public float getCtrl_y() {
		return ctrl_y;
	}

	public float getMag() {
		return mag;
	}
	
	
}
