package com.example.leonardolopez.games.model;

import android.graphics.Rect;
import android.graphics.Canvas;
import sheep.game.Sprite;
import android.graphics.Bitmap;


public class PongBall extends Sprite {

    private Bitmap bm;      // the animation sequence
    private Rect sourceRect;    // the rectangle to be drawn from the animation bitmap
    private int frameNr;        // number of frames in animation
    private int currentFrame;   // the current frame
    private long frameTicker;   // the time of the last frame update
    private int framePeriod;    // milliseconds between each frame (1000/fps)
    private int spriteWidth;    // the width of the sprite to calculate the cut out rectangle
    private int spriteHeight;   // the height of the sprite
    private float x;              // the X coordinate of the object (top left of the image)
    private float y;


    public PongBall(Bitmap bitmap, float x, float y, int fps, int frameCount){
        this.bm = bitmap;
        this.x = x;
        this.y = y;
        currentFrame = 0;
        frameNr = frameCount;
        spriteWidth = bitmap.getWidth()/frameCount;
        spriteHeight = bitmap.getHeight();
        sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
        framePeriod = 1000/fps;
        frameTicker = 0l;
        //this.setShape(spriteWidth, spriteHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        setX(getX() + getSpeed().getX()/100);
        setY(getY() + getSpeed().getY()/100);

        Rect destRect = new Rect((int)x, (int)y, (int)x + spriteWidth, (int)y + spriteHeight);
        canvas.drawBitmap(this.bm, sourceRect, destRect, null);
    }


    //Setters and getters

    public void setX(float x){ this.x = x;}
    public void setY(float y){this.y = y;}
    public void setPosition(float x, float y){this.x = x;this.y = y;}
    public float getX(){return x;}
    public float getY(){return y;}
    public int getSpriteHeight(){return spriteHeight;}
    public int getSpriteWidth(){return spriteWidth;}



    public void setRandomSpeed(){
        float vy = (int)(Math.random()*400)+1;
        float vx = 400;
        int direction = (int)(Math.random()*3);
        if (direction==0) {this.setSpeed(vx, vy);}
        if (direction==1) {this.setSpeed(-vx, vy);}
        if (direction==2) {this.setSpeed(vx, -vy);}
        if (direction==3) {this.setSpeed(-vx, -vy);}
    }

}