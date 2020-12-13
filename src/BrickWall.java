import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BrickWall {

  final int numberOfBricks = 20;
  final List<Brick> brickWall = new ArrayList<>();

  public BrickWall() {
    for (int i = 0; i < numberOfBricks; i++) {
      if (i < 10)
      brickWall.add(new Brick(i * 45 + 120, 100));
      else
        brickWall.add(new Brick((i-10) * 45 + 120, 150));
    }
  }
  public void drawBricks(Graphics2D g){
    for (int i = 0; i < numberOfBricks; i++){
      brickWall.get(i).draw(g);
    }
  }
  public int checkCollision(int ballPositionX, int ballPositionY) {
    Brick currentBrick;
    boolean collisionX;
    boolean collisionY;
    for (int i = 0; i < 20; i++) {
      currentBrick = brickWall.get(i);
      if (currentBrick.checkBrickCollision(ballPositionX, ballPositionY)) {
        collisionX = currentBrick.checkXBrickCollision(ballPositionX);
        collisionY = currentBrick.checkYBrickCollision(ballPositionY);
        currentBrick.setActive(false);
         if (collisionX && !collisionY) {
           return 1;
         } else if (!collisionX && collisionY) {
           return 2;
         } else if (collisionX && collisionY) {
           return 3;
         }
      }
    }
    return 0;
  }
}
