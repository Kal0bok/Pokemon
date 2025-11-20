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
        
        JButton helpBtn = createHelpButton();
        bottomPanel.add(helpBtn);
        
        backgroundPanel.add(title, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        backgroundPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        frame.setContentPane(backgroundPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
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
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.YELLOW);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.WHITE);
            }
        });
        
        return btn;
    }
    
    private JButton createHelpButton() {
        JButton helpBtn = new JButton("?");
        helpBtn.setFont(new Font("Arial", Font.BOLD, 16));
        helpBtn.setForeground(Color.CYAN);
        helpBtn.setBackground(new Color(40, 40, 80));
        helpBtn.setPreferredSize(new Dimension(35, 35));
        helpBtn.addActionListener(e -> showHelp());
        return helpBtn;
    }
    
    private void showHelp() {
        String helpText = """
            <html>
            <body style='width: 300px; padding: 10px;'>
            <h2 style='color: #3366CC; text-align: center;'>Pokémon Game Help</h2>
            <p><b>START GAME:</b> Begin your Pokémon adventure</p>
            <p><b>ABOUT:</b> Learn about the game</p>
            <p><b>EXIT:</b> Close the application</p>
            <hr>
            <p style='color: #666; font-size: 12px;'>
            Create and battle with Pokémon! Choose your trainer and become the best!
            </p>
            </body>
            </html>
            """;
        JOptionPane.showMessageDialog(frame, helpText, "Help", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showAbout() {
        String aboutText = """
            <html>
            <body style='width: 350px; padding: 15px;'>
            <h2 style='color: #FF6600; text-align: center;'>Pokémon Game</h2>
            <p style='text-align: center;'><b>Version 2.0</b></p>
            <p>Features:</p>
            <ul>
            <li>Create custom Pokémon</li>
            <li>Battle system</li>
            <li>Trainer profiles</li>
            <li>Pokémon management</li>
            <li>Beautiful GUI</li>
            </ul>
            <p style='color: #666; font-size: 11px; text-align: center;'>
            Created with Java Swing • OOP Principles
            </p>
            </body>
            </html>
            """;
        JOptionPane.showMessageDialog(frame, aboutText, "About", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void exitGame() {
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "Are you sure you want to exit?", "Exit Game", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(frame, "Thank you for playing Pokémon Game!");
            System.exit(0);
        }
    }
    
    private void selectTrainer() {
        frame.setVisible(false);
        
        JFrame trainerFrame = new JFrame("Select Your Trainer");
        trainerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        trainerFrame.setSize(800, 500);
        trainerFrame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 30, 30));
        mainPanel.setBackground(new Color(20, 30, 60));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        
        JPanel ashCard = createTrainerCard(
            "Ash Ketchum", 
            "The passionate Pokémon Trainer from Pallet Town", 
            new Color(70, 130, 180),
            "• Pikachu (Electric)\n• Charizard (Fire)\n• Bulbasaur (Grass)"
        );
        
        JPanel customCard = createTrainerCard(
            "Custom Trainer", 
            "Create your own trainer profile", 
            new Color(60, 179, 113),
            "• Choose your name\n• Select your age\n• Build your team"
        );
        
        ashCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                aktivTrener = new Trainer("Ash Ketchum", 12, 10);
                Pokedatnis.pokemoni.add(new ElektriskaisP("Pikachu", 5, 80, 8.5, 6.0));
                Pokedatnis.pokemoni.add(new Uguns("Charizard", 8, 120, 9.5, 7.0));
                trainerFrame.dispose();
                showTrainerProfile(aktivTrener, true);
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
    
    private JPanel createTrainerCard(String name, String description, Color color, String pokemons) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
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
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.WHITE, 2),
                    BorderFactory.createEmptyBorder(20, 20, 20, 20)
                ));
            }
        });
        
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JTextArea descArea = new JTextArea(description);
        descArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descArea.setForeground(Color.WHITE);
        descArea.setBackground(color);
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        
        JTextArea pokemonArea = new JTextArea(pokemons);
        pokemonArea.setFont(new Font("Arial", Font.ITALIC, 12));
        pokemonArea.setForeground(Color.YELLOW);
        pokemonArea.setBackground(color);
        pokemonArea.setEditable(false);
        pokemonArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        card.add(nameLabel, BorderLayout.NORTH);
        card.add(descArea, BorderLayout.CENTER);
        card.add(pokemonArea, BorderLayout.SOUTH);
        
        return card;
    }
    
    private void createCustomTrainer() {
        String name = Metodes.virkneParbaud("Enter your trainer name:");
        if (name == null) {
            selectTrainer();
            return;
        }
        
        Integer age = Metodes.skaitlaParbaudeInt("Enter your age (10-99):", 10, 99);
        if (age == null || age < 0) {
            selectTrainer();
            return;
        }
        
        Integer level = Metodes.skaitlaParbaudeInt("Enter your trainer level (1-10):", 1, 10);
        if (level == null || level < 0) {
            selectTrainer();
            return;
        }
        
        aktivTrener = new Trainer(name, age, level);
        
        Pokedatnis.pokemoni.add(new ElektriskaisP("Pikachu", 5, 80, 8.5, 6.0));
        Pokedatnis.pokemoni.add(new UdensP("Squirtle", 5, 85, 7.0, 7.5));
        
        showTrainerProfile(aktivTrener, false);
    }
    
    private void showTrainerProfile(Trainer trainer, boolean isAsh) {
        JFrame profileFrame = new JFrame("Trainer Profile");
        profileFrame.setSize(500, 600);
        profileFrame.setLocationRelativeTo(null);
        profileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 248, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("TRAINER PROFILE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(30, 60, 120));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1, 10, 10));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        infoPanel.add(createInfoLabel("Name: " + trainer.getVards()));
        infoPanel.add(createInfoLabel("Age: " + trainer.getVecums()));
        infoPanel.add(createInfoLabel("Level: " + trainer.getLimenis()));
        infoPanel.add(createInfoLabel("Pokémon: " + Pokedatnis.pokemoni.size()));
        
        JButton continueBtn = new JButton("START ADVENTURE");
        continueBtn.setFont(new Font("Arial", Font.BOLD, 16));
        continueBtn.setBackground(new Color(50, 150, 50));
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setFocusPainted(false);
        continueBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueBtn.addActionListener(e -> {
            profileFrame.dispose();
            frame.dispose();
            SwingUtilities.invokeLater(() -> Pokedatnis.main(new String[]{}));
        });
        
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(infoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        mainPanel.add(continueBtn);
        
        profileFrame.add(mainPanel);
        profileFrame.setVisible(true);
    }
    
    private JLabel createInfoLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());     
            } catch (Exception e) {
                System.err.println("Nevar iestatīt Look and Feel:  " + e.getMessage());
            }
            new MainMenu();
        });
    }
}