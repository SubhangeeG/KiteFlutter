package com.example.kiteflutter;

import android.graphics.Paint;  // Import Paint class

public class GameEngine {
    BackgroundImage backgroundImage;
    Kite kite;
    static int gameState;
    int score;
    PipeGenerator pipeGenerator;  // Added pipe generator to handle pipes
    Paint paint;  // Declare a Paint object

    public GameEngine() {
        backgroundImage = new BackgroundImage();
        kite = new Kite(AppConstants.getBitmapBank().getKite(0));
        pipeGenerator = new PipeGenerator();  // Initialize the pipe generator
        paint = new Paint();  // Initialize the Paint object
        gameState = 0;  // Game state 0 - initial state, 1 - playing, 2 - game over
        score = 0;  // Initialize score
    }

}
