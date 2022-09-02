package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BreakoutGame extends JPanel implements KeyListener, ActionListener{
    private boolean isPlaying = false;
    private int playerScore = 0;
    private int totalBricks = 48;
    private int delay = 8;  // ?
    private Timer timer;
    private int playerPositionX = 310;
    private int ballPositionX = 120;
    private int ballPositionY = 350;
    private int ballDirectionX = -1;
    private int ballDirectionY = -2;
    private MapGenerator map;

    public BreakoutGame() {
        map = new MapGenerator(4, 12);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics gfx) {
        // Background
        gfx.setColor(Color.black);
        gfx.fillRect(1, 1, 692, 592);
        
        map.drawMap((Graphics2D) gfx);
        
        // Borders
        gfx.setColor(Color.red);
        // Left
        gfx.fillRect(0, 0, 3, 592);
        // Top
        gfx.fillRect(0, 0, 692, 3);
        // Right
        gfx.fillRect(683, 0, 3, 592);
        
        // Score
        gfx.setColor(Color.white);
        gfx.setFont(new Font("serif", Font.BOLD, 25));
        gfx.drawString(("" + playerScore), 590, 30);
        
        // Paddle
        gfx.setColor(Color.white);
        gfx.fillRect(playerPositionX, 550, 100, 8);
        
        // Ball
        gfx.setColor(Color.white);
        gfx.fillOval(ballPositionX, ballPositionY, 20, 20);
        
        boolean winCondition = (totalBricks <= 0);
        boolean loseCondition = (ballPositionY > 570);

        if (winCondition || loseCondition) {
            isPlaying = false;
            ballDirectionX *= 0;
            ballDirectionY *= 0;
            gfx.setColor(Color.red);
            gfx.setFont(new Font("serif", Font.BOLD, 30));

            if (winCondition) {
                gfx.drawString("You won!", 260, 300);
            }
            else if (loseCondition) {
                gfx.drawString("Game over. Score: " + playerScore, 260, 300);
            }

            gfx.setColor(Color.red);
            gfx.setFont(new Font("serif", Font.BOLD, 20));
            gfx.drawString("Press [Enter] to play again.", 230, 350);
        }

        gfx.dispose();
        return;
    }

    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerPositionX >= 600) playerPositionX = 600;
            else movePlayerRight();
        }
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerPositionX < 10) playerPositionX = 10;
            else movePlayerLeft();
        }
        if (event.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!(isPlaying)) {
                isPlaying = true;

                // Reset object positions.
                ballPositionX   *= 0; ballPositionX   += 120;
                ballPositionY   *= 0; ballPositionY   += 350;
                ballDirectionX  *= 0; ballDirectionX  += -1;
                ballDirectionY  *= 0; ballDirectionY  += -2;
                playerPositionX *= 0; playerPositionX += 310;
                /*
                    `totalBricks` should be the sum of two variables:
                        - brickRows
                        - brickColumns

                    Columns and rows should grow as you complete levels.
                */
                totalBricks     *= 0; totalBricks     += 21;
                /*
                    Score should accumulate after a win.
                    Add a new variable to count win streak.
                */
                playerScore     *= 0;
                map = new MapGenerator(3, 7);

                repaint();
            }
        }
        return;
    }

    public void keyReleased(KeyEvent event) {
        return;
    }

    public void keyTyped(KeyEvent event) {
        return;
    }

    public void movePlayerRight() {
        if (!(isPlaying)) isPlaying = true;
        playerPositionX += 20;
        return;
    }

    public void movePlayerLeft() {
        if (!(isPlaying)) isPlaying = true;
        playerPositionX -= 20;
        return;
    }

    public void actionPerformed(ActionEvent event) {
        timer.start();
        if (isPlaying) {
            // Ball intersects the left side of the paddle.
            if (
                    new Rectangle(ballPositionX, ballPositionY, 20, 20)
                    .intersects(new Rectangle(playerPositionX, 550, 30, 8))
            ) {
                ballDirectionY *= -1;
                ballDirectionX = -2;
            }
            // Ball intersects the right side of the paddle.
            else if (
                    new Rectangle(ballPositionX, ballPositionY, 20, 20)
                    .intersects(new Rectangle((playerPositionX + 70), 550, 30, 8))
            ) {
                ballDirectionY *= -1;
                ballDirectionX += 1;
            }
            // Ball intersects the center of the paddle.
            else if (
                    new Rectangle(ballPositionX, ballPositionY, 20, 20)
                    .intersects(new Rectangle((playerPositionX + 30), 550, 40, 8))
            ) {
                ballDirectionY *= -1;
            }

            checkBallAndMapCollision:
            for (int y = 0; y < map.map.length; y++) {
                for (int x = 0; x < map.map[0].length; x++) {
                    if (map.map[y][x] > 0) {
                        int brickX = ((x * map.brickWidth) + 80);
                        int brickY = ((y * map.brickHeight) + 50);

                        Rectangle ball = new Rectangle(
                            ballPositionX, ballPositionY, 20, 20
                        );
                        Rectangle brick = new Rectangle(
                            brickX, brickY, map.brickWidth, map.brickHeight
                        );

                        if (ball.intersects(brick)) {
                            map.setBrickValue(0, y, x);
                            playerScore += 5;
                            totalBricks--;
                            ballDirectionX *= 1.25;
                            ballDirectionY *= 1.25;

                            boolean hitBrickLeft = (
                                (ballPositionX + 19) <= (brick.x)
                            );
                            boolean hitBrickRight = (
                                (ballPositionX + 1) >= (brick.x + brick.width)
                            );

                            // This occasionally has strange behaviour.
                            if (hitBrickLeft || hitBrickRight) {
                                ballDirectionX *= -1;
                            }
                            // Ball hit the top or bottom of the brick.
                            else {
                                ballDirectionY *= -1;
                            }

                            break checkBallAndMapCollision;
                        }
                    }
                }
            }
            ballPositionX += ballDirectionX;
            ballPositionY += ballDirectionY;

            if ((ballPositionX < 0) || (ballPositionX > 670)) {
                ballDirectionX *= -1;
            }
            if (ballPositionY < 0) {
                ballDirectionY *= -1;
            }

            repaint();
        }
        return;
    }
}
