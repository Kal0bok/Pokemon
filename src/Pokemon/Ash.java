package Pokemon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Ash {

	public static  void showAshInfo() {
        JFrame info = new JFrame("Profile");
        info.setSize(400, 500);
        info.setLocationRelativeTo(null);
        info.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        info.setLayout(new BorderLayout());
        info.getContentPane().setBackground(new Color(20, 30, 60));

        JLabel photo = new JLabel();
        photo.setHorizontalAlignment(SwingConstants.CENTER);
        photo.setIcon(new ImageIcon(
                new ImageIcon(Ash.class.getResource("/Image/ash.png"))
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

        JButton continueBtn = new JButton("Atpakaļ");
        continueBtn.setFont(new Font("Arial", Font.BOLD, 18));
        continueBtn.setBackground(Color.RED);
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
	
}
