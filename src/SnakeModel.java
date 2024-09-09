import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class SnakeModel {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    int x[] = new int[GAME_UNITS];
    int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    // U, R, L, D
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    public SnakeModel(){
        random = new Random();
    }

    public void startGame(ActionListener actionListener){
        newApple();
        running = true;
        timer = new Timer(DELAY,actionListener);
        timer.start();
    }

    public void resetGame(ActionListener actionListener){
        bodyParts = 6;
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 0;
            y[i] = 0;
        }
        direction = 'R';
        applesEaten = 0;
        newApple();
        running = true;
        timer = new Timer(DELAY,actionListener);
        timer.start();
    }

    public void newApple(){
        //how to prevent apple from spawning on snake body?
        this.appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        this.appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
    }

    public void move(){
        if (direction == 'L' && x[0] - UNIT_SIZE == x[1]){
            direction = 'R';
        }
        if (direction == 'R' && x[0] + UNIT_SIZE == x[1]){
            direction = 'L';
        }
        if (direction == 'U' && y[0] - UNIT_SIZE == y[1]){
            direction = 'D';
        }
        if (direction == 'D' && y[0] + UNIT_SIZE == y[1]) {
            direction = 'U';  // Cancel the move if it's an invalid reverse
        }

        for(int i = bodyParts; i>0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction){
            //move the head up,left,right or down
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
        }

    }

    public void checkApple(){
        if ((this.x[0]==appleX) && (this.y[0]==appleY)){
            bodyParts += 1;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions(){
        // Check if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[i] == x[0]) && (y[0] == y[i])) {
                running = false;
                break;  // Exit the loop since a collision was found
            }
        }

        // Check if head collides with walls (only if still running)
        if (running) {
            if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
                running = false;
            }
        }

        // Stop the timer if not running anymore
        if (!running) {
            timer.stop();
        }
    }

    public boolean isRunning(){
        return running;
    }

    public char getDirection(){
        return direction;
    }

    public void setDirection(char d) {
        this.direction = d;
    }
}
