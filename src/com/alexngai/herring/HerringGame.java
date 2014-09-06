package com.alexngai.herring;

import com.alexngai.framework.Screen;
import com.alexngai.framework.implementation.AndroidGame;

public class HerringGame extends AndroidGame {
    @Override
    public Screen getInitScreen() {
        return new LoadingScreen(this); 
    }

    @Override
    public void onBackPressed() {
    	getCurrentScreen().backButton();
    }
    
}