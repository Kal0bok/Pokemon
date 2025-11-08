package Pokemon;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    
    JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 15, 15));
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 40, 100));
    buttonPanel.setBackground(new Color(20, 30, 60));

    JButton startBtn = createButton("START", Color.GREEN);
    JButton exitBtn = createButton("EXIT", Color.RED);

    startBtn.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Game starts!"));
    exitBtn.addActionListener(e -> System.exit(0));

    buttonPanel.add(startBtn);
    buttonPanel.add(exitBtn);

    frame.add(buttonPanel, BorderLayout.CENTER);
	
}
