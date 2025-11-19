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

            selectFrame.add(title, BorderLayout.NORTH);
            selectFrame.add(chosen, BorderLayout.CENTER);
            selectFrame.revalidate();
            selectFrame.repaint();
        });

        timer.setRepeats(false);
        timer.start();
        
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

        selectFrame.add(bottom, BorderLayout.SOUTH);
    }
    
    
    
}