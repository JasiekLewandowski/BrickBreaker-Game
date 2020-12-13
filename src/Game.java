import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements KeyListener, ActionListener {

  final BrickWall brickWall;
  final Timer timer;
  final int delay = 8;
  private boolean play;
  private boolean win;
  private boolean lost;
  private int bricksLeft = 20;
  private int playerX = 310;
  private int ballPositionX = 120;
  private int ballPositionY = 350;
  private int ballDirectionX = -1;
  private int ballDirectionY = -2;


  public Game(){
    brickWall = new BrickWall();
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    timer = new Timer(delay, this);
    timer.start();
  }

  public void paint(Graphics g) {
    if (win){
      g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
      g.setColor(Color.RED);
      g.drawString("WIN", 40,350);
    }
    if (lost){
      g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
      g.setColor(Color.RED);
      g.drawString("GAME OVER", 40,350);
    } else {
      g.setColor(Color.WHITE);
      g.fillRect(1, 1, 692, 592);

      brickWall.drawBricks((Graphics2D) g);

      g.setColor(Color.RED);
      g.fillRect(0, 0, 3, 592);
      g.fillRect(0, 0, 692, 3);
      g.fillRect(681, 0, 3, 592);

      g.setColor(Color.BLACK);
      g.fillRect(playerX, 530, 100, 18);

      g.setColor(Color.RED);
      g.fillOval(ballPositionX, ballPositionY, 20, 20);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
      if (bricksLeft == 0){
        win = true;
      }
      timer.start();
      int collisionType = brickWall.checkCollision(ballPositionX, ballPositionY);
      if (play) {
        if (new Rectangle(ballPositionX, ballPositionY, 20, 20).intersects(new Rectangle(playerX, 530, 100, 18))) {
          ballDirectionY = -ballDirectionY;
        } else if (collisionType > 0) {
          if (collisionType == 1) {
            ballDirectionX = -ballDirectionX;
          } else if (collisionType == 2) {
            ballDirectionY = -ballDirectionY;
          } else if (collisionType == 3) {
            ballDirectionX = -ballDirectionX;
            ballDirectionY = -ballDirectionY;
          }
          bricksLeft--;
        }
        ballPositionX += ballDirectionX;
        ballPositionY += ballDirectionY;
        if (ballPositionX < 0) {
          ballDirectionX = -ballDirectionX;
        }
        if (ballPositionX > 660) {
          ballDirectionX = -ballDirectionX;
        }
        if (ballPositionY < 0) {
          ballDirectionY = -ballDirectionY;
        }
        if (ballPositionY > 550) {
          lost = true;
          timer.stop();
        }
      }
      repaint();
    }
  @Override
  public void keyTyped(KeyEvent e) {
  }
  @Override
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_RIGHT){
      if (playerX >= 600) {
        playerX = 600;
      } else {
        moveRight();
      }
    }
    if(e.getKeyCode() == KeyEvent.VK_LEFT) {
      if (playerX < 10) {
        playerX = 10;
      } else {
        moveLeft();
      }
    }
  }
  @Override
  public void keyReleased(KeyEvent e) {
  }
  public void moveRight() {
    play = true;
    playerX += 20;
  }
  public void moveLeft() {
    play = true;
    playerX -= 20;
  }
}
