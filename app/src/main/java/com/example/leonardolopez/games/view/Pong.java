package com.example.leonardolopez.games.view;


import android.app.Activity;
import android.os.Bundle;

import com.example.leonardolopez.games.presenter.PongStart;
import sheep.game.Game;


public class Pong extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Game game = new Game(this, null);
        game.pushState(new PongStart(game.getResources(), this.getBaseContext()));
        setContentView(game);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Game game = new Game(this, null);
        game.pushState(new PongStart(game.getResources(), this.getBaseContext()));
        setContentView(game);
    }

}