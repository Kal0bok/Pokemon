package Pokemon;

import java.awt.*;
import javax.swing.*;

public abstract class Pokemons implements Comparable<Pokemons> {
    private String vards;
    private String tips;
    private int limenis;
    private double dziv;
    private double uzbruk;
    private double aizsarg;

    public Pokemons(String vards, String tips, int limenis, double dziv, double uzbruk, double aizsarg) {
        this.vards = vards;
        this.tips = tips;
        this.limenis = limenis;
        this.dziv = dziv;
        this.uzbruk = uzbruk;
        this.aizsarg = aizsarg;
    }

    public String getVards() {
    	return vards; }
    public String getTips() {
    	return tips; 
    	}
    
    public int getLimenis() {
    	return limenis;
    	}
    
    public double getDziv() { 
    	return dziv; 
    	}
    
    public double getUzbruk() {
    	return uzbruk; 
    	}
    
    public double getAizsarg() {
    	return aizsarg; 
    	}

    public void setDziv(double dziv) {
    	this.dziv = dziv; 
    	}
    
    public void setUzbruk(double uzbruk) { 
    	this.uzbruk = uzbruk; 
    	}
    
    public void setAizsarg(double aizsarg) {
    	this.aizsarg = aizsarg; 
    	}

    public abstract String ipaUzbruk();
    public abstract Color getCardColor();

    public void saņBojās(double daudzums) {
        dziv -= daudzums;
        if (dziv < 0) dziv = 0;
    }

    public double dotBoja() {
        return uzbruk * (limenis / 2.0);
    }

    public double aizsarg() {
        return aizsarg * 0.7;
    }

    public void dziedet() {
        dziv += 20;
        if (dziv > 200) dziv = 200;
    }

    public void attīstīt() {
        limenis++;
        uzbruk += 0.5;
        aizsarg += 0.3;
        dziv += 10;
    }

    public String izvadit() {
        return String.format("""
            Vārds: %s
            Tips: %s
            Līmenis: %d
            Dzīvības: %.1f
            Uzbrukums: %.1f
            Aizsardzība: %.1f
            Spēks: %.1f""", 
            vards, tips, limenis, dziv, uzbruk, aizsarg, aprēķinātSpēku());
    }

    public double aprēķinātSpēku() {
        return (dziv + uzbruk * 10 + aizsarg * 5) * limenis;
    }

    public void showProfile() {
        JDialog profile = new JDialog((JFrame)null, "Pokémon Profile - " + vards, true);
        profile.setSize(400, 500);
        profile.setLocationRelativeTo(null);
        profile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(getCardColor().brighter());
        
        JLabel title = new JLabel(vards, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.DARK_GRAY);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        
        JPanel statsPanel = new JPanel(new GridLayout(0, 1, 8, 8));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        statsPanel.add(createStatLabel("Tips: " + tips));
        statsPanel.add(createStatLabel("Līmenis: " + limenis));
        statsPanel.add(createStatLabel("Dzīvības: " + String.format("%.1f", dziv)));
        statsPanel.add(createStatLabel("Uzbrukums: " + String.format("%.1f", uzbruk)));
        statsPanel.add(createStatLabel("Aizsardzība: " + String.format("%.1f", aizsarg)));
        statsPanel.add(createStatLabel("Kopējais spēks: " + String.format("%.1f", aprēķinātSpēku())));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(getCardColor().brighter());
        
        JButton attackBtn = new JButton("Uzbrukt");
        JButton healBtn = new JButton("Dziedēt");
        JButton evolveBtn = new JButton("Attīstīt");
        JButton closeBtn = new JButton("Aizvērt");
        
        attackBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(profile, ipaUzbruk());
        });
        
        healBtn.addActionListener(e -> {
            double oldHp = dziv;
            dziedet();
            JOptionPane.showMessageDialog(profile, 
                String.format("%s dziedēts! Dzīvības: %.1f → %.1f", vards, oldHp, dziv));
            profile.dispose();
            showProfile();
        });
        
        evolveBtn.addActionListener(e -> {
            int oldLevel = limenis;
            attīstīt();
            JOptionPane.showMessageDialog(profile, 
                String.format("%s attīstījās! Līmenis: %d → %d", vards, oldLevel, limenis));
            profile.dispose();
            showProfile();
        });
        
        closeBtn.addActionListener(e -> profile.dispose());
        
        buttonPanel.add(attackBtn);
        buttonPanel.add(healBtn);
        buttonPanel.add(evolveBtn);
        buttonPanel.add(closeBtn);
        
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(statsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        profile.add(mainPanel);
        profile.setVisible(true);
    }
    
    private JLabel createStatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        return label;
    }
    
    @Override
    public int compareTo(Pokemons cits) {
        return Double.compare(this.aprēķinātSpēku(), cits.aprēķinātSpēku());
    }
    
    @Override
    public String toString() {
        return vards + " (" + tips + ") Lv." + limenis;
    }
}