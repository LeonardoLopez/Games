package com.example.leonardolopez.games.view;

import com.example.leonardolopez.games.presenter.Heli2State;
import android.app.Activity;
import android.os.Bundle;
import sheep.game.Game;

public class Helicopter2 extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the game.
        Game game = new Game(this, null);
        // Push the main state.
        game.pushState(new Heli2State(game.getResources(), this.getBaseContext()));
        // View the game.
        setContentView(game);
    }
}
