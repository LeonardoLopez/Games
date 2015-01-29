package com.example.leonardolopez.games.states;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;


public class PongStart extends State implements TouchListener {

    private Font font;

    public PongStart(){
        font = new Font(100, 100, 100, 60, Typeface.SANS_SERIF, Typeface.BOLD);
        font.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawText("Tap to start", canvas.getWidth()/2, 200, font);

    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        getGame().popState();
        getGame().pushState(new PongState());
        return false;
    }





}
