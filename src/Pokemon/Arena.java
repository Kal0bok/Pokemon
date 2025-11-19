package Pokemon;

import javax.swing.*;
import java.awt.*;

public class Arena {

    private JFrame frame;

    public Arena() {
        createMainWindow();
    }

    private void createMainWindow() {
        frame = new JFrame("Pokemon Arena");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/GIF/Arena1.gif")));
        background.setLayout(new BorderLayout());

        JLabel title = new JLabel("POKEMON GAME", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        frame.setContentPane(background);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Arena::new);
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridLayout(2, 1, 15, 15));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 180, 80, 180));

        JButton start = new JButton("START");
        start.setPreferredSize(new Dimension(120, 35));
        start.setFont(new Font("Arial", Font.PLAIN, 22));
        start.setBackground(new Color(255, 140, 0));
        start.setForeground(Color.WHITE);

        JButton exit = new JButton("EXIT");
        exit.setPreferredSize(new Dimension(120, 35));
        exit.setFont(new Font("Arial", Font.PLAIN, 22));
        exit.setBackground(new Color(150, 0, 0));
        exit.setForeground(Color.WHITE);

        buttonsPanel.add(start);
        buttonsPanel.add(exit);
        background.add(buttonsPanel, BorderLayout.CENTER);
    }
}