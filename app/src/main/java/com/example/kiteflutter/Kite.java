package com.example.kiteflutter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Kite {
    private final int kiteX;
    private int kiteY;
    private int currentFrame;
    private int velocity;
    public static int maxFrame;
    private final Bitmap kiteBitmap;

    // Constructor to initialize the kite's position and other properties, with a Bitmap parameter
    public Kite(Bitmap kiteBitmap) {
        this.kiteBitmap = kiteBitmap;
        kiteX = AppConstants.SCREEN_WIDTH / 2 - AppConstants.getBitmapBank().getKiteWidth() / 2;
        kiteY = AppConstants.SCREEN_HEIGHT / 2 - AppConstants.getBitmapBank().getKiteHeight() / 2;
        currentFrame = 0;
        maxFrame = 3;
        velocity = 0;
    }

    // Getters and setters for kite's properties

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }


    public int getX() {
        return kiteX;
    }

    public int getY() {
        return kiteY;
    }

    public void setY(int kiteY) {
        this.kiteY = kiteY;
    }

    // Method to update the kite's position based on its velocity (for gravity and movement)
    public void updatePosition() {
        if (kiteY < (AppConstants.SCREEN_HEIGHT - AppConstants.getBitmapBank().getKiteHeight()) || velocity < 0) {
            velocity += AppConstants.gravity;  // Apply gravity effect to velocity
            kiteY += velocity;  // Update the kite's Y position based on velocity
        }
    }

    // Method to draw the kite on the canvas
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(kiteBitmap, kiteX, kiteY, paint);
    }
}
