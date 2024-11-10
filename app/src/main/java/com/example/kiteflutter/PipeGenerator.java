package com.example.kiteflutter;

import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.graphics.Paint;


public class PipeGenerator {
    private final List<Pipe> pipes;
    private final Random random;

    public PipeGenerator() {
        pipes = new ArrayList<>();
        random = new Random();
    }

    // Update the pipes' position and generate new pipes if needed
    public void updatePipes() {
        // Update each pipe's position
        for (Pipe pipe : pipes) {
            pipe.update();
        }

        // Remove pipes that have moved off-screen
        for (int i = 0; i < pipes.size(); i++) {
            if (pipes.get(i).getX() < 0) {
                final Pipe remove = pipes.remove(i);
            }
        }

        // Generate a new pipe if needed
        if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < AppConstants.SCREEN_WIDTH - 400) {
            generatePipe();
        }
    }

    // Draw all pipes on the screen
    public void drawPipes(Canvas canvas, Paint paint) {
        for (Pipe pipe : pipes) {
            pipe.draw(canvas, paint);
        }
    }

    // Generate a new pipe with a random height
    private void generatePipe() {
        int height = random.nextInt(AppConstants.SCREEN_HEIGHT - 200) + 100;  // Random height
        pipes.add(new Pipe(AppConstants.SCREEN_WIDTH, height));
    }

    // Check if the kite has passed through any pipe
    public boolean isPassed(Kite kite) {
        for (Pipe pipe : pipes) {
            if (pipe.getX() + pipe.getWidth() < kite.getX() && !pipe.isPassed()) {
                pipe.setPassed(true);
                return true;
            }
        }
        return false;
    }

    // Check for collision with pipes
    public boolean checkCollisions(Kite kite) {
        for (Pipe pipe : pipes) {
            if (pipe.collidesWith(kite)) {
                return true;  // Collision detected
            }
        }
        return false;
    }

    // Reset the pipes when restarting the game
    public void resetPipes() {
        pipes.clear();
    }
}
