package com.example.leonardolopez.games.activities;


import android.app.Activity;
import android.os.Bundle;
import com.example.leonardolopez.games.states.PongStart;
import sheep.game.Game;


public class Pong extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Game game = new Game(this, null);
        game.pushState(new PongStart());
        setContentView(game);
    }


}