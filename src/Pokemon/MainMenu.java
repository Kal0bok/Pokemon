package Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu {

    public MainMenu() {
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

        startBtn.addActionListener(e -> openTwoImagesWindow());

        exitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(startBtn);
        buttonPanel.add(exitBtn);

        frame.add(buttonPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(new Color(20, 30, 60));

        JButton helpBtn = new JButton("?");
        helpBtn.setFont(new Font("Arial", Font.BOLD, 18));
        helpBtn.setForeground(Color.CYAN);
        helpBtn.setBackground(new Color(40, 40, 80));
        helpBtn.setPreferredSize(new Dimension(40, 40));
        helpBtn.addActionListener(e -> showHelp(frame));

        bottomPanel.add(helpBtn);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 22));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(12, 30, 12, 30));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void showHelp(JFrame frame) {
        String helpText = """
            <html>
            <h2>Help:</h2>
            <ul>
            <li>Press START to begin.</li>
            <li>Press EXIT to close app.</li>
            </ul>
            </html>
            """;
        JOptionPane.showMessageDialog(frame, helpText, "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private void openTwoImagesWindow() {
        JFrame frame = new JFrame("Pick an Image");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        JLabel img1 = new JLabel();
        img1.setHorizontalAlignment(SwingConstants.CENTER);
        img1.setIcon(new ImageIcon("image_left.png")); 
        img1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        JLabel img2 = new JLabel();
        img2.setHorizontalAlignment(SwingConstants.CENTER);
        img2.setIcon(new ImageIcon("image_right.png")); 
        img2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        img1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Tu izvēlas ASH!");
            }
        });

        img2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Tu izvēlas LEON!");
            }
        });

        mainPanel.add(img1);
        mainPanel.add(img2);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
