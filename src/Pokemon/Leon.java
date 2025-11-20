package Pokemon;

import java.awt.*;
import javax.swing.*;

public class Leon {

    public static void showIzvInfo(String vards, int vecums, int limenis) {
        JDialog info = new JDialog((JFrame)null, "Trenera Profils", true);
        info.setSize(500, 600);
        info.setLocationRelativeTo(null);
        info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        info.setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(30, 60, 120), 
                        getWidth(), getHeight(), new Color(10, 30, 60));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        info.setContentPane(mainPanel);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel icon = new JLabel("⚡", SwingConstants.CENTER);
        icon.setFont(new Font("Arial", Font.PLAIN, 64));
        icon.setForeground(Color.YELLOW);
        
        JLabel title = new JLabel("TRENERA PROFILS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        headerPanel.add(icon, BorderLayout.CENTER);
        headerPanel.add(title, BorderLayout.SOUTH);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));

        addInfoLine(infoPanel, "Vārds:", vards);
        addInfoLine(infoPanel, "Vecums:", vecums + " gadi");
        addInfoLine(infoPanel, "Līmenis:", limenis + "");

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.YELLOW);
        separator.setMaximumSize(new Dimension(400, 2));
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(separator);
        infoPanel.add(Box.createVerticalStrut(15));

        JLabel pokemonTitle = new JLabel("POKEMONI:");
        pokemonTitle.setFont(new Font("Arial", Font.BOLD, 16));
        pokemonTitle.setForeground(Color.YELLOW);
        pokemonTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(pokemonTitle);
        infoPanel.add(Box.createVerticalStrut(10));

        if (Pokedatnis.pokemoni.isEmpty()) {
            JLabel noPokemon = new JLabel("Nav pokemonu");
            noPokemon.setFont(new Font("Arial", Font.ITALIC, 14));
            noPokemon.setForeground(Color.LIGHT_GRAY);
            noPokemon.setAlignmentX(Component.CENTER_ALIGNMENT);
            infoPanel.add(noPokemon);
        } else {
            for (Pokemons p : Pokedatnis.pokemoni) {
                addPokemonLine(infoPanel, p);
            }
        }

        mainPanel.add(infoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JButton backBtn = createButton("ATPAKAĻ", new Color(200, 60, 60));
        backBtn.addActionListener(e -> info.dispose());

        JButton continueBtn = createButton("TURPINĀT", new Color(60, 180, 60));
        continueBtn.addActionListener(e -> {
            info.dispose();
            SwingUtilities.invokeLater(() -> Pokedatnis.main(new String[]{}));
        });

        buttonPanel.add(backBtn);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(continueBtn);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        info.setVisible(true);
    }
    
    private static void addInfoLine(JPanel panel, String label, String value) {
        JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linePanel.setOpaque(false);
        linePanel.setMaximumSize(new Dimension(400, 30));
        
        JLabel labelLbl = new JLabel(label);
        labelLbl.setFont(new Font("Arial", Font.BOLD, 14));
        labelLbl.setForeground(Color.WHITE);
        labelLbl.setPreferredSize(new Dimension(80, 20));
        
        JLabel valueLbl = new JLabel(value);
        valueLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        valueLbl.setForeground(Color.YELLOW);
        
        linePanel.add(labelLbl);
        linePanel.add(valueLbl);
        panel.add(linePanel);
        panel.add(Box.createVerticalStrut(5));
    }
    
    private static void addPokemonLine(JPanel panel, Pokemons pokemon) {
        JPanel pokemonPanel = new JPanel(new BorderLayout());
        pokemonPanel.setOpaque(false);
        pokemonPanel.setMaximumSize(new Dimension(400, 30));
        pokemonPanel.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 20));
        
        JLabel nameLabel = new JLabel("• " + pokemon.getVards());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);
        
        JLabel infoLabel = new JLabel(pokemon.getTips() + " - Lv." + pokemon.getLimenis());
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setForeground(Color.CYAN);
        
        pokemonPanel.add(nameLabel, BorderLayout.WEST);
        pokemonPanel.add(infoLabel, BorderLayout.EAST);
        
        panel.add(pokemonPanel);
        panel.add(Box.createVerticalStrut(3));
    }
    
    private static JButton createButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                g2.setColor(Color.WHITE);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(120, 35));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.YELLOW);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.WHITE);
            }
        });
        
        return button;
    }
}