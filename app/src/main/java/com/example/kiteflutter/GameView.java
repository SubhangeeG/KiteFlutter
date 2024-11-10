package com.example.kiteflutter;

import static com.example.kiteflutter.GameEngine.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private GameThread gameThread;
    private Paint paint;
    private PipeGenerator pipeGenerator;
    private int score;
    private boolean gameOver;

    public GameView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        gameThread = new GameThread(holder);
        paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.WHITE);
        pipeGenerator = new PipeGenerator(); // Create pipe generator
        score = 0;
        gameOver = false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            // Set the game state to running and make the kite jump
            if (!gameOver) {
                gameState = 1;
                AppConstants.getGameEngine().kite.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED);
            } else {
                // If game over, restart the game
                resetGame();
            }
        }
        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if (!gameThread.isRunning()) {
            gameThread = new GameThread(holder);
            gameThread.start();
        } else {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
        // Handle surface changes if needed
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        if (gameThread.isRunning()) {
            gameThread.setIsRunning(false);
            boolean retry = true;
            while (retry) {
                try {
                    gameThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    // Handle interruption
                }
            }
        }
    }

    public void updateGame() {
        if (!gameOver) {
            // Update kite position based on its velocity
            AppConstants.getGameEngine().kite.updatePosition();

            // Generate pipes and check for collisions
            pipeGenerator.updatePipes();
            if (pipeGenerator.checkCollisions(AppConstants.getGameEngine().kite)) {
                gameOver = true;
                gameState = 2; // Game Over state
            }

            // Check if the kite has passed through pipes
            if (pipeGenerator.isPassed(AppConstants.getGameEngine().kite)) {
                score++;
                GameEngine.gameState = 1;
            }
        }
    }

    public void drawGame(Canvas canvas) {
        super.draw(canvas);

        // Draw the kite, pipes, and score
        AppConstants.getGameEngine().kite.updatePosition();
        AppConstants.getGameEngine().kite.draw(canvas, paint);
        pipeGenerator.drawPipes(canvas, paint);

        // Draw the score on the screen
        canvas.drawText("Score: " + score, 50, 100, paint);

        // Draw "Game Over" if the game is over
        if (gameOver) {
            canvas.drawText("Game Over", (float) AppConstants.SCREEN_WIDTH / 2 - 200, (float) AppConstants.SCREEN_HEIGHT / 2, paint);
            canvas.drawText("Tap to Restart", (float) AppConstants.SCREEN_WIDTH / 2 - 250, (float) AppConstants.SCREEN_HEIGHT / 2 + 100, paint);
        }
    }

    private void resetGame() {
        score = 0;
        gameOver = false;
        AppConstants.getGameEngine().kite.setY(AppConstants.SCREEN_HEIGHT / 2 - AppConstants.getBitmapBank().getKiteHeight() / 2);
        AppConstants.getGameEngine().kite.setVelocity(0);
        pipeGenerator.resetPipes();
        GameEngine.gameState = 0; // Reset to initial state
    }

    // Pause the game
    public void pauseGame() {
        if (gameThread != null && gameThread.isRunning()) {
            gameThread.setIsRunning(false);
        }
    }

    // Resume the game
    public void resumeGame() {
        if (gameThread != null && !gameThread.isRunning()) {
            gameThread = new GameThread(holder); // Recreate the thread to resume
            gameThread.start();
        }
    }

    // GameThread class to manage game updates and rendering
    private class GameThread extends Thread {
        public final SurfaceHolder surfaceHolder;
        private boolean running;

        public GameThread(SurfaceHolder holder) {
            surfaceHolder = holder;
            running = false;
        }

        public boolean isRunning() {
            return running;
        }

        public void setIsRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            while (running) {
                Canvas canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        updateGame();
                        drawGame(canvas);
                    }
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }
}
