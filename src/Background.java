import javax.swing.*;

public class Background implements Frame {

  public Background() {
  }
  @Override
  public void createFrame() {
    JFrame frame = new JFrame();
    frame.setBounds(10,10,700,600);
    frame.setTitle("Brick Destroyer");
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Game game = new Game();
    frame.add(game);
  }
}
