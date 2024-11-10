package com.example.kiteflutter;

import android.graphics.Canvas;
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

    // Update and draw the background image
    public void updateAndDrawBackgroundImage(Canvas canvas) {
        // Move the background images
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity());

        // If the background image is off-screen, reset its position
        if (backgroundImage.getX() < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImage.setX(0);
        }

        // Draw the background images at their current positions
        canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(), backgroundImage.getX(), backgroundImage.getY(), null);

        // Draw the second image right after the first one (to create a seamless effect)
        if (backgroundImage.getX() < -(AppConstants.getBitmapBank().getBackgroundWidth() - AppConstants.SCREEN_WIDTH)) {
            canvas.drawBitmap(AppConstants.getBitmapBank().getBackground(),
                    backgroundImage.getX() + AppConstants.getBitmapBank().getBackgroundWidth(), backgroundImage.getY(), null);
        }
    }

    // Update and draw the kite
    public void updateAndDrawKite(Canvas canvas) {
        if (gameState == 1) {  // If the game is running (gameState == 1)
            if (kite.getY() < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getKiteHeight()) || kite.getVelocity() < 0) {
                kite.setVelocity(kite.getVelocity() + AppConstants.gravity);
                kite.setY(kite.getY() + kite.getVelocity());
            }
        }

        int currentFrame = kite.getCurrentFrame();
        canvas.drawBitmap(AppConstants.getBitmapBank().getKite(currentFrame), kite.getX(), kite.getY(), null);

        currentFrame++;
        if (currentFrame > Kite.maxFrame) {
            currentFrame = 0;
        }
        kite.setCurrentFrame(currentFrame);
    }

    // Setter for the game state (useful for game over state and restarting)
    public void setGameState(int gameState) {
        GameEngine.gameState = gameState;
    }
}
