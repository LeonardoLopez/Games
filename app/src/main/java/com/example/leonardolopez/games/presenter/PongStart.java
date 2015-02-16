package com.example.leonardolopez.games.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
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

    public PongStart(Resources rsc, Context ctx){
        font = new Font(255, 255, 0, 100, Typeface.SANS_SERIF, Typeface.BOLD);
        font.setTextAlign(Paint.Align.CENTER);
        this.resources = rsc;
        this.context = ctx;
        this.scrnW= context.getResources().getDisplayMetrics().widthPixels;
        this.scrnH= context.getResources().getDisplayMetrics().heightPixels;
        ballImage = new Image(R.drawable.ball);
        ball = new Sprite(ballImage);
        ball.setPosition((this.scrnW/2), (this.scrnH/2));
        ball.update(System.currentTimeMillis());

    }

    @Override
    public void draw(Canvas canvas) {
        if(null!=canvas) {
            canvas.drawColor(Color.BLACK);
            canvas.drawText("Tap to start!", canvas.getWidth()/2, (canvas.getHeight()/2)-100, font);
            ball.draw(canvas);
        }

    }

    @Override
    public boolean onTouchUp(MotionEvent event) {
        getGame().popState();
        getGame().pushState(PongState.getInstance(resources, context));
        return false;
    }



}
