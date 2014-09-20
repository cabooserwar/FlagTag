package com.lab318.flagtag;

import com.alexngai.framework.Game;
import com.alexngai.framework.Graphics;
import com.alexngai.framework.Screen;
import com.alexngai.framework.Graphics.ImageFormat;


public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }


    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("ocean_background.png", ImageFormat.RGB565);
        //Assets.click = game.getAudio().createSound("explode.ogg");
        
        Assets.avatar = g.newImage("fishy.png", ImageFormat.RGB565);
        Assets.avatar2 = g.newImage("fishy2.png", ImageFormat.RGB565);
        Assets.avatarflip = g.newImage("fishy_flip.png", ImageFormat.RGB565);
        Assets.avatar2flip = g.newImage("fishy2_flip.png", ImageFormat.RGB565);
        
        Assets.bluefish = g.newImage("bluefish.png", ImageFormat.RGB565);
        Assets.bluefish2 = g.newImage("bluefish2.png", ImageFormat.RGB565);
        Assets.bluefishflip = g.newImage("bluefish_flip.png", ImageFormat.RGB565);
        Assets.bluefish2flip = g.newImage("bluefish2_flip.png", ImageFormat.RGB565);
        
        Assets.sardine = g.newImage("sardine.png", ImageFormat.RGB565);
        Assets.sardine2 = g.newImage("sardine2.png", ImageFormat.RGB565);
        Assets.sardineflip = g.newImage("sardine_flip.png", ImageFormat.RGB565);
        Assets.sardine2flip = g.newImage("sardine2_flip.png", ImageFormat.RGB565);
        
        Assets.background = g.newImage("ocean_background.png", ImageFormat.RGB565);
        Assets.bubble = g.newImage("bubble.png", ImageFormat.RGB565);
        
        game.setScreen(new MainMenuScreen(game));


    }


    @Override
    public void paint(float deltaTime) {


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


    }
}