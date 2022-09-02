package breakout;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        BreakoutGame game = new BreakoutGame();
        window.setBounds(10, 10, 700, 600);
        window.setTitle("Breakout");
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(game);
        window.setVisible(true);
        return;
    }
}
