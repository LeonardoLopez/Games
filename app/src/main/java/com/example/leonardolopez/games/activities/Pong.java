package com.example.leonardolopez.games.activities;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.example.leonardolopez.games.states.PongStart;
import sheep.game.Game;


public class Pong extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager window = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = window.getDefaultDisplay();

        Game game = new Game(this, null);
        game.pushState(new PongStart(game.getResources(), this.getBaseContext(), display));
        setContentView(game);
    }


}