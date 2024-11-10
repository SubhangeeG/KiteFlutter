package com.example.kiteflutter;

public class BackgroundImage {
    private int backgroundImageX; // Make this non-final
    private int backgroundImageY;
    private final int backgroundImageVelocity;

    private int pipeX; // For pipe movement
    private int pipeYTop; // For top pipe
    private int pipeYBottom; // For bottom pipe
    private boolean isPipePassed; // To check if kite has passed the pipe

    public BackgroundImage() {
        backgroundImageX = 0;
        backgroundImageY = 0;
        backgroundImageVelocity = 3;

        pipeX = AppConstants.SCREEN_WIDTH; // Start pipe at the right side of the screen
        pipeYTop = (int) (Math.random() * ((double) AppConstants.SCREEN_HEIGHT / 2)); // Randomize top pipe position
        pipeYBottom = pipeYTop + AppConstants.PIPE_GAP; // Keep bottom pipe a fixed distance from the top pipe
        isPipePassed = false;
    }

    public int getX() {
        return backgroundImageX;
    }

    public int getY() {
        return backgroundImageY;
    }

    public int getVelocity() {
        return backgroundImageVelocity;
    }

    public void setX(int backgroundImageX) {
        this.backgroundImageX = backgroundImageX;
    }

}
