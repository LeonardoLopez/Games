package com.example.leonardolopez.games.presenter;

import sheep.game.State;
import sheep.graphics.Font;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.view.MotionEvent;

import com.example.leonardolopez.games.R;
import com.example.leonardolopez.games.model.Helicopter;

import sheep.input.TouchListener;


public class Heli3State extends State implements TouchListener{

    private Helicopter heli1;
    private Helicopter heli2;
    private Helicopter heli3;
    private int canvasHeight, canvasWidth;
    private int scrnW;
    private int scrnH;

    @Override
    public boolean onTouchMove(MotionEvent event) {
        heli1.setPosition(event.getX(), event.getY());
        return true;
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        heli1.setPosition(event.getX(), event.getY());
        return true;
    }

    public Heli3State(Resources resources, Context context){

        this.scrnW= context.getResources().getDisplayMetrics().widthPixels;
        this.scrnH= context.getResources().getDisplayMetrics().heightPixels;

        heli1 = new Helicopter(BitmapFactory.decodeResource(resources, R.drawable.helisprite),0,200,100,4);
        heli2 = new Helicopter(BitmapFactory.decodeResource(resources, R.drawable.helisprite),300,200,100,4);
        heli3 = new Helicopter(BitmapFactory.decodeResource(resources, R.drawable.helisprite),700,400,100,4);

        heli1.setSpeed(1000, -1000);
        heli2.setSpeed(-1000, 1000);
        heli3.setSpeed(-1000, -1000);

        heli1.update(System.currentTimeMillis());
        heli2.update(System.currentTimeMillis());
        heli3.update(System.currentTimeMillis());
    }

    @Override
    public void draw(Canvas canvas) {
        if(null != canvas) {
            canvasHeight = canvas.getHeight();
            canvasWidth = canvas.getWidth();
            canvas.drawColor(Color.BLACK);
            heli1.draw(canvas);
            heli2.draw(canvas);
            heli3.draw(canvas);
            Font font = new Font(0, 55, 20, 30, Typeface.SERIF, Typeface.NORMAL);
            canvas.drawText("Helicopter 1 Position (X:" + heli1.getX() + ", Y:" + heli1.getY() + ")", 30, this.scrnH - 160, font);
            canvas.drawText("Helicopter 2 Position (X:" + heli2.getX() + ", Y:" + heli2.getY() + ")", 30, this.scrnH - 130, font);
            canvas.drawText("Helicopter 3 Position (X:" + heli3.getX() + ", Y:" + heli3.getY() + ")", 30, this.scrnH - 100, font);
        }
    }

    @Override
    public void update(float dt) {
        heli1.update(System.currentTimeMillis());
        heli2.update(System.currentTimeMillis());
        heli3.update(System.currentTimeMillis());

        if(heli1.getX()>(canvasWidth- heli1.getHelicopterWidth()) || heli1.getX()<0)
        {
            heli1.setSpeed(-heli1.getSpeed().getX(), heli1.getSpeed().getY());
            heli1.flipHelicopter();
        }
        if(heli1.getY()>(canvasHeight- heli1.getHelicopterHeight()) || heli1.getY()<0)
        {
            heli1.setSpeed(heli1.getSpeed().getX(), -heli1.getSpeed().getY());
        }

        if(heli2.getX()>(canvasWidth- heli2.getHelicopterWidth()) || heli2.getX()<0)
        {
            heli2.setSpeed(-heli2.getSpeed().getX(), heli2.getSpeed().getY());
            heli2.flipHelicopter();
        }
        if(heli2.getY()>(canvasHeight- heli2.getHelicopterHeight()) || heli2.getY()<0)
        {
            heli2.setSpeed(heli2.getSpeed().getX(), -heli2.getSpeed().getY());
        }

        if(heli3.getX()>(canvasWidth- heli3.getHelicopterWidth()) || heli3.getX()<0)
        {
            heli3.setSpeed(-heli3.getSpeed().getX(), heli3.getSpeed().getY());
            heli3.flipHelicopter();
        }
        if(heli3.getY()>(canvasHeight- heli3.getHelicopterHeight()) || heli3.getY()<0)
        {
            heli3.setSpeed(heli3.getSpeed().getX(), -heli3.getSpeed().getY());
        }



        if(heli1.getSpriteRect().intersect(heli2.getSpriteRect()) ||
                heli2.getSpriteRect().intersect(heli1.getSpriteRect())){
            heli1.setSpeed(-heli1.getSpeed().getX(), -heli1.getSpeed().getY());
            heli1.flipHelicopter();
            heli2.setSpeed(-heli2.getSpeed().getX(), -heli2.getSpeed().getY());
            heli2.flipHelicopter();
        }


        if(heli1.getSpriteRect().intersect(heli3.getSpriteRect()) ||
                heli3.getSpriteRect().intersect(heli1.getSpriteRect())){
            heli1.setSpeed(-heli1.getSpeed().getX(), -heli1.getSpeed().getY());
            heli1.flipHelicopter();
            heli3.setSpeed(-heli3.getSpeed().getX(), -heli3.getSpeed().getY());
            heli3.flipHelicopter();
        }

        if(heli3.getSpriteRect().intersect(heli2.getSpriteRect()) ||
                heli2.getSpriteRect().intersect(heli3.getSpriteRect())){
            heli2.setSpeed(-heli2.getSpeed().getX(), -heli2.getSpeed().getY());
            heli2.flipHelicopter();
            heli3.setSpeed(-heli3.getSpeed().getX(), -heli3.getSpeed().getY());
            heli3.flipHelicopter();
        }
    }

}