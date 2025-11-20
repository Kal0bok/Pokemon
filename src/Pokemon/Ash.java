package Pokemon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Ash {

    public static void showAshInfo() {
        JDialog info = new JDialog((JDialog)null, "Trenera Profils", true);
        info.setSize(450, 500);
        info.setLocationRelativeTo(null);
        info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        info.setLayout(new BorderLayout());
        info.getContentPane().setBackground(new Color(20, 30, 60));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(180, 0, 0)); // Красный для Ash
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel title = new JLabel("ASH KETCHUM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.YELLOW);
        
        JLabel icon = new JLabel("🏆", SwingConstants.CENTER);
        icon.setFont(new Font("Arial", Font.PLAIN, 48));
        icon.setForeground(Color.WHITE);
        
        headerPanel.add(title, BorderLayout.NORTH);
        headerPanel.add(icon, BorderLayout.CENTER);
        
        info.add(headerPanel, BorderLayout.NORTH);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(new Color(20, 30, 60));
        textPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel name = new JLabel("Vārds: Ash Ketchum");
        JLabel level = new JLabel("Trenera līmenis: 10");
        JLabel age = new JLabel("Vecums: 12");
        JLabel region = new JLabel("Reģions: Kanto");

        JLabel pokemonsTitle = new JLabel("Pokemoni:");
        pokemonsTitle.setFont(new Font("Arial", Font.BOLD, 16));
        
        StringBuilder pokemonList = new StringBuilder();
        for (Pokemons p : Pokedatnis.pokemoni) {
            pokemonList.append("• ").append(p.getVards())
                      .append(" (").append(p.getTips())
                      .append(") — ").append(p.getLimenis())
                      .append(". līmenis\n");
        }

        JLabel pokemonLabel = new JLabel("<html>" + pokemonList.toString().replace("\n", "<br>") + "</html>");

        for (JLabel lbl : new JLabel[]{name, level, age, region, pokemonsTitle}) {
            lbl.setForeground(Color.WHITE);
            lbl.setFont(new Font("Arial", Font.PLAIN, 16));
            lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
            textPanel.add(lbl);
            textPanel.add(Box.createVerticalStrut(8));
        }
        
        pokemonLabel.setForeground(Color.YELLOW);
        pokemonLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        pokemonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.add(pokemonLabel);

        info.add(textPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBackground(new Color(20, 30, 60));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton backBtn = new JButton("Atpakaļ");
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.setBackground(Color.RED);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(e -> info.dispose());

        JButton continueBtn = new JButton("Turpināt");
        continueBtn.setFont(new Font("Arial", Font.BOLD, 14));
        continueBtn.setBackground(Color.GREEN);
        continueBtn.setForeground(Color.WHITE);
        continueBtn.addActionListener(e -> {
            info.dispose();
            SwingUtilities.invokeLater(() -> Pokedatnis.main(new String[]{}));
        });

        buttonPanel.add(backBtn);
        buttonPanel.add(continueBtn);

        info.add(buttonPanel, BorderLayout.SOUTH);
        info.setVisible(true);
    }
}