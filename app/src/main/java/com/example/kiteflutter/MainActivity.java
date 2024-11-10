package com.example.kiteflutter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declare a GameEngine object
    GameEngine gameEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  // Ensure this matches your XML layout file
        AppConstants.initialization(this.getApplicationContext());

        // Initialize the GameEngine object
        gameEngine = new GameEngine();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void startGame(View view) {
        // Log the button click if needed
        Log.i("ImageButton", "clicked");

        // Display a welcome message and start the GameActivity
        Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();  // Optional: Only use this if you don’t want to return to MainActivity
    }

}
