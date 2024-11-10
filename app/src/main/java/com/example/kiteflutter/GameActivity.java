package com.example.kiteflutter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class GameActivity extends Activity {
    GameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause the game when the activity is paused
        if (gameView != null) {
            gameView.pauseGame();
            Toast.makeText(this, "Game Paused", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Resume the game when the activity is resumed
        if (gameView != null) {
            gameView.resumeGame();
            Toast.makeText(this, "Game Resumed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (gameView != null) {
            gameView.pauseGame(); // Pause the game when back button is pressed
            Toast.makeText(this, "Game Paused", Toast.LENGTH_SHORT).show();
        }
    }
}
