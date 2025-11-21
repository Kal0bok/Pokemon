package Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu {

    private JFrame frame;
    public static Trainer aktivTrener;

    public MainMenu() {
        initialize();
    }

    // Galvenā loga izveide
    private void initialize() {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            System.err.println("Nevar iestatīt sistēmas izskatu: " + e.getMessage());
        }

        frame = new JFrame("Pokémon Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(30, 60, 120);
                Color color2 = new Color(10, 30, 60);
                GradientPaint gradient = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("POKÉMON GAME", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.YELLOW);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 30, 100));
        buttonPanel.setOpaque(false);

        JButton startBtn = createStyledButton("START GAME", new Color(0, 150, 0));
        JButton aboutBtn = createStyledButton("ABOUT", new Color(0, 100, 200));
        JButton exitBtn = createStyledButton("EXIT", new Color(200, 0, 0));

        startBtn.addActionListener(e -> selectTrainer());
        aboutBtn.addActionListener(e -> showAbout());
        exitBtn.addActionListener(e -> exitGame());

        buttonPanel.add(startBtn);
        buttonPanel.add(aboutBtn);
        buttonPanel.add(exitBtn);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setOpaque(false);
        bottomPanel.add(createHelpButton());

        backgroundPanel.add(title, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH);

        frame.setContentPane(backgroundPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Pogu izskata veidošana
    private JButton createStyledButton(String text, Color color) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(Color.WHITE);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 25, 25);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Arial", Font.BOLD, 18));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        btn.setContentAreaFilled(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) { btn.setForeground(Color.YELLOW); }
            public void mouseExited(MouseEvent evt) { btn.setForeground(Color.WHITE); }
        });
        return btn;
    }

    // Palīdzības poga
    private JButton createHelpButton() {
        JButton helpBtn = new JButton("?");
        helpBtn.setFont(new Font("Arial", Font.BOLD, 16));
        helpBtn.setForeground(Color.CYAN);
        helpBtn.setBackground(new Color(40, 40, 80));
        helpBtn.setPreferredSize(new Dimension(35, 35));
        helpBtn.addActionListener(e -> showHelp());
        return helpBtn;
    }

    // Palīdzības logs
    private void showHelp() {
        String helpText = """
            <html><body style='width: 300px; padding: 10px;'>
            <h2 style='color: #3366CC; text-align: center;'>Palīdzība</h2>
            <p><b>START GAME:</b> Sākt spēli</p>
            <p><b>ABOUT:</b> Par spēli</p>
            <p><b>EXIT:</b> Iziet</p>
            </body></html>
            """;
        JOptionPane.showMessageDialog(frame, helpText, "Palīdzība", JOptionPane.INFORMATION_MESSAGE);
    }

    // "Par spēli" logs
    private void showAbout() {
        String aboutText = """
            <html><body style='width: 350px; padding: 15px;'>
            <h2 style='color: #FF6600; text-align: center;'>Pokémon Game</h2>
            <p style='text-align: center;'><b>Versija 2.0</b></p>
            <ul>
            <li>Pielāgoti Pokémoni</li>
            <li>Cīņu sistēma</li>
            <li>Treneru profili</li>
            <li>Skaists GUI</li>
            </ul>
            </body></html>
            """;
        JOptionPane.showMessageDialog(frame, aboutText, "Par spēli", JOptionPane.INFORMATION_MESSAGE);
    }

    // Spēles aizvēršana
    private void exitGame() {
        int confirm = JOptionPane.showConfirmDialog(frame,
            "Vai tiešām vēlies iziet?", "Iziet no spēles",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(frame, "Paldies par spēlēšanu!");
            System.exit(0);
        }
    }

    // Trenera izvēles logs
    private void selectTrainer() {
        frame.setVisible(false);

        JFrame trainerFrame = new JFrame("Izvēlies treneri");
        trainerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        trainerFrame.setSize(800, 500);
        trainerFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 30, 30));
        mainPanel.setBackground(new Color(20, 30, 60));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        JPanel ashCard = createTrainerCard(
            "Ash Ketchum",
            "Leģendārais treneris no Pallet Town",
            new Color(180, 40, 40),
            "• Pikachu (Electric)\n• Charizard (Fire)\n• Bulbasaur (Grass)"
        );

        JPanel customCard = createTrainerCard(
            "Pielāgots treneris",
            "Izveido pats savu profilu",
            new Color(60, 179, 113),
            "• Vārds\n• Vecums\n• Pokémoni"
        );

        ashCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                aktivTrener = new Trainer("Ash Ketchum", 12, 10);
                Pokedatnis.pokemoni.clear();
                Pokedatnis.pokemoni.add(new ElektriskaisP("Pikachu", 5, 80, 8.5, 6.0));
                Pokedatnis.pokemoni.add(new Uguns("Charizard", 8, 120, 9.5, 7.0));
                trainerFrame.dispose();
                Ash.showAshInfo();
            }
        });

        customCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                trainerFrame.dispose();
                createCustomTrainer();
            }
        });

        mainPanel.add(ashCard);
        mainPanel.add(customCard);
        trainerFrame.add(mainPanel);
        trainerFrame.setVisible(true);
    }

    // Trenera kartiņas izveide
    private JPanel createTrainerCard(String name, String desc, Color color, String pokes) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.WHITE, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.YELLOW, 3),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)));
            }
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.WHITE, 2),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)));
            }
        });

        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);

        JTextArea descArea = new JTextArea(desc);
        descArea.setEditable(false);
        descArea.setBackground(color);
        descArea.setForeground(Color.WHITE);
        descArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);

        JTextArea pokeArea = new JTextArea(pokes);
        pokeArea.setEditable(false);
        pokeArea.setBackground(color);
        pokeArea.setForeground(Color.YELLOW);
        pokeArea.setFont(new Font("Arial", Font.ITALIC, 12));

        card.add(nameLabel, BorderLayout.NORTH);
        card.add(descArea, BorderLayout.CENTER);
        card.add(pokeArea, BorderLayout.SOUTH);
        return card;
    }

    // Pielāgota trenera izveide
    private void createCustomTrainer() {
        String name = Metodes.virkneParbaud("Ievadi trenera vārdu:");
        if (name == null || name.trim().isEmpty()) {
            selectTrainer();
            return;
        }

        Integer age = Metodes.skaitlaParbaudeInt("Ievadi vecumu (10-99):", 10, 99);
        if (age == null) { selectTrainer(); return; }

        Integer level = Metodes.skaitlaParbaudeInt("Ievadi līmeni (1-10):", 1, 10);
        if (level == null) { selectTrainer(); return; }

        aktivTrener = new Trainer(name, age, level);

        Pokedatnis.pokemoni.clear();
        Pokedatnis.pokemoni.add(new ElektriskaisP("Pikachu", 5, 80, 8.5, 6.0));
        Pokedatnis.pokemoni.add(new UdensP("Squirtle", 5, 85, 7.0, 7.5));

        Leon.showIzvInfo(name, age, level);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}
