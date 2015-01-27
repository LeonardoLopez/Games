package com.example.leonardolopez.games.models;

import android.graphics.Rect;
import android.graphics.Matrix;

import android.util.DisplayMetrics;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import sheep.game.Sprite;
import android.graphics.Bitmap;


public class HeliModel extends Sprite {

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


    public HeliModel(Bitmap bitmap, float x, float y, int fps, int frameCount){
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
        flipHelicopter();
        //this.setShape(spriteWidth, spriteHeight);
    }

    @Override
    public void draw(Canvas canvas) {
        setX(getX() + getSpeed().getX()/100);
        setY(getY() + getSpeed().getY()/100);

        Rect destRect = new Rect((int)x, (int)y, (int)x + spriteWidth, (int)y + spriteHeight);
        canvas.drawBitmap(this.bm, sourceRect, destRect, null);
    }

    public void flipHelicopter(){
        Matrix mirrorMatrix = new Matrix();
        mirrorMatrix.preScale(-1, 1);
        Bitmap turnMap = Bitmap.createBitmap(this.bm, 0, 0, this.bm.getWidth(), this.bm.getHeight(), mirrorMatrix, false);
        turnMap.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        this.bm = new BitmapDrawable(turnMap).getBitmap();
    }


    //Setters and getters

    public void setX(float x){ this.x = x;}
    public void setY(float y){this.y = y;}
    public void setPosition(float x, float y){this.x = x;this.y = y;}
    public float getX(){return x;}
    public float getY(){return y;}
    public int getHelicopterHeight(){return spriteHeight;}
    public int getHelicopterWidth(){return spriteWidth;}



    public Rect getSpriteRect(){
        return new Rect((int)getX(), (int)getY(), (int)getX() + spriteWidth, (int)getY() + spriteHeight);
    }


    public void update(long gameTime) {
        if (gameTime > frameTicker + framePeriod) {
            frameTicker = gameTime;
            currentFrame++;
            if (currentFrame >= frameNr) {
                currentFrame = 0;
            }
        }
        this.sourceRect.left = currentFrame * spriteWidth;
        this.sourceRect.right = this.sourceRect.left + spriteWidth;
    }

}