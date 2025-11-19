package Pokemon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

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

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);
        buttonsPanel.setLayout(new GridLayout(2, 1, 15, 15));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(40, 180, 80, 180));

        JButton start = createButton("START", new Color(255, 140, 0));
        JButton exit = createButton("EXIT", new Color(150, 0, 0));

        start.setPreferredSize(new Dimension(120, 35));
        exit.setPreferredSize(new Dimension(120, 35));

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

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/GIF/back1.gif")));
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

        JLabel stageGif = new JLabel(new ImageIcon(getClass().getResource("/GIF/choosen.gif")));
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
            cont.setContentAreaFilled(true);
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
        empty.setSize(800, 500);
        empty.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        empty.setLocationRelativeTo(null);
        empty.setVisible(true);
    }
}