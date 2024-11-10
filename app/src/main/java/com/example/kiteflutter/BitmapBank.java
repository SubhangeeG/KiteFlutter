package com.example.kiteflutter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {
    private Bitmap background;
    Bitmap[] kite;
    Bitmap pipeTop, pipeBottom;
    Bitmap gameOver;
    Bitmap scoreBackground;

    public BitmapBank(Resources res) {
        // Background initialization
        background = BitmapFactory.decodeResource(res, R.drawable.perfect);
        if (background != null) {
            background = scaleImage(background);
        }

        // Kite images initialization
        kite = new Bitmap[4];
        kite[0] = BitmapFactory.decodeResource(res, R.drawable.k11);
        kite[1] = BitmapFactory.decodeResource(res, R.drawable.k22);
        kite[2] = BitmapFactory.decodeResource(res, R.drawable.k33);
        kite[3] = BitmapFactory.decodeResource(res, R.drawable.k44);

        // Pipe images initialization
        pipeTop = BitmapFactory.decodeResource(res, R.drawable.imgup1);
        pipeBottom = BitmapFactory.decodeResource(res, R.drawable.imgdown1);

        // Game over screen initialization
        gameOver = BitmapFactory.decodeResource(res, R.drawable.game_over);

        // Score background initialization
        scoreBackground = BitmapFactory.decodeResource(res, R.drawable.cartoo);
    }

    // Method to get Kite for a specific frame
    public Bitmap getKite(int frame) {
        return kite[frame];
    }

    // Methods for kite image dimensions
    public int getKiteWidth() {
        return kite[0].getWidth();
    }

    public int getKiteHeight() {
        return kite[0].getHeight();
    }

    // Methods for background image
    public Bitmap getBackground() {
        return background;
    }

    public int getBackgroundWidth() {
        return background != null ? background.getWidth() : 0;
    }

    public int getBackgroundHeight() {
        return background != null ? background.getHeight() : 0;
    }

    // Method to scale the background image according to screen size
    public Bitmap scaleImage(Bitmap bitmap) {
        float widthHeightRatio = (float) getBackgroundWidth() / getBackgroundHeight();
        int backgroundScaleWidth = (int) (widthHeightRatio * AppConstants.SCREEN_HEIGHT);
        return Bitmap.createScaledBitmap(bitmap, backgroundScaleWidth, AppConstants.SCREEN_HEIGHT, false);
    }
}
