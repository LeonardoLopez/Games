package com.example.leonardolopez.games;

import sheep.game.Game;
import android.app.Activity;
import android.os.Bundle;

public class Helicopter1 extends Activity {


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Create the game.
        Game game = new Game(this, null);
        // Push the main state.
        game.pushState(new Heli1());
        // View the game.
        setContentView(game);
    }

}