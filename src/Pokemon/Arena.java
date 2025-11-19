package Pokemon;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

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

        start.addActionListener(e -> showPokemonSelect());
        exit.addActionListener(e -> frame.dispose());

        buttonsPanel.add(start);
        buttonsPanel.add(exit);

        background.add(title, BorderLayout.NORTH);
        background.add(buttonsPanel, BorderLayout.CENTER);

        frame.setContentPane(background);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 5, 6, 5));
        return btn;
    }

    private void showPokemonSelect() {
        frame.dispose();

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
                showChosenPokemon(selectFrame, chosenGif.getIcon());
            }
        };

        gif1.addMouseListener(click);
        gif2.addMouseListener(click);
        gif3.addMouseListener(click);
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

        JLabel title = new JLabel("Laba izvēle!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel stageGif = createGif("/GIF/choosen.gif");
        stageGif.setHorizontalAlignment(SwingConstants.CENTER);

        selectFrame.add(title, BorderLayout.NORTH);
        selectFrame.add(stageGif, BorderLayout.CENTER);

        selectFrame.revalidate();
        selectFrame.repaint();

        Timer timer = new Timer(2300, e -> {
            selectFrame.getContentPane().removeAll();
            selectFrame.setLayout(new BorderLayout());

            JLabel chosen = new JLabel(icon);
            chosen.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel title2 = new JLabel("Laba izvēle!", SwingConstants.CENTER);
            title2.setFont(new Font("Arial", Font.BOLD, 30));
            title2.setForeground(Color.WHITE);  
            title2.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

            JButton cont = new JButton("Turpināt");
            cont.setFont(new Font("Arial", Font.BOLD, 22));
            cont.setBackground(new Color(0, 170, 0));
            cont.setOpaque(true);
            cont.setForeground(Color.WHITE);
            cont.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 3));
            cont.addActionListener(ev -> showEmptyWindow(selectFrame));

            JPanel bottom = new JPanel();
            bottom.setOpaque(false);
            bottom.add(cont);

            selectFrame.add(title2, BorderLayout.NORTH);
            selectFrame.add(chosen, BorderLayout.CENTER);
            selectFrame.add(bottom, BorderLayout.SOUTH);

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
        
        JLabel bgLabel = createScaledGifBackground("/GIF/Arena1.gif", 1000, 600);
        bgLabel.setBounds(0, 0, 1000, 600);
        layeredPane.add(bgLabel, Integer.valueOf(0));
        
        JPanel topStatsPanel = new JPanel(new GridLayout(1, 2));
        topStatsPanel.setOpaque(false);
        topStatsPanel.setBounds(0, 10, 1000, 80);
        
        JPanel leftStats = createStatsPanel("HP: 100/100", "Armor: 50");
        leftStats.setBounds(100, 10, 300, 80);
        
        JPanel rightStats = createStatsPanel("HP: 80/100", "Armor: 30");
        rightStats.setBounds(600, 10, 300, 80);
        
        JLabel leftPokemon = createGif("/GIF/machamp.gif");
        leftPokemon.setBounds(100, 200, 300, 300);
        
        JLabel rightPokemon = createGif("/GIF/pikachu.gif");
        rightPokemon.setBounds(600, 200, 300, 300);
        
        layeredPane.add(leftStats, Integer.valueOf(1));
        layeredPane.add(rightStats, Integer.valueOf(1));
        layeredPane.add(leftPokemon, Integer.valueOf(1));
        layeredPane.add(rightPokemon, Integer.valueOf(1));
        
        empty.setContentPane(layeredPane);
        empty.setLocationRelativeTo(null);
        empty.setVisible(true);
    }

    private JPanel createStatsPanel(String hpText, String armorText) {
        JPanel statsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        statsPanel.setOpaque(false);
        statsPanel.setPreferredSize(new Dimension(300, 80));
        
        JLabel hpLabel = createStatLabel(hpText, Color.RED);
        JLabel armorLabel = createStatLabel(armorText, Color.BLUE);
        
        statsPanel.add(hpLabel);
        statsPanel.add(armorLabel);
        
        return statsPanel;
    }

    private JLabel createStatLabel(String text, Color color) {
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