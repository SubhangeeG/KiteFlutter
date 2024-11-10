package com.example.kiteflutter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {
    static BitmapBank bitmapBank;
    static GameEngine gameEngine;
    public static int SCREEN_WIDTH = 1080;  // Remove final to allow modification
    public static int SCREEN_HEIGHT = 1920;  // Remove final to allow modification
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    public static final int PIPE_GAP = 300; // Set the gap between the pipes

    public static void initialization(Context context){
        setScreenSize(context);  // Set dynamic screen size
        bitmapBank = new BitmapBank(context.getResources());
        gameEngine = new GameEngine();
        AppConstants.gravity = 3;
        AppConstants.VELOCITY_WHEN_JUMPED = -40;
    }

    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }

    public static GameEngine getGameEngine(){
        return gameEngine;
    }

    private static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_WIDTH = width;  // Now this is allowed
        AppConstants.SCREEN_HEIGHT = height;  // Now this is allowed
    }
}
