package com.example.leonardolopez.games.states;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import com.example.leonardolopez.games.R;

import java.util.Random;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.input.TouchListener;


public class PongState extends State implements TouchListener {

    private int canvasHeight, canvasWidth;
    private Sprite paddle1, paddle2, ball;
    private Image paddleImage1, paddleImage2, ballImage;
    private int paddle1count, paddle2count;
    private Font font;
    private Resources resources;
    private Context context;
    private int scrnW;
    private int scrnH;

    public PongState(Resources res, Context con) {
        font = new Font(255, 255, 255, 100, Typeface.SERIF, Typeface.NORMAL);
        this.resources = res;
        this.context = con;

        this.scrnW= context.getResources().getDisplayMetrics().widthPixels;
        this.scrnH= context.getResources().getDisplayMetrics().heightPixels;

        paddle1count = 0;
        paddle2count = 0;
        paddleImage1 = new Image(R.drawable.paddlef1);
        paddleImage2 = new Image(R.drawable.paddlef2);
        ballImage = new Image(R.drawable.ball);

        paddle1 = new Sprite(paddleImage1);
        paddle2 = new Sprite(paddleImage2);
        ball = new Sprite(ballImage);

        paddle1.setPosition(30, this.scrnH/2);
        paddle2.setPosition(this.scrnW-30, this.scrnH/2);
        ball.setPosition(this.scrnW/2, this.scrnH/2);
        setRandomSpeed(ball);

        ball.update(System.currentTimeMillis());
        paddle1.update(System.currentTimeMillis());
        paddle2.update(System.currentTimeMillis());
    }

    private void setRandomSpeed(Sprite ball){
        float vy = (int)(Math.random()*400)+1;
        float vx = 400;
        int direction = (int)(Math.random()*3);
        if (direction==0) {ball.setSpeed(vx,vy);}
        if (direction==1) {ball.setSpeed(-vx,vy);}
        if (direction==2) {ball.setSpeed(vx,-vy);}
        if (direction==3) {ball.setSpeed(-vx,-vy);}
    }

    @Override
    public boolean onTouchMove(MotionEvent event) {

        if(event.getX()< this.scrnW/2){
            paddle1.setPosition(paddle1.getX(), event.getY());
            return true;
        }
        if(event.getX()> this.scrnW/2){
            paddle2.setPosition(paddle2.getX(), event.getY());
            return true;
        }
        return false;
    }


    @Override
    public void update(float dt) {

        if(paddle1count==5){
            getGame().popState();
            getGame().pushState(new PongOver(1, resources, context));
        }
        if(paddle2count==5){
            getGame().popState();
            getGame().pushState(new PongOver(2, resources, context));
        }

        if(ball.collides(paddle1)){
            ball.setSpeed(-(ball.getSpeed().getX()+30), ball.getSpeed().getY()+30);
        }
        if(ball.collides(paddle2)){
            ball.setSpeed(-(ball.getSpeed().getX()+30), ball.getSpeed().getY()+30);
        }

        if(ball.getY()>(canvasHeight) || ball.getY()<30){  //20 is the ball's height
            ball.setSpeed(ball.getSpeed().getX()+10, -(ball.getSpeed().getY()+10));
        }

        if(ball.getX()<paddle1.getX()){
            ball.setPosition(this.scrnW/2, this.scrnH/2);
            setRandomSpeed(ball);
            paddle2count++;
            Log.w("GameState", "One point for paddle2: " + paddle2count);
        }
        if(ball.getX()>paddle2.getX()){
            ball.setPosition(this.scrnW/2, this.scrnH/2);
            setRandomSpeed(ball);
            paddle1count++;
            Log.w("GameState", "One point for paddle1: " + paddle1count);
        }

        ball.update(dt);
        paddle1.update(dt);
        paddle2.update(dt);
    }


    @Override
    public void draw(Canvas canvas) {
        if (canvas!=null) {
            canvasHeight = canvas.getHeight();
            canvasWidth = canvas.getWidth();
            canvas.drawColor(Color.BLACK);

            canvas.drawText("" + paddle2count, (this.scrnW / 2) + 30, this.scrnH - 100, font);
            canvas.drawText("" + (paddle1count-1), (this.scrnW / 2) - 60, 100, font);
            canvas.drawLine(this.scrnW / 2, 0, this.scrnW / 2, this.scrnH, sheep.graphics.Color.WHITE);

            ball.draw(canvas);
            paddle1.draw(canvas);
            paddle2.draw(canvas);
        }
    }

}
