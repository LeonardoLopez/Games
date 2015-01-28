package com.example.leonardolopez.games.states;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import com.example.leonardolopez.games.R;
import com.example.leonardolopez.games.models.HeliModel;
import sheep.game.State;
import sheep.input.TouchListener;

public class Heli2State extends State implements TouchListener{
    private HeliModel heli1;
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

    public Heli2State(Resources resources, Context context) {

        this.scrnW= context.getResources().getDisplayMetrics().widthPixels;
        this.scrnH= context.getResources().getDisplayMetrics().heightPixels;

        heli1 = new HeliModel(BitmapFactory.decodeResource(resources, R.drawable.heli1totheleft),0,200,100,1);
        heli1.setSpeed(1000, -1000);
        heli1.update(System.currentTimeMillis());
    }

    @Override
    public void draw(Canvas canvas) {
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
        canvas.drawColor(Color.BLACK);
        heli1.draw(canvas);
        //Font font = new Font(0, 55, 20, 30, Typeface.SERIF, Typeface.NORMAL);
        //canvas.drawText("Helicopter 1 Position (X:" + heli1.getX() + ", Y:" + heli1.getY() +")", 30, this.scrnH-160, font);
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
