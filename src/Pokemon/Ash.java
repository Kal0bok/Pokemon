package Pokemon;

import java.awt.*;
import javax.swing.*;

public class Ash {

    // Galvenā metode Ash Ketchum profila attēlošanai
    public static void showAshInfo() {
        // Dialoga loga izveide ar modālu režīmu (bloķē citus logus)
        JDialog info = new JDialog((JFrame)null, "Ash Ketchum Profils", true);
        info.setSize(500, 600);
        info.setLocationRelativeTo(null); // Centrē logu uz ekrāna
        info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        info.setLayout(new BorderLayout());
        
        // Galvenā panelis ar gradienta fonu
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Sarkanīgs gradienta fons Ash tēmai
                GradientPaint gradient = new GradientPaint(0, 0, new Color(180, 0, 0), 
                        getWidth(), getHeight(), new Color(120, 0, 0));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        info.setContentPane(mainPanel);

        // Virsraksta panelis ar ikonu un nosaukumu
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setOpaque(false);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel icon = new JLabel("🏆", SwingConstants.CENTER);
        icon.setFont(new Font("Arial", Font.PLAIN, 64));
        icon.setForeground(Color.YELLOW);
        
        JLabel title = new JLabel("ASH KETCHUM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        headerPanel.add(icon, BorderLayout.CENTER);
        headerPanel.add(title, BorderLayout.SOUTH);
        
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Informācijas panelis ar trenera datiem
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 20, 40));

        // Trenera pamatinformācijas pievienošana
        addInfoLine(infoPanel, "Vārds:", "Ash Ketchum");
        addInfoLine(infoPanel, "Vecums:", "12 gadi");
        addInfoLine(infoPanel, "Reģions:", "Kanto");
        addInfoLine(infoPanel, "Līmenis:", "10");
        addInfoLine(infoPanel, "Mērķis:", "Pokemon Masters");
        
        // Atdalītājs starp trenera informāciju un pokemonu sarakstu
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.YELLOW);
        separator.setMaximumSize(new Dimension(400, 2));
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(separator);
        infoPanel.add(Box.createVerticalStrut(15));
        
        // Pokemonu sadaļas virsraksts
        JLabel pokemonTitle = new JLabel("POKEMONI:");
        pokemonTitle.setFont(new Font("Arial", Font.BOLD, 16));
        pokemonTitle.setForeground(Color.YELLOW);
        pokemonTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(pokemonTitle);
        infoPanel.add(Box.createVerticalStrut(10));
        
        // Pokemonu saraksta attēlošana vai ziņojums, ja nav pokemonu
        if (Pokedatnis.pokemoni.isEmpty()) {
            JLabel noPokemon = new JLabel("Nav pokemonu");
            noPokemon.setFont(new Font("Arial", Font.ITALIC, 14));
            noPokemon.setForeground(Color.LIGHT_GRAY);
            noPokemon.setAlignmentX(Component.CENTER_ALIGNMENT);
            infoPanel.add(noPokemon);
        } else {
            // Cikls cauri visiem pokemoniem un to pievienošana sarakstam
            for (Pokemons p : Pokedatnis.pokemoni) {
                addPokemonLine(infoPanel, p);
            }
        }

        // Ash citāta pievienošana apakšā
        infoPanel.add(Box.createVerticalStrut(20));
        JLabel quote = new JLabel("<html><div style='text-align: center;'>" +
                "\"Es kļūšu par Pokemon Masters!\"<br>" +
                "<small>- Ash Ketchum</small></div></html>");
        quote.setFont(new Font("Arial", Font.ITALIC, 12));
        quote.setForeground(Color.ORANGE);
        quote.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(quote);

        mainPanel.add(infoPanel, BorderLayout.CENTER);

        // Pogu panelis ar turpināšanas pogu
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        // Galvenā turpināšanas poga
        JButton continueBtn = createButton("TURPINĀT", new Color(60, 180, 60));
        continueBtn.addActionListener(e -> {
            info.dispose(); // Aizver pašreizējo logu
            closeAllWindows(); // Aizver visus citus logus
            startGame(); // Palaiž galveno spēli
        });

        buttonPanel.add(continueBtn);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        info.setVisible(true); // Padara logu redzamu
    }
    
    // Metode spēles palaišanai
    private static void startGame() {
        SwingUtilities.invokeLater(() -> {
            try {
                // Izsauc galveno spēles klasi
                Pokedatnis.main(new String[]{});
            } catch (Exception e) {
                System.err.println("Kļūda palaižot spēli: " + e.getMessage());
            }
        });
    }
    
    // Metode visu logu aizvēršanai
    private static void closeAllWindows() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window.isVisible()) {
                window.dispose(); // Aizver katru redzamo logu
            }
        }
    }
    
    // Palīgmetode informācijas līnijas pievienošanai panelim
    private static void addInfoLine(JPanel panel, String label, String value) {
        JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        linePanel.setOpaque(false);
        linePanel.setMaximumSize(new Dimension(400, 30));
        
        // Etiķetes izveide ar treknu tekstu
        JLabel labelLbl = new JLabel(label);
        labelLbl.setFont(new Font("Arial", Font.BOLD, 14));
        labelLbl.setForeground(Color.WHITE);
        labelLbl.setPreferredSize(new Dimension(80, 20));
        
        // Vērtības izveide ar dzeltenu tekstu
        JLabel valueLbl = new JLabel(value);
        valueLbl.setFont(new Font("Arial", Font.PLAIN, 14));
        valueLbl.setForeground(Color.YELLOW);
        
        linePanel.add(labelLbl);
        linePanel.add(valueLbl);
        panel.add(linePanel);
        panel.add(Box.createVerticalStrut(5)); // Atstarpe starp līnijām
    }
    
    // Palīgmetode pokemona rindas pievienošanai sarakstam
    private static void addPokemonLine(JPanel panel, Pokemons pokemon) {
        JPanel pokemonPanel = new JPanel(new BorderLayout());
        pokemonPanel.setOpaque(false);
        pokemonPanel.setMaximumSize(new Dimension(400, 30));
        pokemonPanel.setBorder(BorderFactory.createEmptyBorder(2, 20, 2, 20));
        
        // Pokemona vārda attēlošana
        JLabel nameLabel = new JLabel("• " + pokemon.getVards());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);
        
        // Pokemona papildinformācijas attēlošana (tips un līmenis)
        JLabel infoLabel = new JLabel(pokemon.getTips() + " - Lv." + pokemon.getLimenis());
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setForeground(Color.CYAN);
        
        pokemonPanel.add(nameLabel, BorderLayout.WEST);
        pokemonPanel.add(infoLabel, BorderLayout.EAST);
        
        panel.add(pokemonPanel);
        panel.add(Box.createVerticalStrut(3)); // Neliela atstarpe starp pokemoniem
    }
    
    // Metode stilizētu pogu izveidei
    private static JButton createButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15); // Noapaļots taisnstūris
                g2.setColor(Color.WHITE);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15); // Balts apmale
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(120, 35));
        button.setContentAreaFilled(false); // Noņem noklusēto fona aizpildījumu
        button.setFocusPainted(false); // Noņem fokusēšanas apmali
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        
        // Peles notikumu apstrāde - izmaiņas kad kursors atrodas virs pogas
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.YELLOW); // Dzeltena krāsa kad kursors virs pogas
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setForeground(Color.WHITE); // Atgriežas baltā krāsā
            }
        });
        
        return button;
    }
}