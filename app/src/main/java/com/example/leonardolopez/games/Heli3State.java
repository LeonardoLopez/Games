package com.example.leonardolopez.games;

import sheep.game.State;
import sheep.graphics.Font;
import android.content.res.Resources;
import android.graphics.*;
import android.view.MotionEvent;
import sheep.input.TouchListener;


public class Heli3State extends State implements TouchListener{

    private Heli3 helicopter;
    private int canvasHeight, canvasWidth;



    @Override
    public boolean onTouchMove(MotionEvent event) {
        helicopter.setPosition(event.getX(), event.getY());
        return true;
    }

    //istedet for å dra og klikke, så kan den bare klikke
    @Override
    public boolean onTouchUp(MotionEvent event) {
        helicopter.setPosition(event.getX(), event.getY());
//		helicopter.setSpeed((event.getX() - helicopter.getX())*10,
//				event.getY() - helicopter.getY())*10 );
        return true;
    }

    public Heli3State(Resources resources){
        helicopter = new Heli3(BitmapFactory.decodeResource(resources, R.drawable.helisprite),
                0, 0, 	//x, y
                100,		//fps
                4		//frameCount
        );
        helicopter.setSpeed(1000, 1000);
        helicopter.update(System.currentTimeMillis());
    }




    @Override
    public void draw(Canvas canvas) {
        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();
        canvas.drawColor(Color.BLACK);
        helicopter.draw(canvas);
        Font font = new Font(0, 55, 20, 30, Typeface.SERIF, Typeface.NORMAL);
        canvas.drawText("Helikopter er på x: " + helicopter.getX() +
                " y: " + helicopter.getY() , 30, 30, font);
    }

    @Override
    public void update(float dt) {
        helicopter.update(System.currentTimeMillis());

        if(helicopter.getX()>(canvasWidth-helicopter.getHelicopterWidth()) || helicopter.getX()<0)
        {
            helicopter.setSpeed(-helicopter.getSpeed().getX(), helicopter.getSpeed().getY());
            helicopter.flipHelicopter();
        }
        if(helicopter.getY()>(canvasHeight-helicopter.getHelicopterHeight()) || helicopter.getY()<0)
        {
            helicopter.setSpeed(helicopter.getSpeed().getX(), -helicopter.getSpeed().getY());
        }


    }

}