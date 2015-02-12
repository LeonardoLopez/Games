package com.example.leonardolopez.games.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import com.example.leonardolopez.games.R;
import com.example.leonardolopez.games.model.Helicopter;

import sheep.game.State;


public class Heli1State extends State {
    private Helicopter heli1;
    private int canvasHeight, canvasWidth;
    private int scrnW;
    private int scrnH;

    public Heli1State(Resources resources, Context context) {

        this.scrnW= context.getResources().getDisplayMetrics().widthPixels;
        this.scrnH= context.getResources().getDisplayMetrics().heightPixels;

        heli1 = new Helicopter(BitmapFactory.decodeResource(resources, R.drawable.heli1totheleft),0,200,100,1);
        heli1.setSpeed(1000, -1000);
        heli1.update(System.currentTimeMillis());
    }

    @Override
    public void draw(Canvas canvas) {
        if(null != canvas) {
            canvasHeight = canvas.getHeight();
            canvasWidth = canvas.getWidth();
            canvas.drawColor(Color.BLACK);
            heli1.draw(canvas);
            //Font font = new Font(0, 55, 20, 30, Typeface.SERIF, Typeface.NORMAL);
            //canvas.drawText("Helicopter 1 Position (X:" + heli1.getX() + ", Y:" + heli1.getY() +")", 30, this.scrnH-160, font);
        }
    }

    @Override
    public void update(float dt) {
        heli1.update(System.currentTimeMillis());

        if(heli1.getX()>(canvasWidth- heli1.getHelicopterWidth()) || heli1.getX()<0)
        {
            heli1.setSpeed(-heli1.getSpeed().getX(), heli1.getSpeed().getY());
            heli1.flipHelicopter();
        }
        if(heli1.getY()>(canvasHeight- heli1.getHelicopterHeight()) || heli1.getY()<0)
        {
            heli1.setSpeed(heli1.getSpeed().getX(), -heli1.getSpeed().getY());
        }
    }
}
