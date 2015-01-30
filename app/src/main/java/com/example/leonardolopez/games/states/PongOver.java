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

public class PongOver extends State implements TouchListener {

    private Font font, font2;
    private int winner;
    private Resources res;
    private Context ctx;

    public PongOver(int winner, Resources resources, Context context){
        this.winner = winner;
        font = new Font(255, 255, 255, 50, Typeface.SERIF, Typeface.BOLD);
        font2 = new Font(255, 255, 255, 30, Typeface.SERIF, Typeface.NORMAL);
        font.setTextAlign(Paint.Align.CENTER);
        font2.setTextAlign(Paint.Align.CENTER);
        this.res = resources;
        this.ctx = context;
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas!=null) {
            canvas.drawColor(Color.BLACK);
            canvas.drawText("The winner is", canvas.getWidth() / 2, 200, font);
            canvas.drawText("player " + winner + "!", canvas.getWidth() / 2, 300, font);
            canvas.drawText("Tap to start a new game", canvas.getWidth() / 2, 450, font2);
        }
    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        getGame().popState();
        getGame().pushState(new PongState(res, ctx));
        return true;
    }


}
