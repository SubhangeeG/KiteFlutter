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
        pipeYTop = (int) (Math.random() * (AppConstants.SCREEN_HEIGHT / 2)); // Randomize top pipe position
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

    public void setY(int backgroundImageY) {
        this.backgroundImageY = backgroundImageY;
    }

    // Methods to manage pipe movement
    public int getPipeX() {
        return pipeX;
    }

    public void setPipeX(int pipeX) {
        this.pipeX = pipeX;
    }

    public int getPipeYTop() {
        return pipeYTop;
    }

    public void setPipeYTop(int pipeYTop) {
        this.pipeYTop = pipeYTop;
    }

    public int getPipeYBottom() {
        return pipeYBottom;
    }

    public void setPipeYBottom(int pipeYBottom) {
        this.pipeYBottom = pipeYBottom;
    }

    public boolean isPipePassed() {
        return isPipePassed;
    }

    public void setPipePassed(boolean isPipePassed) {
        this.isPipePassed = isPipePassed;
    }

    // Method to update the background and pipes' positions
    public void updatePosition() {
        // Move the background
        backgroundImageX -= backgroundImageVelocity;
        if (backgroundImageX < -AppConstants.getBitmapBank().getBackgroundWidth()) {
            backgroundImageX = 0; // Reset the background to create a seamless effect
        }

        // Move the pipes
        pipeX -= backgroundImageVelocity;
        if (pipeX < -AppConstants.getBitmapBank().getPipeWidth()) {
            pipeX = AppConstants.SCREEN_WIDTH; // Reset pipe to the right side
            pipeYTop = (int) (Math.random() * (AppConstants.SCREEN_HEIGHT / 2)); // Randomize top pipe
            pipeYBottom = pipeYTop + AppConstants.PIPE_GAP; // Keep bottom pipe a fixed distance from the top pipe
            isPipePassed = false; // Reset pipe passed state
        }
    }
}
