import javax.swing.*;
import java.awt.*;

public class SnakeView extends JPanel {

    SnakeModel snakeModel;
    private JButton playAgain;

    public SnakeView(SnakeModel snakeModel){
        this.snakeModel = snakeModel;
        this.playAgain = new JButton("Play Again");
        this.playAgain.setVisible(false);
        // Customize the button
        // Set button's background and text color
        playAgain.setBackground(Color.darkGray);
        playAgain.setForeground(Color.red);
        // Set button's font
        playAgain.setFont(new Font("Ink Free", Font.BOLD, 30));

        this.add(playAgain);
        this.setPreferredSize(new Dimension(snakeModel.SCREEN_WIDTH,snakeModel.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void drawApple(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(snakeModel.appleX, snakeModel.appleY, snakeModel.UNIT_SIZE, snakeModel.UNIT_SIZE);
    }

    public void drawSnake(Graphics g){
        for (int i = 0; i < snakeModel.bodyParts; i++) {
            if (i == 0) {
                g.setColor(Color.green);
            } else {
                g.setColor(new Color(45, 180, 0));
            }
            g.fillRect(snakeModel.x[i], snakeModel.y[i], snakeModel.UNIT_SIZE, snakeModel.UNIT_SIZE);
        }
    }

    public void drawScore(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + snakeModel.applesEaten, (snakeModel.SCREEN_WIDTH - metrics.stringWidth("Score: " + snakeModel.applesEaten)) / 2, g.getFont().getSize());
    }

    public void draw(Graphics g){
        /*
        for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
        }
         */
        if (snakeModel.isRunning()) {
            drawApple(g);
            drawSnake(g);
            drawScore(g);
        }   else {
            gameOver(g);
        }
    }

    //Have play again button. Show score below game over
    public void gameOver(Graphics g){
        //Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+snakeModel.applesEaten,(snakeModel.SCREEN_WIDTH-metrics1.stringWidth("Score: "+snakeModel.applesEaten))/2, g.getFont().getSize());
        //Game Over text
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over",(snakeModel.SCREEN_WIDTH-metrics2.stringWidth("Game Over"))/2,snakeModel.SCREEN_HEIGHT/2);
        this.playAgain.setVisible(true);
        this.playAgain.setBounds((snakeModel.SCREEN_WIDTH-250)/2, snakeModel.SCREEN_HEIGHT / 2 + 50, 250, 50);
    }

    // Expose the button to the controller to add a listener
    public JButton getPlayAgainButton() {
        return this.playAgain;
    }





}
