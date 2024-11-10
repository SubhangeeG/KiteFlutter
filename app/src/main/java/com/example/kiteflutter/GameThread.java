package com.example.kiteflutter;

import android.graphics.Canvas;
import android.graphics.Paint;  // Import Paint class
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    final SurfaceHolder surfaceHolder;
    boolean isRunning;
    long startTime, loopTime;
    long DELAY = 33;
    private Log log;
    Paint paint;  // Declare a Paint object

    public GameThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
        paint = new Paint();  // Initialize the Paint object
    }

    @Override
    public void run() {
        while (isRunning) {
            startTime = SystemClock.uptimeMillis();
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if (canvas != null) {
                synchronized (surfaceHolder) {
                    // Update and draw the game background and kite
                    AppConstants.getGameEngine().updateAndDrawBackgroundImage(canvas);
                    AppConstants.getGameEngine().updateAndDrawKite(canvas);

                    // Update and draw pipes, check for collisions
                    AppConstants.getGameEngine().pipeGenerator.updatePipes();
                    AppConstants.getGameEngine().pipeGenerator.drawPipes(canvas, paint);  // Pass paint here

                    // Check for collision
                    if (AppConstants.getGameEngine().pipeGenerator.checkCollisions(AppConstants.getGameEngine().kite)) {
                        // Handle game over
                        AppConstants.getGameEngine().gameState = 2; // Game Over state
                    }

                    // Update the score and check for successful pipe pass
                    if (AppConstants.getGameEngine().pipeGenerator.isPassed(AppConstants.getGameEngine().kite)) {
                        AppConstants.getGameEngine().score++; // Increment the score
                    }

                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            loopTime = SystemClock.uptimeMillis() - startTime;
            if (loopTime < DELAY) {
                try {
                    Thread.sleep(DELAY - loopTime);
                } catch (InterruptedException e) {
                    Log.e("Interrupted", "Interrupted while sleeping");
                }
            }
        }
    }

    // Return whether the thread is running
    public boolean isRunning() {
        return isRunning;
    }

    // Set the thread state
    public void setIsRunning(boolean state) {
        isRunning = state;
    }
}
