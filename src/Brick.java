import java.awt.*;

public class Brick {

  final Rectangle brick;
  final int brickPositionX;
  final int brickPositionY;
  final int brickWidth = 40;
  final int brickHeight = 40;
  private boolean active = true;

  public Brick(int brickPositionX, int brickPositionY) {
    brick = new Rectangle(brickPositionX, brickPositionY, brickWidth, brickHeight);
    this.brickPositionX = brickPositionX;
    this.brickPositionY = brickPositionY;
  }
  public void draw(Graphics2D g) {
    if (active)
      g.setColor(Color.BLACK);
    else
      g.setColor(Color.WHITE);
    g.fillRect(this.brickPositionX, this.brickPositionY, brickWidth, brickHeight);
  }
  public boolean checkBrickCollision(int ballPositionX, int ballPositionY) {
    if (active)
      return this.brick.intersects(ballPositionX, ballPositionY, 20, 20);
    else
      return false;
}
  public boolean checkXBrickCollision(int ballPositionX){
        int collisionRangeRight = (brickPositionX + 38) - ballPositionX;
        int collisionRangeLeft =  brickPositionX - (ballPositionX + 19);
        return (collisionRangeRight > -2  && collisionRangeRight < 2 || collisionRangeLeft >= -2 && collisionRangeLeft < 2);
  }
  public boolean checkYBrickCollision(int ballPositionY){
        int collisionRangeUp = ballPositionY + 18 - brickPositionY;
        int collisionRangeDown = ballPositionY - 38 - brickPositionY;
        return (collisionRangeUp > -2 && collisionRangeUp < 2 || collisionRangeDown > -2 && collisionRangeDown < 2);
  }
  public void setActive(boolean active) {
    this.active = active;
  }
}
