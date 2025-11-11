package Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu {

    private Trainer treneris;  // ← здесь хранится выбранный или созданный тренер

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

        startBtn.addActionListener(e -> trainerMenu());

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
            <li>Press START to begin the game.</li>
            <li>Press EXIT to close the application.</li>
            </ul>
            </html>
            """;
        JOptionPane.showMessageDialog(frame, helpText, "Help", JOptionPane.INFORMATION_MESSAGE);
    }

    private void trainerMenu() {
        JFrame frame = new JFrame("Trainer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 500);
        frame.setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBackground(new Color(20, 30, 60));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel card1 = createGameCard(
                "Ash",
                getClass().getResource("/GIF/ashg.gif"),
                "Pikachu treneris. Galvenais varonis!"
        );

        JPanel card2 = createGameCard(
                "Izveidot treneri",
                getClass().getResource("/GIF/question.gif"),
                "Izveido savu treneri!"
        );

        // Izvēle — Ash
        card1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                treneris = new Trainer("Ash", 15, 10);
                JOptionPane.showMessageDialog(frame, "Tu izvēlējies Ash!");
            }
        });

        // Izvēle — izveidot treneri
        card2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                createTrainer();
            }
        });

        mainPanel.add(card1);
        mainPanel.add(card2);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createGameCard(String name, java.net.URL imageUrl, String description) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(40, 40, 80));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel title = new JLabel(name, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel image = new JLabel();
        image.setHorizontalAlignment(SwingConstants.CENTER);
        image.setIcon(new ImageIcon(imageUrl));

        JLabel desc = new JLabel(description, SwingConstants.CENTER);
        desc.setFont(new Font("Arial", Font.PLAIN, 14));
        desc.setForeground(Color.LIGHT_GRAY);
        desc.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        panel.add(title, BorderLayout.NORTH);
        panel.add(image, BorderLayout.CENTER);
        panel.add(desc, BorderLayout.SOUTH);

        return panel;
    }

    // ✅ ТВОЙ ВСТАВЛЕННЫЙ КОД — СОЗДАНИЕ ТРЕНЕРА
    private void createTrainer() {

        String tVards = Metodes.virkneParbaud("Ievadi trenera vārdu:");
        if (tVards == null) return;

        double vecums = (double) Metodes.skaitlaParbaude("Ievadi vecumu (5-99)", 5, 99);
        if (vecums < 0) return;

        double limenis = (double) Metodes.skaitlaParbaude("Ievadi līmeni (1-10)", 1, 10);
        if (limenis < 0) return;

        treneris = new Trainer(tVards, vecums, limenis);

        JOptionPane.showMessageDialog(null, "Treneris sekmīgi izveidots!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
