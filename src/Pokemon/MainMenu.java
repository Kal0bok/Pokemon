package Pokemon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu {
	
	private JFrame frame;
	
    public MainMenu() {
    	
    	frame = new JFrame("Game");
    	frame.setLocationRelativeTo(null);
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

        startBtn.addActionListener(e -> trainer());

        exitBtn.addActionListener(e -> {JOptionPane.showMessageDialog(
        		null, "Programma apturēta!"); System.exit(0); });

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

    private void trainer() {
        JFrame mainFrame = getMainFrame(); 
        if (mainFrame != null) {
            mainFrame.setVisible(false); 
        }
        
        JFrame frame = new JFrame("Trainer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        	

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 2));
        frame.setLocationRelativeTo(null);

        mainPanel.setBackground(new Color(20, 30, 60));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        frame.setSize(900, 500);
        JPanel card1 = createGameCard(
                "Ash",
                getClass().getResource("/GIF/ashg.gif"),
                "Pikachu treneris. Galvenais varonis!"
        );

        JPanel card2 = createGameCard(
                "67",
                getClass().getResource("/GIF/leon.gif"),
                "676767"
        );

        card1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose(); 

                showAshInfo(); 
            }
        });

        card2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	frame.dispose();
                JOptionPane.showMessageDialog(frame, "Tu izvēlējies izveidot paši!");   
                Trainer treneris = Pokedatnis.createTrainer(null);
                if(treneris == null) {
                	SwingUtilities.invokeLater(() -> trainer()); 
                    return;
                }
                SwingUtilities.invokeLater(()-> Pokedatnis.main(new String[]{}));
                
            }
        });

        mainPanel.add(card1);
        mainPanel.add(card2);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
   
    private void showAshInfo() {
        JFrame info = new JFrame("Profile");
        info.setSize(400, 500);
        info.setLocationRelativeTo(null);
        info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        info.setLayout(new BorderLayout());
        info.getContentPane().setBackground(new Color(20, 30, 60));

        JLabel photo = new JLabel();
        photo.setHorizontalAlignment(SwingConstants.CENTER);
        photo.setIcon(new ImageIcon(
                new ImageIcon(getClass().getResource("/Image/ash.png"))
                        .getImage()
                        .getScaledInstance(150, 150, Image.SCALE_SMOOTH)
        ));

        info.add(photo, BorderLayout.NORTH);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(20, 30, 60));
        textPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel name = new JLabel("Vārds: Ash Kečums");
        JLabel level = new JLabel("Trenera līmenis: 10");
        JLabel age = new JLabel("Vecums: 12");

        JLabel pokemonsTitle = new JLabel("Pokemoni:");
        JLabel p1 = new JLabel("• Pikachu (elektriskais tips) — 5. līmenis");
        JLabel p2 = new JLabel("• Vaporeon (ūdens tips) — 5. līmenis");

        for (JLabel lbl : new JLabel[]{name, level, age, pokemonsTitle, p1, p2}) {
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.PLAIN, 18));
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            textPanel.add(lbl);
            textPanel.add(Box.createVerticalStrut(10));
        }

        info.add(textPanel, BorderLayout.CENTER);

        JButton continueBtn = new JButton("Turpināt");
        continueBtn.setFont(new Font("Arial", Font.BOLD, 18));
        continueBtn.setBackground(Color.GREEN);
        continueBtn.setForeground(Color.WHITE);
        continueBtn.addActionListener(e -> {
            info.dispose();
            SwingUtilities.invokeLater(() -> Pokedatnis.main(new String[]{}));
        });

        JPanel bottom = new JPanel();
        bottom.setBackground(new Color(20, 30, 60));
        bottom.add(continueBtn);

        info.add(bottom, BorderLayout.SOUTH);

        info.setVisible(true);
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
    private JFrame getMainFrame() {
        return frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
