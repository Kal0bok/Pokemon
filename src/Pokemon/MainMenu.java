package Pokemon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainMenu {
	JFrame frame = new JFrame("Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    frame.setResizable(false);
    frame.setLayout(new BorderLayout());
    frame.getContentPane().setBackground(new Color(20, 30, 60));


    JLabel title = new JLabel("POKEMON GAME", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.BOLD, 36));
    title.setForeground(Color.YELLOW);
    title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
    frame.add(title, BorderLayout.NORTH);
	
}
