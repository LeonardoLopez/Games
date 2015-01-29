package com.example.leonardolopez.games.states;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;

import sheep.game.State;
import sheep.graphics.Font;
import sheep.input.TouchListener;

public class PongOver extends State implements TouchListener {

    private Font font, font2;
    private int winner;
    public PongOver(int winner){
        this.winner = winner;
        font = new Font(88, 88, 88, 50, Typeface.SERIF, Typeface.BOLD);
        font2 = new Font(100, 100, 100, 30, Typeface.SERIF, Typeface.NORMAL);
        font.setTextAlign(Paint.Align.CENTER);
        font2.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawText("The winner is", canvas.getWidth()/2, 200, font);
        canvas.drawText("player " + winner + "!", canvas.getWidth()/2, 300, font);
        canvas.drawText("Tap to start a new game", canvas.getWidth()/2, 450, font2);
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {

        getGame().popState();
        getGame().pushState(new PongState());
        return true;
    }


}
