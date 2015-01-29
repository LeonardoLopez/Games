package com.example.leonardolopez.games.states;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.example.leonardolopez.games.R;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;


public class PongStart extends State implements TouchListener {

    private Font font;
    private Resources resources;
    private Context context;

    private Sprite ball;
    private Image ballImage;
    private int scrnW;
    private int scrnH;

    public PongStart(Resources rsc, Context ctx, Display display){
        font = new Font(100, 100, 100, 60, Typeface.SANS_SERIF, Typeface.BOLD);
        font.setTextAlign(Paint.Align.CENTER);
        this.resources = rsc;
        this.context = ctx;

        //test
        this.scrnH = display.getHeight();
        this.scrnW = display.getWidth();
        this.scrnW= context.getResources().getDisplayMetrics().widthPixels;
        this.scrnH= context.getResources().getDisplayMetrics().heightPixels;
        ballImage = new Image(R.drawable.ball);
        ball = new Sprite(ballImage);
        ball.setPosition(0, 200);

    }

    @Override
    public void draw(Canvas canvas) {
        if(null!=canvas) {
            canvas.drawColor(Color.BLACK);
            canvas.drawText("Tap to start" + this.scrnH + " " + this.scrnW, canvas.getWidth() / 2, 200, font);
        }

    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        getGame().popState();
        getGame().pushState(new PongState(resources, context));
        return false;
    }



}
