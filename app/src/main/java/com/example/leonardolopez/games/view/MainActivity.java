package com.example.leonardolopez.games.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.leonardolopez.games.R;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void Helicopter1(View view) {
        Intent intent = new Intent(this, Helicopter1.class);
        startActivity(intent);
    }

    public void Helicopter2(View view) {
        Intent intent = new Intent(this, Helicopter2.class);
        startActivity(intent);
    }

    public void Helicopter3(View view) {
        Intent intent = new Intent(this, Helicopter3.class);
        startActivity(intent);
    }

    public void Pong(View view) {
        Intent intent = new Intent(this, Pong.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
