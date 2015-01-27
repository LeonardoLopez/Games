package com.example.leonardolopez.games.activities;

import com.example.leonardolopez.games.states.Heli3State;

import android.app.Activity;
import android.os.Bundle;

import sheep.game.Game;


public class Helicopter3 extends Activity {


    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Create the game.
        Game game = new Game(this, null);
        // Push the main state.
        game.pushState(new Heli3State(game.getResources(), this.getBaseContext()));
        // View the game.
        setContentView(game);
    }

}