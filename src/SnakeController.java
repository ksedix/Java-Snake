import javax.swing.*;
import java.awt.event.*;

public class SnakeController extends JFrame implements ActionListener{

    private SnakeModel snakeModel;
    private SnakeView snakeView;

    public SnakeController(){
        this.snakeModel = new SnakeModel();
        this.snakeView = new SnakeView(snakeModel);
        snakeView.addKeyListener(new MyKeyAdapter());
        // Attach the Play Again button listener with a lambda expression
        snakeView.getPlayAgainButton().addActionListener(e -> {
            // Here, 'this' refers to SnakeController
            snakeView.getPlayAgainButton().setVisible(false);
            snakeModel.resetGame(this);
        });

        this.add(snakeView);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        snakeModel.startGame(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (snakeModel.running){
            snakeModel.move();
            snakeModel.checkApple();
            snakeModel.checkCollisions();
        }
        snakeView.repaint();
    }

    private class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            char direction = snakeModel.getDirection();
            switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    snakeModel.setDirection('L');
                    break;
                case KeyEvent.VK_RIGHT:
                    snakeModel.setDirection('R');
                    break;
                case KeyEvent.VK_UP:
                    snakeModel.setDirection('U');
                    break;
                case KeyEvent.VK_DOWN:
                    snakeModel.setDirection('D');
                    break;
            }

        }
    }

}
