package com.example.kiteflutter;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Pipe {
    private int x, height;
    private boolean passed;

    public Pipe(int startX, int height) {
        this.x = startX;
        this.height = height;
        this.passed = false;
    }

    // Update the pipe's position
    public void update() {
        x -= 10;  // Move the pipe leftward
    }

    // Draw the pipe
    public void draw(Canvas canvas, Paint paint) {
        int bottomY = AppConstants.SCREEN_HEIGHT - height;
        // Draw the top pipe
        canvas.drawRect(x, 0, x + 100, height, paint);
        // Draw the bottom pipe
        canvas.drawRect(x, bottomY, x + 100, AppConstants.SCREEN_HEIGHT, paint);
    }

    // Check if the kite collides with the pipe
    public boolean collidesWith(Kite kite) {
        int kiteX = kite.getX();
        int kiteY = kite.getY();
        int kiteWidth = AppConstants.getBitmapBank().getKiteWidth();
        int kiteHeight = AppConstants.getBitmapBank().getKiteHeight();

        // Check for collision with top pipe
        if (kiteX + kiteWidth > x && kiteX < x + 100) {
            if (kiteY < height || kiteY + kiteHeight > AppConstants.SCREEN_HEIGHT - height) {
                return true;
            }
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return 100;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public boolean isPassed() {
        return passed;
    }
}
