package test;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Graphics extends JPanel implements ActionListener {
    private Timer timer = new Timer(50, this);
    public String state;
    private Snake snake;
    private Food food;
    private Game game;

    public Graphics(Game g) {
        timer.start();
        state = "Start";
        game = g;
        snake = g.getPlayer();
        food = g.getFood();

        //add keyListener
        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Game.width * Game.dimension, Game.height * Game.dimension);

        if (state == "START") {
            g2d.setColor(Color.white);
            g2d.drawString("Pres any key", Game.width / 2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
        } else if (state == "RUNNING") {
            g2d.setColor(Color.yellow);
            g2d.fillRect(food.getX() * Game.dimension, food.getY() * Game.dimension, Game.dimension, Game.dimension);
            g2d.setColor(Color.CYAN);
            for (Rectangle r : snake.getBody()) {
                g2d.fill(r);
            }
        }else{
            g2d.setColor(Color.white);
            g2d.drawString("Score: " + (snake.getBody().size() - 3), Game.width / 2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }
}
