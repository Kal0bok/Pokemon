package Pokemon;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Arena {

    private JFrame frame;
    private String selectedArena;
    private JPanel pauseOverlay; 
    private battle battleManager;
    private Pokemons izveletaisPokemons;

    public Arena() {
        createMainWindow();
    }

    private void createMainWindow() {
        frame = new JFrame("Pokemon Arena");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        JLabel background = createScaledGifBackground("/GIF/Arena1.gif", 600, 400);
        background.setLayout(new BorderLayout());

        JLabel title = new JLabel("POKEMON GAME", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.BLACK);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1, 15, 15));
        buttonsPanel.setOpaque(false);
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 180, 80, 180));

        JButton start = createButton("START", new Color(255, 140, 0));
        JButton exit = createButton("EXIT", new Color(150, 0, 0));

        start.setFont(new Font("Arial", Font.PLAIN, 22));
        exit.setFont(new Font("Arial", Font.PLAIN, 22));

        start.addActionListener(e -> showArenaSelect());
        exit.addActionListener(e -> Pokedatnis.main(new String[]{}));

        buttonsPanel.add(start);
        buttonsPanel.add(exit);

        background.add(title, BorderLayout.NORTH);
        background.add(buttonsPanel, BorderLayout.CENTER);

        frame.setContentPane(background);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void showArenaSelect() {
        frame.dispose();

        JFrame arenaFrame = new JFrame("Arenas izvēle");
        arenaFrame.setSize(800, 600);
        arenaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel background = createScaledGifBackground("/GIF/mainback.gif", 800, 600);
        background.setLayout(new BorderLayout());
        arenaFrame.setContentPane(background);

        JLabel title = new JLabel("Izvēlies arenu", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        JPanel arenasPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        arenasPanel.setOpaque(false);
        arenasPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 60, 60));

        String[] arenaNames = {"Volcano", "Temple", "Heaven", "Dungeon"};
        String[] arenaPaths = {
            "/GIF/background.gif",
            "/GIF/background11.gif", 
            "/GIF/background12.gif",
            "/GIF/background13.gif"
        };

        for (int i = 0; i < 4; i++) {
            final String arenaPath = arenaPaths[i];
            final String arenaName = arenaNames[i];
            JPanel arenaCard = createArenaCard(arenaName, arenaPath, arenaFrame);
            arenasPanel.add(arenaCard);
        }

        background.add(title, BorderLayout.NORTH);
        background.add(arenasPanel, BorderLayout.CENTER);

        arenaFrame.setLocationRelativeTo(null);
        arenaFrame.setVisible(true);
    }

    private JPanel createArenaCard(String arenaName, String arenaPath, JFrame arenaFrame) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setOpaque(false);

        JLabel nameLabel = new JLabel(arenaName, SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JLabel gifLabel = createGif(arenaPath);
        gifLabel.setPreferredSize(new Dimension(200, 150));
        gifLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        gifLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedArena = arenaPath;
                showPokemonSelect();
                arenaFrame.dispose();
            }
        });

        card.add(nameLabel, BorderLayout.NORTH);
        card.add(gifLabel, BorderLayout.CENTER);

        return card;
    }

    private void showPokemonSelect() {
        JFrame selectFrame = new JFrame("Pokemona izvēle");
        selectFrame.setSize(600, 400);
        selectFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel background = createScaledGifBackground("/GIF/back1.gif", 600, 400);
        background.setLayout(new BorderLayout());
        selectFrame.setContentPane(background);

        JLabel title = new JLabel("Izvēlies pokemonu:", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel gifs = new JPanel(new GridLayout(1, 3, 10, 0));
        gifs.setOpaque(false);
        gifs.setBorder(BorderFactory.createEmptyBorder(10, 40, 40, 40));

        JLabel gif1 = createGif("/GIF/machamp.gif");
        JLabel gif2 = createGif("/GIF/pikachu.gif");
        JLabel gif3 = createGif("/GIF/squirlte.gif");

        Dimension gifSize = new Dimension(130, 130);
        gif1.setPreferredSize(gifSize);
        gif2.setPreferredSize(gifSize);
        gif3.setPreferredSize(gifSize);

        gifs.add(gif1);
        gifs.add(gif2);
        gifs.add(gif3);

        background.add(title, BorderLayout.NORTH);
        background.add(gifs, BorderLayout.CENTER);

        selectFrame.setLocationRelativeTo(null);
        selectFrame.setVisible(true);

        MouseAdapter click = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel chosenGif = (JLabel) e.getSource();
                String iconText = chosenGif.getIcon().toString();
                
                if (iconText.contains("machamp")) {
                    izveletaisPokemons = new UdensP(); 
                    izveletaisPokemons = new Pokemons("Machamp", "Fighting", 5, 100, 10, 80) {
                        @Override
                        public String ipaUzbruk() {
                            return getVards() + " izmanto Četru roku triecienu!";
                        }
                    };
                } else if (iconText.contains("pikachu")) {
                    izveletaisPokemons = new ElektriskaisP();
                } else if (iconText.contains("squirlte")) {
                    izveletaisPokemons = new UdensP();
                }
                
                showChosenPokemon(selectFrame, chosenGif.getIcon());
            }
        };

        gif1.addMouseListener(click);
        gif2.addMouseListener(click);
        gif3.addMouseListener(click);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 5, 6, 5));
        return btn;
    }

    private JLabel createGif(String path) {
        JLabel label = new JLabel(new ImageIcon(getClass().getResource(path)));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

    private void showChosenPokemon(JFrame selectFrame, Icon icon) {
        selectFrame.getContentPane().removeAll();
        selectFrame.setLayout(new BorderLayout());

        JLabel background = createScaledGifBackground("/GIF/back.gif", 600, 400);
        background.setLayout(new BorderLayout());
        selectFrame.setContentPane(background);

        JLabel title = new JLabel("Laba izvēle!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel stageGif = createGif("/GIF/choosen.gif");
        stageGif.setHorizontalAlignment(SwingConstants.CENTER);

        background.add(title, BorderLayout.NORTH);
        background.add(stageGif, BorderLayout.CENTER);

        selectFrame.revalidate();
        selectFrame.repaint();

        Timer timer = new Timer(2300, e -> {
            selectFrame.getContentPane().removeAll();
            selectFrame.setLayout(new BorderLayout());

            JLabel bg2 = createScaledGifBackground("/GIF/back.gif", 600, 400);
            bg2.setLayout(new BorderLayout());
            selectFrame.setContentPane(bg2);

            JLabel chosen = new JLabel(icon);
            chosen.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel title2 = new JLabel("Laba izvēle!", SwingConstants.CENTER);
            title2.setFont(new Font("Arial", Font.BOLD, 30));
            title2.setForeground(Color.WHITE);  
            title2.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

            JButton cont = new JButton("Start");
            cont.setFont(new Font("Arial", Font.BOLD, 22));
            cont.setBackground(new Color(0, 170, 0));
            cont.setOpaque(true);
            cont.setForeground(Color.WHITE);
            cont.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 3));
            cont.addActionListener(ev -> showEmptyWindow(selectFrame));

            JPanel bottom = new JPanel();
            bottom.setOpaque(false);
            bottom.add(cont);

            bg2.add(title2, BorderLayout.NORTH);
            bg2.add(chosen, BorderLayout.CENTER);
            bg2.add(bottom, BorderLayout.SOUTH);

            selectFrame.revalidate();
            selectFrame.repaint();
        });

        timer.setRepeats(false);
        timer.start();
    }

    private void showEmptyWindow(JFrame oldFrame) {
        oldFrame.dispose();

        JFrame empty = new JFrame("Arena");
        empty.setSize(1000, 600);
        empty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        empty.setResizable(false);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1000, 600));
        
        Pokemons ienaidniekaPokemons = new ElektriskaisP();
        Trainer speletajaTreneris = MainMenu.aktivTrener;
        
        battleManager = new battle(izveletaisPokemons, ienaidniekaPokemons, speletajaTreneris);
        
        JLabel bgLabel = createScaledGifBackground(selectedArena, 1000, 600);
        bgLabel.setBounds(0, 0, 1000, 600);
        layeredPane.add(bgLabel, Integer.valueOf(0));
        
        JLabel aizsardzibasPoga = izveidotDarbibasPogu("/Image/defense.png", 50, 470, "aizsardziba");
        JLabel uzbrukumaPoga = izveidotDarbibasPogu("/Image/attack.png", 450, 470, "uzbrukums");
        JLabel superPoga = izveidotDarbibasPogu("/Image/power.png", 850, 470, "super");
        
        layeredPane.add(aizsardzibasPoga, Integer.valueOf(1));
        layeredPane.add(uzbrukumaPoga, Integer.valueOf(1));
        layeredPane.add(superPoga, Integer.valueOf(1));
        
        JPanel kreisieStatistika = izveidotStatistikasPanel(
            "HP: " + (int)izveletaisPokemons.getDziv() + "/100", 
            "Bruņas: " + (int)izveletaisPokemons.getAizsarg()
        );
        kreisieStatistika.setBounds(100, 10, 300, 80);
        
        JPanel labieStatistika = izveidotStatistikasPanel(
            "HP: " + (int)ienaidniekaPokemons.getDziv() + "/100", 
            "Bruņas: " + (int)ienaidniekaPokemons.getAizsarg()
        );
        labieStatistika.setBounds(600, 10, 300, 80);
        
        JLabel kreisaisPokemons;
        if (izveletaisPokemons.getVards().equals("Squirtle")) {
            kreisaisPokemons = createGif("/GIF/squirlte.gif");
        } else if (izveletaisPokemons.getVards().equals("Pikachu")) {
            kreisaisPokemons = createGif("/GIF/pikachu.gif");
        } else {
            kreisaisPokemons = createGif("/GIF/machamp.gif");
        }
        kreisaisPokemons.setBounds(100, 200, 300, 300);
        
        JLabel labaisPokemons = createGif("/GIF/pikachu.gif");
        labaisPokemons.setBounds(600, 200, 300, 300);
        
        JLabel vsGif = createGif("/GIF/vs.gif"); 
        vsGif.setBounds(450, 250, 150, 150); 
        
        JLabel pauzesEtiķete = createPauseLabel();
        pauzesEtiķete.setBounds(485, 25, 30, 30);
        
        pauzesEtiķete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showPauseOverlay(layeredPane);
            }
        });
        
        layeredPane.add(kreisieStatistika, Integer.valueOf(1));
        layeredPane.add(labieStatistika, Integer.valueOf(1));
        layeredPane.add(kreisaisPokemons, Integer.valueOf(1));
        layeredPane.add(labaisPokemons, Integer.valueOf(1));
        layeredPane.add(vsGif, Integer.valueOf(2));
        layeredPane.add(pauzesEtiķete, Integer.valueOf(3));
        
        empty.setContentPane(layeredPane);
        empty.setLocationRelativeTo(null);
        empty.setVisible(true);
        
        Timer taimeris = new Timer(2000, e -> {
            layeredPane.remove(vsGif);
            layeredPane.revalidate();
            layeredPane.repaint();
        });
        taimeris.setRepeats(false);
        taimeris.start();
    }

    private JLabel izveidotDarbibasPogu(String attelaCels, int x, int y, String darbibasTips) {
        JLabel poga = new JLabel();
        poga.setBounds(x, y, 100, 80);
        
        ImageIcon ikona = new ImageIcon(getClass().getResource(attelaCels));
        if (ikona.getImage() != null) {
            Image skaletsAttels = ikona.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH);
            poga.setIcon(new ImageIcon(skaletsAttels));
        } else {
            poga.setText(darbibasTips.toUpperCase());
            poga.setFont(new Font("Arial", Font.BOLD, 14));
            poga.setForeground(Color.WHITE);
            poga.setHorizontalAlignment(SwingConstants.CENTER);
            poga.setOpaque(true);
            poga.setBackground(new Color(100, 100, 100, 200));
        }
        
        poga.setHorizontalAlignment(SwingConstants.CENTER);
        poga.setVerticalAlignment(SwingConstants.CENTER);
        poga.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        poga.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                apstradatKaujasDarbibu(darbibasTips, poga);
            }
        });
        
        return poga;
    }

    private void apstradatKaujasDarbibu(String darbibasTips, JLabel poga) {
        if (battleManager == null) return;
        
        String rezultats = "";
        
        switch (darbibasTips) {
            case "aizsardziba":
                rezultats = battleManager.izmantotAizsardzibu();
                break;
            case "uzbrukums":
                rezultats = battleManager.izmantotParastoUzbrukumu();
                break;
            case "super":
                rezultats = battleManager.izmantotSuperUzbrukumu();
                break;
        }
        
        // Tūlīt pēc spēlētāja gājiena - ienaidnieka gājiens
        if (!rezultats.contains("nav gatavs") && battleManager.getEnemyPokemon().getDziv() > 0) {
            String ienaidniekaRezultats = battleManager.veiktIenaidniekaGajienu();
            if (!ienaidniekaRezultats.isEmpty()) {
                rezultats += "\n\n" + ienaidniekaRezultats;
            }
        }
        
        atjauninatHpAttelosanu();
        
        if (battleManager.getPlayerPokemon().getDziv() <= 0) {
            paradiKaujasZinu("Jūsu pokemons ir sakauts! Spēle beigusies.");
        } else if (battleManager.getEnemyPokemon().getDziv() <= 0) {
            paradiKaujasZinu("Ienaidnieks ir sakauts! Jūs uzvarējāt!");
        } else {
            paradiKaujasZinu(rezultats);
        }
    }

    private void paradiKaujasZinu(String zina) {
        JOptionPane.showMessageDialog(null, zina, "Kauja", JOptionPane.INFORMATION_MESSAGE);
    }

    private void atjauninatHpAttelosanu() {
        if (battleManager != null) {
            System.out.println("Spēlētāja HP: " + battleManager.getPlayerPokemon().getDziv());
            System.out.println("Ienaidnieka HP: " + battleManager.getEnemyPokemon().getDziv());
        }
    }

    private void showPauseOverlay(JLayeredPane layeredPane) {
        pauseOverlay = new JPanel();
        pauseOverlay.setBounds(0, 0, 1000, 600);
        pauseOverlay.setLayout(new GridBagLayout());
        
        JLabel pauseBackground = createScaledGifBackground("/GIF/backi.gif", 1000, 600); 
        pauseBackground.setBounds(0, 0, 1000, 600);
        
        JLayeredPane pauseLayeredPane = new JLayeredPane();
        pauseLayeredPane.setPreferredSize(new Dimension(1000, 600));
        pauseLayeredPane.setBounds(0, 0, 1000, 600);
        
        pauseLayeredPane.add(pauseBackground, Integer.valueOf(0));
        
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(200, 150));
        
        JButton resumeButton = createPauseButton("Turpināt", new Color(0, 150, 0));
        JButton exitButton = createPauseButton("Iziet", new Color(150, 0, 0));
        
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(layeredPane);
        
        resumeButton.addActionListener(e -> {
            layeredPane.remove(pauseOverlay);
            layeredPane.revalidate();
            layeredPane.repaint();
        });
        
        exitButton.addActionListener(e -> {
            if (currentFrame != null) {
                currentFrame.dispose();
            }
            System.exit(0);
        });
        
        buttonPanel.add(resumeButton);
        buttonPanel.add(exitButton);
        
        buttonPanel.setBounds(400, 225, 200, 150);
        pauseLayeredPane.add(buttonPanel, Integer.valueOf(1));
        
        pauseOverlay.add(pauseLayeredPane);
        
        layeredPane.add(pauseOverlay, Integer.valueOf(10));
        layeredPane.revalidate();
        layeredPane.repaint();
    }
    
    private JButton createPauseButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JLabel createPauseLabel() {
        JLabel pauseLabel = new JLabel();
        pauseLabel.setPreferredSize(new Dimension(100, 100));
        
        ImageIcon pauseIcon = new ImageIcon(getClass().getResource("/Image/pause.png"));
        if (pauseIcon.getImage() != null) {
            Image scaledPause = pauseIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            pauseLabel.setIcon(new ImageIcon(scaledPause));
        } else {
            pauseLabel.setText("II");
            pauseLabel.setFont(new Font("Arial", Font.BOLD, 20));
            pauseLabel.setForeground(Color.BLACK); 
        }
        
        pauseLabel.setOpaque(true);
        pauseLabel.setBackground(Color.WHITE);
        
        pauseLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        pauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pauseLabel.setVerticalAlignment(SwingConstants.CENTER);
        pauseLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        return pauseLabel;
    }

    private JPanel izveidotStatistikasPanel(String hpText, String armorText) {
        JPanel statsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        statsPanel.setOpaque(false);
        statsPanel.setPreferredSize(new Dimension(300, 80));
        
        JLabel hpLabel = izveidotStatistikasEtiķeti(hpText, Color.RED);
        JLabel armorLabel = izveidotStatistikasEtiķeti(armorText, Color.BLUE);
        
        statsPanel.add(hpLabel);
        statsPanel.add(armorLabel);
        
        return statsPanel;
    }

    private JLabel izveidotStatistikasEtiķeti(String text, Color color) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0, 180));
        label.setBorder(BorderFactory.createLineBorder(color, 3));
        label.setPreferredSize(new Dimension(300, 35));
        return label;
    }

    private JLabel createScaledGifBackground(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaled);
        return new JLabel(scaledIcon);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Arena::new);
    }
}